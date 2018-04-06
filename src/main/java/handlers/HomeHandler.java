package handlers;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.ejdb.bson.BSONObject;
import org.ejdb.driver.EJDBCollection;
import org.ejdb.driver.EJDBQueryBuilder;
import org.ejdb.driver.EJDBResultSet;
import spark.Request;
import spark.Response;
import spark.Route;
import java.util.HashMap;
import java.util.Map;
import config.App;
import config.Database;
import helpers.ViewHelper;
import helpers.HomeHelper;

public class HomeHandler {
  public static Route index = (Request request, Response response) -> {
    Config constants = ConfigFactory.parseResources("config/application.conf");
    Map<String, Object> model = new HashMap<>();
    model.put("partial", "templates/home/index.vm");
    model.put("title", "Home");
    model.put("constants", constants);
    model.put("load_css", ViewHelper.loadCSS(constants, HomeHelper.indexCSS(constants)));
    model.put("load_js", ViewHelper.loadJS(constants, HomeHelper.indexJS(constants)));
    return App.renderTemplate("templates/layouts/blank.vm", model);
  };

  public static Route listar = (Request request, Response response) -> {
    String rpta = "";
    try{
      EJDBQueryBuilder qb;
      EJDBResultSet rs;
      Database db = new Database();
      db.open();
      EJDBCollection parrots = db.getDB().getCollection("parrots");
      qb = new EJDBQueryBuilder();
      qb.field("likes", "toys").orderBy().asc("name").desc("age");
      rs = parrots.createQuery(qb).find();
      for (BSONObject r : rs) {
        System.out.println("\t" + r);
        rpta = rpta + r;
      }
      rs.close();
    }catch (Exception e) {
      rpta = e.toString();
    }
    return rpta;
  };
}

