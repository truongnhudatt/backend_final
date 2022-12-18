package com.example.backend_final.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private long id;
    private String title;
    private String author;
    private String typeBook;
    private String description;
    private Date dateRelease;
    private int totalPage;
    private float price;
}
