package com.test.fibonacci.handler;

import com.test.fibonacci.businesslogic.operation.FibonacciCalculator;
import com.test.fibonacci.businesslogic.operation.FibonacciMap;
import com.test.fibonacci.businesslogic.operation.FibonacciSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class FibonacciHandlerImpl implements FibonacciHandler {

    private FibonacciCalculator fibonacciCalculator;
    private FibonacciMap fibonacciMap;
    private FibonacciSequence fibonacciSequence;

    @Autowired
    public FibonacciHandlerImpl(FibonacciCalculator fibonacciCalculator, FibonacciMap fibonacciMap,
            FibonacciSequence fibonacciSequence) {
        this.fibonacciCalculator = fibonacciCalculator;
        this.fibonacciMap = fibonacciMap;
        this.fibonacciSequence = fibonacciSequence;
    }

    @Override
    public Long handleGet(final Long userId) {
        Long result;
        if (!fibonacciMap.hasUser(userId)) {
            fibonacciMap.addNewUser(userId);
        }
        result = fibonacciCalculator.getNextNumber(fibonacciMap.getIndexForUser(userId));
        fibonacciMap.incrementFibonacciIndexForUser(userId);
        return result;
    }

    @Override
    public List<Long> handleList(final Long userId) {
        Integer lastIndexForUser = fibonacciMap.getIndexForUser(userId);
        List<Long> subSequence = new ArrayList<>();
        if (lastIndexForUser != null) {
            subSequence = fibonacciSequence.getSequenceToIndex(lastIndexForUser);
        }
        return subSequence;
    }

    @Override
    public void handleGoingBack(final Long userId) {
        fibonacciMap.decrementFibonacciIndexForUser(userId);
    }
}