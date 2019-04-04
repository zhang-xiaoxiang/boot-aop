package com.example.boot.aop.serviceimpl;

import com.example.boot.aop.dao.UserDao;
import com.example.boot.aop.entity.User;
import com.example.boot.aop.serivce.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
@Autowired
private UserDao userDao;

    @Override
    public User getUser(Integer id) {
        User user = userDao.getUserById(id);
        System.out.println("---------UserServiceImpl--------"+user);
        return user;
    }
}
