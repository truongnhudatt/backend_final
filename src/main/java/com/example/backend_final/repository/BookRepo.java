package com.example.backend_final.repository;

import com.example.backend_final.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Repository
public interface BookRepo extends JpaRepository<Book, Long> {


}
