package tikape.runko;

import java.io.File;
import java.util.HashMap;
import spark.ModelAndView;
import spark.Spark;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import tikape.runko.database.Database;
import tikape.runko.database.KysymysDao;
import tikape.runko.database.VastausDao;

public class Main {

    public static void main(String[] args) throws Exception {
        //asetetaan portti jos heroku antaa PORT-ympäristömuuttujan
        if (System.getenv("PORT") != null) {
            Spark.port(Integer.valueOf(System.getenv("PORT")));
        }//testi
        
        File tied = new File("db", "tk.db"); 
        Database database = new Database("jdbc:sqlite:" + tied.getAbsolutePath());
        database.init();

        KysymysDao kysdao = new KysymysDao(database);
        VastausDao vastDao = new VastausDao(database, kysdao);
        
        
        Spark.get("/", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("kysymykset", kysdao.findAll());
            return new ModelAndView(map, "index");
        }, new ThymeleafTemplateEngine());
        
        Spark.get("/vastaukset/:id", (req,res) ->{
            HashMap map = new HashMap<>();
            int id = Integer.parseInt(req.params(":id"));
            map.put("kysymys",kysdao.findOne(id));
            map.put("vastaukset", vastDao.findAllFrom(id)); ///// TÄNNE
            return new ModelAndView(map, "vastind");
        }, new ThymeleafTemplateEngine());
        
        Spark.post("/kysymys", (req,res)->{
            String kurssi = req.queryParams("kurssi");
            String aihe = req.queryParams("aihe");
            String kysymysteksti = req.queryParams("kysymysteksti");
            
            kysdao.saveOrUpdate(new Kysymys(kurssi, aihe, kysymysteksti));
            res.redirect("/");
            return null;
        });
        
        Spark.post("/poista/:id", (req,res)->{
            Integer id = Integer.parseInt(req.params(":id"));
            kysdao.delete(id);
            
            res.redirect("/");
            return null;
        });
        Spark.post("/vastaa/:id", (req,res)->{
           int id = Integer.parseInt(req.params(":id"));
           String vastausTeksti = req.queryParams("teksti");
           vastDao.saveTo(new Vastaus(vastausTeksti), id);
            
            
            res.redirect("/vastaukset/" + id);
            return null;
        });

        
        
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
