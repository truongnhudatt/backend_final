package com.example.backend_final.controller;

import com.example.backend_final.dto.BookDto;
import com.example.backend_final.service.BookService;
import com.example.backend_final.service.ImageStorageService;
import com.example.backend_final.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
//        @RequestBody BookDto bookDto,
        @RequestParam("image")MultipartFile image
    ){
        String generatedFileName = imageStorageService.storeFile(image);
        System.out.println(generatedFileName);
//        return ResponseEntity.ok(bookService.save(mapper.toBook(bookDto)));
        return ResponseEntity.ok(generatedFileName);
    }

    @DeleteMapping("/remove-image/{filename}")
    public ResponseEntity<?> deleteFileImage(@PathVariable("filename") String fileName){
        imageStorageService.deleteFile(fileName);
        return ResponseEntity.noContent().build();
    }

}
