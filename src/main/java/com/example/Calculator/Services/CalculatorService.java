package com.example.Calculator.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.BiFunction;

@Service
public class CalculatorService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CalculatorService.class);

    public double applyFunction(double first, double second, BiFunction<Double, Double, Double> function) {
        LOGGER.info("Performing {} on {} and {}", function.getClass().getSimpleName(), first, second);
        return function.apply(first, second);
    }
}
