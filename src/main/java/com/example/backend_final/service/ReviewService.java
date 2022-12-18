package com.example.backend_final.service;


import com.example.backend_final.error.BookNotFoundException;
import com.example.backend_final.model.Review;
import com.example.backend_final.payload.request.ReviewRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public interface ReviewService {
    Optional<Review> createReview(ReviewRequest request) throws BookNotFoundException;
}
