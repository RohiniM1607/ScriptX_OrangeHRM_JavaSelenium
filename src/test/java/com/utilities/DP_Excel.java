package com.utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.*;
import org.testng.annotations.DataProvider;

public class DP_Excel {

    @DataProvider(name = "createUserWithoutMandatoryFieldData")
    public Object[][] createUserMandatoryFieldData() throws IOException {
        return getExcelData("src/test/resources/TestData/CreateUserCredential.xlsx","Sheet1");
    }

    public Object[][] getExcelData(String fileName, String sheetName) throws IOException {

        FileInputStream fis = new FileInputStream(fileName);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet(sheetName);

        XSSFRow row = sheet.getRow(0);

        int rows = sheet.getPhysicalNumberOfRows();
        int cols = row.getPhysicalNumberOfCells();

        Object[][] data = new Object[rows - 1][cols];

        for (int i = 1; i < rows; i++) {
            row = sheet.getRow(i);
            for (int j = 0; j < cols; j++) {
                Cell cell = row.getCell(j);
                data[i - 1][j] = (cell != null) ? cell.toString() : "";
            }
        }

        workbook.close();
        fis.close();

        return data;
    }
}