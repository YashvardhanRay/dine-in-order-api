package com.example.dio.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    String uploadImage(MultipartFile imageURL, long foodId) throws IOException;
}
