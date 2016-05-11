import org.junit.*;
import java.util.*;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class RecipeTest {

  @Test
  public void Recipe_recipeInstantiatesCorrectky_true() {
    Recipe newRecipe = new Recipe(5, "Todd's Tacos", "Beef, tortillas, cheese, beans, salsa, sourcream", "Put all the ingredients together");
    assertEquals(true, newRecipe instanceof Recipe);
  }

  @Test
  public void getRating_returnsRating_int() {
    Recipe newRecipe = new Recipe(5, "Todd's Tacos", "Beef, tortillas, cheese, beans, salsa, sourcream", "Put all the ingredients together");
    assertEquals(5, newRecipe.getRating());
  }

  @Test
  public void getDate_returnsDate_Date() {
    Recipe newRecipe = new Recipe(5, "Todd's Tacos", "Beef, tortillas, cheese, beans, salsa, sourcream", "Put all the ingredients together");
    java.util.Date currentDate = new java.util.Date();
    assertEquals(currentDate, newRecipe.getDate());
    System.out.println(currentDate);
    System.out.println(newRecipe.getDate());
  }

  
}
