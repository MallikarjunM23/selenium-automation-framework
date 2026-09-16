package utils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	public Object[][] getData(String sheetName) throws IOException {

		String path = System.getProperty("user.dir") + "/src/test/java/resources/TestDataSheet.xlsx";

		FileInputStream fis = new FileInputStream(path);

		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet(sheetName);
		DataFormatter formatter = new DataFormatter();

		int rows = sheet.getPhysicalNumberOfRows();
		int cols = sheet.getRow(0).getPhysicalNumberOfCells();

		Object[][] data = new Object[rows - 1][cols];

		for (int i = 1; i < rows; i++) {

			for (int j = 0; j < cols; j++) {

				data[i - 1][j] = formatter.formatCellValue(sheet.getRow(i).getCell(j));
			}
		}

		workbook.close();
		fis.close();

		return data;
	}
}