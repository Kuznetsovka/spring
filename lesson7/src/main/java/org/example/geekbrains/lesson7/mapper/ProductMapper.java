package org.example.geekbrains.lesson7.mapper;

import org.example.geekbrains.lesson7.domain.Product;
import org.example.geekbrains.lesson7.dto.ProductDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductMapper {
    ProductMapper MAPPER = Mappers.getMapper(ProductMapper.class);

    Product toProduct(ProductDto dto);

    List<Product> toProductList(List<ProductDto> products);

    @InheritInverseConfiguration
    ProductDto fromProduct(Product product);

    List<ProductDto> fromProductList(List<Product> products);

}
