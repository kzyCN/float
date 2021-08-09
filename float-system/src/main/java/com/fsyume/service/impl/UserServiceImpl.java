package com.fsyume.service.impl;

import com.fsyume.dao.UserDao;
import com.fsyume.eneity.User;
import com.fsyume.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }


    /**
     * 查询所有用户
     *
     * @return 数据库中所有用户列表List
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

        return name != null;
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
     * 通过uid查找用户
     * @param uid
     * @return
     */
    @Override
    public User getUserByUid(int uid) {
        return userDao.getUserByUid(uid);
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

    /**
     * 用户信息更新
     * @param user
     */
    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }
}
