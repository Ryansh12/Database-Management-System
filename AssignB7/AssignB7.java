package mongoconn;
//import com.mongodb.*;
import com.mongodb.client.*; 
import com.mongodb.MongoClient; 
//import com.mongodb.MongoClientURI; 
//import com.mongodb.MongoCredential;
import org.bson.*;
//import org.json.*;
import org.json.simple.JSONObject; 
import org.json.simple.JSONValue;   

public class Mongoconn { 
   
   public static void main( String args[] ) {  
	 //  MongoClientURI uri = new MongoClientURI("mongodb://te3341:te3341@10.10.15.196:27017/te3341db") ; 
      // Creating a Mongo client 
      MongoClient mongo = new MongoClient( "localhost" , 27017 ); 
      //MongoClient mongo = new MongoClient(uri) ;   
      // Creating Credentials 
    //  MongoCredential credential; 
      //credential = MongoCredential.createCredential("root", "practice", 
        // "root".toCharArray()); 
      System.out.println("Connected to the database successfully");  
      
      // Accessing the database 
      MongoDatabase database = mongo.getDatabase("practice"); 
      //MongoDatabase database = mongo.getDatabase(te3341"); 
      //System.out.println("Credentials ::"+ credential);     
      
      //Creating Collection
      database.createCollection("sample1"); 
      System.out.println("\nCollection created successfully");
      MongoCollection<Document> col= database.getCollection("sample1");
      
      
      //Encoding JSON Object
      JSONObject obj=new JSONObject();    
	  obj.put("name","Pikachu");    
	  obj.put("age",new Integer(19));    
	  obj.put("salary",new Double(600000));  
	  obj.put("address","Pune");
		
	  Document doc= Document.parse(obj.toString());
	  col.insertOne(doc);
	
	  
	//Decoding JSON Object
	  
	  Object obj1=JSONValue.parse(obj.toString());  
	  JSONObject jsonObject = (JSONObject) obj1;  
  
	  String name = (String) jsonObject.get("name");  
	  double salary = (Double) jsonObject.get("salary");  
	  long age = (Long) jsonObject.get("age"); 
	  String address = (String) jsonObject.get("address");
	  System.out.println("Name : "+name+"\nSalary :  "+salary+"\nAge :  "+age+"\nAddress : "+address);  
	
	
	


   } 
}