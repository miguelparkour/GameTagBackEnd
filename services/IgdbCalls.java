package services;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.client.Entity;

public class IgdbCalls {
	private static String CLIENT_ID;
	private static String AUTHORIZATION;
	private static final String API_URL = "https://api.igdb.com/v4/games";
	private Client client;
	private WebTarget target;
	public IgdbCalls() {
		try {
			client = ClientBuilder.newClient();			
		} catch(Exception e) {
			e.printStackTrace();
		}
		target = client.target(API_URL);
	}
	public String getGames(String body) {
		
		return target
				.request(MediaType.APPLICATION_JSON)
				.header("Client-ID", CLIENT_ID)
				.header("Authorization", AUTHORIZATION)
				.post(Entity.text(body))
				.readEntity(String.class);
	}
	
	
	public static void setCredentials(String[] creds) {
		CLIENT_ID = creds[0];
		AUTHORIZATION = creds[1];
	}
}
