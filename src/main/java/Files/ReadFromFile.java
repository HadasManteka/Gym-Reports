package Files;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;

public class ReadFromFile {
    XSSFWorkbook wb;

    public ReadFromFile(String path) throws IOException, InvalidFormatException {
        OPCPackage fs = OPCPackage.open(new File(path));
        wb = new XSSFWorkbook(fs);
    }

    public void close() {
        try {
            wb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public XSSFSheet getSheet() {
        return wb.getSheetAt(0);
    }

    public int calcColumnNum() {
        int rows = getSheet().getPhysicalNumberOfRows();
        XSSFRow row;

        int cols = 0; // No of columns
        int tmp = 0;

        // This trick ensures that we get the data properly even if it doesn't start from first few rows
        for(int i = 0; i < 10 || i < rows; i++) {
            row = getSheet().getRow(i);
            if(row != null) {
                tmp = getSheet().getRow(i).getPhysicalNumberOfCells();
                if(tmp > cols) cols = tmp;
            }
        }

        return cols;
    }
}
