package org.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

public class PdfUtil {

	public static String pdfCreate(String templatePath,String uploadPath,String fixed,BigDecimal fee,Integer size) throws IOException, DocumentException {
    	long time = System.currentTimeMillis();
    	String namepdf = String.valueOf(time)+".pdf";
		PdfReader reader = new PdfReader(templatePath); // 模版文件目录
		PdfStamper ps = new PdfStamper(reader, new FileOutputStream(uploadPath+namepdf)); // 生成的输出流
		BaseFont bf = BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
		AcroFields s =  ps.getAcroFields();
		//设置文本域表单的字体
		// 对于模板要显中文的，在此处设置字体比在pdf模板中设置表单字体的好处：
		//1.模板文件的大小不变；2.字体格式满足中文要求
	//	s.setFieldProperty("中文测试","textfont",bf,null);
		//编辑文本域表单的内容
		//骑行地点
		if(null!=fixed){
			s.setField("fixed", fixed);
		}
		//骑行时间
		Date today = new Date();
		s.setField("time", DateUtil.format(today));
		//费用
		s.setField("fee", fee.toString());
		ps.setFormFlattening(true); // 这句不能少
		ps.close();
		reader.close();  
		return namepdf;
	}
	
	
	public static void main(String[] args) throws Exception {
		pdfCreate("F:/pdfFile/moban/pdffile.pdf","F:/pdfFile/target/","中央",new BigDecimal(1),1);
	}

}
