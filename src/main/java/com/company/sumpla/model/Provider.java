package com.company.sumpla.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Provider {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Long cnpj;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public Long getCnpj() {
        return cnpj;
    }

    private void setCnpj(Long cnpj) {
        this.cnpj = cnpj;
    }

    public static class Builder implements BaseBuilder<Provider> {

        private Provider entity;

        public Builder(Provider entity) {
            this.entity = entity;
        }

        public static Builder create() {
            return new Builder(new Provider());
        }

        public static Builder from(Provider entity) {
            return new Builder(entity);
        }

        public Builder name(String name) {
            this.entity.setName(name);
            return this;
        }

        public Builder cnpj(Long cnpj) {
            this.entity.setCnpj(cnpj);
            return this;
        }

        @Override
        public Provider build() {
            return this.entity;
        }
    }
}
