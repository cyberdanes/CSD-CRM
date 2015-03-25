package com.learn.csd;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class MongoDAOImpl {
	
	private static MongoDAOImpl STATICINSTANCE;

	// Standard URI format: mongodb://[dbuser:dbpassword@]host:port/dbname
	private static MongoClientURI uri;
	private static MongoClient client;
	private static 	DB db;

	// get a collection object to work with
	private static 	DBCollection customerColl;
	private static Map<String, DBCollection> tableCollectionMap = new HashMap<String, DBCollection>();

	public MongoDAOImpl(){
		try {
			uri = new MongoClientURI("mongodb://pccoe:pccoe2015@ds027758.mongolab.com:27758/idis"); 
			client = new MongoClient(uri);
			db = client.getDB(uri.getDatabase());
			customerColl = db.getCollection("customer");
			tableCollectionMap.put("customer", customerColl);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	public static MongoDAOImpl getInstance(){
		if(STATICINSTANCE==null){
			STATICINSTANCE = new MongoDAOImpl();
		}
		return STATICINSTANCE;
	}

	public DBObject findByCourse(String courseName){		
		BasicDBObject findQuery = new  BasicDBObject("course", courseName);

		DBCursor cursor = customerColl.find(findQuery);
		try {
			if(cursor.hasNext()) {
				DBObject dbo =  cursor.next();
				return dbo;
			}
		} finally {
			cursor.close();
		}
		return null;
	}
	
	public DBObject findByLocation(String location){		
		BasicDBObject findQuery = new  BasicDBObject("location", location);

		DBCursor cursor = customerColl.find(findQuery);
		try {
			if(cursor.hasNext()) {
				DBObject dbo =  cursor.next();
				return dbo;
			}
		} finally {
			cursor.close();
		}
		return null;
	}
	

	public void insert(String tableName, Map<String, String> data){
		if(tableName!=null && !tableName.isEmpty() && data!=null && !data.isEmpty()){
			if(tableCollectionMap.containsKey(tableName)){
				DBCollection collection = tableCollectionMap.get(tableName);
				if(collection!=null){
					BasicDBObject basicDBObject = new BasicDBObject();
					for(String colName : data.keySet()){
						basicDBObject.put(colName, data.get(colName));
					}
					collection.insert(basicDBObject);
				}
			}
		}
	}
}
