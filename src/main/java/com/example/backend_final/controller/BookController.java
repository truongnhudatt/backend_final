package com.example.backend_final.controller;

import com.example.backend_final.dto.BookDto;
import com.example.backend_final.dto.ImageDto;
import com.example.backend_final.dto.OrderDto;
import com.example.backend_final.error.BookNotFoundException;
import com.example.backend_final.model.Book;
import com.example.backend_final.model.Image;
import com.example.backend_final.model.Order;
import com.example.backend_final.payload.response.BookResp;
import com.example.backend_final.payload.response.OrderResp;
import com.example.backend_final.service.BookService;
import com.example.backend_final.service.ImageStorageService;
import com.example.backend_final.util.Mapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private ImageStorageService imageStorageService;

    @Autowired
    private Mapper mapper;

    @PostMapping("/save")
    public ResponseEntity<?> saveBook(
        @RequestParam("book") String book,
        @RequestParam("image")MultipartFile[] images
    ) throws BookNotFoundException, JsonProcessingException {
        BookDto bookJson = new BookDto();
        ObjectMapper objectMapper = new ObjectMapper();
        bookJson = objectMapper.readValue(book, BookDto.class);

        List<ImageDto> imageList = Arrays.stream(images).map(item ->
                                                            new ImageDto(imageStorageService.storeFile(item)))
                                                                    .collect(Collectors.toList());
        bookJson.setImageList(imageList);
        return ResponseEntity.ok(mapper.toBookDto(bookService.save(mapper.toBook(bookJson))));
    }

    @DeleteMapping("/remove-image/{filename}")
    public ResponseEntity<?> deleteFileImage(@PathVariable("filename") String fileName){
        imageStorageService.deleteFile(fileName);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getDetailBook(@PathVariable("id") Long id) throws BookNotFoundException {
        Book book = bookService.findById(id).orElseThrow(() -> new BookNotFoundException("Can not found book with bookId: " + id));
        return ResponseEntity.ok(mapper.toBookDto(book));
    }


    @GetMapping("/all")
    public ResponseEntity<?> getAllBooks(@RequestParam(value = "pageNo",defaultValue = "0") Integer pageNo,
                                          @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
                                          @RequestParam(value = "sortBy",defaultValue = "title") String sortBy){
        Pageable paging = PageRequest.of(pageNo,pageSize, Sort.by(sortBy));
        Page<Book> bookPage = bookService.findAll(paging);
        List<Book> bookList = bookPage.getContent();

        List<BookDto> bookDtoList = bookList.stream().map(b -> mapper.toBookDto(b)).collect(Collectors.toList());
        BookResp bookResp = new BookResp();
        bookResp.setBookDtoList(bookDtoList);
        bookResp.setPageNo(bookPage.getNumber());
        bookResp.setPageSize(bookPage.getSize());
        bookResp.setTotalElements(bookPage.getTotalElements());
        bookResp.setTotalPages(bookPage.getTotalPages());
        bookResp.setLast(bookPage.isLast());
        return ResponseEntity.ok(bookResp);
    }
}
