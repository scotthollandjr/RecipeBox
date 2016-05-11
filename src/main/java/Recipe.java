import java.util.*;
import org.sql2o.*;

public class Recipe {
  private int rating;
  private String name;
  private String instructions;
  private Date date_created;
  private int id;

  public Recipe(int rating, String name, String instructions) {
    this.rating = rating;
    this.name = name;
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
      this.getName().equals(newRecipe.getName()) && this.getInstructions().equals(newRecipe.getInstructions()) &&
      this.getDate().equals(newRecipe.getDate());
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO recipes(rating, name, instructions, date_created) VALUES (:rating, :name, :instructions, :date_created);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("rating", this.rating)
        .addParameter("name", this.name)
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

  public void update(String newName) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE recipes SET name = :name WHERE id = :id;";
      con.createQuery(sql)
        .addParameter("name", newName)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public List<Tag> getTags() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT tag_id FROM tags_recipes WHERE recipe_id = :recipe_id;";
      List<Integer> tagIds = con.createQuery(sql)
      .addParameter("recipe_id", this.getId())
      .executeAndFetch(Integer.class);

      List<Tag> tags = new ArrayList<Tag>();

      for (Integer tagId : tagIds) {
        String recipeQuery = "SELECT * FROM tags WHERE id = :tagId;";
        Tag tag = con.createQuery(recipeQuery)
        .addParameter("tagId", tagId)
        .executeAndFetchFirst(Tag.class);
        tags.add(tag);
      }
      return tags;
    }
  }

  public void addTag(Tag tag) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO tags_recipes (tag_id, recipe_id) VALUES (:tag_id, :recipe_id);";
      con.createQuery(sql)
        .addParameter("tag_id", tag.getId())
        .addParameter("recipe_id",this.getId())
        .executeUpdate();
    }
  }
}
