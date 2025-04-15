package com.example.dio.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.dio.exception.FoodNotFoundException;
import com.example.dio.model.FoodItem;
import com.example.dio.model.Image;
import com.example.dio.repository.FoodItemRepository;
import com.example.dio.repository.ImageRepository;
import com.example.dio.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@AllArgsConstructor
public class ImageServiceImpl implements ImageService {

    private Cloudinary cloudinary;
    private ImageRepository imageRepository;
    private FoodItemRepository foodItemRepository;

    @Override
    @Transactional
    public String uploadImage(MultipartFile file, long foodId) throws IOException {

        FoodItem foodItem =foodItemRepository.findById(foodId).orElseThrow(
                () -> new FoodNotFoundException("No Food item found with this id" + foodId));

        Map result = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        String url = result.get("url").toString();

        Image image = new Image();
        image.setImageURL(url);
        image.setFoodItem(foodItem);

        imageRepository.save(image);
        return url;
    }

}
