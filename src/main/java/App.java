import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.ArrayList;

public class App {

  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("recipes", Recipe.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      String ingredients = request.queryParams("ingredients");
      String instructions = request.queryParams("instructions");
      int rating = Integer.parseInt(request.queryParams("rating"));
      String category = request.queryParams("category");
      Recipe newRecipe = new Recipe(rating, name, ingredients, instructions);
      Tag newTag = new Tag(category);
      newRecipe.save();
      newTag.save();
      newRecipe.addTag(newTag);
      response.redirect("/");
      return null;
    });

    get("/recipe/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Recipe newRecipe = Recipe.find(Integer.parseInt(request.params(":id")));
      model.put("recipe", newRecipe);
      model.put("template", "templates/recipe.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/recipe/:id/delete", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Recipe newRecipe = Recipe.find(Integer.parseInt(request.params(":id")));
      newRecipe.delete();
      response.redirect("/");
      return null;
    });
}
}
