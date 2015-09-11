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
  public void search_filtersJokes() {
    Joke myJoke = new Joke("What does a ghost eat for breakfast?", "booberries");
    myJoke.save();
    List searchResult = Joke.search("breakfast");
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
    assertEquals(1, Joke.all().get(0).getHilarity());
  }

 }
