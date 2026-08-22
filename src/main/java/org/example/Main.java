package org.example;

import org.example.adapter.OrderAdapterFactory;
import org.example.source.OrderSource;

import java.nio.file.Path;
import java.util.List;
import java.io.IOException;
import java.util.Map;

public class Main {

    public static void main (String[] args) throws IOException {

        Path inputFile = Path.of(args[0]);
        OrderSource source = OrderAdapterFactory.create(inputFile);
        List<Order> orders = source.getOrders();

        OrderCalculator calculator = new OrderCalculator(9.0, 0.50, 0.05);
        Map<String, Double> result = calculator.calculate(orders);
        FileService fileService = new FileService();
        fileService.writeResult(result, "result.txt");
    }
}