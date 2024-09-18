package api.utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import api.payloads.User;

import java.io.FileInputStream;
import java.io.IOException;

public class UserExcelReader {

    private static final String FILE_PATH = "path_to_your_excel_file.xlsx";

    public User getUserData(int rowNum) throws IOException {
        FileInputStream file = new FileInputStream(FILE_PATH);
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);  // Assuming first sheet

        Row row = sheet.getRow(rowNum);

        if (row == null) {
            workbook.close();
            throw new IllegalArgumentException("Row " + rowNum + " does not exist in the sheet.");
        }

        User user = new User();

        // Assuming columns are ordered as: id, username, firstName, lastName, email, password, phone, userStatus
        user.setId((int) row.getCell(0).getNumericCellValue());
        user.setUsername(row.getCell(1).getStringCellValue());
        user.setFirstName(row.getCell(2).getStringCellValue());
        user.setLastName(row.getCell(3).getStringCellValue());
        user.setEmail(row.getCell(4).getStringCellValue());
        user.setPassword(row.getCell(5).getStringCellValue());
        user.setPhone(row.getCell(6).getStringCellValue());
        user.setUserStatus((int) row.getCell(7).getNumericCellValue());

        workbook.close();
        return user;
    }
}
