package com.example.boot.aop.dao;

import com.example.boot.aop.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserDaoImpl implements UserDao {
    @Override
    public User getUserById(Integer id) {
        User user1 = new User(1, "zxx1", 11, "2001");
        User user2 = new User(2, "zxx2", 12, "2002");
        User user3 = new User(3, "zxx2", 13, "2003");
        User user4 = new User(4, "zxx3", 14, "2004");
        User user5 = new User(5, "zxx4", 15, "2005");

        if (1 == id) {
            return user1;
        }
        if (2 == id) {
            return user2;
        }
        if (3 == id) {
            return user3;
        }
        if (4 == id) {
            return user4;
        }
        if (5 == id) {
            return user5;
        }
        return null;


    }
}
