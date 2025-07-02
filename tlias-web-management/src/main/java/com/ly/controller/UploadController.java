package com.ly.controller;

import com.ly.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController

public class UploadController {
    @PostMapping("/upload")
    public Result upload(String name, Integer age, MultipartFile file){
        log.info("文件上传:{},{},{}",name,age,file);
        try {
            String originalFilename = file.getOriginalFilename();//获取原始文件名

            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));//获取原始文件后缀名
            String newFilename = UUID.randomUUID().toString()+extension;//生成新的文件名;

            file.transferTo(new File("D:/images/"+newFilename));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Result.success();
    }
}
