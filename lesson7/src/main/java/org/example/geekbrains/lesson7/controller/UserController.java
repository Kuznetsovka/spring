package org.example.geekbrains.lesson7.controller;

import org.example.geekbrains.lesson7.config.UserInfo;
import org.example.geekbrains.lesson7.dao.UserDao;
import org.example.geekbrains.lesson7.domain.User;
import org.example.geekbrains.lesson7.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserDao userDao;
    private List<User> users;
    private final UserService service;

    public UserController(UserService service, UserInfo userInfo, UserDao userDao) {
        this.service = service;
        this.userDao = userDao;
    }


    @ModelAttribute("users")
    public List<User> populateUsers(){
        return userDao.findAll ();
    }

    // http://localhost:8090/app/users - GET
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model){
        users = userDao.findAll ();
        model.addAttribute("users", users);
        return "userList";
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public String getById(Model model, @PathVariable Long id) {
        User byId = service.getById (id);
        model.addAttribute("user", byId == null ? new User() : byId);
        return "user";
    }

    @GetMapping("/")
    public String userList(Model model){
        model.addAttribute("user", new User());
        return "userList";
    }

    // http://localhost:8090/app/users/new - GET
    @GetMapping("/new")
    public String getFormNewProduct(Model model){
        model.addAttribute("user", new User ());
        return "new-user";
    }

    // http://localhost:8090/app/users/new - POST
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String addNewProduct(User savedUser){
        userDao.save(savedUser);
        System.out.println(savedUser);
        return "redirect:/users/" + savedUser.getId();
    }

//    // http://localhost:8090/app/users/update?id=3 - GET
//    @GetMapping("/update")
//    public String getFormUpdateProduct(Model model,@RequestParam(name = "id") long id){
//        UserDto byId = service.findById(id);
//        model.addAttribute("user",
//                byId == null ? new UserDto(): byId);
//        return "update-user";
//    }

    // http://localhost:8090/app/users/update - POST
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateProduct(User updateUser){
        service.update (updateUser);
        System.out.println(updateUser);
        return "redirect:/users/" + updateUser.getId();
    }

}
