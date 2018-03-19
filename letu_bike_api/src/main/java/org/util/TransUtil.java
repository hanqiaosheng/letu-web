package org.util;

/**
 * 转换类通用函数
 */
public class TransUtil {

    /**
     * 首字母大写转换
     * @param str
     * @return
     */
    public static String transInitialUpper(String str){
        //正则判断字符串是否是纯字母
        boolean is_alpha = str.matches("[a-zA-Z]+");
        if(!is_alpha){
            return str;
        }
        char[] cs = str.toCharArray();
        //asii编码前移
        cs[0]-=32;
        return String.valueOf(cs);
    }

    /**
     * 首字母小写转换
     * @param str
     * @return
     */
    public static String transInitiallower(String str){
        //正则判断字符串是否是纯字母
        boolean is_alpha = str.matches("[a-zA-Z]+");
        if(!is_alpha){
            return str;
        }
        char[] cs = str.toCharArray();
        //asii编码后移
        cs[0]+=32;
        return String.valueOf(cs);
    }

    /**
     * 将数据字段名转换为Criteria标准名字
     * @param property_name
     * @return
     */
    public static String transCriteriaFunctionName(String property_name){
        String[] tmp_str = property_name.split("_");
        String new_str = new String();
        for(String sub_str:tmp_str){
            sub_str = transInitialUpper(sub_str);
            new_str+=sub_str;
        }
        return new_str;
    }
}
