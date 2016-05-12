import java.util.*;
import org.sql2o.*;

public class Tag {
  private int id;
  private String category;

  public Tag(String category) {
    this.category = category;
  }

  public String getCategory() {
    return category;
  }

  public int getId() {
    return id;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Tag)) {
      return false;
    } else {
      Tag newTag = (Tag) obj;
      return this.getCategory().equals(newTag.getCategory()) &&
      this.getId() == newTag.getId();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO tags(category) VALUES (:category);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("category", this.category)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<Tag> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM tags;";
      List<Tag> all = con.createQuery(sql)
        .executeAndFetch(Tag.class);
      return all;
    }
  }

  public static Tag find(int idInput) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM tags WHERE id=:id;";
      return con.createQuery(sql)
        .addParameter("id", idInput)
        .executeAndFetchFirst(Tag.class);
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM tags WHERE id=:id;";
      con.createQuery(sql)
        .addParameter("id", this.getId())
        .executeUpdate();
    }
  }

  public void update(String newCategory) {
    if(newCategory.trim().length() != 0) {
      try(Connection con = DB.sql2o.open()) {
        String sql = "UPDATE tag SET category = :category WHERE id = :id;";
        con.createQuery(sql)
          .addParameter("category", newCategory)
          .addParameter("id", id)
          .executeUpdate();
      }
    }
  }
}
