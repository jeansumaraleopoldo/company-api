package com.company.sumpla.dtos;

import com.company.sumpla.model.BaseBuilder;
import com.company.sumpla.model.Provider;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class ProviderDto implements Serializable {
    
    private static final Long serialVersionUID = 1L;

    private Long id;
    private String name;
    private Long cnpj;

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

    public Long getCnpj() {
        return cnpj;
    }

    private void setCnpj(Long cnpj) {
        this.cnpj = cnpj;
    }

    public static class Builder implements BaseBuilder<ProviderDto> {

        private ProviderDto entity;

        public Builder(ProviderDto entity) {
            this.entity = entity;
        }

        public static Builder create() {
            return new Builder(new ProviderDto());
        }

        public static Builder from(ProviderDto entity) {
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

        public Builder cnpj(Long cnpj){
            this.entity.setCnpj(cnpj);
            return this;
        }

        @Override
        public ProviderDto build() {
            return this.entity;
        }
    }

    @Component
    public static class RepresentationBuilder implements BaseRepresentationBuilder<Provider, ProviderDto, Provider.Builder> {

        @Override
        public ProviderDto toRepresentation(Provider entity) {
            return ProviderDto.Builder.create()
                    .id(entity.getId())
                    .name(entity.getName())
                    .cnpj(entity.getCnpj())
                    .build();
        }

        @Override
        public Provider fromRepresentation(ProviderDto representation, Provider.Builder builder) {
            return builder
                    .name(representation.getName())
                    .cnpj(representation.getCnpj())
                    .build();
        }
    }
}
