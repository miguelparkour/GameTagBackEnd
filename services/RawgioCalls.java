package services;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class RawgioCalls {
	private static String TOKEN = "7a3ec153788fe6b6192fb5e68b03eb60c961d91a";
	private static String USER_AGENT = "TagGames";
	private final String API_URL = "https://rawg.io/api/games";
	private Client client;
	private WebTarget target;
	
	public RawgioCalls() {
		try {
			client = ClientBuilder.newClient();			
		} catch(Exception e) {
			e.printStackTrace();
		}
		target = client.target(API_URL);
	}
	public String getGame(String slug) {
		try {
			return target.path(slug)
						 .request(MediaType.APPLICATION_JSON)
						 .header("token", TOKEN)
						 .header("User-Agent", USER_AGENT)
						 .get(String.class);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static void setCredentials(String[] creds) {
		TOKEN = creds[2];
		USER_AGENT = creds[3];
	}
}