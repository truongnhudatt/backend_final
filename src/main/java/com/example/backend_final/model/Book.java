package com.example.backend_final.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "books")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;


    private String title;

    private String author;

    @Column(length = 500)
    private String description;

    private String typeBook;

    private Date dateRelease;

    private int totalPage;

    private BigDecimal price;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "book")
    private List<Image> imageList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private List<OrderDetail> orderDetailList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER)
    private List<Review> reviewList = new ArrayList<>();

    private float rating = 0;


}
