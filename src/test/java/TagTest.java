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

}
