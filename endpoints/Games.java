package endpoints;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import controllers.Home;

@Path("games")
public class Games {
	public static int conections = 0;
	
	@Path("/firstCall")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String firstCall() {
		System.out.println("/getTags firstCall");
		return Home.firstCall();
	}

	@Path("/getGames")
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public String getGames(String body) {
		System.out.println("/getGames call");
		return Home.getGames(body);
	}
	

	@Path("/getGame")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getGameBySlug(@QueryParam("slug") String slug) {
		System.out.println("llamada a getgamebyslug");
		return Home.getGame(slug);
	}
}
