package Files;

import entities.StudioHoldsDays;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class PrintToExcel {

    static XSSFWorkbook workbook = new XSSFWorkbook();
    FileOutputStream out;
    int sheetNum = 1;

    public PrintToExcel(String filePath) {
        try {
            out = new FileOutputStream(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void createTableSheet(Map<String, Object[]> data, String sheetName) throws IOException {
        XSSFSheet spreadsheet = workbook.createSheet(sheetName);

        XSSFRow headerRow = spreadsheet.createRow(0);
        XSSFRow row;
        Set<String> keySet = data.keySet();
        int rowId = 1;

        for (String key : keySet) {

            row = spreadsheet.createRow(rowId++);
            Object[] objectArr = data.get(key);

            for (int cellId=0; cellId<objectArr.length; cellId++) {
                Cell cell;

                if(objectArr[cellId] != null) {
                    cell = row.createCell(cellId);
                    cell.setCellValue(((StudioHoldsDays) objectArr[cellId]).getNumberOfStudio());

                    if (cellId != 0) {
                        cell = headerRow.createCell(cellId);
                        cell.setCellValue(cellId + "-" + ((StudioHoldsDays) objectArr[cellId]).getDayName());
                    }
                }
            }
        }

        sheetNum++;
    }

    public void write() throws IOException {
        workbook.write(out);
        out.close();;
    }


    public void writeMap(Map<String, ?> data, String sheetName) {
        XSSFSheet spreadsheet = workbook.createSheet(sheetName);
        XSSFRow row;

        Set<String> keySet = data.keySet();
        int rowId = 0;
        int cellId;

        // writing the data into the sheets...
        for (String key : keySet) {
            cellId = 0;
            row = spreadsheet.createRow(rowId++);
            Cell cell = row.createCell(cellId++);
            cell.setCellValue(key);

            cell = row.createCell(cellId);
            cell.setCellValue(String.valueOf(data.get(key)== null ? "" : data.get(key)));
        }

        sheetNum++;
    }

}
