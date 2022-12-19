package com.example.backend_final.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Data
@Setter
@Getter
@ToString
public class BillRequest {
    @NotBlank
    private String username;
    @NotBlank
    private List<Long> listBookId;
    @NotBlank
    private String phone;
    @NotBlank
    private String address;
}
