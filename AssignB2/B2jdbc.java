package crud;

import java.util.Scanner;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;


public class Crud {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		MongoClient mongoClient = new MongoClient("localhost");
	//	MongoCredential credential=MongoCredential.createCredential("root", "practice", "".toCharArray());
		MongoDatabase database = mongoClient.getDatabase("practice");
		MongoCollection<Document> collection = database.getCollection("student");
		int ch;
		do
		{
		System.out.println("1.Insert");
		System.out.println("2.Update");
		System.out.println("3.Read");
		System.out.println("4.Delete");
		System.out.println("Enter Choice");
		ch = sc.nextInt();
		
		switch (ch) {
		case 1:
				Document doc = new Document();
				System.out.println("Enter no field to be inserted in a document");
				int nof = sc.nextInt();
				for( int i=0 ; i < nof ;i++)
				{
					System.out.println("Enter key followed by value");
					String key = sc.next();
					String value = sc.next();
					doc.append(key, value);
					System.out.println("Inserted");
					
				}
				collection.insertOne(doc);
			break;
		case 2:
			System.out.println("Query criteria::::");
			System.out.println("Enter field name");
			String s1 = sc.next();
			System.out.println("Enter value for that field");
			String s2 = sc.next();
			System.out.println("Update Value:");
			System.out.println("Enter key to be updated");
			String key = sc.next();
			System.out.println("Enter value to be updated");
			String value = sc.next();
			Bson filter = new Document(s1,s2);
			Bson up1 = new Document(key,value);
			Bson up2 = new Document("$set",up1);
			collection.updateMany(filter, up2);
			
			
			System.out.println("updated");
			break;
			
		case 3:
			
			MongoCursor<Document> cursor = collection.find().iterator();
			
			while(cursor.hasNext())
			{
				System.out.println(cursor.next().toJson());
			}
			System.out.println("read");
			break;
			
		case 4:
			
			System.out.println("Query criteria::::");
		
			System.out.println("Enter key to be deleted");
			String key1 = sc.next();
			System.out.println("Enter value to be deleted");
			String value1 = sc.next();
			Bson filter1 = new Document(key1,value1);
		
			collection.deleteMany(filter1);
			
			System.out.println("deleted");
			break;
		default:
			break;
		}
		}while(ch!=0);
		
		
		

	}

}