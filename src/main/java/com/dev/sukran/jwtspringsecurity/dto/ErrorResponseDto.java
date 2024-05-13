package com.dev.sukran.jwtspringsecurity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class ErrorResponseDto {
    private String message;
    private String statusCode;
    private Date time;
    private List<String> details;

    public ErrorResponseDto (String message, String statusCode, List<String> details) {
        this.message = message;
        this.statusCode = statusCode;
        this.details = details;
        this.time = new Date();
    }
}
