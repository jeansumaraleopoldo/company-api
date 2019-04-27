package com.company.sumpla.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Long cpf;

    public Long getId() {
        return id;
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

    public static class Builder implements BaseBuilder<Client> {

        private Client entity;

        public Builder(Client entity) {
            this.entity = entity;
        }

        public static Builder create() {
            return new Builder(new Client());
        }

        public static Builder from(Client entity) {
            return new Builder(entity);
        }

        public Builder name(String name) {
            this.entity.setName(name);
            return this;
        }

        public Builder cpf(Long cpf) {
            this.entity.setCpf(cpf);
            return this;
        }

        @Override
        public Client build() {
            return this.entity;
        }
    }
}
