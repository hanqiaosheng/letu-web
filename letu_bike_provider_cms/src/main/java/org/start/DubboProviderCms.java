package org.start;


import com.alibaba.dubbo.container.Main;


public class DubboProviderCms {
	
//	private static final Log log = LogFactory.getLog(DubboProvider.class);  
	  
    public static void main(String[] args) {  
        try {  
        	Main.main(args);
        } catch (Exception e) {  
        }  
        synchronized (DubboProviderCms.class) {  
            while (true) {  
                try {  
                    DubboProviderCms.class.wait();  
                } catch (InterruptedException e) {  
//                    log.error("== synchronized error:",e);  
                }
            }
        }
    }
}
