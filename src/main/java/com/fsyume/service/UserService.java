package com.fsyume.service;

import com.fsyume.eneity.User;

import java.util.List;

public interface UserService {

    // 查询所有用户
    List<User> findAllUser();

    // 用户登录
    User login(User user);
}
