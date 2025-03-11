package com.example.dio.dto.response;

import com.example.dio.enums.TableStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TableResponse {

    private long tableNo;
    private long tableCapacity;
    private TableStatus tableStatus;
    private LocalDate createAt;
    private LocalDate lastModifiedAt;

}
