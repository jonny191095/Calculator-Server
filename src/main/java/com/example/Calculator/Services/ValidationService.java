package com.example.Calculator.Services;

import com.example.Calculator.Common.CalculatorDto;
import com.example.Calculator.Common.Operation;
import com.example.Calculator.Common.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ValidationService.class);

    private final Validator validator;

    public ValidationService(Validator validator) {
        this.validator = validator;
    }

    public Boolean validate(CalculatorDto calculatorDto, Operation operation) {
        LOGGER.info("Validating");
        return validator.validate(calculatorDto, operation);
    }
}
