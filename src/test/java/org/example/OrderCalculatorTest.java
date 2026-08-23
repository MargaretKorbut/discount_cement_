package org.example;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderCalculatorTest {

    private static final double PRICE_PER_KG = 9.0;
    private static final double DELTA = 0.001;

    @Test
    void firstOrderGetsStartDiscount() {
        OrderCalculator calculator = new OrderCalculator(PRICE_PER_KG, 0.50, 0.05);
        Order order = new Order(LocalDateTime.of(2026, 1, 1, 10, 0), "Компания1", 100);

        Map<String, Double> result = calculator.calculate(List.of(order));

        assertEquals(450.0, result.get("Компания1"), DELTA);
    }

    @Test
    void discountDecreasesByStepForEachNextOrder() {
        OrderCalculator calculator = new OrderCalculator(PRICE_PER_KG, 0.50, 0.05);
        Order order1 = new Order(LocalDateTime.of(2026, 1, 1, 10, 0), "Компания1", 100);
        Order order2 = new Order(LocalDateTime.of(2026, 1, 1, 11, 0), "Компания2", 50);

        Map<String, Double> result = calculator.calculate(List.of(order1, order2));

        assertEquals(450.0, result.get("Компания1"), DELTA);
        assertEquals(247.5, result.get("Компания2"), DELTA);
    }

    @Test
    void discountNeverGoesBelowZero() {
        OrderCalculator calculator = new OrderCalculator(PRICE_PER_KG, 0.05, 0.10);
        Order order1 = new Order(LocalDateTime.of(2026, 1, 1, 10, 0), "Компания1", 20);
        Order order2 = new Order(LocalDateTime.of(2026, 1, 1, 11, 0), "Компания2", 30);

        Map<String, Double> result = calculator.calculate(List.of(order1, order2));

        assertEquals(171.0, result.get("Компания1"), DELTA);
        assertEquals(270.0, result.get("Компания2"), DELTA);
    }

    @Test
    void ordersFromSameCompanyAreSummed() {
        OrderCalculator calculator = new OrderCalculator(PRICE_PER_KG, 0.50, 0.05);
        Order order1 = new Order(LocalDateTime.of(2026, 1, 1, 10, 0), "Компания1", 100);
        Order order2 = new Order(LocalDateTime.of(2026, 1, 1, 11, 0), "Компания1", 50);

        Map<String, Double> result = calculator.calculate(List.of(order1, order2));

        assertEquals(697.5, result.get("Компания1"), DELTA);
        assertEquals(1, result.size());
    }

    @Test
    void ordersAreProcessedInTimeOrderRegardlessOfInputOrder() {
        OrderCalculator calculator = new OrderCalculator(PRICE_PER_KG, 0.50, 0.05);
        Order later = new Order(LocalDateTime.of(2026, 1, 1, 11, 0), "Компания2", 50);
        Order earlier = new Order(LocalDateTime.of(2026, 1, 1, 10, 0), "Компания1", 100);

        Map<String, Double> result = calculator.calculate(List.of(later, earlier));

        assertEquals(450.0, result.get("Компания1"), DELTA);
        assertEquals(247.5, result.get("Компания2"), DELTA);
    }
}