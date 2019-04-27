package com.company.sumpla.services.client;

import com.company.sumpla.model.Client;

import java.util.List;

public interface ClientService {

    public List<Client> getClients();

    public Client findById(Long id);

    public Client saveClient(Client client);

    public void deleteById(Long idClient);
}
