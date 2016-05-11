import org.junit.*;
import java.util.*;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class TagTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Tag_instantiatesLikeItDo_true() {
    Tag newTag = new Tag("Mexican");
    assertTrue(newTag instanceof Tag);
  }

  @Test
  public void Tag_returnsWithCategory_String() {
    Tag newTag = new Tag("Mexican");
    assertEquals("Mexican", newTag.getCategory());
  }

  @Test
  public void Tag_returnsTrueIfObjectsEqual_true() {
    Tag firstTag = new Tag("Mexican");
    Tag secondTag = new Tag("Mexican");
    assertTrue(firstTag.equals(secondTag));
  }

  @Test
  public void save_savesTagToDatabase_true() {
    Tag newTag = new Tag("Mexican");
    newTag.save();
    assertTrue(newTag.all().size() == 1);
  }

  @Test
  public void find_findsTagById_true() {
    Tag newTag = new Tag("Mexican");
    newTag.save();
    Tag foundTag = Tag.find(newTag.getId());
    assertTrue(foundTag.equals(newTag));
  }

  @Test
  public void delete_deletesTagFromDatabase_true() {
    Tag newTag = new Tag("Mexican");
    newTag.save();
    newTag.delete();
    assertTrue(newTag.all().size() == 0);
  }
}
