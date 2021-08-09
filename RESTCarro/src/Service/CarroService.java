package Service;

import Controller.CarroController;
import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.List;
import static javax.management.Query.times;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import Model.Carro;


/**
 *
 * @author RaelH
 */
@Path("/cars")
public class CarroService {
    
   
    CarroController   controller = new CarroController();
    
    
    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public List <Carro> getListaCarros() throws ClassNotFoundException{   
        return controller.getListCarro();    
    }
   
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response findCarroJson(@PathParam("id") int id){
        try {
            return Response.ok((Object)controller.getCarro(id)).build();
        }catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
   
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("")
    public Response saveJson(Carro obj){
        try {
            System.out.println(obj.toString());
            obj.setId(controller.getListCarro().size() );
            controller.addCarro(obj);
     
        return Response.ok((Object)obj).build();
        }catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
    
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response updateJson(@PathParam("id") int id,Carro obj){
        try {
            System.out.println(obj.toString());
            controller.update(id, obj);
     
        return Response.ok((Object)obj).build();
        }catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response delete(@PathParam("id") int id) throws ClassNotFoundException {
       
        if(controller.delete(id)){
            return Response.ok().build();
        }else{
            return Response.serverError().build();
        }
        
    }
    
}
