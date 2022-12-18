package com.example.backend_final.dto;


import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@Getter
@ToString
public class OrderDto {
    private long id;
    private String username;
    private List<OrderDetailDto> orderDetailDtoList;
    private Date created = new Date();
    private BigDecimal totalPrice;
}
