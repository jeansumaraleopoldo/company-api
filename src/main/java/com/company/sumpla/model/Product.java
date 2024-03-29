package com.company.sumpla.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "PRODUCTS")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "SALE_VALUE")
    private BigDecimal saleValue;

    @Column(name = "PURCHASE_VALUE")
    private BigDecimal purchaseValue;

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
    }

    public Long getId() {
        return id;
    }

    public static class Builder implements BaseBuilder<Product> {

        private Product entity;

        public Builder(Product entity) {
            this.entity = entity;
        }

        public static Builder create() {
            return new Builder(new Product());
        }

        public static Builder from(Product entity) {
            return new Builder(entity);
        }

        public Builder description(String description) {
            this.entity.setDescription(description);
            return this;
        }

        public Builder saleValue(BigDecimal saleValue) {
            this.entity.setSaleValue(saleValue);
            return this;
        }

        public Builder purchaseValue(BigDecimal purchaseValue) {
            this.entity.setPurchaseValue(purchaseValue);
            return this;
        }

        @Override
        public Product build() {
            return this.entity;
        }
    }
}
