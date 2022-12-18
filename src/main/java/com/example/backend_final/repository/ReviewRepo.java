package com.example.backend_final.repository;


import com.example.backend_final.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface ReviewRepo extends JpaRepository<Review, Long> {
}
