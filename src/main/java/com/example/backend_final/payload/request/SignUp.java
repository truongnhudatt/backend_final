package com.example.backend_final.payload.request;


import com.example.backend_final.util.Role;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Setter
@Getter
public class SignUp {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private Role role;
}
