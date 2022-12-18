package com.example.backend_final.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ReviewDto {
    private long id;
    private UserDto userDto;
    private BookDto bookDto;
    private float score;
    private String comment;
}
