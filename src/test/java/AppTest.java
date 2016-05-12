import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.assertj.core.api.Assertions.assertThat;
import static org.fluentlenium.core.filter.FilterConstructor.*;
import org.sql2o.*;
import org.junit.*;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @Rule
  public DatabaseRule database = new DatabaseRule();

 @ClassRule
 public static ServerRule server = new ServerRule();

 @Test
 public void rootTest() {
   goTo("http://localhost:4567/");
   assertThat(pageSource()).contains("Add a recipe");
 }

 @Test
 public void displayAddedRecipePage() {
   goTo("http://localhost:4567/");
   fill("#name").with("Scotts Tots");
   submit(".btn", withText("Add"));
   assertThat(pageSource()).contains("Scotts Tots");
 }

 @Test
 public void updateAddedRecipe() {
   goTo("http://localhost:4567/");
   fill("#name").with("Scotts Tots");
   submit(".btn", withText("Add"));
   click("a", withText("Scotts Tots"));
   click("a", withText("Update This Recipe"));
   fill("#name").with("Todds Tots");
   submit(".btn", withText("Update"));
   assertThat(pageSource()).contains("Todds Tots");
 }
}
