package org.example.adapter;

import org.example.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TxtOrderAdapterTest {

    @TempDir
    Path tempDir;

    @Test
    void parsesOrdersFromPipeDelimitedFile() throws IOException {
        Path file = tempDir.resolve("orders.txt");
        Files.writeString(file,
                "2026-01-01T10:00:00|Компания1|100\n" +
                        "2026-01-01T11:00:00|Компания2|50\n");

        TxtOrderAdapter adapter = new TxtOrderAdapter(file);
        List<Order> orders = adapter.getOrders();

        assertEquals(2, orders.size());

        assertEquals(LocalDateTime.of(2026, 1, 1, 10, 0), orders.get(0).dateTime());
        assertEquals("Компания1", orders.get(0).companyName());
        assertEquals(100, orders.get(0).kg());

        assertEquals(LocalDateTime.of(2026, 1, 1, 11, 0), orders.get(1).dateTime());
        assertEquals("Компания2", orders.get(1).companyName());
        assertEquals(50, orders.get(1).kg());
    }

    @Test
    void trimsWhitespaceFromCompanyName() throws IOException {
        Path file = tempDir.resolve("orders.txt");
        Files.writeString(file, "2026-01-01T10:00:00| Компания с пробелами |100\n");

        TxtOrderAdapter adapter = new TxtOrderAdapter(file);
        List<Order> orders = adapter.getOrders();

        assertEquals("Компания с пробелами", orders.get(0).companyName());
    }
}