package org.example.geekbrains.lesson7.service.order;

import org.example.geekbrains.lesson7.dao.OrderDao;
import org.example.geekbrains.lesson7.domain.Order;
import org.example.geekbrains.lesson7.dto.OrderDto;
import org.example.geekbrains.lesson7.mapper.OrderMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderMapper mapper = OrderMapper.MAPPER;

    private final OrderDao dao;

    public OrderServiceImpl(OrderDao dao) {
        this.dao = dao;
    }

    @Override
    public OrderDto findById(Long id) {
        return mapper.fromOrder(dao.getOne(id));
    }

    @Override
    public List<OrderDto> findAll() {
        return mapper.fromOrderList(dao.findAll());
    }

    @Override
    public OrderDto save(OrderDto dto) {
        Order entity = mapper.toOrder(dto);
        Order savedEntity = dao.save(entity);
        return mapper.fromOrder(savedEntity);
    }
}
