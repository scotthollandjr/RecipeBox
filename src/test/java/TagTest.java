import org.junit.*;
import java.util.*;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class TagTest {

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
}
