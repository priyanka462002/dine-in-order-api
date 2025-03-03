package com.example.dio.dto.request;

import com.example.dio.enums.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationRequest {

    @NotEmpty(message = "Username cannot be null or blank")
    @NotBlank(message = "Username cannot be blank")
    @Pattern(regexp = "^[a-zA-Z0-9_]+$",message = "Username can only contain Alphabets,Numbers and Underscore")
    private String username;

    @NotEmpty(message = "email cannot be null or blank")
    @NotBlank(message = "email cannot be  not blank")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",message = "Email must be a valid format ,containing a username,\"@\" symbol,domain name,and a valid top-level domain(e.g. .com,.net,.org)")
    private String email;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",message = "Password must be 1-12 characters long,containing at least one Uppercase letter,one lowercase letter,one digit,and one special character (@$!%*&). ")
    private String password;

    @Pattern(regexp = "^[7-9]\\d{9}$",message = "Invalid phone number")
    private String phNo;

    private UserRole role;

}
