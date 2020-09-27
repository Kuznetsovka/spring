package org.example.geekbrains.lesson7.dao;

import org.example.geekbrains.lesson7.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDao extends JpaRepository<Order, Long> {
}
