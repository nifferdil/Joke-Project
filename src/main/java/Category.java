import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;

public class Category {
  private int id;
  private String type;

  public int getId() {
    return id;
  }

  public String getType() {
    return type;
  }

  public Category(String type) {
    this.type = type;
  }

  @Override
  public boolean equals(Object otherCategory) {
    if (!(otherCategory instanceof Category)) {
      return false;
    } else {
      Category newCategory = (Category) otherCategory;
      return this.getType().equals(newCategory.getType());
    }
  }

  public static List<Category> all() {
    String sql = "SELECT DISTINCT * FROM categories ORDER BY type ASC";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Category.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO categories(type) VALUES (:type)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("type", this.type)
      .executeUpdate()
      .getKey();
    }
  }

  public static Category find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM categories WHERE id=:id";
      Category category = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Category.class);
      return category;
    }
  }

  public void addJoke(Joke joke) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO jokes_categories (joke_id, category_id) VALUES (:joke_id, :category_id)";
      con.createQuery(sql)
      .addParameter("joke_id", joke.getId())
      .addParameter("category_id", this.getId())
      .executeUpdate();
    }
  }

  public ArrayList<Joke> getJokes() {
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT DISTINCT joke_id FROM jokes_categories WHERE category_id = :category_id";
      List<Integer> jokeIds = con.createQuery(sql)
      .addParameter("category_id", this.getId())
      .executeAndFetch(Integer.class);

      ArrayList<Joke> jokes = new ArrayList<Joke>();

      for (Integer jokeId : jokeIds) {
        String jokeQuery = "Select * From jokes WHERE id = :jokeId";
        Joke joke = con.createQuery(jokeQuery)
        .addParameter("jokeId", jokeId)
        .executeAndFetchFirst(Joke.class);
        jokes.add(joke);
      }
      return jokes;
    }
  }

  public static List<Category> search(String searchCategory) {
    String lowerCaseSearch = searchCategory.toLowerCase();
    String sql = "SELECT * FROM categories WHERE LOWER (categories.type) LIKE '%" + lowerCaseSearch + "%'";
    List<Category> categoryResults;
    try (Connection con = DB.sql2o.open()) {
      categoryResults = con.createQuery(sql)
        .executeAndFetch(Category.class);
    }
    return categoryResults;
  }

}
