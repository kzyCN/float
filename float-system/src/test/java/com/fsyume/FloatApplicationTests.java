package com.fsyume;

import com.upyun.*;
import okhttp3.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class FloatApplicationTests {

    @Value("${upYun.upYunServerName}")
    private String ServerName;

    @Value("${upYun.upYunUsername}")
    private String Username;

    @Value("${upYun.upYunPassword}")
    private String password;


    @Test
    void upYunUpdate() {

        RestManager manager = new RestManager(ServerName, Username,password);

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


    }

    @Test
    void upYunFromUpLoader() throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        FormUploader formUploader = new FormUploader(ServerName, Username, password);

        //初始化参数组 Map
        final Map<String, Object> paramsMap = new HashMap<String, Object>();

        //添加 SAVE_KEY 参数
        //filename为文件名(例如：12345.jpg)
        String filename = "kx.png";
        paramsMap.put(Params.SAVE_KEY, "/float/img/" + filename);

        //添加同步上传作图参数 X_GMKERL_THUMB
        // //限定图片宽度为 300px、锐化、压缩质量 80、存储为 png 格式（参数不区分先后顺序）
        // paramsMap.put(Params.X_GMKERL_THUMB, "/fw/300/unsharp/true/quality/80/format/png");

        //打印结果
        Result result = null;

        String imgPath = "C:/Users/kzy/Pictures/kx.png";
        File file = new File(imgPath);

        result = formUploader.upload(paramsMap, file);

        System.out.println(result);

        String msg = result.getMsg();

        System.out.println(msg);


    }

}
