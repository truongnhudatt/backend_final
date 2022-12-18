package com.example.backend_final.util;


import com.example.backend_final.dto.BookDto;
import com.example.backend_final.dto.OrderDetailDto;
import com.example.backend_final.dto.OrderDto;
import com.example.backend_final.dto.UserDto;
import com.example.backend_final.model.Book;
import com.example.backend_final.model.Order;
import com.example.backend_final.model.OrderDetail;
import com.example.backend_final.model.User;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.stream.Collectors;

@Component
public class Mapper {

    public User toUser(UserDto userDto){
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setRole(userDto.getRole());
        return user;
    }

    public UserDto toUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setUsername(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setRole(user.getRole());
        return userDto;
    }

    public Book toBook(BookDto bookDTO) {
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setTypeBook(bookDTO.getTypeBook());
        book.setDescription(bookDTO.getDescription());
        book.setTotalPage(bookDTO.getTotalPage());
        book.setPrice(new BigDecimal(bookDTO.getPrice()));
        book.setDateRelease(bookDTO.getDateRelease());
        return book;
    }

    public BookDto toBookDto(Book book){
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setTypeBook(book.getTypeBook());
        bookDto.setDescription(book.getDescription());
        bookDto.setTotalPage(book.getTotalPage());
        bookDto.setDateRelease(book.getDateRelease());
        bookDto.setPrice(book.getPrice().floatValue());
        return bookDto;
    }

    public OrderDto toOrderDto(Order order){
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setUsername(order.getUser().getUsername());
        orderDto.setCreated(order.getCreated());
        orderDto.setOrderDetailDtoList(order.getOrderDetailList().stream().map(item -> this.toOrderDetailDto(item)).collect(Collectors.toList()));
        orderDto.setTotalPrice(order.getOrderDetailList().stream().map(OrderDetail::getUnitPrice).reduce(BigDecimal.ZERO,BigDecimal::add));
        return orderDto;
    }

    public OrderDetailDto toOrderDetailDto(OrderDetail orderDetail){
        OrderDetailDto orderDetailDto = new OrderDetailDto();
        orderDetailDto.setId(orderDetail.getId());
        orderDetailDto.setBookDto(this.toBookDto(orderDetail.getBook()));
        orderDetailDto.setPrice(orderDetail.getPrice());
        orderDetailDto.setQuantity(orderDetail.getQuantity());
        orderDetailDto.setUnitPrice(orderDetail.getUnitPrice());
        return orderDetailDto;
    }

}
