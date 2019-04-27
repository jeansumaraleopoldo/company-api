package com.company.sumpla.dtos;

import com.company.sumpla.model.BaseBuilder;
import com.company.sumpla.model.Product;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigDecimal;

@Component
public class ProductDto implements Serializable {
    private static final Long serialVersionUID = 1L;

    private Long id;
    private String description;
    private BigDecimal saleValue;
    private BigDecimal purchaseValue;

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getSaleValue() {
        return saleValue;
    }

    public void setSaleValue(BigDecimal saleValue) {
        this.saleValue = saleValue;
    }

    public BigDecimal getPurchaseValue() {
        return purchaseValue;
    }

    public void setPurchaseValue(BigDecimal purchaseValue) {
        this.purchaseValue = purchaseValue;
    }public static class Builder implements BaseBuilder<ProductDto> {

        private ProductDto entity;

        public Builder(ProductDto entity) {
            this.entity = entity;
        }

        public static Builder create() {
            return new Builder(new ProductDto());
        }

        public static Builder from(ProductDto entity) {
            return new Builder(entity);
        }

        public Builder id(Long id) {
            this.entity.setId(id);
            return this;
        }

        public Builder description(String description) {
            this.entity.setDescription(description);
            return this;
        }

        public Builder saleValue(BigDecimal saleValue){
            this.entity.setSaleValue(saleValue);
            return this;
        }

        public Builder purchaseValue(BigDecimal purchaseValue){
            this.entity.setPurchaseValue(purchaseValue);
            return this;
        }

        @Override
        public ProductDto build() {
            return this.entity;
        }
    }

    @Component
    public static class RepresentationBuilder implements BaseRepresentationBuilder<Product, ProductDto, Product.Builder> {

        @Override
        public ProductDto toRepresentation(Product entity) {
            return ProductDto.Builder.create()
                    .id(entity.getId())
                    .description(entity.getDescription())
                    .purchaseValue(entity.getPurchaseValue())
                    .saleValue(entity.getSaleValue())
                    .build();
        }

        @Override
        public Product fromRepresentation(ProductDto representation, Product.Builder builder) {
            return builder
                    .description(representation.getDescription())
                    .purchaseValue(representation.getPurchaseValue())
                    .saleValue(representation.getSaleValue())
                    .build();
        }
    }
}
