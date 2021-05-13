package com.chong.service;

import com.chong.dao.UserDao;
import com.chong.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao usertDao;


    public void saveUser(User user) {
        usertDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return usertDao.findByUsernameAndPassword(user);
    }

    @Override
    public User findByName(String name) {
        return usertDao.findByName(name);
    }

}
