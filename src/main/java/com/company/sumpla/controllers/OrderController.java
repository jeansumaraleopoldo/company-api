package com.company.sumpla.controllers;

import com.company.sumpla.dtos.OrderDto;
import com.company.sumpla.model.Order;
import com.company.sumpla.services.order.OrderService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class OrderController {
    
    private final OrderService orderService;
    private final OrderDto.RepresentationBuilder orderRB;

    public OrderController(OrderService orderService, OrderDto.RepresentationBuilder orderRB) {
        this.orderService = orderService;
        this.orderRB = orderRB;
    }

    @GetMapping("orders")
    public ResponseEntity getOrders(){
        List<OrderDto> orders = orderService.getOrders().stream()
                .map(orderRB::toRepresentation)
                .collect(Collectors.toList());
        return ResponseEntity.ok(orders);
    }

    @GetMapping("orders/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Long id) {
        Order order = orderService.findById(id);
        OrderDto providerDto = orderRB.toRepresentation(order);
        return ResponseEntity.ok(providerDto);
    }

    @PostMapping("orders")
    public ResponseEntity addOrder(@RequestBody OrderDto representation) {
        Order fromRepresentation = orderRB.fromRepresentation(representation, Order.Builder.create());
        Order savedOrder = orderService.saveOrder(fromRepresentation);

        final URI location = ServletUriComponentsBuilder
                .fromCurrentServletMapping().path("/orders/{id}").build()
                .expand(savedOrder.getId()).toUri();

        final HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping("orders/{id}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable Long id, @RequestBody OrderDto representation) {
        Order order = orderService.findById(id);
        Order.Builder builder = Order.Builder.from(order);
        Order fromRepresentation = orderRB.fromRepresentation(representation, builder);
        Order savedOrder = orderService.saveOrder(fromRepresentation);
        OrderDto providerDto = orderRB.toRepresentation(savedOrder);
        return ResponseEntity.ok(providerDto);
    }

    @DeleteMapping("orders/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        orderService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
