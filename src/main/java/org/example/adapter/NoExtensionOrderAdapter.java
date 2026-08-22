package org.example.adapter;

import org.example.Order;
import org.example.source.OrderSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NoExtensionOrderAdapter implements OrderSource {

    private final Path filePath;

    public NoExtensionOrderAdapter(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Order> getOrders() throws IOException {
        List<Order> orders = new ArrayList<>();
        List<String> lines = Files.readAllLines(filePath);

        for (String line : lines) {
            String[] parts = line.split("#");
            LocalDateTime dateTime = LocalDateTime.parse(parts[0]);
            String companyName = parts[1].trim();
            double kg = Double.parseDouble(parts[2]);
            orders.add(new Order(dateTime, companyName, kg));
        }
        return orders;
    }
}
