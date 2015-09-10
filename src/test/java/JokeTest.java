import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class JokeTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Joke.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfQuestionsAretheSame() {
    Joke firstJoke = new Joke("What does a ghost eat for breakfast?", "booberries");
    Joke secondJoke = new Joke("What does a ghost eat for breakfast?", "booberries");
    assertTrue(firstJoke.equals(secondJoke));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Joke myJoke = new Joke("What does a ghost eat for breakfast?", "booberries");
    myJoke.save();
    assertTrue(Joke.all().get(0).equals(myJoke));
  }

  @Test
  public void find_findOneQuestionInJoke() {
    Joke myJoke = new Joke("What does a ghost eat for breakfast?", "booberries");
    myJoke.save();
    Joke savedJoke = Joke.find(myJoke.getId());
    assertTrue(myJoke.equals(savedJoke));
  }

  @Test
  public void delete_deleteQuestionFromJoke() {
    Joke myJoke = new Joke("What does a ghost eat for breakfast?", "booberries");
    myJoke.save();
    myJoke.delete();
    assertEquals(Joke.all().size(), 0);
  }


  @Test
 public void addCategory_addsCategoryToJoke() {
   Joke myJoke = new Joke("What does a ghost eat for breakfast?", "booberries", 3);
   myJoke.save();
   Category myCategory = new Category("Food", "USA");
   myCategory.save();
   myJoke.addCategory(myCategory);
   Joke savedJoke = myCategory.getJokes().get(0);
   assertTrue(myJoke.equals(savedJoke));
 }

 @Test
 public void getCategorys_returnAllCategorys_ArrayList() {
   Category myCategoryOne = new Category("Food", "USA");
   myCategoryOne.save();
   Category myCategoryTwo = new Category("Holiday", "USA");
   myCategoryTwo.save();
   Joke myJoke = new Joke("What does a ghost eat for breakfast?", "booberries", 3);
   myJoke.save();
   myJoke.addCategory(myCategoryOne);
   myJoke.addCategory(myCategoryTwo);
   List savedCategories = myJoke.getCategories();
   assertEquals(savedCategories.size(), 2);
 }

 @Test
  public void removeCategory_removesCategoryFromJoke() {
    Category myCategory = new Category("Food", "USA");
    myCategory.save();
    Joke myJoke = new Joke("What does a ghost eat for breakfast?", "booberries", 3);
    myJoke.save();
    myJoke.addCategory(myCategory);
    myJoke.removeCategory(myCategory.getId());
    assertFalse(myJoke.getCategories().contains("Food"));
  }


    @Test
    public void search_filtersJokesQuestions() {
      Joke myJoke = new Joke("What does a ghost eat for breakfast?", "booberries");
      myJoke.save();
      List searchResult = Joke.searchQuestion("ghost");
      assertTrue(myJoke.equals(searchResult.get(0)));
    }

    @Test
    public void search_filtersJokesAnswers() {
      Joke myJoke = new Joke("What does a ghost eat for breakfast?", "booberries");
      myJoke.save();
      List searchResult = Joke.searchAnswer("boo");
      assertTrue(myJoke.equals(searchResult.get(0)));
    }

  @Test
  public void count_increaseHilarityLevel() {
    Joke myJoke = new Joke("What does a ghost eat for breakfast?", "booberries");
    myJoke.save();
    myJoke.hilarityUp();
    assertEquals(1, Joke.all().get(0).getHilarity());
    }

    @Test
    public void count_decreaseHilarityLevel() {
      Joke myJoke = new Joke("What does a ghost eat for breakfast?", "booberries");
      myJoke.save();
      myJoke.hilarityUp();
      assertEquals(-1, Joke.all().get(0).getHilarity());
      }
 }
