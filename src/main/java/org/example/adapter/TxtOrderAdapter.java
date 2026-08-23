package org.example.adapter;

import java.nio.file.Path;

public class TxtOrderAdapter extends AbstractOrderAdapter {

    public TxtOrderAdapter(Path filePath) {
        super(filePath, "\\|");
    }
}