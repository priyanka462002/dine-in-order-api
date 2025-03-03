package com.example.dio.dto;

import com.example.dio.enums.UserRole;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserResponse {
    private Long userId;
    private String username;
    private UserRole role;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;


}
