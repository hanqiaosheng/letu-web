package org.start;


import com.alibaba.dubbo.container.Main;


public class DubboProviderWeixin {
	
//	private static final Log log = LogFactory.getLog(DubboProvider.class);  
	  
    public static void main(String[] args) {  
        try {  
        	Main.main(args);
        } catch (Exception e) {  
        }  
        synchronized (DubboProviderWeixin.class) {  
            while (true) {  
                try {  
                    DubboProviderWeixin.class.wait();  
                } catch (InterruptedException e) {  
//                    log.error("== synchronized error:",e);  
                }
            }
        }
    }
}
