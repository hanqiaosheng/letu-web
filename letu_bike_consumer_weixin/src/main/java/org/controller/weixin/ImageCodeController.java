package org.controller.weixin;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.IOException;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.util.VerifyCodeUtils;
 
@Controller
@Scope("prototype")
public final class ImageCodeController {  
  
    private static final Random random = new Random();  
  
    private static final Font[] CODEFONT = { new Font("宋体", Font.BOLD, 25), new Font("宋体", Font.BOLD, 25), new Font("宋体", Font.BOLD, 25),  
            new Font("宋体", Font.BOLD, 25) };  
    private static final Color[] FONTCOLOR = { Color.ORANGE, Color.RED, Color.PINK, Color.BLUE, Color.GREEN };  
    private static final Color[] BGCOLOR = { Color.BLACK, Color.WHITE };  
    private static final Color LINECOLOR = new Color(242, 234, 22);  
    private static final String[] CODE = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",  
            "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };  
    private static StringBuffer CONDENUMBER = null;  
    private static final int WIDTH = 100, HEIGHT = 25;  
  
    @RequestMapping("code")
    public final void image(HttpServletRequest request, HttpServletResponse response) throws IOException {  
//        CONDENUMBER = new StringBuffer();  
//        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);  
//        Graphics g = image.getGraphics();  
//        g.setColor(BGCOLOR[random.nextInt(2)]);  
//        g.fillRect(0, 0, WIDTH, HEIGHT);  
//        for (int i = 0; i < 4; i++) {  
//            drawCode(g, i);  
//        }  
//        drawNoise(g, 8);  
//        //用随机数产生的颜色绘制图像
//        g.setColor(Color.gray);  
//        g.drawRect(0, 0, WIDTH - 1, HEIGHT - 1);  
//        g.dispose();  
//        
//        //将生成的验证码加入到session中
//        HttpSession session = request.getSession();
//        session.setAttribute("code", CONDENUMBER.toString());
//        //禁止图片缓存
//        response.setHeader("Pragma", "No-cache");  
//        response.setHeader("Cache-Control", "no-cache");  
//        response.setDateHeader("Expires", 0);  
//        response.setContentType("image/png");  
//        ServletOutputStream sos = null;  
//        try {  
//            sos = response.getOutputStream();  
//            ImageIO.write(image, "png", sos);  
//            sos.flush();  
//            sos.close();  
//        } catch (Exception e) {  
//            e.printStackTrace();  
//        }
    	
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");

		// 生成随机字串
		String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
		// 存入会话session
		HttpSession session = request.getSession(true);
		session.setAttribute("code", verifyCode.toLowerCase());
		// 生成图片
		int w = 200, h = 80;
		VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);  

    }  
  
    private final void drawCode(Graphics graphics, int i) {  
        String number = CODE[random.nextInt(36)];  
        graphics.setFont(CODEFONT[random.nextInt(4)]);  
        graphics.setColor(FONTCOLOR[random.nextInt(5)]);  
        graphics.drawString(number, 10 + i * 20, 20);  
        CONDENUMBER.append(number);  
    }  
  
    private final void drawNoise(Graphics graphics, int lineNumber) {  
        graphics.setColor(LINECOLOR);  
        int pointX1, pointY1, pointX2, pointY2;  
        for (int i = 0; i < lineNumber; i++) {  
            pointX1 = 1 + (int) (Math.random() * WIDTH);  
            pointY1 = 1 + (int) (Math.random() * HEIGHT);  
            pointX2 = 1 + (int) (Math.random() * WIDTH);  
            pointY2 = 1 + (int) (Math.random() * HEIGHT);  
            graphics.drawLine(pointX1, pointY1, pointX2, pointY2);  
        }  
    }  
  
}  
