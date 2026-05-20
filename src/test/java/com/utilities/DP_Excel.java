package com.utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DP_Excel {

    public Object[][] getExcelData(String filePath, String sheetName) throws IOException {

        FileInputStream fis = new FileInputStream(filePath);

        XSSFWorkbook workbook = new XSSFWorkbook(fis);

        XSSFSheet sheet = workbook.getSheet(sheetName);

        int rowCount = sheet.getPhysicalNumberOfRows();

        int cellCount = sheet.getRow(0).getPhysicalNumberOfCells();

        Object[][] data = new Object[rowCount - 1][cellCount];

        DataFormatter formatter = new DataFormatter();

        for (int i = 1; i < rowCount; i++) {

            for (int j = 0; j < cellCount; j++) {

                data[i - 1][j] = formatter.formatCellValue(sheet.getRow(i).getCell(j));
            }
        }

        workbook.close();
        fis.close();

        return data;
    }
   
        public Object[][] getLeaveData() throws IOException {
            return getExcelData("src/test/resources/testdata/OrangeHRM.xlsx","Leave Test data");
        }
    }
