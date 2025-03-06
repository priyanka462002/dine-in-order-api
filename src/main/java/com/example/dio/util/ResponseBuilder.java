package com.example.dio.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ResponseBuilder {


     /**
     * Helps to create success response with data including the Http status code , message and data itself
     * @param status it specifies the status code of the request
     * @param message it specifies the success message of the request
     * @param data  it specifies the data which is involved .
     * @return it returns the data of type ResponseEntity<ResponseStructure<T>>
     * */


    public static<T>ResponseEntity<ResponseStructure<T>> success(HttpStatus status,String message,T data){
        ResponseStructure<T> structure=ResponseStructure.<T>builder()
                .status(status.value())
                .message(message)
                .data(data)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(structure);



    }

    public static <T> ResponseEntity<ResponseStructure<T>> success(HttpStatus status, HttpHeaders headers,String message,T data){

        ResponseStructure<T> structure =ResponseStructure.<T>builder()
                .status(status.value())
                .message(message)
                .data(data)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED)
                .headers(headers)
                .body(structure);
    }

    /**
     * Helps to create error response including Httpstatus code,message itself.
     * @param status returns the status type and  status cod of an error
     * @param message returns the message of the errror
     * @return it returns  the data of the type ResponseEntity<SimpleErrorResponse>>
     **/

public static ResponseEntity<SimpleErrorResponse> error(HttpStatus status,String message){
        SimpleErrorResponse error=SimpleErrorResponse.builder()
                .type(status.name())
                .message(message)
                .status(status.value())
                .build();

        return ResponseEntity.status(status)
                .body(error);

}
    public static ResponseEntity<FieldErrorResponse>error(HttpStatus status, String message, List<FieldErrorResponse.FieldError> errors){
        FieldErrorResponse error =FieldErrorResponse.builder()
                .type(status.name())
                .status(status.value())
                .message(message)
                .build();
        return  ResponseEntity.status(status)
                .body(error);
    }

    /**
     *Generates a successful HTTP response with a 200 OK status, wrapping the provided data and message in a ResponseStructure. This method simplifies creating standard responses for successful requests.
     * @param data to be included in the response body. It can be of any type (T).
     * @param message  to be included in the response, typically indicating the result or success of the operation.
     * @return  A ResponseEntity containing a ResponseStructure object. The ResponseStructure holds the provided message and data, and the response has a 200 OK status.
     */

    public static<T> ResponseEntity<ResponseStructure<T>>ok(T data,String message){
        return success(HttpStatus.OK,message,data);
    }

    /**
     *Generates a successful HTTP response with a 200 OK status, wrapping the provided data and message in a ResponseStructure. This method simplifies creating standard responses for successful requests.
     * @param data to be included in the response body. It can be of any type (T).
     * @param message  to be included in the response, typically indicating the result or success of the operation.
     * @return  A ResponseEntity containing a ResponseStructure object. The ResponseStructure holds the provided message and data, and the response has a 200 OK status.
     */


    public static<T> ResponseEntity<ResponseStructure<T>>created(T data,String message){
        return success(HttpStatus.CREATED,message,data);

    }

    /**
     *Generates an HTTP 404 Not Found response with a custom error message. This method is typically used to return an error when a requested resource is not found.
     * @param message describing why the resource was not found.
     * @return A ResponseEntity containing a SimpleErrorResponse object, which holds the provided error message. The response will have an HTTP status of 404 Not Found.
     */

    public static ResponseEntity<SimpleErrorResponse>notFound(String message){
        return error(HttpStatus.NOT_FOUND,message);
    }

}
