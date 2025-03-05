package com.example.dio.dto.request;


import com.example.dio.enums.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserRequest {

    @NotEmpty(message = "Username cannot be null or blank")
    @NotBlank(message = "Username cannot be blank")
    private String username;

    @NotEmpty(message = "Email cannot be null or blank")
    @NotBlank(message = "Email cannot be blank")
    private String email;

    private String phNo;

}
