package resource;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelData {
	
		public static FileInputStream fis;
		public static FileOutputStream fo;
		public static XSSFWorkbook workBook;
		public static XSSFSheet sheet;
		public static XSSFRow row;
		public static XSSFCell cell;

		public static int getRowCount(String excellFile, String excellSheet) throws IOException {
			fis = new FileInputStream(excellFile);
			workBook = new XSSFWorkbook(fis);
			sheet = workBook.getSheet(excellSheet);
			int rows = sheet.getLastRowNum();
			workBook.close();
			fis.close();
			return rows;
		}

		public static int getCellCount(String excellFile, String excellSheet, int rowc) throws IOException {
			fis = new FileInputStream(excellFile);
			workBook = new XSSFWorkbook(fis);
			sheet = workBook.getSheet(excellSheet);
			row = sheet.getRow(rowc);
			int cellCount = row.getLastCellNum();
			workBook.close();
			fis.close();
			return cellCount;
		}

		public static String getCellData(String excellFile, String excellSheet, int rowNum, int coloumn)
				throws IOException {
			fis = new FileInputStream(excellFile);
			workBook = new XSSFWorkbook(fis);
			sheet = workBook.getSheet(excellSheet);
			row = sheet.getRow(rowNum);
			cell = row.getCell(coloumn);
			String data;
			try {
				DataFormatter formater = new DataFormatter();
				String cellData = formater.formatCellValue(cell);
				return cellData;
			} catch (Exception e) {
				data = "";
			}
			workBook.close();
			fis.close();
			return data;
		}

		public static void setCellData(String excellFile, String excellSheet, int rowNum, int cellNum, String data)
				throws IOException {
			fis = new FileInputStream(excellFile);
			workBook = new XSSFWorkbook(fis);
			sheet = workBook.getSheet(excellSheet);
			row = sheet.createRow(rowNum);
			cell = row.createCell(cellNum);
			cell.setCellValue(data);
			fo = new FileOutputStream(excellFile);
			workBook.write(fo);
			workBook.close();
			fis.close();
			fo.close();
		}
}