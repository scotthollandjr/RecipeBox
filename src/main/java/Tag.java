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
}
