package com.example.Calculator;

import com.example.Calculator.Common.CalculatorDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CalculatorIT {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @ParameterizedTest
    @CsvSource({
            "0.0, 1.0, 1.0",
            "1, 2.0, 3.0",
            "-400, -800.0, -1200",
            "-5, 10, 5",
            "0, -0, 0.0"
    })
    public void addition_test(double first, double second, double expected) throws Exception {
        callEndpoint("/add", first, second, expected);
    }

    @ParameterizedTest
    @CsvSource({
            "0.0, 1.0, -1.0",
            "1, 2.0, -1.0",
            "-400, -800.0, 400",
            "-5, 10, -15",
            "0, -0, 0.0"
    })
    public void subtraction_test(double first, double second, double expected) throws Exception {
        callEndpoint("/subtract", first, second, expected);
    }

    @ParameterizedTest
    @CsvSource({
            "0.0, 1.0, 0.0",
            "1, 2.0, 2.0",
            "-400, -800.0, 320000",
            "-5, 10, -50",
            "0, -0, -0.0"
    })
    public void multiplication_test(double first, double second, double expected) throws Exception {
        callEndpoint("/multiply", first, second, expected);
    }

    @ParameterizedTest
    @CsvSource({
            "0.0, 1.0, 0.0",
            "1, 2.0, 0.5",
            "-400, -800.0, 0.5",
            "-5, 10, -0.5",
            "-6, -6.0, 1"
    })
    public void division_test(double first, double second, double expected) throws Exception {
        callEndpoint("/divide", first, second, expected);
    }

    @ParameterizedTest
    @CsvSource({"0","-0"})
    public void badRequest_test(double second)  throws Exception {
        CalculatorDto dto = new CalculatorDto(1, second);

        String json = objectMapper.writeValueAsString(dto);

        mvc.perform(get("/divide")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    private void callEndpoint(String url, double first, double second, double expected) throws Exception {
        CalculatorDto dto = new CalculatorDto(first, second);

        String json = objectMapper.writeValueAsString(dto);

        mvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(expected)));
    }
}
