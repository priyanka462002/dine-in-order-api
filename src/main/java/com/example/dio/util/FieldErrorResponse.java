package com.example.dio.util;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.validation.FieldError;

import java.util.List;

@Getter
@SuperBuilder
public class FieldErrorResponse extends SimpleErrorResponse{

    private List<FieldError> errors;

    /**
     * createFiled error is used to initialize the custom FieldError object
     *
     * @param message return the error message
     * @param rejectedValue return the wrong value which hsa been entered.
     * @param field returns the field due the which error is occured.
     * @return returns the error object of type (FiledError nested static class).
     * */


    public static FieldError createFieldError(String message,Object rejectedValue,String field){
          FieldError error=new FieldError();
          error.message=message;
          error.rejectedValue=rejectedValue;
          error.field=field;

          return  error;
    }
    @Getter
    public static class FieldError{
        private String message;
        private Object rejectedValue;
        private String  field;
    }



}
