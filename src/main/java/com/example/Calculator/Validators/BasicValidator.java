package com.example.Calculator.Validators;

import com.example.Calculator.Common.CalculatorDto;
import com.example.Calculator.Common.Operation;
import com.example.Calculator.Common.Validator;

public class BasicValidator implements Validator {
    @Override
    public Boolean validate(CalculatorDto calculatorDto, Operation operation) {
        if (calculatorDto == null) return false;
        return !Operation.DIVISION.equals(operation) || (calculatorDto.getSecondValue() != 0);
    }
}
