import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;

public class CategoryTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void category_instantiates_True() {
    Category testCategory = new Category("Pun");
    assertTrue(testCategory instanceof Category);
  }

  @Test
  public void equals_returnsTrueIfCategoriesAretheSame_true() {
    Category firstCategory = new Category("Pun");
    Category secondCategory = new Category("Pun");
    assertTrue(firstCategory.equals(secondCategory));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Category myCategory = new Category("Pun");
    myCategory.save();
    assertTrue(Category.all().get(0).equals(myCategory));
  }

  @Test
  public void find_findCategoryInDatabase_true() {
    Category myCategory = new Category("Pun");
    myCategory.save();
    Category savedCategory = Category.find(myCategory.getId());
    assertTrue(myCategory.equals(savedCategory));
  }

  @Test
  public void addJoke_addsJokeToCategory() {
    Category myCategory = new Category("Riddle", "Holiday");
    myCategory.save();
    Joke myJoke = new Joke("Knock Knock", "Orange you glad", 1);
    myJoke.save();
    myCategory.addJoke(myJoke);
    Joke savedJoke = myCategory.getJokes().get(0);
    assertTrue(myJoke.equals(savedJoke));
  }

  @Test
  public void getJokes_returnsAllJokes_ArrayList() {
    Category myCategory = new Category("Riddle", "Holiday");
    myCategory.save();
    Joke myJoke = new Joke("Knock Knock", "Orange you glad", 1);
    myJoke.save();
    myCategory.addJoke(myJoke);
    List savedJokes = myCategory.getJokes();
    assertEquals(savedJokes.size(), 1);
  }

  @Test
  public void delete_deletesAllJokesAndListAssoications() {
    Category myCategory = new Category("Riddle", "Holiday");
    myCategory.save();
    Joke myJoke = new Joke("Knock Knock", "Orange you glad", 1);
    myJoke.save();
    myCategory.addJoke(myJoke);
    myCategory.delete();
    assertEquals(myJoke.getCategories().size(), 0);
  }

}
