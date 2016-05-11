import java.util.*;
import org.sql2o.*;


public class Recipe {
  private int rating;
  private String name;
  private String ingredients;
  private String instructions;
  private Date date_created;
  private int id;
  private List<String> categories;

  public Recipe(int rating, String name, String ingredients, String instructions) {
    this.rating = rating;
    this.name = name;
    this.ingredients = ingredients;
    this.instructions = instructions;
    this.date_created = date_created;
  }

  public int getId() {
    return id;
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

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Recipe)) {
      return false;
    } else {
      Recipe newRecipe = (Recipe) obj;
      return this.getId() == newRecipe.getId() && this.getRating() == newRecipe.getRating() &&
      this.getName().equals(newRecipe.getName()) && this.getIngredients().equals(newRecipe.getIngredients()) && this.getInstructions().equals(newRecipe.getInstructions()) &&
      this.getDate().equals(newRecipe.getDate());
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO recipes(rating, name, ingredients, instructions, date_created) VALUES (:rating, :name, :ingredients, :instructions, :date_created);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("rating", this.rating)
        .addParameter("name", this.name)
        .addParameter("ingredients", this.ingredients)
        .addParameter("instructions", this.instructions)
        .addParameter("date_created", this.getDate())
        .executeUpdate()
        .getKey();
    }
  }

  public static List<Recipe> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM recipes;";
      List<Recipe> all = con.createQuery(sql)
        .executeAndFetch(Recipe.class);
      return all;
    }
  }

  public static Recipe find(int idInput) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM recipes WHERE id=:id;";
      return con.createQuery(sql)
        .addParameter("id", idInput)
        .executeAndFetchFirst(Recipe.class);
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM recipes WHERE id=:id;";
      con.createQuery(sql)
        .addParameter("id", this.getId())
        .executeUpdate();
    }
  }
}
