package org.datasource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;

public class DataSourceInterceptor {
    
    /** 数据源切换常量 */
//    public static final String DATASOURCE_READ_DB="READ";  //key
//    public static final String DATASOURCE_WRITE_DB="WRITE";
    
 // 192.168.10.72:3307/100msh_admin
// 	public static final String SOURCE_ADMIN = "ds_admin";
// 	// 192.168.10.72:3308/100msh_partner
// 	public static final String SOURCE_PARTNER = "ds_partner";
// 	// 192.168.10.72:3309/100msh_mop
// 	public static final String SOURCE_MOP = "ds_mop";

    /**
     * 设置数据源为Read数据库所对应的数据源。
     * @param jp
     */
    public void setAdminDataSource(JoinPoint jp) {
    	DataSourceContextHolder.setDbType(DataSourceType.SOURCE_ADMIN);
    }
    
    /**
     * 设置数据源为Write数据库所对应的数据源。
     * @param jp
     */
    public void setParterDataSource(JoinPoint jp) {
    	DataSourceContextHolder.setDbType(DataSourceType.SOURCE_PARTNER);
    }
    
    
//    public void setMopDataSource(JoinPoint jp) {
//    	DataSourceContextHolder.setDbType(DataSourceType.SOURCE_MOP);
//    }
}