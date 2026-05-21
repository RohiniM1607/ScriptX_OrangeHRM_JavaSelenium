package com.utilities;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	XSSFWorkbook workbook;
	XSSFSheet sheet;

	public ExcelUtility(String path) {

		try {

			FileInputStream fis = new FileInputStream(path);

			workbook = new XSSFWorkbook(fis);

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public String getData(String sheetName, int row, int cell) {

		sheet = workbook.getSheet(sheetName);

		Cell celldata = sheet.getRow(row).getCell(cell);

		DataFormatter formatter = new DataFormatter();

		return formatter.formatCellValue(celldata);
	}
}