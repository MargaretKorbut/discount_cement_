package org.example.adapter;

import org.example.Order;
import org.example.source.OrderSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractOrderAdapter implements OrderSource {

    private final Path filePath;
    private final String delimiter;

    protected AbstractOrderAdapter(Path filePath, String delimiter) {
        this.filePath = filePath;
        this.delimiter = delimiter;
    }

    @Override
    public List<Order> getOrders() throws IOException {
        List<Order> orders = new ArrayList<>();
        List<String> lines = Files.readAllLines(filePath);

        for (String line : lines) {
            String[] parts = line.split(delimiter);
            LocalDateTime dateTime = LocalDateTime.parse(parts[0]);
            String companyName = parts[1].trim();
            double kg = Double.parseDouble(parts[2]);
            orders.add(new Order(dateTime, companyName, kg));
        }
        return orders;
    }
}