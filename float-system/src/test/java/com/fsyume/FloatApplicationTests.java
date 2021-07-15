package com.fsyume;

import com.upyun.RestManager;
import com.upyun.UpYunUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FloatApplicationTests {

    @Value("${upYun.upYunServerName}")
    private String ServerName;

    @Value("${upYun.upYunUserName}")
    private String UserName;

    @Value("${upYun.upYunPassword}")
    private String password;

    @Value("${upYun.upYunUrl}")
    private String Url;

    @Test
    void upYunUpdate() {

        RestManager manager = new RestManager(ServerName,UserName,password);



        System.out.println(Url);

    }

}
