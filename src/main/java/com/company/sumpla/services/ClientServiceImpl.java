package com.company.sumpla.services;

import com.company.sumpla.model.Client;
import com.company.sumpla.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> getClients() {
        ArrayList<Client> list = new ArrayList<>();
        clientRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }
}
