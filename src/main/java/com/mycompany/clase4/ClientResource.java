package com.mycompany.clase4;

import com.mycompany.model.Client;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import java.util.List;
import java.util.ArrayList;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.Produces;

@Path("clients")
        public class ClientResource {
    
    @GET
    @Produces(value = {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public List<Client> getClients(){
    
     List<Client> clients = new ArrayList<>();
     
          Client c1 = new Client();

          Client c2 = new Client();
          
              c1.setName("COSME");
              c1.setLastName("FULANO");
              c1.setAge(99);


              c2.setName("RAMON");
              c2.setLastName("PEREZ");
              c2.setAge(99);
              
              clients.add(c1);
              clients.add(c2);

              
    return clients;
    
}
  
}
