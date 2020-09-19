package org.example.geekbrains.lesson5.controller;

import org.example.geekbrains.lesson5.InitData;
import org.example.geekbrains.lesson5.domain.Product;
import org.example.geekbrains.lesson5.domain.User;
import org.example.geekbrains.lesson5.service.ProductServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/products")
public class ProductController {
    List<Product> products = InitData.getProducts ();
    private final ProductServiceImpl service;

    public ProductController(ProductServiceImpl service) {
        this.service = service;
        init();
    }

    @ModelAttribute("products")
    public List<Product> populateProducts(){
        return products;
    }

    private void init() {
        for (Product product : products) {
            System.out.println(product);
            service.saveAndSet (product);
        }
    }

    // http://localhost:8090/app/products - GET
    @RequestMapping(method = RequestMethod.GET)
    @GetMapping("/list")
    public String list(Model model){
        products = service.getProductJpaDAOImpl ().findAll ();
        model.addAttribute("products", products);
        return "list";
    }

    // http://localhost:8090/app/products/1 - GET
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getById(Model model, @PathVariable("id") Long id){
        Product byId = service.getProductJpaDAOImpl ().findById (id);
        model.addAttribute("product",
                byId == null ? new Product(): byId);
        return "product";
    }

    // http://localhost:8090/app/products/1/price - GET
    @RequestMapping(value = "/{id}/price", method = RequestMethod.GET)
    @ResponseBody
    public String apiPrice(@PathVariable Long id){
        Product byId = service.getProductJpaDAOImpl ().findById (id);
        return String.valueOf(byId == null ? null : byId.getPrice());
    }

    // http://localhost:8090/app/products/new - GET
    @GetMapping("/new")
    public String getFormNewProduct(Model model){
        model.addAttribute("product", new Product());
        return "new-product";
    }

    // http://localhost:8090/app/products/new - POST
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String addNewProduct(Product savedProduct){
        service.getProductJpaDAOImpl ().save(savedProduct);
        System.out.println(savedProduct);
        return "redirect:/products/" + savedProduct.getId();
    }

    // http://localhost:8090/app/products/any
    @RequestMapping(value = "any")
    @ResponseBody
    public String anyRequest(){
        return "any request " + UUID.randomUUID().toString();
    }

    // http://localhost:8090/app/products?price_from=35.4&priceTo=3
    @GetMapping(params = {"price_from", "priceTo"})
    public String productsByPrice(Model model,
                                  @RequestParam(name = "price_from") double priceFrom,
                                  @RequestParam double priceTo){
        List<Product> products = service.getProductJpaDAO ().findAllByPriceBetween (priceFrom, priceTo);
        //List<Product> products = service.getProductJpaDAOImpl ().findAllBetween(priceFrom, priceTo);
        model.addAttribute("products", products);
        return "list";
    }

    // http://localhost:8090/app/products/filter?price_from=35.4&priceTo=3
    @GetMapping("/filter")
    public String filterByPrice(Model model,
                                @RequestParam(name = "price_from") double priceFrom,
                                @RequestParam(required = false) Double priceTo){
        List<Product> products = service.getProductJpaDAOImpl ().findAllByIdBetween(priceFrom, priceTo == null ? Double.MAX_VALUE : priceTo);
        model.addAttribute("products", products);
        return "list";
    }

    // http://localhost:8090/app/products/delete?id=1
    @RequestMapping(value = "/delete")
    public String removeById(Model model,
                             @RequestParam(name = "id") long id){
        products.remove (service.findById (id));
        service.delete (id);
        System.out.println("Удален продукт с id" + id);
        model.addAttribute("products", products);
        return "redirect:/products/";
    }

    // http://localhost:8090/app/products/filter?name=asd
    @RequestMapping("/filter")
    public String filterByTitle(Model model,
                                @RequestParam(name = "name") String name){
        List<Product> products = service.getProductJpaDAOImpl ().findByName (name);
        model.addAttribute("products", products);
        return "list";
    }

    // http://localhost:8090/app/products/maxprice - GET
    @RequestMapping("/maxprice")
    public String maxPriceProduct(Model model){
        Product maxProduct = service.getProductJpaDAOImpl ().findMaxPrice ();
        model.addAttribute("product", maxProduct == null ? new Product(): maxProduct);
        return "list";
    }

    // http://localhost:8090/app/products/minprice - GET
    @RequestMapping("/minprice")
    public String minPriceProduct(Model model){
        Product minProduct = service.getProductJpaDAOImpl ().findMinPrice ();
        model.addAttribute("product", minProduct == null ? new Product(): minProduct);
        return "list";
    }

    @PostMapping("/minprice")
    public String minPriceProduct(User userForm){
        List<Product> products = service.getProductJpaDAOImpl ().filterMinPrice ();
        return "list";
    }

    @PostMapping("/maxprice")
    public String maxPriceProduct(User userForm){
        List<Product> products = service.getProductJpaDAOImpl ().filterMaxPrice ();
        return "list";
    }

}
