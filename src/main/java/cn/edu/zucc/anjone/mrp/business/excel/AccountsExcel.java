package cn.edu.zucc.anjone.mrp.business.excel;

import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import cn.edu.zucc.anjone.mrp.business.dto.AccountsDto;

public class AccountsExcel extends AbstractExcelView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String fileName = "账目.xls";  
		response.setCharacterEncoding("UTF-8");  
		response.setContentType("application/ms-excel");  
		response.setHeader("Content-Disposition", "inline; filename="+new String(fileName.getBytes(),"iso8859-1"));  
		OutputStream outputStream = response.getOutputStream();

		// 产生Excel表头
		HSSFSheet sheet = workbook.createSheet("账目信息");
		HSSFRow header = sheet.createRow(0);

		//首行格式
		HSSFCellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(HSSFColor.YELLOW.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		// 产生标题列
		HSSFCell c = header.createCell(0);	c.setCellValue("订单时间");	c.setCellStyle(style);
		c = header.createCell(1); 			c.setCellValue("类型");		c.setCellStyle(style);
		c = header.createCell(2);			c.setCellValue("用户编号");	c.setCellStyle(style);
		c = header.createCell(3);			c.setCellValue("用户名称");	c.setCellStyle(style);
		c = header.createCell(4);			c.setCellValue("订单编号");	c.setCellStyle(style);
		c = header.createCell(5);			c.setCellValue("总金额");		c.setCellStyle(style);
		c = header.createCell(6);			c.setCellValue("订单状态");	c.setCellStyle(style);
		c = header.createCell(7);			c.setCellValue("备注");		c.setCellStyle(style);

		List<AccountsDto> list = (List<AccountsDto>) model.get("list");

		DateFormat formater = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		int rowNumber = 1;
		for (AccountsDto dto : list) {
			HSSFRow row = sheet.createRow(rowNumber++);

			row.createCell(0).setCellValue( formater.format(dto.getDate()) );
			row.createCell(1).setCellValue(dto.getType().equals("0") ? "应收" : "应付");
			row.createCell(2).setCellValue(dto.getPeopleNumber());
			row.createCell(3).setCellValue(dto.getPeopleName());
			row.createCell(4).setCellValue(dto.getOrderNumber());
			row.createCell(5).setCellValue(dto.getAmount());
			row.createCell(6).setCellValue(dto.getState().equals("0") ? "未完成" : "已完成");
			row.createCell(7).setCellValue(dto.getRemark());
		}
		workbook.write(outputStream);
		outputStream.flush();
		outputStream.close();
	}
}
