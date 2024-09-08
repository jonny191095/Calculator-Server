package com.example.Calculator.Config;

import com.example.Calculator.Common.Validator;
import com.example.Calculator.Validators.BasicValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CalculatorConfig {

    @Bean
    public Validator validator() {
        return new BasicValidator();
    }
}
