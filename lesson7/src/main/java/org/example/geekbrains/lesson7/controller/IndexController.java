package org.example.geekbrains.lesson7.controller;

import org.example.geekbrains.lesson7.bootstrap.InitData;
import org.example.geekbrains.lesson7.domain.User;
import org.example.geekbrains.lesson7.service.user.UserService;
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
    private final UserService service;
    List<User> userList = InitData.getUsers ();

    @Autowired
    public IndexController(UserService service) {
        this.service = service;
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

}
