package Files;

import entities.StudioHoldsDays;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class PrintToExcel {

    static XSSFWorkbook workbook = new XSSFWorkbook();

    public static void write(String path, Map<String, Object[]> data) throws IOException {
        XSSFSheet spreadsheet = workbook.createSheet(" Gym Data ");
        XSSFRow row;
//        Map<String, Object[]> studentData
//                = new TreeMap<String, Object[]>();

        Set<String> keySet = data.keySet();

        int rowId = 1;
        XSSFRow headerRow = spreadsheet.createRow(0);

        // writing the data into the sheets...
        for (String key : keySet) {

            row = spreadsheet.createRow(rowId++);
            Object[] objectArr = data.get(key);

            for (int cellId=0; cellId<objectArr.length; cellId++) {
                Cell cell;

                if(objectArr[cellId] != null) {
                    cell = row.createCell(cellId);
                    cell.setCellValue(((StudioHoldsDays) objectArr[cellId]).getNumberOfStudio());

                    cell = headerRow.createCell(cellId);
                    cell.setCellValue(cellId + "-" + ((StudioHoldsDays) objectArr[cellId]).getDayName());
                }
            }
        }

        // writing the workbook into the file...
        FileOutputStream out = new FileOutputStream(new File(path));
        workbook.write(out);
        out.close();
    }
}
