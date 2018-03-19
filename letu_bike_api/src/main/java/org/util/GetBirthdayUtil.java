package org.util;


public class GetBirthdayUtil {
	   /**
     * 根据身份编号获取生日
     *
     * @param idCard 身份编号
     * @return 生日(yyyyMMdd)
     */
    public static String getBirthByIdCard18(String idCard) {
        return idCard.substring(6, 14);
    }

    /**
     * 根据身份编号获取生日年
     *
     * @param idCard 身份编号
     * @return 生日(yyyy)
     */
    public static String getYearByIdCard18(String idCard) {
        return idCard.substring(6, 10);
    }

    /**
     * 根据身份编号获取生日月
     *
     * @param idCard
     *            身份编号
     * @return 生日(MM)
     */
    public static String getMonthByIdCard18(String idCard) {
        return idCard.substring(10, 12);
    }

    /**
     * 根据身份编号获取生日天
     *
     * @param idCard
     *            身份编号
     * @return 生日(dd)
     */
    public static String getDateByIdCard18(String idCard) {
        return idCard.substring(12, 14);
    }

    /**
     * 根据身份编号获取性别
     *
     * @param idCard 身份编号
     * @return 性别(M-男，F-女，N-未知)
     */
    public static Integer getGenderByIdCard18(String idCard) {
        Integer sGender = 3;//未知

        String sCardNum = idCard.substring(16, 17);
        if (Integer.parseInt(sCardNum) % 2 != 0) {
            sGender = 1;//男
        } else {
            sGender = 2;//女
        }
        return sGender;
    }
    
    /**
     * 根据身份编号获取生日年
     *
     * @param idCard 身份编号
     * @return 生日(yyyy)
     */
    public static String getYearByIdCard15(String idCard) {
        return idCard.substring(6, 8);
    }

    /**
     * 根据身份编号获取生日月
     *
     * @param idCard
     *            身份编号
     * @return 生日(MM)
     */
    public static String getMonthByIdCard15(String idCard) {
        return idCard.substring(8, 10);
    }

    /**
     * 根据身份编号获取生日天
     *
     * @param idCard
     *            身份编号
     * @return 生日(dd)
     */
    public static String getDateByIdCard15(String idCard) {
        return idCard.substring(10, 12);
    }

    /**
     * 根据身份编号获取性别
     *
     * @param idCard 身份编号
     * @return 性别(M-男，F-女，N-未知)
     */
    public static Integer getGenderByIdCard15(String idCard) {
        Integer sGender = 0;//未知

        String sCardNum = idCard.substring(14, 15);
        if (Integer.parseInt(sCardNum) % 2 != 0) {
            sGender = 1;//男
        } else {
            sGender = 2;//女
        }
        return sGender;
    }

}