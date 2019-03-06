package ru.nikitamedvedev.service;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class DiscountCalculator {

    public Double calculate(Integer spentMoney, Integer joinedYearsAgo) {
        return 50d * (1 / (1 + Math.exp(-(joinedYearsAgo * 0.25d + spentMoney / 10000d))) - 0.5d);
    }
}
