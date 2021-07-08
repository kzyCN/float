package com.fsyume.dao;

import com.fsyume.eneity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {

    // 查询所有用户
    List<User> findAllUser();

    // 通过用户名查找用户
    User getUsernameByName(String username);

    // 普通用户注册
    void UserRegistration(User user);

    // 通过uid删除用户
    void deleteUserByUid(int uid);


}
