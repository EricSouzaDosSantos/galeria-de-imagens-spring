package com.desafios.galeriaimagensspring.interfaces.dto.imagens;

import java.util.Date;

public record GetImageDto(String name, String description, Date creationDate, String alternateText, String imageURL) {

}
