package com.test.fibonacci.businesslogic.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class Response {

    @Setter
    @Getter
    private Long nextNumber;
    @Setter
    @Getter
    private List<Long> fibonacciSequence;
}