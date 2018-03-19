package org.util;

/**
 * 折扣 工具
 * @author Administrator
 *
 */
public class LevelUtil {

	/**
	 * 根据经验获取等级
	 * @param userExp
	 * @return
	 */
	public static Integer getLevel(Integer userExp){
		if(userExp<51){
			return 0 ;
		}else if(userExp<201){
			return 1;
		}else if(userExp<501){
			return  2;
		}else if(userExp<2001){
			return  3;
		}else if(userExp<4001){
			return  4;
		}else if(userExp<7001){
			return  5;
		}else if(userExp<15001){
			return  6;
		}else if(userExp<30001){
			return  7;
		}else{
			return  8;
		}
		
		
	}
	
	
	/**
	 * 根据等级获取所需最低经验
	 * @param userExp
	 * @return
	 */
	public static Integer getMinExp(Integer userLevel){
		Integer minExp =0;
		switch (userLevel) {
		case 1:
			minExp = 51;
			break;
		case 2:
			minExp = 201;
			break;
		case 3:
			minExp = 501;
			break;
		case 4:
			minExp = 2001;
			break;
		case 5:
			minExp = 4001;
			break;
		case 6:
			minExp = 7001;
			break;
		case 7:
			minExp = 15001;
			break;
		case 8:
			minExp = 30001;
			break;
		}
		return minExp;
		
	}
	
	
	
	/**
	 * 根据用户等级获取折扣
	 * @return
	 */
	public static Double getDiscount(Integer userLevel) {
		Double discount =null;
		switch (userLevel) {
		case 1:
			discount = 1.0;
			break;
		case 2:
			discount = 0.95;
			break;
		case 3:
			discount = 0.9;
			break;
		case 4:
			discount = 0.85;
			break;
		case 5:
			discount = 0.80;
			break;
		case 6:
			discount = 0.75;
			break;
		case 7:
			discount = 0.70;
			break;
		}
		return discount;
		
	}
	
}
