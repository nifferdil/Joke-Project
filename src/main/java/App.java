import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;


public class App {
  public static void main(String[] args) {
    ProcessBuilder process = new ProcessBuilder();
    Integer port;
    if (process.environment().get("PORT") != null) {
      port = Integer.parseInt(process.environment().get("PORT"));
    } else {
      port = 4567;
    }
    setPort(port);
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    /* Index */
    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("categories", Category.all());
      model.put("jokes", Joke.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    /* Index --> list of Jokes */
    get("/jokes", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("jokes", Joke.all());
      model.put("categories", Category.all());
      model.put("template", "templates/jokes.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    /* Index --> list of Categorys */
    get("/categories", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("jokes", Joke.all());
      model.put("categories", Category.all());
      model.put("template", "/templates/categories.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


    post("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String question = request.queryParams("question");
      String answer = request.queryParams("answer");
      Joke newJoke = new Joke(question, answer);
      newJoke.save();
      response.redirect("/");
      return null;
    });

  }
}
