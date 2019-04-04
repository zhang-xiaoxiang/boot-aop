package com.example.boot.aop.controller;

import com.example.boot.aop.entity.User;
import com.example.boot.aop.serivce.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserCtr {
    @Autowired
    private UserService userService;

    @RequestMapping("/getUser")
    public User getUser(Integer id) {
        User user = userService.getUser(id);
        System.out.println("-----------UserCtr---------" + id);
        return user;
    }
}
