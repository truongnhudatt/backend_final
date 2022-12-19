package com.example.backend_final.service.Impl;

import com.example.backend_final.error.BookNotFoundException;
import com.example.backend_final.model.Book;
import com.example.backend_final.model.Order;
import com.example.backend_final.model.OrderDetail;
import com.example.backend_final.model.User;
import com.example.backend_final.payload.request.OrderRequest;
import com.example.backend_final.repository.OrderDetailRepo;
import com.example.backend_final.repository.OrderRepo;
import com.example.backend_final.service.BookService;
import com.example.backend_final.service.OrderService;
import com.example.backend_final.service.UserService;
import com.example.backend_final.util.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Pageable;
import java.math.BigDecimal;
import java.util.*;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private OrderDetailRepo orderDetailRepo;

    @Override
    public <S extends Order> S save(S entity) {
        return orderRepo.save(entity);
    }

    @Override
    public Optional<Order> findById(Long aLong) {
        return orderRepo.findById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return orderRepo.existsById(aLong);
    }

    @Override
    public long count() {
        return orderRepo.count();
    }

    @Override
    public void deleteById(Long aLong) {
        orderRepo.deleteById(aLong);
    }

    @Override
    public void delete(Order entity) {
        orderRepo.delete(entity);
    }


    @Override
    public Page<Order> findAll(Pageable pageable) {
        return orderRepo.findAll(pageable);
    }

    @Override
    public Optional<Order> createOrder(OrderRequest orderRequest) throws BookNotFoundException {
        User user = userService.findByUsername(orderRequest.getUsername()).orElseThrow(() -> new UsernameNotFoundException("Cannot find user with username: "+ orderRequest.getUsername()));
        Optional<Order> orderTmp = orderRepo.getOrderByUsername(orderRequest.getUsername());
        Order order;
        if(orderTmp.isPresent()) {
            order = orderTmp.get();
            for (Map.Entry item : orderRequest.getListItems().entrySet()) {
                Book bookTmp = bookService.findById((Long) item.getKey()).orElseThrow(() -> new BookNotFoundException("Cannot find book with bookId: " + item.getKey()));
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setBook(bookTmp);
                orderDetail.setQuantity((Integer) item.getValue());
                orderDetail.setPrice(bookTmp.getPrice());
                orderDetail.setUnitPrice(orderDetail.getPrice().multiply(new BigDecimal(orderDetail.getQuantity())));
                order.getOrderDetailList().add(orderDetail);
                orderDetail.setOrder(order);
            }
        }
        else {
            order = new Order();
            for (Map.Entry item : orderRequest.getListItems().entrySet()) {
                Book bookTmp = bookService.findById((Long) item.getKey()).orElseThrow(() -> new BookNotFoundException("Cannot find book with bookId: " + item.getKey()));
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setBook(bookTmp);
                orderDetail.setQuantity((Integer) item.getValue());
                orderDetail.setPrice(bookTmp.getPrice());
                orderDetail.setUnitPrice(orderDetail.getPrice().multiply(new BigDecimal(orderDetail.getQuantity())));
                order.getOrderDetailList().add(orderDetail);
                orderDetail.setOrder(order);
            }
        }
        order.setOrderStatus(OrderStatus.NEW);
        order.setUser(user);
        user.getOrderList().add(order);
        return Optional.of(orderRepo.save(order));
    }


    @Override
    public Optional<Order> getOrderByUserName(String username){
        return orderRepo.getOrderByUsername(username);
    }

    @Override
    public void deleteOrderDetail(Long id){
        orderDetailRepo.deleteOrderDetail(id);
    }
}
