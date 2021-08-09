package com.fsyume.interceptors;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fsyume.utils.JwtUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class JwtAdminInterceptors implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<String, Object> map = new HashMap<>();

        // 获取请求头的token，来判断是否为管理员
        String token = request.getHeader("token");

        System.out.println("token：" + token);

        if (token == null) {
            map.put("static", false);
            map.put("msg", "token为空");
            map.put("code", 403);
        } else {
            try {
                int isAdmin = JwtUtil.verifyIsAdmin(token);//验证令牌，确定是否为管理员
                System.out.println(isAdmin);
                return isAdmin == 1;
            } catch (SignatureVerificationException e) {
                e.printStackTrace();
                map.put("msg", "签名不一致");
                map.put("static", false);
            } catch (TokenExpiredException e) {
                e.printStackTrace();
                map.put("msg", "令牌过期");
                map.put("static", false);
            } catch (AlgorithmMismatchException e) {
                e.printStackTrace();
                map.put("msg", "算法不匹配");
                map.put("static", false);
            } catch (InvalidClaimException e) {
                e.printStackTrace();
                map.put("msg", "失效的payload");
                map.put("static", false);
            } catch (Exception e) {
                e.printStackTrace();
                map.put("msg", "token无效");
                map.put("static", false);
            }
        }

        String json = new ObjectMapper().writeValueAsString(map);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);

        return false;
    }
}
