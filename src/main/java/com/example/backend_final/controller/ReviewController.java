package com.example.backend_final.controller;


import com.example.backend_final.error.BookNotFoundException;
import com.example.backend_final.payload.request.ReviewRequest;
import com.example.backend_final.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping("/create")
    public ResponseEntity<?> createReview(@RequestBody ReviewRequest request) throws BookNotFoundException {
        return ResponseEntity.ok(reviewService.createReview(request));
    }
}
