package org.example.geekbrains.lesson7.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.example.geekbrains.lesson7.domain.Order;
import org.example.geekbrains.lesson7.dto.OrderDto;
import org.mapstruct.factory.Mappers;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-09-29T23:16:24+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_231 (Oracle Corporation)"
)
public class OrderMapperImpl implements OrderMapper {

    private final ProductMapper productMapper = Mappers.getMapper( ProductMapper.class );

    @Override
    public Order toOrder(OrderDto dto) {
        if ( dto == null ) {
            return null;
        }

        Order order = new Order();

        order.setProducts( productMapper.toProductList( dto.getOrderedProducts() ) );
        order.setId( dto.getId() );

        return order;
    }

    @Override
    public List<Order> toOrderList(List<OrderDto> orders) {
        if ( orders == null ) {
            return null;
        }

        List<Order> list = new ArrayList<Order>( orders.size() );
        for ( OrderDto orderDto : orders ) {
            list.add( toOrder( orderDto ) );
        }

        return list;
    }

    @Override
    public OrderDto fromOrder(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderDto orderDto = new OrderDto();

        orderDto.setOrderedProducts( productMapper.fromProductList( order.getProducts() ) );
        orderDto.setId( order.getId() );

        return orderDto;
    }

    @Override
    public List<OrderDto> fromOrderList(List<Order> orders) {
        if ( orders == null ) {
            return null;
        }

        List<OrderDto> list = new ArrayList<OrderDto>( orders.size() );
        for ( Order order : orders ) {
            list.add( fromOrder( order ) );
        }

        return list;
    }
}
