package com.chong.service;


import com.chong.domain.User;

import java.util.List;

public interface UserService {

    public void saveUser(User user);

    User login(User user);

    User findByName(String user);

}
