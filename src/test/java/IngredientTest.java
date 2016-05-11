import org.junit.*;
import java.util.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.*;

public class IngredientTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Ingredient_ingredientInstantiatesCorrectly_true() {
    Ingredient newIngredient = new Ingredient("beef, cheese");
    assertEquals(true, newIngredient instanceof Ingredient);
  }

  @Test
  public void IngredientList_ingredientsAreReturnedInList_List() {
    Ingredient newIngredient = new Ingredient("beef, cheese, corn, cabbage, spinach, limabeans");
    assertEquals("b", newIngredient.getIngredients());
  }
}
