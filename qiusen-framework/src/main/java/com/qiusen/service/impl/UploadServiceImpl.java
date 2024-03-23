package com.qiusen.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.qiusen.config.SecurityConfig;
import com.qiusen.domain.entity.User;
import com.qiusen.enums.AppHttpCodeEnum;
import com.qiusen.enums.ResponseResult;
import com.qiusen.exception.SystemException;
import com.qiusen.properties.MinioProperties;
import com.qiusen.service.UploadService;
import com.qiusen.service.UserService;
import com.qiusen.utils.SecurityUtils;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    private MinioProperties minioProperties;

    @Autowired
    private UserService userService;
    @Override
    public ResponseResult uploadImg(MultipartFile img) {

        String originalFilename = img.getOriginalFilename();

        int index = originalFilename.lastIndexOf(".");
        String fileType = originalFilename.substring(index);

        if(!fileType.equals(".png") && !fileType.equals(".jpg")) {
            throw new SystemException(AppHttpCodeEnum.FILE_TYPE_ERR);
        }

        try {
            // 创建一个Minio的客户端对象
            MinioClient minioClient = MinioClient.builder()
                    .endpoint(minioProperties.getEndpointUrl())
                    .credentials(minioProperties.getAccessKey(), minioProperties.getSecreKey())
                    .build();

            // 判断桶是否存在
            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(minioProperties.getBucketName()).build());
            if (!found) {       // 如果不存在，那么此时就创建一个新的桶
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(minioProperties.getBucketName()).build());
            } else {  // 如果存在打印信息
                System.out.println("Bucket 'qs-blog' already exists.");
            }

            // 设置存储对象名称
            String dateDir = DateUtil.format(new Date(), "yyyyMMdd");
            String uuid = UUID.randomUUID().toString().replace("-", "");
            //20230801/443e1e772bef482c95be28704bec58a901.jpg
            String fileName = dateDir+"/"+uuid+img.getOriginalFilename();
            System.out.println(fileName);

            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                    .bucket(minioProperties.getBucketName())
                    .stream(img.getInputStream(), img.getSize(), -1)
                    .object(fileName)
                    .build();
            minioClient.putObject(putObjectArgs);

            String imgUrl = minioProperties.getEndpointUrl() + "/" + minioProperties.getBucketName() + "/" + fileName;

            return ResponseResult.okResult(imgUrl);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
