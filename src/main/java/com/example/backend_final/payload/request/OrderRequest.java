package com.example.backend_final.payload.request;


import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
@Getter
@Setter
@NoArgsConstructor
@ToString
public class OrderRequest implements Serializable {
    @NotBlank
    private String username;
    @NotBlank
    private Map<Long, Integer> listItems = new HashMap<>();

}
