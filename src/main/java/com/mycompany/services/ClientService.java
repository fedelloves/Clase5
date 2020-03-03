package com.mycompany.services;

import com.mycompany.model.Client;
import com.mycompany.repository.ClientRepository;
import java.util.List;

public class ClientService {
    
    private ClientRepository repository = new ClientRepository();

    public List<Client> getClients() {
        return repository.getClients();
    }
    
}
