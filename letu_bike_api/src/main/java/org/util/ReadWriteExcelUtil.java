package org.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.entity.dto.Bike;
import org.entity.dto.BikeFixInfo;
import org.entity.dto.BikeLockInfo;
import org.entity.dto.User;


/**
 * Excel组件
 * 
 * @author Snowolf
 * @version 1.0
 * @since 1.0
 */
public class ReadWriteExcelUtil {

	/**
	 * Excel 2003
	 */
	private final static String XLS = "xls";
	/**
	 * Excel 2007
	 */
	private final static String XLSX = "xlsx";

	public static void main(String[] args) {
		String path = "C:/Users/hellopeace/Desktop/单车/test.xls";
		List<Bike> list = null;
		try {
			list = ReadWriteExcelUtil.exportListFromExcel(new File(path), 0);
			System.out.println(list.toString());
		} catch (IOException e) {

		}
	}

	/**
	 * 由Excel文件的Sheet导出至List
	 * 
	 * @param file
	 * @param sheetNum
	 * @return
	 */
	public static List<Bike> exportListFromExcel(File file, int sheetNum) throws IOException {
		return exportListFromExcel(new FileInputStream(file), FilenameUtils.getExtension(file.getName()), sheetNum);
	}

	/**
	 * 由Excel流的Sheet导出至List
	 * 
	 * @param is
	 * @param extensionName
	 * @param sheetNum
	 * @return
	 * @throws IOException
	 */
	public static List<Bike> exportListFromExcel(InputStream is, String extensionName, int sheetNum)
			throws IOException {

		Workbook workbook = null;

		if (extensionName.toLowerCase().equals(XLS)) {
			workbook = new HSSFWorkbook(is);
		} else if (extensionName.toLowerCase().equals(XLSX)) {
			workbook = new XSSFWorkbook(is);
		}

		return exportListFromExcel(workbook, sheetNum);
	}

	/**
	 * 由指定的Sheet导出至List
	 * 
	 * @param workbook
	 * @param sheetNum
	 * @return
	 * @throws IOException
	 */
	private static List<Bike> exportListFromExcel(Workbook workbook, int sheetNum) {

		Sheet sheet = workbook.getSheetAt(sheetNum);

		// 解析公式结果
		FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

		List<Bike> bikes = new ArrayList<Bike>();
		int minRowIx = sheet.getFirstRowNum()+1;
		int maxRowIx = sheet.getLastRowNum();
		for (int rowIx = minRowIx; rowIx <= maxRowIx; rowIx++) {
			int count = 0;
			Bike bike = new Bike();
			Row row = sheet.getRow(rowIx);
			if(null!=row){
				StringBuilder sb = new StringBuilder();
				short minColIx = (short) (row.getFirstCellNum());
				short maxColIx = row.getLastCellNum();
				for (short colIx = minColIx; colIx <= maxColIx; colIx++) {
					Cell cell = row.getCell(new Integer(colIx));
					CellValue cellValue = evaluator.evaluate(cell);
					if (cellValue == null) {
						continue;
					}
					// 经过公式解析，最后只存在Boolean、Numeric和String三种数据类型，此外就是Error了
					// 其余数据类型，根据官方文档，完全可以忽略http://poi.apache.org/spreadsheet/eval.html
					switch (cellValue.getCellType()) {
					case Cell.CELL_TYPE_BOOLEAN:
						break;
					case Cell.CELL_TYPE_NUMERIC:
						// 这里的日期类型会被转换为数字类型，需要判别后区分处理
						if (DateUtil.isCellDateFormatted(cell)) {
							sb.append(cell.getDateCellValue());
							//bike.setBikeCreatetime(cell.getDateCellValue());
						} else {
							if (cell.getNumericCellValue() - (int) cell.getNumericCellValue() < Double.MIN_VALUE) {
								// 是否为int型
								//sb.append(cell.getNumericCellValue());
								DecimalFormat df = new DecimalFormat("#");
								if(count==0){
									bike.setBikeCode(df.format(cell.getNumericCellValue()));
								}else if(count == 1){
									//String str = StringUtil.trimStr(cellValue.getStringValue());  //去空格
									bike.setLockCode(df.format(cell.getNumericCellValue()));
								}else if(count == 2){
									bike.setBikeManagerName(String.valueOf(cell.getNumericCellValue()));
								}else if(count==3){
									bike.setBikeManagerTel(df.format(cell.getNumericCellValue()));
								}else if(count==4){
									bike.setSimCode(df.format(cell.getNumericCellValue()));
								}
							} else {
								// 是否为double型
								DecimalFormat df = new DecimalFormat("#");
								if(count==0){
									bike.setBikeCode(df.format(cell.getNumericCellValue()));
								}else if(count == 1){
									//String str = StringUtil.trimStr(cellValue.getStringValue());  //去空格
									bike.setLockCode(df.format(cell.getNumericCellValue()));
								}else if(count == 2){
									bike.setBikeManagerName(String.valueOf(cell.getNumericCellValue()));
								}else if(count==3){
									bike.setBikeManagerTel(df.format(cell.getNumericCellValue()));
								}else if(count==4){
									bike.setSimCode(df.format(cell.getNumericCellValue()));
								}
							}
						}
						break;
					case Cell.CELL_TYPE_STRING:
						
						if(count==0){
							String str = StringUtil.trimStr(cellValue.getStringValue());  //去空格
							bike.setBikeCode(str);
						}else if(count == 1){
							String str = StringUtil.trimStr(cellValue.getStringValue());  //去空格
							bike.setLockCode(str);
						}else if(count == 2){
							String str = StringUtil.trimStr(cellValue.getStringValue());  //去空格
							bike.setBikeManagerName(str);
						}else if(count == 3){
							
						}else if(count == 4){
							String str = StringUtil.trimStr(cellValue.getStringValue());  //去空格
							bike.setSimCode(str);
						}
						break;
					case Cell.CELL_TYPE_FORMULA:
						break;
					case Cell.CELL_TYPE_BLANK:
						break;
					case Cell.CELL_TYPE_ERROR:
						break;
					default:
						break;
					}
					count++;
				}
			}
			if(null!=bike.getBikeCode()){
				bikes.add(bike);
			}
			
		
		}
		return bikes;
	}

	/**
	 * 由Excel流的Sheet导出至List
	 * 
	 * @param is
	 * @param extensionName
	 * @param sheetNum
	 * @return
	 * @throws IOException
	 */
	public static List<BikeLockInfo> exportLockListFromExcel(InputStream is, String extensionName, int sheetNum)
			throws IOException {

		Workbook workbook = null;

		if (extensionName.toLowerCase().equals(XLS)) {
			workbook = new HSSFWorkbook(is);
		} else if (extensionName.toLowerCase().equals(XLSX)) {
			workbook = new XSSFWorkbook(is);
		}

		return exportLockListFromExcel(workbook, sheetNum);
	}

	/**
	 * 由指定的Sheet导出至List
	 * 
	 * @param workbook
	 * @param sheetNum
	 * @return
	 * @throws IOException
	 */
	private static List<BikeLockInfo> exportLockListFromExcel(Workbook workbook, int sheetNum) {

		Sheet sheet = workbook.getSheetAt(sheetNum);

		// 解析公式结果
		FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

		List<BikeLockInfo> locks = new ArrayList<BikeLockInfo>();
		int minRowIx = sheet.getFirstRowNum()+1;
		int maxRowIx = sheet.getLastRowNum();
		for (int rowIx = minRowIx; rowIx <= maxRowIx; rowIx++) {
			int count = 0;
			BikeLockInfo bikeLockInfo = new BikeLockInfo();
			Row row = sheet.getRow(rowIx);
			if(null!=row){
				//StringBuilder sb = new StringBuilder();
				short minColIx = (short) (row.getFirstCellNum());
				short maxColIx = row.getLastCellNum();
				for (short colIx = minColIx; colIx <= maxColIx; colIx++) {
					Cell cell = row.getCell(new Integer(colIx));
					CellValue cellValue = evaluator.evaluate(cell);
					if (cellValue == null) {
						continue;
					}
					// 经过公式解析，最后只存在Boolean、Numeric和String三种数据类型，此外就是Error了
					// 其余数据类型，根据官方文档，完全可以忽略http://poi.apache.org/spreadsheet/eval.html
					switch (cellValue.getCellType()) {
					case Cell.CELL_TYPE_BOOLEAN:
						break;
					case Cell.CELL_TYPE_NUMERIC:
						// 这里的日期类型会被转换为数字类型，需要判别后区分处理
						DecimalFormat df = new DecimalFormat("#");
						bikeLockInfo.setBikeLockCode(df.format(cellValue.getNumberValue()));
						break;
					case Cell.CELL_TYPE_STRING:
						bikeLockInfo.setBikeLockCode(cellValue.getStringValue());
						break;
					case Cell.CELL_TYPE_FORMULA:
						break;
					case Cell.CELL_TYPE_BLANK:
						break;
					case Cell.CELL_TYPE_ERROR:
						break;
					default:
						break;
					}
					count++;
				}
			}
			if(null!=bikeLockInfo.getBikeLockCode()){
				locks.add(bikeLockInfo);
			}
		}
		return locks;
	}
	
	/**
	 * 由Excel流的Sheet导出至List
	 * 
	 * @param is
	 * @param extensionName
	 * @param sheetNum
	 * @return
	 * @throws IOException
	 */
	public static List<BikeFixInfo> exportListExcel(InputStream is, String extensionName, int sheetNum)
			throws IOException {

		Workbook workbook = null;

		if (extensionName.toLowerCase().equals(XLS)) {
			workbook = new HSSFWorkbook(is);
		} else if (extensionName.toLowerCase().equals(XLSX)) {
			workbook = new XSSFWorkbook(is);
		}

		return exportFromExcel(workbook, sheetNum);
	}

	
	/**
	 * 由指定的Sheet导出至List（bikeFixInfo）
	 * 
	 * @param workbook
	 * @param sheetNum
	 * @return
	 * @throws IOException
	 */
	private static List<BikeFixInfo> exportFromExcel(Workbook workbook, int sheetNum) {

		Sheet sheet = workbook.getSheetAt(sheetNum);

		// 解析公式结果
		FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

		List<BikeFixInfo> bikeFixInfos = new ArrayList<BikeFixInfo>();
		int minRowIx = sheet.getFirstRowNum()+1;
		int maxRowIx = sheet.getLastRowNum();
		for (int rowIx = minRowIx; rowIx <= maxRowIx; rowIx++) {
			int count = 0;
			BikeFixInfo bikeFixInfo = new BikeFixInfo();
			Row row = sheet.getRow(rowIx);
			StringBuilder sb = new StringBuilder();
			short minColIx = (short) (row.getFirstCellNum());
			short maxColIx = row.getLastCellNum();
			for (short colIx = minColIx; colIx <= maxColIx; colIx++) {
				Cell cell = row.getCell(new Integer(colIx));
				CellValue cellValue = evaluator.evaluate(cell);
				if (cellValue == null) {
					continue;
				}
				// 经过公式解析，最后只存在Boolean、Numeric和String三种数据类型，此外就是Error了
				// 其余数据类型，根据官方文档，完全可以忽略http://poi.apache.org/spreadsheet/eval.html
				switch (cellValue.getCellType()) {
				case Cell.CELL_TYPE_BOOLEAN:
					break;
				case Cell.CELL_TYPE_NUMERIC:
					// 这里的日期类型会被转换为数字类型，需要判别后区分处理
					if (DateUtil.isCellDateFormatted(cell)) {
						sb.append(cell.getDateCellValue());
						//bike.setBikeCreatetime(cell.getDateCellValue());
					} else {
						if (cell.getNumericCellValue() - (int) cell.getNumericCellValue() < Double.MIN_VALUE) {
							// 是否为int型
							sb.append(cell.getNumericCellValue());
							if(count==0){
								Long value =(long) cell.getNumericCellValue();
								//bike.setBikeManagerId(value);
							}else if(count==2){
								Long lockvalue = (long) cell.getNumericCellValue();
								//bike.setBikeLockId(lockvalue);
							}
							
						} else {
							// 是否为double型
							DecimalFormat df = new DecimalFormat("#");
							//student.setStuTel(df.format(cell.getNumericCellValue()));
						}
					}
					break;
				case Cell.CELL_TYPE_STRING:
					
					if(count==0){
						//bikeFixInfo.setBikecode(cellValue.getStringValue());
					}else if(count == 1){
						//String str = StringUtil.trimStr(cellValue.getStringValue());  //去空格
						bikeFixInfo.setFixMan(cellValue.getStringValue());
					}else if(count == 2){
						bikeFixInfo.setFixTel(cellValue.getStringValue());
					}else if(count == 3){
						DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
						try {
							bikeFixInfo.setFixStarttime(format.parse(cellValue.getStringValue()));
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else if(count == 4){
						DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
						try {
							bikeFixInfo.setFixEndtime(format.parse(cellValue.getStringValue()));
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else if(count == 5){
						bikeFixInfo.setFixRemark(cellValue.getStringValue());
					}else if(count == 6){
					}
					
					break;
				case Cell.CELL_TYPE_FORMULA:
					break;
				case Cell.CELL_TYPE_BLANK:
					break;
				case Cell.CELL_TYPE_ERROR:
					break;
				default:
					break;
				}
				count++;
			}
			bikeFixInfos.add(bikeFixInfo);
		
		}
		return bikeFixInfos;
	}
	/**
	 * 由Excel流的Sheet导出至List
	 * 
	 * @param is
	 * @param extensionName
	 * @param sheetNum
	 * @return
	 * @throws IOException
	 */
	public static List<User> exportVillagerListExcel(InputStream is, String extensionName, int sheetNum)
			throws IOException {

		Workbook workbook = null;

		if (extensionName.toLowerCase().equals(XLS)) {
			workbook = new HSSFWorkbook(is);
		} else if (extensionName.toLowerCase().equals(XLSX)) {
			workbook = new XSSFWorkbook(is);
		}

		return exportVillagerListFromExcel(workbook, sheetNum);
	}
	
	/**
	 * 由指定的Sheet导出至List
	 * 
	 * @param workbook
	 * @param sheetNum
	 * @return
	 * @throws IOException
	 */
	private static List<User> exportVillagerListFromExcel(Workbook workbook, int sheetNum) {

		Sheet sheet = workbook.getSheetAt(sheetNum);

		// 解析公式结果
		FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

		//List<Bike> bikes = new ArrayList<Bike>();
		List<User> users = new ArrayList<User>();
		int minRowIx = sheet.getFirstRowNum()+1;
		int maxRowIx = sheet.getLastRowNum();
		for (int rowIx = minRowIx; rowIx <= maxRowIx; rowIx++) {
			int count = 0;
			User user = new User();
			Row row = sheet.getRow(rowIx);
			if(null!=row){
				StringBuilder sb = new StringBuilder();
				short minColIx = (short) (row.getFirstCellNum());
				short maxColIx = row.getLastCellNum();
				for (short colIx = minColIx; colIx <= maxColIx; colIx++) {
					Cell cell = row.getCell(new Integer(colIx));
					CellValue cellValue = evaluator.evaluate(cell);
					if (cellValue == null) {
						continue;
					}
					// 经过公式解析，最后只存在Boolean、Numeric和String三种数据类型，此外就是Error了
					// 其余数据类型，根据官方文档，完全可以忽略http://poi.apache.org/spreadsheet/eval.html
					switch (cellValue.getCellType()) {
					case Cell.CELL_TYPE_BOOLEAN:
						break;
					case Cell.CELL_TYPE_NUMERIC:
						// 这里的日期类型会被转换为数字类型，需要判别后区分处理
						DecimalFormat df = new DecimalFormat("#");
						if(count==0){
						user.setUserTel(df.format(cellValue.getNumberValue()));
						}
						else if(count==1){
							user.setUserIdcard(df.format(cellValue.getNumberValue()));
						}
						else if(count==2){
							user.setAccountAvailableBalance(BigDecimal.valueOf(cell.getNumericCellValue()));
						}
						break;
					case Cell.CELL_TYPE_STRING:
						if(count==1){
							user.setUserIdcard(cellValue.getStringValue());
						}
						break;
					case Cell.CELL_TYPE_FORMULA:
						break;
					case Cell.CELL_TYPE_BLANK:
						break;
					case Cell.CELL_TYPE_ERROR:
						break;
					default:
						break;
					}
					count++;
				}
			}
			if(null!=user.getUserTel()&&null!=user.getUserIdcard()){
				users.add(user);
			}
			
		
		}
		return users;
	}

	/**
	 * 导入游客excel
	 * @param is
	 * @param extensionName
	 * @param sheetNum
	 * @return
	 * @throws IOException
	 */
	public static List<User> exportTourist(InputStream is, String extensionName, int sheetNum) throws IOException{
		Workbook workbook = null;
		if (extensionName.toLowerCase().equals(XLS)) {
			workbook = new HSSFWorkbook(is);
		} else if (extensionName.toLowerCase().equals(XLSX)) {
			workbook = new XSSFWorkbook(is);
		}
		return exportTourist(workbook,sheetNum);
	}

	/**
	 * 游客导入
	 * @param workbook
	 * @param sheetNum
	 * @return
	 */
	private static List<User> exportTourist(Workbook workbook, int sheetNum){
		Sheet sheet = workbook.getSheetAt(sheetNum);
		// 解析公式结果
		FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
		List<User> users = new ArrayList<>();
		int minRowIx = sheet.getFirstRowNum()+1;
		int maxRowIx = sheet.getLastRowNum();
		for (int rowIx = minRowIx; rowIx <= maxRowIx; rowIx++) {
			int count = 0;
			User user = new User();
			Row row = sheet.getRow(rowIx);
			if(null!=row){
				StringBuilder sb = new StringBuilder();
				short minColIx = (short) (row.getFirstCellNum());
				short maxColIx = row.getLastCellNum();
				for (short colIx = minColIx; colIx <= maxColIx; colIx++) {
					Cell cell = row.getCell(new Integer(colIx));
					CellValue cellValue = evaluator.evaluate(cell);
					if (cellValue == null) {
						continue;
					}
					// 经过公式解析，最后只存在Boolean、Numeric和String三种数据类型，此外就是Error了
					// 其余数据类型，根据官方文档，完全可以忽略http://poi.apache.org/spreadsheet/eval.html
					switch (cellValue.getCellType()) {
						case Cell.CELL_TYPE_BOOLEAN:
							break;
						case Cell.CELL_TYPE_NUMERIC:
							// 这里的日期类型会被转换为数字类型，需要判别后区分处理
							DecimalFormat df = new DecimalFormat("#");
							if(count==1){
								user.setUserTel(df.format(cellValue.getNumberValue()));
							}
							else if(count==2){
								user.setUserIdcard(df.format(cellValue.getNumberValue()));
							}
							break;
						case Cell.CELL_TYPE_STRING:
							if(count==0){
								user.setUserRealname(cellValue.getStringValue());
							}
							else if(count==1){
								user.setUserTel(cellValue.getStringValue());
							}
							else if(count==2){
								user.setUserIdcard(cellValue.getStringValue());
							}
							break;
//						case Cell.CELL_TYPE_FORMULA:
//							break;
//						case Cell.CELL_TYPE_BLANK:
//							break;
//						case Cell.CELL_TYPE_ERROR:
//							break;
						default:
							break;
					}
					count++;
				}
			}
			if(null!=user.getUserTel()&&null!=user.getUserIdcard()){
				users.add(user);
			}
		}
		return users;
	}
}
