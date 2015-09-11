import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.fluentlenium.core.filter.FilterConstructor.*;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;


public class AppTest extends FluentTest {

  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @ClassRule
  public static DatabaseRule database = new DatabaseRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource().contains("Knee Slappers"));
  }

  @Test
  public void formForAddingJokesIsDisplayedOntheHomePage() {
   goTo("http://localhost:4567");
   assertThat(pageSource()).contains("Add a joke to make someone smile.");
 }

 @Test
  public void jokeIsSavedToDatabaseAndDisplayedOntheJokesPage() {
    Joke myJoke = new Joke("What does a ghost eat for breakfast?", "booberries");
    myJoke.save();
    String jokePath = ("http://localhost:4567/jokes");
    goTo(jokePath);
    assertThat(pageSource()).contains("What does a ghost eat for breakfast?", "booberries");
  }

  @Test
  public void formForAddingCategoriesIsDisplayedOntheCategoriesPage() {
    goTo("http://localhost:4567/categories");
    assertThat(pageSource()).contains("Add a new category");
  }

  @Test
   public void categoryIsSavedToDatabaseAndDisplayedOntheCategoriesPage() {
     Category category = new Category("Blonde Jokes");
     category.save();
     String categoryPath = ("http://localhost:4567/categories");
     goTo(categoryPath);
     assertThat(pageSource()).contains("Blonde Jokes");
   }

  @Test
  public void categoryIsDisplayedOnItsPage() {
    Category category = new Category("Blonde Jokes");
    category.save();
    String categoryPath = String.format("http://localhost:4567/categories/%d", category.getId());
    goTo(categoryPath);
    assertThat(pageSource()).contains("Welcome to Blonde Jokes page");
  }

  @Test
  public void jokeIsAddedAndDisplayedOntheCategoryPage() {
    Category myCategory = new Category("Pun");
    myCategory.save();
    Joke myJoke = new Joke("What does a ghost eat for breakfast?", "booberries");
    myJoke.save();
    myCategory.addJoke(myJoke);
    String categoryPath = String.format("http://localhost:4567/categories/%d", myCategory.getId());
    goTo(categoryPath);
    assertThat(pageSource()).contains("What does a ghost eat for breakfast?", "booberries");
  }

  @Test
  public void search_FindsAJokeAndDisplaysOntheResultsPage() {
    Joke myJoke = new Joke("What does a ghost eat for breakfast?", "booberries");
    myJoke.save();
    List<Joke> jokes = Joke.search("ghost");
    String searchPath = String.format("http://localhost:4567/search/results?main-search=ghost");
    goTo(searchPath);
    assertThat(pageSource()).contains(myJoke.getQuestion());
  }

  @Test
  public void search_FindsCategoryAndDisplaysOntheResultsPage() {
    Category myCategory = new Category("Technology");
    myCategory.save();
    List<Category> categories = Category.search("Technology");
    String searchPath = String.format("http://localhost:4567/search/results?main-search=Technology");
    goTo(searchPath);
    assertThat(pageSource()).contains(myCategory.getType());
  }

}
