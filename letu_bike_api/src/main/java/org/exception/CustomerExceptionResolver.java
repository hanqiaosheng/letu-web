package org.exception;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class CustomerExceptionResolver implements HandlerExceptionResolver{
	
	protected final Logger logger = LoggerFactory.getLogger(CustomerExceptionResolver.class);

	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handle, Exception exception) {
		CustomException customException = null;
		CustomExceptionWeixin customExceptionWeixin = null;
		  //将错误信息传到页面
        ModelAndView modelAndView = new ModelAndView();
		if(exception instanceof CustomException){
			customException = (CustomException)exception;
			//指向错误页面
            modelAndView.setViewName("exception");
		}else if(exception instanceof CustomExceptionWeixin){
			customExceptionWeixin = new CustomExceptionWeixin(exception.getMessage());
	            //指向错误页面
		}else{
			customException = new CustomException(exception.getMessage());
			//指向错误页面
            modelAndView.setViewName("exception");
		}
		
		//将错误信息传到页面
        if(customException!=null){
            String message = customException.getMessage();
            logger.error(message,exception);
            modelAndView.addObject("message", message);
        }
        if(customExceptionWeixin!=null){
            String message_weixin = customExceptionWeixin.getMessage();
            logger.error(message_weixin,exception);
            response.setContentType("application/json;charset=utf-8");
    		try {
    			PrintWriter printWriter = response.getWriter();
    			printWriter.write("{msg:系统异常}");
    			printWriter.flush();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    		
        }
        
		return modelAndView;
	}

}
