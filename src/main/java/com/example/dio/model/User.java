package com.example.dio.model;


import com.example.dio.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="users")
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="user_id")
    private long userId;

    @Column(name="username")
    private String username;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="ph_no")
    private String phNo;

    @Column(name="role")
    private UserRole role;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="last_modified_At")
    private LocalDateTime lastModifiedAt;





}
