package org.example.geekbrains.lesson6.controller;

import org.example.geekbrains.lesson6.InitData;
import org.example.geekbrains.lesson6.domain.User;
import org.example.geekbrains.lesson6.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.UUID;

@Controller
public class IndexController {
    private final ProductServiceImpl service;
    List<User> userList = InitData.getUsers ();

    @Autowired
    public IndexController(ProductServiceImpl service) {
        this.service = service;
        init();
    }

    @ModelAttribute("users")
    public List<User> populateUsers(){
        return userList;
    }


    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("message", "My message plus random UUID 1-> " + UUID.randomUUID());
        model.addAttribute("title", "Spring Level one");
        model.addAttribute("user", new User("Vasiliy"));
        return "index";
    }


    @GetMapping("/users")
    public String userList(Model model){
        model.addAttribute("user", new User());
        return "userList";
    }

    @PostMapping("/users")
    public String addUser(User userForm){
        System.out.println("Request contains user -> " + userForm.toString());
        return "redirect:/users";
    }

    private void init() {
        for (User user : userList) {
            System.out.println(user);
            service.saveAndSet (user);
        }
    }

}
