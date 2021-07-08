package com.fsyume.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    /**
     * 设置过期时间及密匙
     * CALENDAR_FIELD 时间单位
     * CALENDAR_INTERVAL 有效时间
     * SING 密匙
     */
    private static final String SING = "!=SSSQ@WE!TG&^&F$";
    private static final int CALENDAR_FIELD = Calendar.MINUTE;
    private static final int CALENDAR_INTERVAL = 60 * 24;

    /**
     * 生成JWT token
     *
     * @param username
     * @return
     */
    public static String getToken(String username, int uid) {

        // 头部
        Map<String, Object> headerMap = new HashMap<>(4);
        headerMap.put("alg", "HS256");
        headerMap.put("typ", "JWT");

        // 当前时间与过期时间
        Calendar time = Calendar.getInstance();
        Date now = time.getTime();
        time.add(CALENDAR_FIELD, CALENDAR_INTERVAL);

        Date expireTime = time.getTime();


        // 选择签名加密算法
        Algorithm algorithm = Algorithm.HMAC256(SING);


        String token = JWT.create()
                .withIssuedAt(now)  //签发时间
                .withHeader(headerMap)
                .withClaim("username", username)
                .withClaim("uid", uid)
                .withSubject("user")
                .withExpiresAt(expireTime)
                .sign(algorithm);

        return token;

    }

    /**
     * 验证token 合法性
     *
     * @param token
     */
    public static void verify(String token) {
        //创建验证对象，这里的签名一定要保持一直
        JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
    }

    /**
     * 验证、解析Token
     *
     * @param token 用户提交的token
     * @return 该token中的用户名
     */
    public static String verifyToken(String token) {
        DecodedJWT verifier = null;
        Algorithm algorithm = Algorithm.HMAC256(SING);
        try {
            verifier = JWT.require(algorithm).build().verify(token);
        } catch (Exception e) {
            //JSONObject jsonObject = new JSONObject();
            //jsonObject.put("status", "401");
            //jsonObject.put("msg", "验证失败，请重新登录!");
            //处理验证异常
        }
        assert verifier != null;
        return verifier.getClaim("username").asString();
    }

}
