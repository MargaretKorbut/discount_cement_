package org.example.adapter;

import org.example.source.OrderSource;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class OrderAdapterFactory {

    private static final Map<String, Function<Path, OrderSource>> REGISTRY = new HashMap<>();

    static {
        register(".txt", TxtOrderAdapter::new);
        register("", NoExtensionOrderAdapter::new);
    }

    public static void register(String extension, Function<Path, OrderSource> factory) {
        REGISTRY.put(extension, factory);
    }

    public static OrderSource create(Path path) {
        String name = path.getFileName().toString();
        String ext = name.contains(".") ? name.substring(name.lastIndexOf('.')) : "";
        Function<Path, OrderSource> factory = REGISTRY.get(ext);
        if (factory == null) {
            throw new IllegalArgumentException("Неизвестный формат файла: " + name);
        }
        return factory.apply(path);
    }
}
