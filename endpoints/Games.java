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
	@Path("/getTags")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getTags() {
		System.out.println("/getTags call");
		return Home.getTags();
	}

	@Path("/getGames")
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public String getGames(String body) {
		System.out.println("/getGames call");
		return Home.getGames(body);
	}
	

	@Path("/getGameById")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getGameById(@QueryParam("id") String id) {
		System.out.println("llamada a getgamebyid");
		System.out.println("id: "+id);
		return "";
	}
}
