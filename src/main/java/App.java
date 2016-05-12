import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.ArrayList;
import java.util.List;

public class App {

  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      System.out.println(request.queryParams("search"));
      if (request.queryParams("search") != null) {
        String search = request.queryParams("search");
        List<Recipe> foundRecipes = Recipe.getRecipeWithIngredient("%" + search + "%");
        model.put("foundRecipes", foundRecipes);
      }
      model.put("recipes", Recipe.all());
      model.put("template", "templates/index.vtl");

      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      String ingredients = request.queryParams("ingredients");
      String instructions = request.queryParams("instructions");
      String rating = request.queryParams("rating");
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
      model.put("tags", newRecipe.getTags());
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

    get("/recipe/:id/update", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Recipe newRecipe = Recipe.find(Integer.parseInt(request.params(":id")));
      model.put("tags", newRecipe.getTags());
      model.put("recipe", newRecipe);
      model.put("template", "templates/recipe-update.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/recipe/:id/update", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Recipe newRecipe = Recipe.find(Integer.parseInt(request.params(":id")));
      String name = request.queryParams("name");
      String ingredients = request.queryParams("ingredients");
      String instructions = request.queryParams("instructions");
      String rating = request.queryParams("rating");
      String category = request.queryParams("category");
      Tag newTag = new Tag(category);
      newTag.save();
      newRecipe.addTag(newTag);
      newRecipe.update(rating, name, ingredients, instructions);
      model.put("recipe", newRecipe);
      response.redirect("/recipe/" + newRecipe.getId());
      return null;
    });

    // post("/search", (request, response) -> {
    //   Map<String, Object> model = new HashMap<String, Object>();
      // String search = request.queryParams("search");
      // List<Recipe> foundRecipes = Recipe.getRecipeWithIngredient("%" + search + "%");
      // model.put("foundRecipes", foundRecipes);
    //   response.redirect("/");
    //   return null;
    // });
  }
}
