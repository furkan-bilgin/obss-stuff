package com.furkanbilgin.week3.services.impl;

import com.furkanbilgin.week3.components.Calculator;
import com.furkanbilgin.week3.services.CalcService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PrimarySchoolCalcService implements CalcService {
    private final Calculator calculator;

    public PrimarySchoolCalcService(@Qualifier("simpleCalculator") Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public double calculate(double... numbers) {
        return calculator.calculate(
            java.util.Arrays.stream(numbers)
                .boxed()
                .toList()
        );
    }
}
