package com.fsyume.utils;

import com.upyun.FormUploader;
import com.upyun.Params;
import com.upyun.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.File;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;


public class upYunUtil {

    private String ServerName = "float";

    private String Username = "float";

    private String password = "nWR9B5CCL8sGIN2sYg1AK0DyK6LaiNHd";


    public Result upImage(String filename , byte[] file) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {

        System.out.println(ServerName+ "-" + Username+  "-"  + password);

        //初始化
        FormUploader formUploader = new FormUploader(ServerName, Username, password);

        //初始化参数组 Map
        final Map<String, Object> paramsMap = new HashMap<String, Object>();

        //添加 SAVE_KEY 参数
        //filename为文件名(例如：12345.jpg)
        // filename = "kx.png";
        paramsMap.put(Params.SAVE_KEY, "/float/img/" + filename);

        //添加同步上传作图参数 X_GMKERL_THUMB
        // //限定图片宽度为 300px、锐化、压缩质量 80、存储为 png 格式（参数不区分先后顺序）
        // paramsMap.put(Params.X_GMKERL_THUMB, "/fw/300/unsharp/true/quality/80/format/png");

        //打印结果
        Result result = null;

        // String imgPath = "C:/Users/kzy/Pictures/kx.png";
        // File file = new File(imgPath);

        result = formUploader.upload(paramsMap, file);

        System.out.println(result);

        return result;

    }
}
