package com.desafios.galeriaimagensspring.interfaces.dto.imagens;

import java.time.LocalDate;

public record ImageResponseDto(String name, String description, LocalDate creationDate, String alternateText, String imageURL) {

}
