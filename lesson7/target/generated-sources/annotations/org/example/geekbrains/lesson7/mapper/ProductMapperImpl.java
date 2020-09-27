package org.example.geekbrains.lesson7.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.example.geekbrains.lesson7.domain.Product;
import org.example.geekbrains.lesson7.dto.ProductDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-09-27T23:15:18+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_231 (Oracle Corporation)"
)
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product toProduct(ProductDto dto) {
        if ( dto == null ) {
            return null;
        }

        Product product = new Product();

        product.setPrice( dto.getPrice() );
        product.setName( dto.getName() );
        product.setId( dto.getId() );

        return product;
    }

    @Override
    public List<Product> toProductList(List<ProductDto> products) {
        if ( products == null ) {
            return null;
        }

        List<Product> list = new ArrayList<Product>( products.size() );
        for ( ProductDto productDto : products ) {
            list.add( toProduct( productDto ) );
        }

        return list;
    }

    @Override
    public ProductDto fromProduct(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDto productDto = new ProductDto();

        productDto.setId( product.getId() );
        productDto.setPrice( product.getPrice() );
        productDto.setName( product.getName() );

        return productDto;
    }

    @Override
    public List<ProductDto> fromProductList(List<Product> products) {
        if ( products == null ) {
            return null;
        }

        List<ProductDto> list = new ArrayList<ProductDto>( products.size() );
        for ( Product product : products ) {
            list.add( fromProduct( product ) );
        }

        return list;
    }
}
