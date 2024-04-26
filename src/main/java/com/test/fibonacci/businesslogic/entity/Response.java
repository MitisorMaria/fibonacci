package com.test.fibonacci.businesslogic.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

/**
 * Holds the fields that can be returned when making a request to any of the endpoints in this application.
 */
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class Response {

    @Setter
    @Getter
    private Long nextNumber;
    @Setter
    @Getter
    private List<Long> fibonacciSequence;
    @Setter
    @Getter
    private String message;
}