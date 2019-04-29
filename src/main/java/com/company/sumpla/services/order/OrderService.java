package com.company.sumpla.services.order;

import com.company.sumpla.model.Order;

import java.util.List;

public interface OrderService {

    public List<Order> getOrders();

    public Order findById(Long id);

    public Order saveOrder(Order order);

    public void deleteById(Long id);
}
