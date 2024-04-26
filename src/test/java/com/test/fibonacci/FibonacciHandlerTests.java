package com.test.fibonacci;

import com.test.fibonacci.businesslogic.operation.FibonacciCalculator;
import com.test.fibonacci.businesslogic.operation.FibonacciMap;
import com.test.fibonacci.businesslogic.operation.FibonacciSequence;
import com.test.fibonacci.handler.FibonacciHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;


@SpringBootTest
public class FibonacciHandlerTests {

    @MockBean private FibonacciCalculator fibonacciCalculator;
    @MockBean private FibonacciSequence fibonacciSequence;
    @MockBean private FibonacciMap fibonacciMap;
    @Autowired private FibonacciHandler fibonacciHandler;

    @Test
    public void getNextNumber() {
        when(fibonacciCalculator.getNextNumber(0)).thenReturn(1l);
        when(fibonacciSequence.getElement(0)).thenReturn(1l);
        when(fibonacciMap.getIndexForUser(0l)).thenReturn(0);
        Assertions.assertEquals(1l, fibonacciHandler.handleGet(0l));
    }

    @Test
    public void listSequence() {
        List<Long> expectedList = new ArrayList<>();
        expectedList.add(1l);
        expectedList.add(1l);
        expectedList.add(2l);
        expectedList.add(3l);
        when(fibonacciSequence.getSequenceToIndex(0)).thenReturn(expectedList);
        Assertions.assertEquals(expectedList, fibonacciHandler.handleList(0l));
    }

    @Test
    public void goBack() {
        fibonacciHandler.handleGoingBack(0l);
    }
}