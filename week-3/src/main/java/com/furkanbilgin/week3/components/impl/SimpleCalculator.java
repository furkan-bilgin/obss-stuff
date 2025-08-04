package com.furkanbilgin.week3.components.impl;

import com.furkanbilgin.week3.components.Calculator;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("simpleCalculator")
public class SimpleCalculator implements Calculator {

    @Override
    public double calculate(List<Double> numbers) {
       return numbers.stream().reduce(0.0, Double::sum);
    }
}
