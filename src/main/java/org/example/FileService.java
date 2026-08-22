package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class FileService {

    public void writeResult(Map<String, Double> companyTotals, String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter( new FileWriter(fileName))) {
            for (Map.Entry<String, Double> entry : companyTotals.entrySet()) {
                writer.write(entry.getKey() + " - " + String.format("%.2f", entry.getValue()));
                writer.newLine();
            }
        }
    }

}
