package com.example.backend_final.controller;

import com.example.backend_final.dto.OrderDto;
import com.example.backend_final.error.BookNotFoundException;
import com.example.backend_final.error.OrderException;
import com.example.backend_final.model.Order;
import com.example.backend_final.payload.request.OrderRequest;
import com.example.backend_final.payload.response.OrderResp;
import com.example.backend_final.repository.OrderRepo;
import com.example.backend_final.service.OrderService;
import com.example.backend_final.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired
    private Mapper mapper;

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepo orderRepo;

    @GetMapping("/all")
    public ResponseEntity<OrderResp> getAllOrders(@RequestParam(value = "pageNo",defaultValue = "0") Integer pageNo,
                                          @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
                                          @RequestParam(value = "sortBy",defaultValue = "created") String sortBy){
        Pageable paging = PageRequest.of(pageNo,pageSize, Sort.by(sortBy));
        Page<Order> orderPage = orderRepo.findAll(paging);
        List<Order> orderList = orderPage.getContent();

        List<OrderDto> orderDtoList = orderList.stream().map(ord -> mapper.toOrderDto(ord)).collect(Collectors.toList());
        OrderResp orderResp = new OrderResp();
        orderResp.setOrderDtoList(orderDtoList);
        orderResp.setPageNo(orderPage.getNumber());
        orderResp.setPageSize(orderPage.getSize());
        orderResp.setTotalElements(orderPage.getTotalElements());
        orderResp.setTotalPages(orderPage.getTotalPages());
        orderResp.setLast(orderPage.isLast());
        return ResponseEntity.ok(orderResp);
    }

    @PostMapping("/create")
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderRequest orderRequest) throws BookNotFoundException, OrderException {
        System.out.println(orderRequest);
        return ResponseEntity.ok(mapper.toOrderDto(orderService.createOrder(orderRequest)
                                                                    .orElseThrow(() -> new OrderException("Cannot create order at this time"))));
    }

    @GetMapping("/{username}/detail-order")
    public ResponseEntity<?> getAllOrderByUsername(@PathVariable("username") String username){
        return ResponseEntity.ok(orderService.getOrderByUserName(username).stream().map(ord -> mapper.toOrderDto(ord)).collect(Collectors.toList()));
    }

    @DeleteMapping("/{username}/delete/detail-order/{id}")
    public ResponseEntity<?> deleteOrderDetailById(@PathVariable("username") String username,
                                                   @PathVariable("id") Long id){
        orderService.deleteOrderDetail(id);
        return ResponseEntity.ok(orderService.getOrderByUserName(username).stream().map(ord -> mapper.toOrderDto(ord)).collect(Collectors.toList()));
    }

}
