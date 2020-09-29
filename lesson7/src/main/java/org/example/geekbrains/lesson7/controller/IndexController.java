package org.example.geekbrains.lesson7.controller;

import org.example.geekbrains.lesson7.bootstrap.InitData;
import org.example.geekbrains.lesson7.domain.User;
import org.example.geekbrains.lesson7.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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


    @RequestMapping(value = {"","/"})
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/login")
    public String loginPage(){
        return "login";
    }

}
