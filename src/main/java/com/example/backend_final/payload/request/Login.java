package com.example.backend_final.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

@Data
@Getter

public class Login implements Serializable {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
