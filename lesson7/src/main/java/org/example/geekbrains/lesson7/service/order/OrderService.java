package org.example.geekbrains.lesson7.service.order;

import org.example.geekbrains.lesson7.domain.Order;
import org.example.geekbrains.lesson7.dto.OrderDto;

import java.util.List;

public interface OrderService {
    Order getById(Long id);

    OrderDto findById(Long id);
    List<OrderDto> findAll();
    OrderDto save(OrderDto dto);
}
