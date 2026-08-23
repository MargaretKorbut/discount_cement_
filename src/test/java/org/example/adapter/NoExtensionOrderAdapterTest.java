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

class NoExtensionOrderAdapterTest {

    @TempDir
    Path tempDir;

    @Test
    void parsesOrdersFromHashDelimitedFile() throws IOException {
        Path file = tempDir.resolve("orders");
        Files.writeString(file,
                "2026-01-01T10:00:00#Компания1#100\n" +
                        "2026-01-01T11:00:00#Компания2#50\n");

        NoExtensionOrderAdapter adapter = new NoExtensionOrderAdapter(file);
        List<Order> orders = adapter.getOrders();

        assertEquals(2, orders.size());
        assertEquals("Компания1", orders.get(0).companyName());
        assertEquals("Компания2", orders.get(1).companyName());
    }
}