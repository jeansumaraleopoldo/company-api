package com.company.sumpla.controllers;
import com.company.sumpla.dtos.ClientDto;
import com.company.sumpla.services.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClientController {

    private final ClientService clientService;
    private final ClientDto.RepresentationBuilder clientRB;

    public ClientController(ClientService clientService, ClientDto.RepresentationBuilder clientRB) {
        this.clientService = clientService;
        this.clientRB = clientRB;
    }

    @GetMapping("clients")
    public ResponseEntity getClients(){
        return ResponseEntity.ok("Show");
    }

    /*@GetMapping("clients/{id}")
    public ResponseEntity<ClientDto> getClientById(@PathVariable Long id) {
        Client client = clientService.findById(id);
        ClientDto clientDto = clientRB.toRepresentation(client);
        return ResponseEntity.ok(clientDto);
    }

    @PostMapping("clients")
    public ResponseEntity addClient(@RequestBody ClientDto representation) {
        Client fromRepresentation = clientRB.fromRepresentation(representation, Client.Builder.create());
        Client savedClient = clientService.saveClient(fromRepresentation);

        final URI location = ServletUriComponentsBuilder
                .fromCurrentServletMapping().path("/clients/{id}").build()
                .expand(savedClient.getId()).toUri();

        final HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping("clients/{id}")
    public ResponseEntity<ClientDto> updateClient(@PathVariable Long id, ClientDto representation) {
        Client product = clientService.findById(id);
        Client.Builder builder = Client.Builder.from(product);
        Client fromRepresentation = clientRB.fromRepresentation(representation, builder);
        Client savedClient = clientService.saveClient(fromRepresentation);
        ClientDto clientDto = clientRB.toRepresentation(savedClient);
        return ResponseEntity.ok(clientDto);
    }

    @DeleteMapping("clients/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        clientService.deleteById(id);
        return ResponseEntity.noContent().build();
    }*/
}
