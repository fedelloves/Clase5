package com.mycompany.resources;

import com.mycompany.model.Client;
import com.mycompany.services.ClientService;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import java.util.List;
import java.util.ArrayList;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.Produces;

@Path("clients")
public class ClientResource {
    
    private ClientService service = new ClientService();

    @GET
    @Produces(value = {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Client> getClients() {
        
        return service.getClients();

    }
    
    @GET
    @Path("{id}")
    public Client getClient(@PathParam("id") int id, @HeaderParam("X-Custom") int header ){
        System.out.println("ID: " + id);
        System.out.println("Header: " + header);
        return service.getClient();
    }

}
