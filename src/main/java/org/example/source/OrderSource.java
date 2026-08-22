package org.example.source;

import org.example.Order;
import java.io.IOException;
import java.util.List;

public interface OrderSource {
    List<Order> getOrders() throws IOException;
}
