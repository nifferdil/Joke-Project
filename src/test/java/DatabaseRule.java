import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

  protected void before() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/knee_slappers_test", null, null);
   }

   protected void after() {
     try(Connection con = DB.sql2o.open()) {
       String deleteQuery = "DELETE FROM jokes *;";
       String deleteCategoriesQuery = "DELETE FROM categories *;";
       String deleteJokesCategoriesQuery = "DELETE FROM jokes_categories *";
       con.createQuery(deleteQuery).executeUpdate();
       con.createQuery(deleteCategoriesQuery).executeUpdate();
       con.createQuery(deleteJokesCategoriesQuery).executeUpdate();
     }
   }
}
