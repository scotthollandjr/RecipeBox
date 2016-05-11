import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class RecipeTest {

  @Test
  public void Recipe_recipeInstantiatesCorrectky_true() {
    Recipe newRecipe = new Recipe(5, "Todd's Tacos", "Beef, tortillas, cheese, beans, salsa, sourcream", "Put all the ingredients together");
    assertEquals(true, newRecipe instanceof Recipe);
  }
}
