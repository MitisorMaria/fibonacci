package com.test.fibonacci;

import com.test.fibonacci.businesslogic.entity.Response;
import com.test.fibonacci.controller.FibonacciController;
import com.test.fibonacci.handler.FibonacciHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.ArrayList;

import static com.test.fibonacci.util.ErrorMessages.BAD_VALUE;
import static com.test.fibonacci.util.ErrorMessages.NEGATIVE_USER_ID_MESSAGE;
import static com.test.fibonacci.util.ErrorMessages.NULL_ERROR_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@AutoConfigureJsonTesters
@WebMvcTest(FibonacciController.class)
public class FibonacciControllerTests {

    @Autowired private MockMvc mvc;
    @Autowired private JacksonTester<Response> jsonResponse;
    @MockBean private FibonacciHandler fibonacciHandler;

    @Test
    public void negativeUserId() throws Exception {
        MockHttpServletResponse response = mvc.perform(
                MockMvcRequestBuilders.get("/get?userId=-1")
                        .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonResponse.write(Response.builder()
                .message(NEGATIVE_USER_ID_MESSAGE).build()).getJson());
    }

    @Test
    public void nullUserId() throws Exception {
        MockHttpServletResponse response = mvc.perform(
                MockMvcRequestBuilders.get("/get?userId=")
                        .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonResponse.write(Response.builder()
                .message(NULL_ERROR_MESSAGE).build()).getJson());
    }

    @Test
    public void badUserId() throws Exception {
        MockHttpServletResponse response = mvc.perform(
                MockMvcRequestBuilders.get("/get?userId=bad")
                        .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonResponse.write(Response.builder()
                .message(BAD_VALUE).build()).getJson());
    }

    @Test
    public void getNextNumber() throws Exception {
        when(fibonacciHandler.handleGet(0l)).thenReturn(1l);
        MockHttpServletResponse response = mvc.perform(
                MockMvcRequestBuilders.get("/get?userId=0")
                        .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonResponse.write(Response.builder()
                .nextNumber(1l).build()).getJson());
    }

    @Test
    public void goBack() throws Exception {
        MockHttpServletResponse response = mvc.perform(
                MockMvcRequestBuilders.get("/goBack?userId=0")
                        .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void getList() throws Exception {
        when(fibonacciHandler.handleList(0l)).thenReturn(new ArrayList<>());
        MockHttpServletResponse response = mvc.perform(
                MockMvcRequestBuilders.get("/list?userId=0")
                        .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonResponse.write(Response.builder()
                .fibonacciSequence(new ArrayList<>()).build()).getJson());
    }
}