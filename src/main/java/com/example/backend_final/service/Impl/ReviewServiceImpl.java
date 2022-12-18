package com.example.backend_final.service.Impl;

import com.example.backend_final.error.BookNotFoundException;
import com.example.backend_final.model.Book;
import com.example.backend_final.model.Review;
import com.example.backend_final.model.User;
import com.example.backend_final.payload.request.ReviewRequest;
import com.example.backend_final.repository.ReviewRepo;
import com.example.backend_final.service.BookService;
import com.example.backend_final.service.ReviewService;
import com.example.backend_final.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepo reviewRepo;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @Override
    public Optional<Review> createReview(ReviewRequest request) throws BookNotFoundException {
        User user = userService.findByUsername(request.getUsername())
                                                .orElseThrow(
                                                        () ->
                                                                new UsernameNotFoundException("Can not found user with username: "+ request.getUsername()));
        Book book = bookService.findById(request.getBookId())
                                                .orElseThrow(() ->
                                                                    new BookNotFoundException("Can not found book with bookId: " + request.getBookId()));
        Review review = new Review();
        review.setUser(user);
        review.setBook(book);
        review.setScore(request.getScore());
        review.setComment(request.getComment());
        user.getReviewList().add(review);
        book.getReviewList().add(review);
        return Optional.of(reviewRepo.save(review));
    }
}
