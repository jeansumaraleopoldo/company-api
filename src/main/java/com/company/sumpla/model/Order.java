package com.company.sumpla.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Client client;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public static class Builder implements BaseBuilder<Order> {

        private Order entity;

        public Builder(Order entity) {
            this.entity = entity;
        }

        public static Builder create() {
            return new Builder(new Order());
        }

        public static Builder from(Order entity) {
            return new Builder(entity);
        }

        public Builder client(Client client) {
            this.entity.setClient(client);
            return this;
        }

        public Builder products(List<Product> products) {
            this.entity.setProducts(products);
            return this;
        }

        @Override
        public Order build() {
            return this.entity;
        }
    }
}
