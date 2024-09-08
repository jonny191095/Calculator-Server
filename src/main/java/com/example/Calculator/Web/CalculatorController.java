package com.example.Calculator.Web;

import com.example.Calculator.Common.CalculatorDto;
import com.example.Calculator.Common.InvalidInputException;
import com.example.Calculator.Common.Operation;
import com.example.Calculator.Functions.Addition;
import com.example.Calculator.Functions.Division;
import com.example.Calculator.Functions.Multiplication;
import com.example.Calculator.Functions.Subtraction;
import com.example.Calculator.Services.CalculatorService;
import com.example.Calculator.Services.ValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(value = "/")
public class CalculatorController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CalculatorController.class);

    @Autowired
    private CalculatorService calculatorService;

    @Autowired
    private ValidationService validationService;

    //Authenticate request is from calculator
    //Rate limit
    //Switch out 4 endpoints for a single "Calculate" endpoint

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "add")
    public double add(@RequestBody final CalculatorDto calculatorDto) throws InvalidInputException {
        LOGGER.info("Received request to add {} and {}", calculatorDto.getFirstValue(), calculatorDto.getSecondValue());
        performValidation(calculatorDto, Operation.ADDITION);
        return calculatorService.applyFunction(calculatorDto.getFirstValue(), calculatorDto.getSecondValue(), new Addition());
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "subtract")
    public double subtract(@RequestBody final CalculatorDto calculatorDto) throws InvalidInputException {
        LOGGER.info("Received request to subtract {} from {}", calculatorDto.getFirstValue(), calculatorDto.getSecondValue());
        performValidation(calculatorDto, Operation.SUBTRACTION);
        return calculatorService.applyFunction(calculatorDto.getFirstValue(), calculatorDto.getSecondValue(), new Subtraction());
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "multiply")
    public double multiply(@RequestBody final CalculatorDto calculatorDto) throws InvalidInputException {
        LOGGER.info("Received request to multiply {} and {}", calculatorDto.getFirstValue(), calculatorDto.getSecondValue());
        performValidation(calculatorDto, Operation.MULTIPLICATION);
        return calculatorService.applyFunction(calculatorDto.getFirstValue(), calculatorDto.getSecondValue(), new Multiplication());
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "divide")
    public double divide(@RequestBody final CalculatorDto calculatorDto) throws InvalidInputException {
        LOGGER.info("Received request to divide {} by {}", calculatorDto.getFirstValue(), calculatorDto.getSecondValue());
        performValidation(calculatorDto, Operation.DIVISION);
        return calculatorService.applyFunction(calculatorDto.getFirstValue(), calculatorDto.getSecondValue(), new Division());
    }

    private void performValidation(CalculatorDto calculatorDto, Operation operation) throws InvalidInputException {
        if (!validationService.validate(calculatorDto, operation)) {
            throw new InvalidInputException("Input not valid");
        }
    }
}
