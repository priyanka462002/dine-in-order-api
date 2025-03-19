package com.example.dio.dto.request;

import com.example.dio.enums.TableStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TableRequest {

    @Min(value = 1, message = "Capacity must be at least 1")
    @Max(value = 20, message = "Capacity cannot be more than 20")
    private long tableCapacity;

    @Enumerated(EnumType.STRING)
    private TableStatus tableStatus;

}
