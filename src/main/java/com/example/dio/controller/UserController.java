package com.example.dio.controller;

import com.example.dio.dto.UserResponse;
import com.example.dio.dto.request.RegistrationRequest;
import com.example.dio.dto.request.UserRequest;
import com.example.dio.model.User;
import com.example.dio.service.UserService;
import com.example.dio.util.FieldErrorResponse;
import com.example.dio.util.ResponseBuilder;
import com.example.dio.util.ResponseStructure;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Tag(name = "User Controller",description = "Collection of Endpoints dealing user data" )
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    @Operation(description = """
            The API Endpoint is used to register the user data.
            The Endpoint the user to select one of the specified role along with the other details
            """,
    responses = {
    @ApiResponse(responseCode = "201",description = "User Created"),
    @ApiResponse(responseCode = "400",description = "Invalid Input",content = {
            @Content(schema = @Schema(implementation = FieldErrorResponse.class))
        })
    })
    public ResponseEntity<ResponseStructure<UserResponse>> registerUser(@RequestBody @Valid RegistrationRequest registrationRequest){
        UserResponse response =userService.registerUser(registrationRequest);


        return ResponseBuilder.success(HttpStatus.CREATED, "User Created",response);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<ResponseStructure<UserResponse>> findUserById(@PathVariable long userId){
        UserResponse response=userService.findUserById(userId);
        return ResponseBuilder.success(HttpStatus.OK, "User Found",response);

    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<ResponseStructure<UserResponse>> updateUserById(@PathVariable long userId,  @RequestBody @Valid UserRequest userRequest){
        UserResponse response =userService.updateUserById(userId,userRequest);

        return ResponseBuilder.success(HttpStatus.OK,"User Updated",response);
    }



}
