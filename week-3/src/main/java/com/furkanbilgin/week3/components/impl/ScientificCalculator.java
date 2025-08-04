package com.furkanbilgin.week3.components.impl;

import com.furkanbilgin.week3.components.Calculator;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("scientificCalculator")
public class ScientificCalculator implements Calculator {
    @Override
    public double calculate(List<Double> numbers) {
        return numbers.stream().reduce(1.0, (a, b) -> a * b);
    }
}
