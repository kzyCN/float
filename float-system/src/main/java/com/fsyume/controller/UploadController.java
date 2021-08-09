package com.fsyume.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fsyume.config.UpYunConfig;
import com.upyun.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin// 允许跨域
public class UploadController {

    @Autowired
    private UpYunConfig upYunConfig;

    @PostMapping("upimage")
    public Map<String,Object> UpImage(@RequestBody MultipartFile file) throws IOException, NoSuchAlgorithmException, SignatureException, InvalidKeyException {

        Map<String, Object> map = new HashMap<>();

        // 上传的文件名
        String filename = file.getOriginalFilename();

        // System.out.println(filename);


        //获取文件的后缀
        String suffixName = null;

        if (filename != null) {
            suffixName = filename.substring(filename.lastIndexOf("."));
        }

        // System.out.println(suffixName);

        byte[] fileBytes = file.getBytes();

        Result result = upYunConfig.upImage(filename, fileBytes);

        map.put("msg","上传成功");
        map.put("log",result.getMsg());

        return map;
    }


}
