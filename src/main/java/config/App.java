package config;

import java.util.Calendar;
import java.util.Date;
import org.ejdb.bson.BSONObject;
import org.ejdb.driver.EJDB;
import org.ejdb.driver.EJDBCollection;
import org.ejdb.driver.EJDBQueryBuilder;
import org.ejdb.driver.EJDBResultSet;

public class App {
  public static void main(String[] args) {
      EJDB ejdb = new EJDB();

      try {
          // Used modes:
          //  - read
          //  - write
          //  - create db if not exists
          //  - truncate existing db
          //ejdb.open("db/zoo", EJDB.JBOREADER | EJDB.JBOWRITER | EJDB.JBOCREAT | EJDB.JBOTRUNC);
          ejdb.open("db/zoo", EJDB.JBOREADER | EJDB.JBOWRITER);

          BSONObject parrot1 = new BSONObject("name", "Grenny")
                  .append("type", "African Grey")
                  .append("male", true)
                  .append("age", 1)
                  .append("birthhdate", new Date())
                  .append("likes", new String[]{"green color", "night", "toys"})
                  .append("extra1", null);

          Calendar calendar = Calendar.getInstance();
          calendar.set(2013, 1, 1, 0, 0, 1);

          BSONObject parrot2 = new BSONObject();
          parrot2.put("name", "Bounty");
          parrot2.put("type", "Cockatoo");
          parrot2.put("male", false);
          parrot2.put("age", 15);
          parrot2.put("birthdate", calendar.getTime());
          parrot2.put("likes", new String[]{"sugar cane"});
          parrot2.put("extra1", null);

          System.out.println("parrot1 =\n\t\t" + parrot1);
          System.out.println("parrot2 =\n\t\t" + parrot2);

          EJDBCollection parrots = ejdb.getCollection("parrots");

          // saving
          //parrots.save(parrot1);
          //parrots.save(parrot2);

          EJDBQueryBuilder qb;
          EJDBResultSet rs;

          // Below two equivalent queries:
          // Q1
          qb = new EJDBQueryBuilder();
          qb.field("likes", "toys")
                  .orderBy().asc("name").desc("age");

          rs = parrots.createQuery(qb).find();
          System.out.println();
          System.out.println("Results (Q1): " + rs.length());
          for (BSONObject r : rs) {
              System.out.println("\t" + r);
          }
          rs.close();

          // Q2
          qb = new EJDBQueryBuilder();
          qb.field("likes").eq("toys")
                  .orderBy().add("name", true).add("age", false);

          rs = parrots.createQuery(qb).find();
          System.out.println();
          System.out.println("Results (Q2): " + rs.length());
          for (BSONObject r : rs) {
              System.out.println("\t" + r);
          }

          // Second way to iterate
          System.out.println();
          System.out.println("Results (Q2): " + rs.length());
          for (int i = 0; i < rs.length(); ++i) {
              System.out.println("\t" + i + " => " + rs.get(i));
          }

          rs.close();
      } finally {
          if (ejdb.isOpen()) {
              ejdb.close();
          }
      }
  }
}