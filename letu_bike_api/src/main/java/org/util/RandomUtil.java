package org.util;

import java.util.Random;

/**
 * 生成【min,max】的随机数
 * @author Administrator
 *
 */
public class RandomUtil {

	public static Integer Random(Integer min, Integer max){
		Random random = new Random();
        Integer ran = random.nextInt(max - min + 1) + min;
        return ran;
	}
}
