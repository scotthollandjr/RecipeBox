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
}
