package com.example.backend_final.service;


import com.example.backend_final.dto.BookDto;
import com.example.backend_final.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public interface BookService {
    <S extends Book> S save(S entity);

    Optional<Book> findById(Long aLong);

    boolean existsById(Long aLong);

    long count();

    void deleteById(Long aLong);

    void delete(Book entity);

    Page<Book> findAll(Pageable pageable);
}
