package com.company.sumpla.controllers;

import com.company.sumpla.dtos.ProviderDto;
import com.company.sumpla.model.Provider;
import com.company.sumpla.services.provider.ProviderService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProviderController {
    
    private final ProviderService providerService;
    private final ProviderDto.RepresentationBuilder providerRB;

    public ProviderController(ProviderService providerService, ProviderDto.RepresentationBuilder providerRB) {
        this.providerService = providerService;
        this.providerRB = providerRB;
    }

    @GetMapping("providers")
    public ResponseEntity getClients(){
        List<ProviderDto> providers = providerService.getProviders().stream()
                .map(providerRB::toRepresentation)
                .collect(Collectors.toList());
        return ResponseEntity.ok(providers);
    }

    @GetMapping("providers/{id}")
    public ResponseEntity<ProviderDto> getProviderById(@PathVariable Long id) {
        Provider provider = providerService.findById(id);
        ProviderDto providerDto = providerRB.toRepresentation(provider);
        return ResponseEntity.ok(providerDto);
    }

    @PostMapping("providers")
    public ResponseEntity addProvider(@RequestBody ProviderDto representation) {
        Provider fromRepresentation = providerRB.fromRepresentation(representation, Provider.Builder.create());
        Provider savedProvider = providerService.saveProvider(fromRepresentation);

        final URI location = ServletUriComponentsBuilder
                .fromCurrentServletMapping().path("/providers/{id}").build()
                .expand(savedProvider.getId()).toUri();

        final HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping("providers/{id}")
    public ResponseEntity<ProviderDto> updateProvider(@PathVariable Long id, @RequestBody ProviderDto representation) {
        Provider provider = providerService.findById(id);
        Provider.Builder builder = Provider.Builder.from(provider);
        Provider fromRepresentation = providerRB.fromRepresentation(representation, builder);
        Provider savedProvider = providerService.saveProvider(fromRepresentation);
        ProviderDto providerDto = providerRB.toRepresentation(savedProvider);
        return ResponseEntity.ok(providerDto);
    }

    @DeleteMapping("providers/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        providerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
