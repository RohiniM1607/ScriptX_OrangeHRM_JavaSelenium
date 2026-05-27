package com.utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {

    public String[] getCSVData(String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        br.readLine();
        String line = br.readLine();
        br.close();
        if (line == null) {
            throw new RuntimeException("CSV file does not contain test data");
        }

        return line.split(",");
    }
}