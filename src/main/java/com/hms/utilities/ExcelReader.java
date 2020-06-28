package com.hms.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	
	
	// 4 classes
	public FileInputStream file;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public FileOutputStream fileout;
	public String path;
	
	
	public ExcelReader(String path) throws IOException {
		 this.path = path;
		 file = new FileInputStream(path);
		 workbook =  new XSSFWorkbook(file);
	}
	
	
	public String getCellData(String sheetName, int colNum, int rowNum) throws IOException {
		
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNum-1);
		cell = row.getCell(colNum);
		
		String output = cell.getStringCellValue();
		return output;
		
	}
	
	public void setCellData(String sheetName, int colNum, int rowNum, String data) throws IOException {
		
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		
		if(row==null) {
			row = sheet.createRow(rowNum);
		}
		cell = row.getCell(colNum);
		
		if(cell==null) {
			cell = row.createCell(colNum);
		}
		cell.setCellValue(data);
		fileout = new FileOutputStream(path);
		workbook.write(fileout);
		fileout.close();
		
	}
	
	public void setCellData(String sheetName, String columnName, int rowNum, String data) throws IOException {
			
		int colNum = 0;
		sheet = workbook.getSheet(sheetName);

		row = sheet.getRow(0);
		for (int i = 0; i < row.getLastCellNum(); i++) {
			if (row.getCell(i).getStringCellValue().equals(columnName))
				colNum = i;
		}

		row = sheet.getRow(rowNum-1);

		if(row==null) {
			row = sheet.createRow(rowNum-1);
		}
		cell = row.getCell(colNum);

		if(cell==null) {
			cell = row.createCell(colNum);
		}
		cell.setCellValue(data);
		fileout = new FileOutputStream(path);
		workbook.write(fileout);
		fileout.close();
			
		}
	
	public String getCellData(String sheetName, String columnName, int rowNum) throws IOException {
		
		int colNum = 0;
		sheet = workbook.getSheet(sheetName);
		
		row = sheet.getRow(0);
		for (int i = 0; i < row.getLastCellNum(); i++) {
			if (row.getCell(i).getStringCellValue().equals(columnName))
				colNum = i;
		}
		
		row = sheet.getRow(rowNum-1);
		cell = row.getCell(colNum);

		if (cell.getCellType() == CellType.STRING) {

			return cell.getStringCellValue();
		}

		else if (cell.getCellType() == CellType.NUMERIC || cell.getCellType() == CellType.FORMULA) {
			String cellText = String.valueOf(cell.getNumericCellValue());
			return cellText;

		}

		else if (cell.getCellType() == CellType.BOOLEAN) {

			return String.valueOf(cell.getBooleanCellValue());
		}

		else if (cell.getCellType() == CellType.BLANK) {
			return "";
		}
		return null;
	
	}
	
	 // returns number of  rows in a sheet
	  public int getRowCount(String sheetName)
	  {
		  sheet = workbook.getSheet(sheetName);
		  int number=sheet.getLastRowNum()+1;
		  return number;
		   
	  }
	  
	  
	  public int getColumnCount(String sheetName)
	  {
		  sheet = workbook.getSheet(sheetName);
		   row = sheet.getRow(0);
		   return row.getLastCellNum();
	  }
	  
	  public boolean isSheetExist(String sheetName)
	  {
		   int index = workbook.getSheetIndex(sheetName);
		   index=workbook.getSheetIndex(sheetName.toUpperCase());
		   if(index==-1){
			   
			   return false;
		   }
		   return true;  
	  }
	  
	  
	public static void main(String[] args) throws IOException {
		
		ExcelReader excel = new ExcelReader(System.getProperty("user.dir")+"\\src\\main\\resources\\TestData.xlsx");
		
		String out1 = excel.getCellData("Admission", 0, 2);
		System.out.println(out1);
		
		String out = excel.getCellData("Admission", "Name", 2);
		System.out.println(out);
		
		int num = excel.getRowCount("Login");
		System.out.println(num);
		
		excel.setCellData("Login", "Username", num+1, "Dhoni");
		excel.setCellData("Login", "Password", num+1, "Dhoni");
		
		System.out.println(excel.isSheetExist("Login1"));
		System.out.println(excel.getColumnCount("Login"));
		
	}
}
