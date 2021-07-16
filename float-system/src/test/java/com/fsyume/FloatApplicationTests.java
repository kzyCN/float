package com.fsyume;

import com.upyun.RestManager;
import com.upyun.UpException;
import com.upyun.UpYunUtils;
import okhttp3.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

        // 设置超时时间
        manager.setTimeout(60);

        // 创建目录
        // try {
        //     Response response = manager.mkDir("/float/img");
        // } catch (IOException e) {
        //     e.printStackTrace();
        // } catch (UpException e) {
        //     e.printStackTrace();
        // }

        String str = "C:/Users/kzy/Pictures/kx.png";
        Map<String, String> params = new HashMap<String, String>();

        // 设置待上传文件的 Content-MD5 值
        // 如果又拍云服务端收到的文件MD5值与用户设置的不一致，将回报 406 NotAcceptable 错误
        File file = new File(str);
        params.put(RestManager.PARAMS.CONTENT_MD5.getValue(), UpYunUtils.md5(file, 1024));

        // 设置待上传文件的"访问密钥"
        // 注意：
        // 仅支持图片空！，设置密钥后，无法根据原文件URL直接访问，需带URL后面加上（缩略图间隔标志符+密钥）进行访问
        // 举例：
        // 如果缩略图间隔标志符为"!"，密钥为"bac"，上传文件路径为"/folder/test.jpg"，
        // 那么该图片的对外访问地址为：http://空间域名 /folder/test.jpg!bac

        params.put(RestManager.PARAMS.CONTENT_SECRET.getValue(), "ark");

        try {
            Response result = manager.writeFile("/float/img/kx",file,params);
            System.out.println(result.isSuccessful());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UpException e) {
            e.printStackTrace();
        }


        System.out.println(Url);

    }

}
