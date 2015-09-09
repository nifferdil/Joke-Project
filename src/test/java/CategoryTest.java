import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;

public class CategoryTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void category_instantiates_True() {
    Category testCategory = new Category("Pun", "Spain");
    assertTrue(testCategory instanceof Category);
  }

  @Test
  public void equals_returnsTrueIfCategoriesAretheSame_true() {
    Category firstCategory = new Category("Pun", "Spain");
    Category secondCategory = new Category("Pun", "Spain");
    assertTrue(firstCategory.equals(secondCategory));
  }

}
