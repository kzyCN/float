package com.fsyume.service.impl;

import com.fsyume.dao.UserDao;
import com.fsyume.eneity.User;
import com.fsyume.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    // 依赖注入
    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

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
}
