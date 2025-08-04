package com.furkanbilgin.week3.services.impl;

import com.furkanbilgin.week3.components.Calculator;
import com.furkanbilgin.week3.services.CalcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class AcademicCalcService implements CalcService {
    private final Calculator calculator;

    @Autowired
    public AcademicCalcService(@Qualifier("scientificCalculator") Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public double calculate(double... numbers) {
       return calculator.calculate(
            Arrays.stream(numbers)
                .boxed()
                .toList()
        );
    }
}
