import com.aspose.cells.SaveFormat;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.util.List;

public class FileWriter {

    public void writeToFile(List<Result> resultList) throws Exception {
        String[] columns = {"#", "sellerId", "oriMinPrice", "oriMaxPrice",
                "promotionId", "startTime", "endTime", "phase", "productTitle",
                "minPrice", "maxPrice", "discount", "orders", "productImage",
                "productDetailUrl", "shopUrl", "trace", "totalTranpro3",
                "productPositiveRate", "productAverageStar", "itemEvalTotalNum"};

        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet sheet = workbook.createSheet("Zipy Aliexpress page goods");

        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 12);

        Font bodyFont = workbook.createFont();
        bodyFont.setFontHeightInPoints((short) 12);

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        Row headerRow = sheet.createRow(0);

        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }

        CellStyle bodyCellStyle = workbook.createCellStyle();
        bodyCellStyle.setFont(bodyFont);
        final int[] rowNum = {1};
        resultList.forEach(result -> {
            Row row = sheet.createRow(rowNum[0]++);
            row.createCell(0).setCellValue(rowNum[0] - 1);

            for (int i = 1; i < result.getClass().getDeclaredFields().length; i++) {
                try {
                    row.createCell(i)
                            .setCellValue(result.getClass().getDeclaredFields()[i].get(result).toString());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }

            row.setRowStyle(bodyCellStyle);
        });


        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }


        String filePath = "src/main/resources/";
        String fileName = "Excel.xlsx";
        FileOutputStream fileOut = new FileOutputStream(filePath + fileName);
        workbook.write(fileOut);
        fileOut.close();

        workbook.close();


        com.aspose.cells.Workbook csvWorkbook = new com.aspose.cells.Workbook(filePath + fileName);
        csvWorkbook.save(filePath + "CSV.csv", SaveFormat.CSV);

    }


}
