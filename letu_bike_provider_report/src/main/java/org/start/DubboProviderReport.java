package org.start;


import com.alibaba.dubbo.container.Main;


public class DubboProviderReport {
	
//	private static final Log log = LogFactory.getLog(DubboProvider.class);  
	  
    public static void main(String[] args) {  
        try {  
        	Main.main(args);
        } catch (Exception e) {  
        }  
        synchronized (DubboProviderReport.class) {  
            while (true) {  
                try {  
                    DubboProviderReport.class.wait();  
                } catch (InterruptedException e) {  
//                    log.error("== synchronized error:",e);  
                }
            }
        }
    }
}
