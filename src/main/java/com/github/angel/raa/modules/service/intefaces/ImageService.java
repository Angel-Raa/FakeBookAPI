package com.github.angel.raa.modules.service.intefaces;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    String uploadFile(MultipartFile file, String token);


}
