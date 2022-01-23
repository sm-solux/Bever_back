package com.example.bever.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Transactional
public class ImageService {

    public String uploadImage(MultipartFile file) throws IOException {
        // gcp cloud storage 버킷에 파일 업로드
        try {
            String keyFileName = "static/idyllic-aspect-328219-5ec2b12c674f.json";
            InputStream keyfile = ResourceUtils.getURL("classpath:" + keyFileName).openStream();
            System.out.println("====ggg1====");
            Storage storage = StorageOptions.newBuilder()
                    .setCredentials(GoogleCredentials.fromStream(keyfile))
                    .build().getService();
            System.out.println("====ggg2====");

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd"); // 폴더 이름 업로드 날짜로 바꾸기
            String folderName = simpleDateFormat.format(new Date());
            String objectName = Long.toString(System.nanoTime()); // 파일명 변경
            System.out.println("====ggg3====");


            BlobInfo blobInfo = storage.create(
                    BlobInfo.newBuilder("plket", objectName).build(), //get original file name
                    file.getBytes() // the file
            );
            System.out.println(blobInfo.getMediaLink());
            return blobInfo.getMediaLink();
        } catch (IllegalStateException e) {
            throw new RuntimeException(e);
        }
    }

}
