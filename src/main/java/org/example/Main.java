package org.example;

import org.example.adapter.OrderAdapterFactory;
import org.example.source.OrderSource;

import java.nio.file.Path;
import java.util.List;
import java.io.IOException;
import java.util.Map;

public class Main {

    private static final double PRICE_PER_KG = 9.0;
    private static final double START_DISCOUNT = 0.50;
    private static final double DISCOUNT_STEP = 0.05;
    private static final String RESULT_FILE_NAME = "result.txt";

    public static void main(String[] args) throws IOException {

        if (args.length == 0) {
            System.out.println("Не передан путь к файлу с заказами. Пример запуска: java -jar app.jar discount_day.txt");
            return;
        }

        Path inputFile = Path.of(args[0]);
        OrderSource source = OrderAdapterFactory.create(inputFile);
        List<Order> orders = source.getOrders();

        OrderCalculator calculator = new OrderCalculator(PRICE_PER_KG, START_DISCOUNT, DISCOUNT_STEP);
        Map<String, Double> result = calculator.calculate(orders);

        FileService fileService = new FileService();
        fileService.writeResult(result, RESULT_FILE_NAME);
    }
}