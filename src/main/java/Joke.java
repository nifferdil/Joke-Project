import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;

public class Joke {
  private int id;
  private String question;
  private String answer;
  private int hilarity;

  public int getId() {
    return id;
  }

  public String getQuestion() {
    return question;
  }

  public String getAnswer() {
    return answer;
  }

  public int getHilarity() {
    return hilarity;
  }

  public Joke(String question, String answer, int hilarity) {
    this.question = question;
    this.answer = answer;
    this.hilarity = hilarity;
  }


  @Override
  public boolean equals(Object otherJoke){
    if (!(otherJoke instanceof Joke)) {
      return false;
    } else {
      Joke newJoke = (Joke) otherJoke;
      return this.getQuestion().equals(newJoke.getQuestion());
    }
  }


  public static List<Joke> all() {
    String sql = "SELECT * FROM jokes";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Joke.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO jokes (question, answer, hilarity) VALUES (:question, :answer, :hilarity)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("question", this.question)
      .addParameter("answer", this.answer)
      .addParameter("hilarity", this.hilarity)
      .executeUpdate()
      .getKey();
    }
  }

  public static Joke find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM jokes WHERE id = :id";
      Joke Joke = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Joke.class);
      return Joke;
    }
  }


  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM jokes WHERE id = :id";
      con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();

      String joinDeleteQuery = "DELETE FROM jokes_categories WHERE joke_id = :joke_id";
      con.createQuery(joinDeleteQuery)
      .addParameter("joke_id", this.getId())
      .executeUpdate();
    }
  }

  public void hilarityUp() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE jokes SET hilarity = hilarity + 1 WHERE id = :id";
      con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();
    }
  }


    public static List<Joke> search(String searchName) {
      String lowerCaseSearch = searchName.toLowerCase();
      String sql = "SELECT * FROM students WHERE LOWER (students.name) LIKE '%" + lowerCaseSearch + "%'";
      List<Joke> studentResults;
      try (Connection con = DB.sql2o.open()) {
        studentResults = con.createQuery(sql)
          .executeAndFetch(Joke.class);
      }
      return studentResults;
    }

  public void addCategory(Category category) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO jokes_categories (joke_id, category_id) VALUES (:joke_id, :category_id)";
      con.createQuery(sql)
      .addParameter("joke_id", this.getId())
      .addParameter("category_id", category.getId())
      .executeUpdate();
    }
  }

  public List<Category> getCategories() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT categories.* FROM jokes JOIN jokes_categories ON (jokes_categories.joke_id = jokes.id) JOIN categories ON (jokes_categories.category_id = categories.id) WHERE joke_id=:id";
      return con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetch(Category.class);
    }
  }

  public void removeCategory(int category_id) {
    try (Connection con = DB.sql2o.open()) {
      String removeCategoryQuery = "DELETE FROM jokes_categories WHERE category_id = :category_id AND joke_id = :id";
      con.createQuery(removeCategoryQuery)
      .addParameter("category_id", category_id)
      .addParameter("id", this.getId())
      .executeUpdate();
    }
  }
}
