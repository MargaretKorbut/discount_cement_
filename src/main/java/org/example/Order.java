package org.example;

import java.time.LocalDateTime;

public record Order(LocalDateTime dateTime, String companyName, double kg) {
}
