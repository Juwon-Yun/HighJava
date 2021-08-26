package kr.or.ddit.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class WriteExcel {

	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("MYMEMBER");
			
			XSSFRow row = sheet.createRow(0);
			row.createCell(0).setCellValue("MEM_ID");
			row.createCell(1).setCellValue("MEM_PASS");
			row.createCell(2).setCellValue("MEM_NAME");
			row.createCell(3).setCellValue("MEM_TEL");
			row.createCell(4).setCellValue("MEM_ADDR");
			int r = 1;
			conn = DBUtil3.getConnection();
			String sql = "SELECT * FROM MYMEMBER";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while( rs.next() ) {
				String id = rs.getString("MEM_ID");
				String pass = rs.getString("MEM_PASS");
				String name = rs.getString("MEM_NAME");
				String tel = rs.getString("MEM_TEL");
				String addr = rs.getString("MEM_ADDR");
				
				row = sheet.createRow(r++);
				row.createCell(0).setCellValue(id);
				row.createCell(1).setCellValue(pass);
				row.createCell(2).setCellValue(name);
				row.createCell(3).setCellValue(tel);
				row.createCell(4).setCellValue(addr);
			}
			FileOutputStream fos = new FileOutputStream("E://D_Other/ddit.xlsx");
			workbook.write(fos);
			
			workbook.close();
			fos.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
