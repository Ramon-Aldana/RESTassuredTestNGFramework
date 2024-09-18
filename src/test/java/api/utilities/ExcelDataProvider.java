package api.utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelDataProvider {

	@DataProvider(name = "Data")
	public String[][] getAllData() throws IOException {
		return ExcelDataProvider.readExcelToArray(".//testdata/userdata.xlsx", null);
	}
	
	@DataProvider(name = "UserName")
	public String[][] getUserName() throws IOException {
		return ExcelDataProvider.readExcelToArray(".//testdata/userdata.xlsx", 1); //quick implementation. We know username is in second column
	}

	public static String[][] readExcelToArray(String excelFilePath, Integer colIndex) throws IOException {
		FileInputStream fis = new FileInputStream(excelFilePath);
		Workbook workbook = new XSSFWorkbook(fis);
		Sheet sheet = workbook.getSheetAt(0); // Get the first sheet

		// Determine the number of rows and columns
		int rows = sheet.getPhysicalNumberOfRows();
		int cols = sheet.getRow(0).getPhysicalNumberOfCells();

		
		// Create a two-dimensional array to hold the data
		String[][] data = new String[rows-1][(colIndex==null) ? cols :  1]; //If I want a column then only return 1 column in data

		// Read the data into the array
		for (int i = 1; i < rows; i++) { // Start from 1 as 0 has the column name
			Row row = sheet.getRow(i);
			for (int j = 0; j < cols; j++) {
				//if I find the column I want then proceed.
				if (colIndex == null || j==colIndex) { //Enter if I want all columns (colIndex==null) or found the particular column
					Cell cell = row.getCell(j);
					data[i-1][(colIndex==null) ? j :  0] = (cell != null) ? cell.toString() : ""; // Store cell value as string handling empty cells
				}
			}
		}

		// Close the workbook and input stream
		workbook.close();
		fis.close();

		return data; // Return the 2D array
	}

	//Pending in case we can use for apicall chaining
	public static String[] getColumnData(String[][] data, int columnIndex) {
		if (data.length == 0 || columnIndex < 0 || columnIndex >= data[0].length) {
			return new String[0]; // Return an empty array if invalid index
		}

		String[] columnData = new String[data.length];
		for (int i = 0; i < data.length; i++) {
			columnData[i] = data[i][columnIndex]; // Extract data for the specified column
		}
		return columnData; // Return the column data
	}
}
