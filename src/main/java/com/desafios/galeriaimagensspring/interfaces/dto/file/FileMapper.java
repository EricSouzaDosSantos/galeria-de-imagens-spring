package com.desafios.galeriaimagensspring.interfaces.dto.file;

import com.desafios.galeriaimagensspring.core.model.FileData;
import org.springframework.web.multipart.MultipartFile;

public class FileMapper {
    public static FileData toFileData(MultipartFile multipartFile){
        try {
            return new FileData(
                    multipartFile.getOriginalFilename(),
                    multipartFile.getBytes(),
                    multipartFile.getContentType(),
                    multipartFile.getInputStream()
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
