package com.company.sumpla.services;

import com.company.sumpla.exceptions.NotFoundException;
import com.company.sumpla.model.Client;
import com.company.sumpla.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public Client findById(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        if (!client.isPresent()) {
            throw new NotFoundException("Entity Client not found for ID: " + id);
        }
        return client.get();
    }

    @Override
    public Client saveClient(Client entity) {
        Client client = Client.Builder.from(entity).build();
        return clientRepository.save(client);
    }

    @Override
    public void deleteById(Long idClient) {
        clientRepository.deleteById(idClient);
    }
}
