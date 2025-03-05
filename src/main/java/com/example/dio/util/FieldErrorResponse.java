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
