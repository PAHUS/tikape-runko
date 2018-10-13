package tikape.runko;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import spark.ModelAndView;
import spark.Spark;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import tikape.runko.database.Database;
import tikape.runko.database.KysymysDao;

public class Main {

    public static void main(String[] args) throws Exception {
        //asetetaan portti jos heroku antaa PORT-ympäristömuuttujan
        if (System.getenv("PORT") != null) {
            Spark.port(Integer.valueOf(System.getenv("PORT")));
        }//testi
        
        File tied = new File("db", "tk.db"); 
        Database database = new Database("jdbc:sqlite:" + tied.getAbsolutePath());
        database.init();

        //KysymysDao kysdao = new KysymysDao(database);
                
        Spark.get("*", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("kysymykset", "testi"/*, kysdao.findAll()*/);
            return new ModelAndView(map, "index");
        }, new ThymeleafTemplateEngine());

        
        
        /*get("/", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("viesti", "tervehdys");

            return new ModelAndView(map, "index");
        }, new ThymeleafTemplateEngine());

        get("/opiskelijat", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("opiskelijat", opiskelijaDao.findAll());

            return new ModelAndView(map, "opiskelijat");
        }, new ThymeleafTemplateEngine());

        get("/opiskelijat/:id", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("opiskelija", opiskelijaDao.findOne(Integer.parseInt(req.params("id"))));

            return new ModelAndView(map, "opiskelija");
        }, new ThymeleafTemplateEngine());*/
    }
}
