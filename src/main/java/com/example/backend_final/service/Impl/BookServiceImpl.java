package com.example.backend_final.service.Impl;

import com.example.backend_final.model.Book;
import com.example.backend_final.repository.BookRepo;
import com.example.backend_final.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepo bookRepo;


    @Override
    public <S extends Book> S save(S entity) {
        return bookRepo.save(entity);
    }

    @Override
    public Optional<Book> findById(Long aLong) {
        return bookRepo.findById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return bookRepo.existsById(aLong);
    }

    @Override
    public long count() {
        return bookRepo.count();
    }

    @Override
    public void deleteById(Long aLong) {
        bookRepo.deleteById(aLong);
    }

    @Override
    public void delete(Book entity) {
        bookRepo.delete(entity);
    }

    @Override
    public Page<Book> findAll(Pageable pageable) {
        return bookRepo.findAll(pageable);
    }
}
