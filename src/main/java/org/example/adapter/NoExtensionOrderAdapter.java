package org.example.adapter;

import java.nio.file.Path;

public class NoExtensionOrderAdapter extends AbstractOrderAdapter {

    public NoExtensionOrderAdapter(Path filePath) {
        super(filePath, "#");
    }
}
