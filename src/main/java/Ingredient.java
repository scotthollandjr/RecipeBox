import java.util.*;
import java.io.*;

public class Ingredient {
  private String ingredient;
  private String[] ingredientList;

  public Ingredient(String ingredient) {
    this.ingredient = ingredient;
  }

  public String getIngredient() {
    return ingredient;
  }

  public String[] getIngredientList() {
    String[] ingredientList = new String[]{ingredient};
    ingredientList.split(",");
    return ingredientList;
  }
}
