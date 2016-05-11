import java.util.*;
import java.sql.Timestamp;
import org.sql2o.*;


public class Recipe {
  private int rating;
  private String name;
  private String ingredients;
  private String instructions;
  private Timestamp date_created;

  public Recipe(int rating, String name, String ingredients, String instructions) {
    this.rating = rating;
    this.name = name;
    this.ingredients = ingredients;
    this.instructions = instructions;
    this.date_created = date_created;
  }

}
