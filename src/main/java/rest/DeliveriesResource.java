package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.DeliveriesDTO;
import entities.Deliveries;
import utils.EMF_Creator;
import facades.DeliveriesFacade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//Todo Remove or change relevant parts before ACTUAL use
@Path("deliveries")
public class DeliveriesResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/progeks_db",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
    private static final DeliveriesFacade FACADE =  DeliveriesFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getDeliveriesCount() {
        long count = FACADE.getDeliveriesCount();
        return "{\"count\":"+count+"}";  //Done manually so no need for a DTO
    }
    
    //shows all deliveries
    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAll() {
        List<DeliveriesDTO> d = FACADE.getAllDeliveries();
        return GSON.toJson(d);
    }
    
    //delete delivery by id
    @DELETE
    @Path("/{id}")
    public Response deleteDelivery(@PathParam("id") Long id){
        DeliveriesDTO cdto = FACADE.deleteDelivery(id);
        return Response.ok(GSON.toJson(cdto)).build();
    }
    
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public String addDelivery(String deliveries){
        DeliveriesDTO d = GSON.fromJson(deliveries, DeliveriesDTO.class);
        Deliveries dNew = FACADE.addDelivery(d);
        return GSON.toJson(new DeliveriesDTO(dNew));
    }
 
}
