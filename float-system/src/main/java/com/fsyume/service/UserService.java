package com.fsyume.service;

import com.fsyume.eneity.User;

import java.util.List;

public interface UserService {

    // 查询所有用户
    List<User> findAllUser();

    // 用户登录
    User login(User user);

    // 用户注册
    void UserRegistration(User user);

    // 查找重复用户
    Boolean findUsernameRepeat(String username);

    // 通过用户名查找用户
    User findByUsername(String username);

    // 通过uid查找用户
    User getUserByUid(int uid);

    // 通过uid删除用户
    void deleteUserByUid(int uid);

    // 用户信息更新
    void updateUser(User user);
}
