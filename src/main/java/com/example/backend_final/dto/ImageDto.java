package com.example.backend_final.dto;

import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@ToString
public class ImageDto {
    private long id;
    private String fileName;

    public ImageDto(String fileName) {
        this.fileName = fileName;
    }
}
