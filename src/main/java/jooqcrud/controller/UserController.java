package jooqcrud.controller;

import jooqcrud.service.UserService;
import org.example.tables.pojos.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value ="/")
    String hello(){
        return "Cool Yo Fool";
    }
    @GetMapping(value ="/list")
    List<Users> getUsers(){
        return userService.getUserList();
    }
    @PostMapping("/add")
    String insertUser(@RequestBody Users usersVO){
        return userService.insertUser(usersVO);
    }


}
