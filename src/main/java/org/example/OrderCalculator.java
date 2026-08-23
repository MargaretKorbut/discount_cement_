package org.example;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderCalculator {

    private final double pricePerKg;
    private final double startDiscount;
    private final double discountStep;

    public OrderCalculator(double pricePerKg, double startDiscount, double discountStep){
        this.pricePerKg = pricePerKg;
        this.startDiscount = startDiscount;
        this.discountStep = discountStep;
    }

    public Map<String, Double> calculate(List<Order> orders){

        orders.sort(Comparator.comparing(Order::dateTime));

        Map<String, Double> companyTotals = new HashMap<>();
        double discount = startDiscount;

        for (Order order : orders) {
            double orderCost = order.kg() * pricePerKg * (1 - discount);
            companyTotals.merge(order.companyName(), orderCost, Double::sum);
            discount -= discountStep;
            if (discount < 0) {
                discount = 0;
            }
        }
        return companyTotals;
    }
}
