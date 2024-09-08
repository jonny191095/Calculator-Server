package com.example.Calculator.Functions;

import java.util.function.BiFunction;

public class Multiplication implements BiFunction<Double, Double, Double> {
    @Override
    public Double apply(Double first, Double second) {
        return first * second;
    }
}
