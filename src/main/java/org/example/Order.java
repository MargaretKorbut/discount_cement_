package org.example;

import java.time.LocalDateTime;

public record Order(LocalDateTime dateTime, String companyName, double kg) {
    public Order {
        if (kg < 0) {
            throw new IllegalArgumentException("Количество кг не может быть отрицательным: " + kg);
        }
    }
}
