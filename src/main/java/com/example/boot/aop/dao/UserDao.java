package com.example.boot.aop.dao;

import com.example.boot.aop.entity.User;
import org.springframework.stereotype.Component;

@Component
public interface UserDao {

    User getUserById(Integer id);

}
