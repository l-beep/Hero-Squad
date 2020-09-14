import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class App {
    public static void main(String[] args) {


        Map<String, Object> model = new HashMap<String, Object>();

        ProcessBuilder process = new ProcessBuilder();
        Integer port;
        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 4567;
        }

        setPort(port);


        staticFileLocation("/public");
        String layout = "templates/layout.hbs";

        get("/", (request, response) -> {

            model.put("template", "templates/home.hbs" );
            return new ModelAndView(model, layout);
        }, new HandlebarsTemplateEngine());

        get("/squadform", (request, response) -> {

            model.put("template", "templates/squadform.hbs");
            return new ModelAndView(model, layout);
        }, new HandlebarsTemplateEngine());

        get("/squad", (request, response) -> {

            String squadname = request.queryParams("squadname");
            String squadcause = request.queryParams("squadcause");
            String membernumber = request.queryParams("membernumber");
            model.put("squadname", squadname);
            model.put("squadcause", squadcause);
            model.put("membernumber", membernumber);
            model.put("template", "templates/squadteam.hbs");
            return new ModelAndView(model, layout);
        }, new HandlebarsTemplateEngine());

        get("/heroform", (request, response) -> {

            model.put("template", "templates/heroform.hbs");
            return new ModelAndView(model, layout);
        }, new HandlebarsTemplateEngine());

        get("/hero", (request, response) -> {

            String heroname = request.queryParams("heroname");
            String whichsquad = request.queryParams("whichsquad");
            String heropower = request.queryParams("heropower");
            String heroweakness = request.queryParams("heroweakness");
            String heroage = request.queryParams("heroage");
            model.put("heroname", heroname);
            model.put("whichsquad", whichsquad);
            model.put("heropower", heropower);
            model.put("heroweakness", heroweakness);
            model.put("heroage", heroage);
            model.put("template", "templates/hero.hbs");
            return new ModelAndView(model, layout);
        }, new HandlebarsTemplateEngine());

        get("/herolist", (request, response) -> {

            model.put("template", "templates/listhero.hbs" );
            return new ModelAndView(model, layout);
        }, new HandlebarsTemplateEngine());
    }
}