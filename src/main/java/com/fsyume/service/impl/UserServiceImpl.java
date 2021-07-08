package com.fsyume.service.impl;

import com.fsyume.dao.UserDao;
import com.fsyume.eneity.User;
import com.fsyume.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    /**
     * 查询所有用户
     *
     * @return
     */
    @Override
    public List<User> findAllUser() {
        return userDao.findAllUser();
    }

    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    @Override
    public User login(User user) {

        // 根据接受的用户名和密码查询数据库
        User login = userDao.getUsernameByName(user.getUsername());

        if (login.getPassword().equals(user.getPassword())) {
            return login;
        }

        throw new RuntimeException("登录失败，请检查用户名和密码");
    }

    /**
     * 用户注册
     *
     * @param user
     */
    @Override
    public void UserRegistration(User user) {
        userDao.UserRegistration(user);
    }

    /**
     * 查找重复用户
     *
     * @param username
     * @return
     */
    @Override
    public Boolean findUsernameRepeat(String username) {

        User name = userDao.getUsernameByName(username);

        if (name == null) {
            return false;
        }

        return true;
    }


    /**
     * 通过用户名查找用用户
     *
     * @param username
     * @return
     */
    @Override
    public User findByUsername(String username) {
        return userDao.getUsernameByName(username);
    }

    /**
     * 删除用户
     *
     * @param uid
     */
    @Override
    public void deleteUserByUid(int uid) {
        userDao.deleteUserByUid(uid);
    }
}
