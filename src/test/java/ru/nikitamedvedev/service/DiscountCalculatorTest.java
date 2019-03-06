package ru.nikitamedvedev.service;

import org.junit.Test;

import static org.junit.Assert.*;

public class DiscountCalculatorTest {

    private DiscountCalculator discountCalculator = new DiscountCalculator();

    @Test
    public void shouldAlmostEqual25IfYearIsHuge() {

        Double result = discountCalculator.calculate(0, 100000000);

        assertEquals(25d, result, 1d);
    }

    @Test
    public void shouldCalculate() {
        Double result = discountCalculator.calculate(5000, 3);

        assertEquals(13.8d, result, 1d);
    }
}