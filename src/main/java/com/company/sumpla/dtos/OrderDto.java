package com.company.sumpla.dtos;

import com.company.sumpla.model.BaseBuilder;
import com.company.sumpla.model.Order;
import com.company.sumpla.model.Product;
import com.company.sumpla.repositories.ClientRepository;
import com.company.sumpla.repositories.ProductRepository;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderDto implements Serializable {

    private Long id;
    private ClientDto client;
    private List<ProductDto> products = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClientDto getClient() {
        return client;
    }

    public void setClient(ClientDto client) {
        this.client = client;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }

    public static class Builder implements BaseBuilder<OrderDto> {

        private OrderDto entity;

        public Builder(OrderDto entity) {
            this.entity = entity;
        }

        public static Builder create() {
            return new Builder(new OrderDto());
        }

        public static Builder from(OrderDto entity) {
            return new Builder(entity);
        }

        public Builder id(Long id) {
            this.entity.setId(id);
            return this;
        }

        public Builder client(ClientDto client) {
            this.entity.setClient(client);
            return this;
        }

        public Builder products(List<ProductDto> products){
            this.entity.setProducts(products);
            return this;
        }

        @Override
        public OrderDto build() {
            return this.entity;
        }
    }

    @Component
    public static class RepresentationBuilder implements BaseRepresentationBuilder<Order, OrderDto, Order.Builder> {

        private final ClientDto.RepresentationBuilder clientRB;
        private final ProductDto.RepresentationBuilder productRB;
        private final ClientRepository clientRepository;
        private final ProductRepository productRepository;

        public RepresentationBuilder(ClientDto.RepresentationBuilder clientRB,
                                     ProductDto.RepresentationBuilder productRB,
                                     ClientRepository clientRepository,
                                     ProductRepository productRepository) {
            this.clientRB = clientRB;
            this.productRB = productRB;
            this.clientRepository = clientRepository;
            this.productRepository = productRepository;
        }

        @Override
        public OrderDto toRepresentation(Order entity) {
            return OrderDto.Builder.create()
                .id(entity.getId())
                .client(clientRB.toRepresentation(entity.getClient()))
                .products(entity.getProducts().stream().map(productRB::toRepresentation).collect(Collectors.toList()))
                .build();
        }

        @Override
        public Order fromRepresentation(OrderDto representation, Order.Builder builder) {
            Iterable<Long> productsIds = representation.getProducts().stream().map(ProductDto::getId).collect(Collectors.toList());

            ArrayList<Product> products = new ArrayList<>();
            productRepository.findAllById(productsIds).forEach(products::add);

            return builder
                .client(clientRepository.findById(representation.getClient().getId()).orElse(null))
                .products(products)
                .build();
        }
    }
}
