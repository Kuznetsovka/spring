package org.example.geekbrains.lesson5.controller;

import org.example.geekbrains.lesson5.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class IndexController {

    private List<User> userList = new ArrayList<>();

    {
        userList.add(new User("Ivan", "ivanov@email.ru", 30, new Date(1990, 1, 10)));
        userList.add(new User("Petr", "petrov@email.ru", 60, new Date(1993, 4, 15)));
        userList.add(new User("Alex", "romanov@email.ru", -200, new Date(2000, 11, 1)));
    }

    public IndexController() {
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
