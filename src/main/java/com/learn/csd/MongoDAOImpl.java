package com.learn.csd;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

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

	public List<Customer> findByCourse(String courseName){
		List<Customer> matchingCustomers = null;
		BasicDBObject findQuery = new  BasicDBObject("Course", courseName);
		DBCursor cursor = customerColl.find(findQuery);
		try {
			if(cursor.hasNext()) {
				matchingCustomers = new ArrayList<Customer>();
				while(cursor.hasNext()){
					matchingCustomers.add(Customer.convertDBObjectToCustomer(cursor.next()));
				}
			}
		} finally {
			cursor.close();
		}
		return matchingCustomers;
	}
	
	public List<Customer> findByLocation(String location){
		List<Customer> matchingCustomers = null;
		BasicDBObject findQuery = new  BasicDBObject("Location", location);
		DBCursor cursor = customerColl.find(findQuery);
		try {
			if(cursor.hasNext()) {
				matchingCustomers = new ArrayList<Customer>();
				while(cursor.hasNext()){
					matchingCustomers.add(Customer.convertDBObjectToCustomer(cursor.next()));
				}
			}
		} finally {
			cursor.close();
		}
		return matchingCustomers;
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
	
	public void insertCustomer(String tableName, JSONObject jo){
		if(tableName!=null && !tableName.isEmpty() && jo!=null && jo.length()>0){
			if(tableCollectionMap.containsKey(tableName)){
				DBCollection collection = tableCollectionMap.get(tableName);
				if(collection!=null){
					BasicDBObject basicDBObject = (BasicDBObject)com.mongodb.util.JSON.parse(jo.toString());
					collection.insert(basicDBObject);
				}
			}
		}
	}
}
