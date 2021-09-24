package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.GameUserDTO;
import entities.GameUser;
import utils.EMF_Creator;
import facades.GameUserFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//Todo Remove or change relevant parts before ACTUAL use
@Path("gameuser")
public class GameUserResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/game_users",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
    private static final GameUserFacade FACADE =  GameUserFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    
    //Shows total number of users in database
    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getUserCount() {
        long count = FACADE.getUserCount();
        return "{\"count\":"+count+"}";  //Done manually so no need for a DTO
    }
    
    //Shows all user OBS we disable this cause we dont need it, unless admin users are to be applied
    
    @GET
    @Path("/users")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllUsers() {
        List<GameUserDTO> d = FACADE.getAllUsers();
        return Response.ok(GSON.toJson(d)).build();
    }
    
    
    //Used for login
    @Path("/login/l")
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public String loginGameUser(String login){
        GameUserDTO g = GSON.fromJson(login, GameUserDTO.class);

        String username = g.getUserEmail();
        String password = g.getUserPass();
        String user = FACADE.loginCheckUser(username, password).getUserEmail();

        return user;
    }    
        
    //Used for user creation
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public String addUser(String gameuser){
        GameUserDTO g = GSON.fromJson(gameuser, GameUserDTO.class);
        GameUser gNew = FACADE.addUser(g);
        if(g.getUserEmail()==null){return "User Already exists!";}
        return "User Created!";
    }
 
}
