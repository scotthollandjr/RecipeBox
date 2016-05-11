import java.util.*;
import org.sql2o.*;


public class Recipe {
  private int rating;
  private String name;
  private String ingredients;
  private String instructions;
  private Date date_created;

  public Recipe(int rating, String name, String ingredients, String instructions) {
    this.rating = rating;
    this.name = name;
    this.ingredients = ingredients;
    this.instructions = instructions;
    this.date_created = date_created;
  }

  public int getRating() {
    return rating;
  }

  public String getName() {
    return name;
  }

  public String getIngredients() {
    return ingredients;
  }

  public String getInstructions() {
    return instructions;
  }

  public Date getDate() {
    java.util.Date date_created = new java.util.Date();
    return date_created;
  }
}
