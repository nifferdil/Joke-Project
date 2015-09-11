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

  public Joke(String question, String answer) {
    this.question = question;
    this.answer = answer;
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
    String sql = "SELECT * FROM jokes ORDER BY hilarity DESC";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Joke.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO jokes (question, answer) VALUES (:question, :answer)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("question", this.question)
      .addParameter("answer", this.answer)
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

  public void hilarityUp() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE jokes SET hilarity = hilarity + 1 WHERE id = :id";
      con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

  public void hilarityDown() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE jokes SET hilarity = hilarity - 1 WHERE id = :id";
      con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

  public static List<Joke> search(String searchJoke) {
    String lowerCaseSearch = searchJoke.toLowerCase();
    String sql = "SELECT * FROM jokes WHERE LOWER (jokes.question) LIKE '%" + lowerCaseSearch + "%'";
    List<Joke> jokeResults;
    try (Connection con = DB.sql2o.open()) {
      jokeResults = con.createQuery(sql)
        .executeAndFetch(Joke.class);
    }
      return jokeResults;
  }

}
