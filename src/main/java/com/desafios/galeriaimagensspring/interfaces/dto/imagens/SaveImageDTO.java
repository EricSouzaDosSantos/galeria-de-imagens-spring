package com.desafios.galeriaimagensspring.interfaces.dto.imagens;

import org.springframework.web.multipart.MultipartFile;

public record SaveImageDTO(String name, String description, String alternateText, MultipartFile image) {
}
