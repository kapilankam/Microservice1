package com.centime.microservice1.exception;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ErrorResponse {
    private String message;
    private List<String> details;

}
