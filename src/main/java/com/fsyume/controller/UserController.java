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
     * @param user
     * @return
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
     * 查找全部用户
     *
     * @return
     */
    @GetMapping("user/findall")
    public List<User> findAll() {
        return userService.findAllUser();
    }

}
