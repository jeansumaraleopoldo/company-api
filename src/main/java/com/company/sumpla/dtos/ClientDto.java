package com.company.sumpla.dtos;

import com.company.sumpla.model.BaseBuilder;
import com.company.sumpla.model.Client;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class ClientDto implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Long id;
    private String name;
    private Long cpf;

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public Long getCpf() {
        return cpf;
    }

    private void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public static class Builder implements BaseBuilder<ClientDto> {

        private ClientDto entity;

        public Builder(ClientDto entity) {
            this.entity = entity;
        }

        public static Builder create() {
            return new Builder(new ClientDto());
        }

        public static Builder from(ClientDto entity) {
            return new Builder(entity);
        }

        public Builder id(Long id) {
            this.entity.setId(id);
            return this;
        }

        public Builder name(String name) {
            this.entity.setName(name);
            return this;
        }

        public Builder cpf(Long cpf){
            this.entity.setCpf(cpf);
            return this;
        }

        @Override
        public ClientDto build() {
            return this.entity;
        }
    }

    @Component
    public static class RepresentationBuilder implements BaseRepresentationBuilder<Client, ClientDto, Client.Builder> {

        @Override
        public ClientDto toRepresentation(Client entity) {
            return ClientDto.Builder.create()
                    .id(entity.getId())
                    .name(entity.getName())
                    .cpf(entity.getCpf())
                    .build();
        }

        @Override
        public Client fromRepresentation(ClientDto representation, Client.Builder builder) {
            return builder
                    .name(representation.getName())
                    .cpf(representation.getCpf())
                    .build();
        }
    }
}
