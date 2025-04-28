package com.desafios.galeriaimagensspring.application.dto;

import com.desafios.galeriaimagensspring.domain.model.Album;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public record SaveImageDTO(String name, String description, String alternateText, MultipartFile image, List<Album> albums) {
}
