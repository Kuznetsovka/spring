package org.example.spring.lesson2.repository;

import org.example.spring.lesson2.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductRepoImpl {

    private Map<Long, Product> repo = new HashMap<> ();
    private long ind = 0;
    {
        repo.put(++ind, new Product(ind, "Cheese", 635.5,"http://localhost:8090/app/products/delete?id="+ ind));
        repo.put(++ind, new Product(ind, "Milk", 66.0,"http://localhost:8090/app/products/delete?id="+ ind));
        repo.put(++ind, new Product(ind, "Chocolate", 96.0,"http://localhost:8090/app/products/delete?id="+ ind));
        repo.put(++ind, new Product(ind, "Bread", 39.0,"http://localhost:8090/app/products/delete?id="+ ind));
        repo.put(++ind, new Product(ind, "Beer", 88.0,"http://localhost:8090/app/products/delete?id="+ ind));
    }

    public Product getById(Long id){
        return repo.get(id);
    }

    public List<Product> getAll(){
        return new ArrayList<> (repo.values());
    }

    public Product save(Product product){
        if(product.getId() == null){
            product.setId(++ind);
        }
        repo.put(product.getId(), product);
        return product;
    }

    public void remove(Long id){
        repo.remove(id);
    }

}
