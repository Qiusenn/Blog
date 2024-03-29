package com.qiusen.controller;

import com.qiusen.enums.ResponseResult;
import com.qiusen.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class UploadController {
    @Autowired
    private UploadService uploadService;

    /**
     * 上传文件
     * @param multipartFile
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    public ResponseResult uploadImg(@RequestParam("img") MultipartFile multipartFile) throws IOException {
        return uploadService.uploadImg(multipartFile);
    }
}

