import com.aspose.cells.SaveFormat;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.util.List;

public class FileWriterImpl implements FileWriter{

    @Override
    public void writeToFile(List<Result> resultList) throws Exception {
        String[] columns = {"#", "sellerId", "oriMinPrice", "oriMaxPrice",
                "promotionId", "startTime", "endTime", "phase", "productTitle",
                "minPrice", "maxPrice", "discount", "orders", "productImage",
                "productDetailUrl", "shopUrl", "trace", "totalTranpro3",
                "productPositiveRate", "productAverageStar", "itemEvalTotalNum"}; //List of columns that we paste at the top of table

        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet sheet = workbook.createSheet("Zipy Aliexpress page goods");

        Font headerFont = workbook.createFont(); //Fonts for header columns
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 12);

        Font bodyFont = workbook.createFont(); //Fonts for result columns
        bodyFont.setFontHeightInPoints((short) 12);

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        CellStyle bodyCellStyle = workbook.createCellStyle();
        bodyCellStyle.setFont(bodyFont);

        Row headerRow = sheet.createRow(0);

        for (int i = 0; i < columns.length; i++) { //Loop to fill first row of table with columns
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }



        final int[] rowNum = {1};

        resultList.forEach(result -> { //for each result filling rows
            Row row = sheet.createRow(rowNum[0]++);
            row.createCell(0).setCellValue(rowNum[0] - 1); //Setting row number ( -1 because we start from 2'nd row)

            for (int i = 1; i < result.getClass().getDeclaredFields().length; i++) {
                try {
                    row.createCell(i)  //this loop is feeling table with results. i've used Reflection to fill it automatically
                            .setCellValue(result.getClass().getDeclaredFields()[i].get(result).toString());  //getting values of result
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
            row.setRowStyle(bodyCellStyle);
        });


        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i); //Required to make columns width increasing in depend of result size
        }


        String filePath = "src/main/resources/";
        String fileName = "Excel.xlsx";

        FileOutputStream fileOut = new FileOutputStream(filePath + fileName);
        workbook.write(fileOut); //writing the result to file


        fileOut.close(); //closing stream
        workbook.close();

        new com.aspose.cells.Workbook(filePath + fileName)
                .save(filePath + "CSV.csv", SaveFormat.CSV);
        //this method tranforms XLSX file to CSV and saves at same filepath

    }


}
