package com.example.backend_final.service;


import com.example.backend_final.dto.OrderDto;
import com.example.backend_final.error.BookNotFoundException;
import com.example.backend_final.error.OrderException;
import com.example.backend_final.model.Order;
import com.example.backend_final.payload.request.OrderRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface OrderService {

    <S extends Order> S save(S entity);

    Optional<Order> findById(Long aLong);

    boolean existsById(Long aLong);

    long count();

    void deleteById(Long aLong);

    void delete(Order entity);


    Page<Order> findAll(Pageable pageable);

    Optional<Order> createOrder(OrderRequest orderRequest) throws BookNotFoundException;

    List<Order> getOrderByUserName(String username);

    void deleteOrderDetail(Long id);

}
