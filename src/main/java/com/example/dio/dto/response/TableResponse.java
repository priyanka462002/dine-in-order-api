package com.example.dio.dto.response;

import com.example.dio.enums.TableStatus;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class TableResponse {

    private long tableId;

    private long tableNo;

    private long tableCapacity;

    private TableStatus tableStatus;


}
