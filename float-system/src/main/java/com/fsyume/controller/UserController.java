package com.fsyume.controller;

import com.fsyume.eneity.User;
import com.fsyume.service.UserService;
import com.fsyume.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin// 允许跨域
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 用户登录
     *
     * @param user 用户登录信息
     * @return 登录成功或登录失败 + JWT令牌
     */
    @PostMapping("login")
    public Map<String, Object> login(@RequestBody User user) {

        Map<String, Object> map = new HashMap<>();

        try {

            User userDB = userService.login(user);

            //生成jwt令牌
            String token = JwtUtil.getToken(userDB.getUsername(), userDB.getUid());


            map.put("username", userDB.getUsername());
            map.put("uid", userDB.getUid());
            map.put("token", token);
            map.put("static", true);
            map.put("msg", "登录成功");

        } catch (Exception e) {
            map.put("static", false);
            map.put("msg", e.getMessage());
        }

        return map;
    }

    /**
     * 普通用户注册接口
     *
     * @param user 用户注册信息
     * @return 注册成功或失败
     */
    @PostMapping("reg")
    public Map<String, Object> UserRegistration(@RequestBody User user) {

        Map<String, Object> map = new HashMap<>();

        try {

            Boolean byUsername = userService.findUsernameRepeat(user.getUsername());

            if (byUsername) {

                map.put("static", false);
                map.put("msg", "注册失败，用户名重复");

            } else {

                userService.UserRegistration(user);
                map.put("static", true);
                map.put("msg", "注册成功");

            }

        } catch (Exception e) {
            map.put("static", false);
            map.put("msg", e.getMessage());
        }

        return map;
    }

    /**
     * 查找全部用户
     *
     * @return 用户对象数组
     */
    @GetMapping("user/findall")
    public List<User> findAll() {
        return userService.findAllUser();
    }

    /**
     * 用户删除
     *
     * @param user 要删除的用户信息
     * @return 删除成功或失败
     */
    @PostMapping("user/delete")
    public Map<String, Object> deleteUser(@RequestBody User user) {

        Map<String, Object> map = new HashMap<>();

        try {

            User userDB = userService.findByUsername(user.getUsername());

            userService.deleteUserByUid(userDB.getUid());
            map.put("static", true);
            map.put("msg", "删除成功");

        } catch (Exception e) {
            map.put("static", false);
            map.put("msg", e.getMessage());
        }

        return map;
    }

    /**
     * 用户更新
     * @param user 要更新的用户信息
     * @return 更新成功或者失败
     */
    @PostMapping("user/update")
    public Map<String,Object> updateUser(@RequestBody User user){
        Map<String, Object> map = new HashMap<>();


        try {
            userService.updateUser(user);

            map.put("static", true);
            map.put("msg", "更新成功");

        } catch (Exception e) {
            map.put("static", false);
            map.put("msg", e.getMessage());
        }


        return map;
    }

}
