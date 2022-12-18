package com.example.backend_final.repository;


import com.example.backend_final.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Repository
public interface ReviewRepo extends JpaRepository<Review, Long> {

    @Query("SELECT r FROM Review r WHERE r.book.id = ?1")
    Optional<Review> findReviewByBookId(Long bookId);

    @Query("SELECT r FROM Review r WHERE r.user.username = ?1")
    Optional<Review> findReviewByUsername(String username);

}
