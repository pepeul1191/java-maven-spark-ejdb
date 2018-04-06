package config;

import org.ejdb.driver.EJDB;
import org.bson.types.ObjectId;

public class Database {
	private EJDB db;
	
	public Database() throws Exception{
		this.db = new EJDB();
  }
  
  public void open(){
    //db.open("db/zoo", EJDB.JBOREADER | EJDB.JBOWRITER | EJDB.JBOCREAT | EJDB.JBOTRUNC);
    this.db.open("db/zoo", EJDB.JBOREADER | EJDB.JBOWRITER);
  }

  public void close(){
    this.db.close();
  }

  public String generateId(){
    ObjectId id = new ObjectId();
    return id.toString();
  }
}
