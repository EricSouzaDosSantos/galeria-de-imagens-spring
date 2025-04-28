package com.desafios.galeriaimagensspring.application.dto;

import com.desafios.galeriaimagensspring.domain.model.Album;

import java.util.List;

public record SaveImageDTO(String name, String description, String alternateText, List<Album> albums) {
}
