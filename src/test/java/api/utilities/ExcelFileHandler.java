package api.utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ExcelFileHandler {

    private Workbook workbook;
    private Sheet sheet;
    private String filePath;

    // Constructor to load the Excel file
    public ExcelFileHandler(String filePath) throws IOException {
        this.filePath = filePath;
        FileInputStream fileInputStream = new FileInputStream(filePath);
        this.workbook = new XSSFWorkbook(fileInputStream);
    }

    // Method to select the sheet by name or index
    public void selectSheet(int index) {
        this.sheet = workbook.getSheetAt(index);
    }

    public void selectSheet(String sheetName) {
        this.sheet = workbook.getSheet(sheetName);
    }

    // Method to read data from a specific cell
    public String readCell(int rowNumber, int columnNumber) {
        Row row = sheet.getRow(rowNumber);
        if (row != null) {
            Cell cell = row.getCell(columnNumber);
            if (cell != null) {
                return cell.toString();  // It returns the string representation of the cell.
            }
        }
        return null;
    }
    
    // Method to get the number of rows in the sheet
    public int getRowCount() {
        return sheet.getPhysicalNumberOfRows();
    }

    // Method to get the number of cells in a specific row
    public int getCellCount(int rowNumber) {
        Row row = sheet.getRow(rowNumber);
        if (row != null) {
            return row.getPhysicalNumberOfCells(); //.getLastCellNum ?
        }
        return 0; // Return 0 if the row does not exist
    }

    // Method to read an entire row as a map (columnIndex -> cellValue)
    public Map<Integer, String> readRow(int rowNumber) {
        Map<Integer, String> rowData = new HashMap<>();
        Row row = sheet.getRow(rowNumber);

        if (row != null) {
            for (Cell cell : row) {
                rowData.put(cell.getColumnIndex(), cell.toString());
            }
        }
        return rowData;
    }

    // Method to update a cell value
    public void updateCell(int rowNumber, int columnNumber, String newValue) {
        Row row = sheet.getRow(rowNumber);
        if (row == null) {
            row = sheet.createRow(rowNumber);
        }
        Cell cell = row.getCell(columnNumber);
        if (cell == null) {
            cell = row.createCell(columnNumber);
        }
        cell.setCellValue(newValue);
    }

    // Method to save changes back to the file
    public void save() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        workbook.write(fileOutputStream);
        fileOutputStream.close();
    }

    // Close the workbook
    public void close() throws IOException {
        workbook.close();
    }
}
