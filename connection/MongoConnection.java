package connection;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class MongoConnection {

	private static MongoClient mongoClient;
	
	public static MongoClient getMongoClient() {
		
		if(mongoClient == null) {
			try {
				mongoClient = MongoClients.create();
			} catch (Exception e) {
				System.out.println("mongoDB ERROR => " + e.getMessage());
			}
		}
		return mongoClient;
	}
	public static void closeClient() {
		mongoClient.close();
		mongoClient = null;
	}
}
