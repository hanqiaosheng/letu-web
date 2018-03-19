package org.start;

import com.alibaba.dubbo.container.Main;


public class DubboProviderLock {
	
//	private static final Log log = LogFactory.getLog(DubboProvider.class);  
	  
    public static void main(String[] args) {  
        try {  
        	Main.main(args);
        } catch (Exception e) {  
        }  
        synchronized (DubboProviderLock.class) {  
            while (true) {  
                try {  
                    DubboProviderLock.class.wait();  
                } catch (InterruptedException e) {  
//                    log.error("== synchronized error:",e);  
                }
            }
        }
    }
}
