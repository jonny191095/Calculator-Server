package com.example.Calculator.Common;

public interface Validator {
    Boolean validate(CalculatorDto calculatorDto, Operation operation);
}
