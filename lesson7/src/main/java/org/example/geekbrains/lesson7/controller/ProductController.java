package org.example.geekbrains.lesson7.controller;

import org.example.geekbrains.lesson7.dao.ProductDao;
import org.example.geekbrains.lesson7.domain.Product;
import org.example.geekbrains.lesson7.dto.EntityNotFoundResponse;
import org.example.geekbrains.lesson7.dto.ProductDto;
import org.example.geekbrains.lesson7.exception.EntityNotFoundException;
import org.example.geekbrains.lesson7.service.product.ProductService;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductDao productDao;
    private List<Product> products;
    private final ProductService service;

    public ProductController(ProductDao productDao, ProductService service) {
        this.productDao = productDao;
        this.service = service;
    }

    @ModelAttribute("products")
    public List<Product> populateProducts(){
        return productDao.findAll ();
    }

    // http://localhost:8090/app/products - GET
    @RequestMapping(method = RequestMethod.GET)
    @GetMapping("/list")
    public String list(Model model){
        products = productDao.findAll ();
        model.addAttribute("products", products);
        return "list";
    }
//
//    // http://localhost:8090/app/products/page - GET
//    @RequestMapping(method = RequestMethod.GET)
//    @GetMapping("/page")
//    public String page(Model model){
//        Pageable pageable = PageRequest.of(0, 5, Sort.by("name").ascending());
//        Page<Product> page = service.getProductJpaDAO ().findAll(pageable);
////      products = service.getProductJpaDAO ().findByName("name", pageable);
////      page.get ().collect(Collectors.toList()).contains ();
////      model.addAttribute("products", products);
//        model.addAttribute (page);
//        return "page";
//    }

    // http://localhost:8090/app/products?id=1 - GET
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String getByIdLink(Model model, @RequestParam Long id){
        checkById(id);
        ProductDto byId = service.findById (id);
        model.addAttribute("product",
                byId == null ? new Product(): byId);
        return "product";
    }

    // http://localhost:8090/app/products/1 - GET
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getById(Model model, @PathVariable("id") Long id){
        checkById(id);
        List<Product> byId = productDao.findAllById (id);
        model.addAttribute("product",
                byId == null ? new Product(): byId.get (0));
        return "product";
    }

    // http://localhost:8090/app/products/1/price - GET
    @RequestMapping(value = "/{id}/price", method = RequestMethod.GET)
    @ResponseBody
    public String apiPrice(@PathVariable Long id){
        checkById(id);
        List<Product> byId = productDao.findAllById (id);
        return String.valueOf(byId == null ? null : byId.get (0).getPrice());
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
        productDao.save(savedProduct);
        System.out.println(savedProduct);
        return "redirect:/products/" + savedProduct.getId();
    }

    // http://localhost:8090/app/products/update?id=3 - GET
    @GetMapping("/update")
    public String getFormUpdateProduct(Model model,@RequestParam(name = "id") long id){
        Product byId = service.getById (id);
        model.addAttribute("product",
                byId == null ? new ProductDto(): byId);
        return "update-product";
    }

    // http://localhost:8090/app/products/update - POST
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateProduct(Product updateProduct){
        service.update (updateProduct);
        System.out.println(updateProduct);
        return "redirect:/products/" + updateProduct.getId();
    }

    // http://localhost:8090/app/products/any
    @RequestMapping(value = "any")
    @ResponseBody
    public String anyRequest(){
        return "any request " + UUID.randomUUID().toString();
    }

    // http://localhost:8090/app/products/like?name=Product_7 {name:"asd"}
    @GetMapping("/like")
    public String filterByName(Model model,
                               @RequestParam String name){
        List<Product> products = productDao.findAllByNameLike (name);
        model.addAttribute("products", products);
        return "list";
    }

    // http://localhost:8090/app/products?priceFrom=500&priceTo=700
    @GetMapping(params = {"priceFrom", "priceTo"})
    public String productsByPrice(Model model,
                                  @RequestParam double priceFrom,
                                  @RequestParam double priceTo){
        List<Product> products = productDao.findAllByPriceBetween (priceFrom, priceTo);
        model.addAttribute("products", products);
        return "list";
    }

    // http://localhost:8090/app/products/filter?priceFrom=500&priceTo=700
    @GetMapping("/filter")
    public String filterByPrice(Model model,
                                @RequestParam double priceFrom,
                                @RequestParam(required = false) Double priceTo){
        List<Product> products = productDao.findAllByPriceBetween (priceFrom, priceTo == null ? Double.MAX_VALUE : priceTo);
        model.addAttribute("products", products);
        return "list";
    }

    // http://localhost:8090/app/products/delete?id=1
    @RequestMapping(value = "/delete")
    public String removeById(Model model,
                             @RequestParam(name = "id") long id){
        checkById(id);
        products.remove (service.findById (id));
        productDao.deleteById (id);
        System.out.println("Удален продукт с id" + id);
        model.addAttribute("products", products);
        return "redirect:/products";
    }

    // http://localhost:8090/app/products/maxupprice - GET
    @RequestMapping("/maxupprice")
    public String filterByMaxPriceProduct(Model model){
        List<Product> products = productDao.findAll (Sort.by("price").descending ());
        model.addAttribute("products", products);
        return "list";
    }

    // http://localhost:8090/app/products/minupprice - GET
    @RequestMapping("/minupprice")
    public String filterByMinPriceProduct(Model model){
        List<Product> products = productDao.findAll (Sort.by("price").ascending());
        model.addAttribute("products", products);
        return "list";
    }

    // http://localhost:8090/app/products/maxprice - GET
    @RequestMapping("/maxprice")
    public String maxPriceProduct(Model model){
        Product maxProduct = productDao.findAll (Sort.by("price").descending ()).get(0);
        model.addAttribute("product",
                maxProduct == null ? new Product(): maxProduct);
        return "product";
    }

    // http://localhost:8090/app/products/minprice - GET
    @RequestMapping("/minprice")
    public String minPriceProduct(Model model){
        Product minProduct = productDao.findAll (Sort.by("price").ascending()).get(0);
        model.addAttribute("product",
                minProduct == null ? new Product(): minProduct);
        return "product";
    }

    private void checkById(@PathVariable Long id) {
        if(!productDao.existsById(id)){
            throw new EntityNotFoundException("Product", id, "Product not found");
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
