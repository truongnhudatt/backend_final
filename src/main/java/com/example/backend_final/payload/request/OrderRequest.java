package com.example.backend_final.payload.request;


import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Data
@Getter
@Setter
@NoArgsConstructor
@ToString
public class OrderRequest {

    private String username;
    private Map<Long, Integer> listItems = new HashMap<>();

}
