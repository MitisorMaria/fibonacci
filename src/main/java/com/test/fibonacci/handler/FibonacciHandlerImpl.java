package com.test.fibonacci.handler;

import com.test.fibonacci.businesslogic.operation.FibonacciCalculator;
import com.test.fibonacci.businesslogic.operation.FibonacciMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class FibonacciHandlerImpl implements FibonacciHandler {

    private FibonacciCalculator fibonacciCalculator;
    private FibonacciMap fibonacciMap;

    @Autowired
    public FibonacciHandlerImpl(FibonacciCalculator fibonacciCalculator, FibonacciMap fibonacciMap) {
        this.fibonacciCalculator = fibonacciCalculator;
        this.fibonacciMap = fibonacciMap;
    }

    @Override
    public Long handleGet(final Long userId) {
        return fibonacciCalculator.getNextNumber(userId);
    }

    @Override
    public List<Long> handleList(final Long userId) {
        Integer lastIndexForUser = fibonacciMap.getIndexForUser(userId);
        List<Long> subSequence = new ArrayList<>();
        if (lastIndexForUser != null) {
            subSequence = fibonacciCalculator.getFibonacciSequence().subList(0, lastIndexForUser);
        }
        return subSequence;
    }

    @Override
    public void handleGoingBack(final Long userId) {
        fibonacciMap.decrementFibonacciIndexForUser(userId);
    }
}