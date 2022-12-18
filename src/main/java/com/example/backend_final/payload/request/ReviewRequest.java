package com.example.backend_final.payload.request;


import lombok.*;

@Data
@Setter
@Getter
@ToString
@NoArgsConstructor
public class ReviewRequest {
    private String username;
    private long bookId;
    private float score;
    private String comment;
}
