package com.github.angel.raa.modules.utils.DTO;

import org.springframework.web.multipart.MultipartFile;

public record ImageDTO(String token, MultipartFile file) {
}
