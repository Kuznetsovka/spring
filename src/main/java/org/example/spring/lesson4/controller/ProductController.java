package org.example.spring.lesson4.controller;

import org.example.spring.lesson4.domain.Product;
import org.example.spring.lesson4.repository.ProductJpqlDAOImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductJpqlDAOImpl productService;

    public ProductController(ProductJpqlDAOImpl productService) {
        this.productService = productService;
    }

    // http://localhost:8080/app/products - GET
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model){
        List<Product> products = productService.findAll ();
        model.addAttribute("products", products);
        return "list";
    }

    // http://localhost:8080/app/products/1 - GET
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getById(Model model, @PathVariable("id") Long id){
        Product byId = productService.findById (id);
        model.addAttribute("product",
                byId == null ? new Product(): byId);
        return "product";
    }

    // http://localhost:8080/app/products/1/price - GET
    @RequestMapping(value = "/{id}/price", method = RequestMethod.GET)
    @ResponseBody
    public String apiPrice(@PathVariable Long id){
        Product byId = productService.findById (id);
        return String.valueOf(byId == null ? null : byId.getPrice());
    }

    // http://localhost:8080/app/products/new - GET
    @GetMapping("/new")
    public String getFormNewProduct(Model model){
        model.addAttribute("product", new Product());
        return "new-product";
    }

    // http://localhost:8080/app/products/new - POST
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String addNewProduct(Product savedProduct){
        productService.save(savedProduct);
        System.out.println(savedProduct);
        return "redirect:/products/" + savedProduct.getId();
    }

    // http://localhost:8080/app/products/any
    @RequestMapping(value = "any")
    @ResponseBody
    public String anyRequest(){
        return "any request " + UUID.randomUUID().toString();
    }

    // http://localhost:8080/app/products?price_from=35.4&priceTo=3
    @GetMapping(params = {"price_from", "priceTo"})
    public String productsByPrice(Model model,
                                  @RequestParam(name = "price_from") double priceFrom,
                                  @RequestParam double priceTo){
        List<Product> products = productService.findAllByIdBetween(priceFrom, priceTo);
        model.addAttribute("products", products);
        return "list";
    }

    // http://localhost:8080/app/products/filter?price_from=35.4&priceTo=3
    @GetMapping("/filter")
    public String filterByPrice(Model model,
                                @RequestParam(name = "price_from") double priceFrom,
                                @RequestParam(required = false) Double priceTo){
        List<Product> products = productService.findAllByIdBetween(priceFrom, priceTo == null ? Double.MAX_VALUE : priceTo);
        model.addAttribute("products", products);
        return "list";
    }

    // http://localhost:8080/app/products/delete?id=1
    @RequestMapping(value = "/delete")
    public void removeById(Model model,
                             @RequestParam(name = "id") long id){
        productService.delete (productService.findById (id));
        System.out.println("Удален продукт с id" + id);
        list(model);
    }

    // http://localhost:8080/app/products/filter?name=asd
    @RequestMapping("/filter")
    public String filterByTitle(Model model,
                                @RequestParam(name = "name") String name){
        List<Product> products = productService.findByName (name);
        model.addAttribute("products", products);
        return "list";
    }

    // http://localhost:8080/app/products/maxprice - GET
    @RequestMapping(value="/maxprice", method = RequestMethod.GET)
    public String maxPriceProduct(Model model){
        Product maxProduct = productService.findMaxPrice ();
        model.addAttribute("product", maxProduct == null ? new Product(): maxProduct);
        return "product";
    }

    // http://localhost:8080/app/products/minprice - GET
    @RequestMapping(value="/minprice", method = RequestMethod.GET)
    public String minPriceProduct(Model model){
        Product minProduct = productService.findMaxPrice ();
        model.addAttribute("product", minProduct == null ? new Product(): minProduct);
        return "product";
    }

}
