import org.junit.*;
import java.util.*;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class RecipeTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Recipe_recipeInstantiatesCorrectky_true() {
    Recipe newRecipe = new Recipe(5, "Todd's Tacos", "beef, cheese", "Put all the ingredients together");
    assertEquals(true, newRecipe instanceof Recipe);
  }

  @Test
  public void getRating_returnsRating_int() {
    Recipe newRecipe = new Recipe(5, "Todd's Tacos", "beef, cheese", "Put all the ingredients together");
    assertEquals(5, newRecipe.getRating());
  }

  @Test
  public void getDate_returnsDate_Date() {
    Recipe newRecipe = new Recipe(5, "Todd's Tacos", "beef, cheese", "Put all the ingredients together");
    java.util.Date currentDate = new java.util.Date();
    assertEquals(currentDate, newRecipe.getDate());
  }

  @Test
  public void Recipe_returnsTrueIfNamesAreEqual_true() {
    Recipe firstRecipe = new Recipe(5, "Todd's Tacos", "beef, cheese", "Put all the ingredients together");
    Recipe secondRecipe = new Recipe(5, "Todd's Tacos", "beef, cheese", "Put all the ingredients together");
    assertTrue(firstRecipe.equals(secondRecipe));
  }

  @Test
  public void Recipe_ingredientsAreReturnedInList_List() {
    Recipe newRecipe = new Recipe(5, "Todd's Tacos", "beef,  cheese", "Put all the ingredients together");
    List<String> expected = new ArrayList<String>();
    expected.add("beef");
    expected.add("cheese");
    assertEquals(expected, newRecipe.getIngredientList());
  }

  @Test
  public void save_savesRecipes_true() {
    Recipe newRecipe = new Recipe(5, "Todd's Tacos", "beef, cheese", "Put all the ingredients together");
    newRecipe.save();
    assertTrue(newRecipe.all().size() == 1);
  }

  @Test
  public void find_findsRecipeById_true() {
    Recipe newRecipe = new Recipe(5, "Todd's Tacos", "beef, cheese", "Put all the ingredients together");
    newRecipe.save();
    Recipe foundRecipe = Recipe.find(newRecipe.getId());
    assertTrue(foundRecipe.equals(newRecipe));
  }

  @Test
  public void delete_deletesRecipeFromDB_true() {
    Recipe newRecipe = new Recipe(5, "Todd's Tacos", "beef, cheese", "Put all the ingredients together");
    newRecipe.save();
    newRecipe.delete();
    assertTrue(newRecipe.all().size() == 0);
  }

  @Test
  public void update_updateRecipeName_true() {
    Recipe newRecipe = new Recipe(5, "Todd's Tacos", "beef, cheese", "Put all the ingredients together");
    newRecipe.save();
    newRecipe.update("Timmy's Tacos");
    assertEquals("Timmy's Tacos", Recipe.find(newRecipe.getId()).getName());
  }

  @Test
  public void addTags_addsTag_true() {
    Recipe newRecipe = new Recipe(5, "Todd's Tacos", "beef, cheese", "Put all the ingredients together");
    Tag newTag = new Tag("Mexican");
    newRecipe.save();
    newTag.save();
    newRecipe.addTag(newTag);
    assertTrue(Recipe.find(newRecipe.getId()).getTags().size() == 1);
  }

  @Test
  public void addTags_addsMultipleTags_true() {
    Recipe newRecipe = new Recipe(5, "Todd's Tacos", "beef, cheese", "Put all the ingredients together");
    Tag firstTag = new Tag("Mexican");
    Tag secondTag = new Tag("Breakfast");
    newRecipe.save();
    firstTag.save();
    secondTag.save();
    newRecipe.addTag(firstTag);
    newRecipe.addTag(secondTag);
    assertTrue(Recipe.find(newRecipe.getId()).getTags().size() == 2);
  }
  // @Test
  // public void getRecipe_returnsRecipeWithSpecificIngredient_Recipe() {
  //   Recipe newRecipe = new Recipe(5, "Todd's Tacos", "beef", "Put all the ingredients together");
  //   newRecipe.save();
  //   Recipe testRecipe = Recipe.getRecipeWithIngredient("\'%beef%\'");
  //
  //   assertTrue(newRecipe.getName().equals(testRecipe.getName()));
  // }
}

//Recipe testRecipe = Recipe.find((Recipe.getRecipeWithIngredient("beef")).getId());
