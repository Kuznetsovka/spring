package org.example.geekbrains.lesson7.dto;

import java.util.List;

public class OrderDto {

    private Long id;
    private List<ProductDto> orderedProducts;
    private UserDto userDto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ProductDto> getOrderedProducts() {
        return orderedProducts;
    }

    public void setOrderedProducts(List<ProductDto> orderedProducts) {
        this.orderedProducts = orderedProducts;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }
}
