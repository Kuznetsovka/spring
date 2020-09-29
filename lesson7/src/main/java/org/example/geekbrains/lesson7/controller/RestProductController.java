package org.example.geekbrains.lesson7.controller;

import org.example.geekbrains.lesson7.dao.ProductDao;
import org.example.geekbrains.lesson7.domain.Product;
import org.example.geekbrains.lesson7.dto.EntityNotFoundResponse;
import org.example.geekbrains.lesson7.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest-products")
public class RestProductController {

    private final ProductDao productDao;

    public RestProductController(ProductDao productDao) {
        this.productDao = productDao;
    }

    @GetMapping
    public List<Product> getAll(){
        return productDao.findAll();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id){
        checkById(id);
        return productDao.findById(id).orElse(null);
    }

    @PostMapping("/")
    public Product addProduct(@RequestBody Product product){
        System.out.println(product);
        return productDao.save(product);
    }

    @PutMapping("/{productId}")
    public Product updateProduct(@RequestBody Product product, @PathVariable(name = "productId") Long id){
        product.setId(id);
        System.out.println(product);
        return productDao.save(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
        checkById(id);
        productDao.deleteById(id);
    }

    private void checkById(@PathVariable Long id) {
        if(!productDao.existsById(id)){
            throw new EntityNotFoundException ("Product", id, "Product not found");
        }
    }

    @ExceptionHandler
    public ResponseEntity<EntityNotFoundResponse> handleException(EntityNotFoundException ex){
        EntityNotFoundResponse response = new EntityNotFoundResponse();
        response.setEntityName(ex.getEntityName());
        response.setEntityId(ex.getEntityId());
        response.setMessage(ex.getMessage());
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }


}
