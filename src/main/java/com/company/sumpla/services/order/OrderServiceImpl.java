package com.company.sumpla.services.order;

import com.company.sumpla.exceptions.NotFoundException;
import com.company.sumpla.model.Order;
import com.company.sumpla.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getOrders() {
        ArrayList<Order> list = new ArrayList<>();
        orderRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Order findById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (!order.isPresent()) {
            throw new NotFoundException("Entity Order not found for ID: " + id);
        }
        return order.get();
    }

    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }
}
