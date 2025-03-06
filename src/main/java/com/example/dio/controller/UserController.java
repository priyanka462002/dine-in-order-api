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

@RequestMapping("${app.base-url}")
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


        return ResponseBuilder.created( response,"User Created");
    }



    @GetMapping("/users/{userId}")
    @Operation(description = """
             The API Endpoint is used to find the user data.
            The Endpoint requires the user to enter the id to the details
            """,
      responses = {
      @ApiResponse(responseCode = "201",description = "User Found") ,
          @ApiResponse(responseCode = "400",description = "User not Found",content = {
                  @Content(schema = @Schema(implementation = FieldErrorResponse.class))

      })
})


    public ResponseEntity<ResponseStructure<UserResponse>> findUserById(@PathVariable long userId){
        UserResponse response=userService.findUserById(userId);
        return ResponseBuilder.ok(response,"User Found");

    }


    @PutMapping("/users/{userId}")
    @Operation(description = """
            The API Endpoint is used to update the user data.
            The Endpoint the user has to entire the id details to be updated.
            """,
            responses = {
            @ApiResponse(responseCode = "201",description = "User Data Updated"),
                    @ApiResponse(responseCode = "400",description ="User data not updated",content = {
                            @Content(schema = @Schema(implementation = FieldErrorResponse.class))
                    })
            })

    public ResponseEntity<ResponseStructure<UserResponse>> updateUserById(@PathVariable long userId,  @RequestBody @Valid UserRequest userRequest){
        UserResponse response =userService.updateUserById(userId,userRequest);

        return ResponseBuilder.ok(response,"User Updated");
    }



}
