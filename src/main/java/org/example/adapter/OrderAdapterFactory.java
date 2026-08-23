package org.example.adapter;

import org.example.source.OrderSource;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class OrderAdapterFactory {

    private final Map<String, Function<Path, OrderSource>> registry;

    public OrderAdapterFactory() {
        registry = new HashMap<>();
        registerDefaultAdapters();
    }

    private void registerDefaultAdapters() {
        registry.put(".txt", TxtOrderAdapter::new);
        registry.put("", NoExtensionOrderAdapter::new);
    }

    public OrderSource create(Path path) {
        String name = path.getFileName().toString();
        String ext = name.contains(".") ? name.substring(name.lastIndexOf('.')) : "";
        Function<Path, OrderSource> factory = registry.get(ext);
        if (factory == null) {
            throw new IllegalArgumentException("Неизвестный формат файла: " + name);
        }
        return factory.apply(path);
    }
}