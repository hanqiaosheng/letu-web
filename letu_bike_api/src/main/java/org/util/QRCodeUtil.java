package org.util;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOInvalidTreeException;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import javax.imageio.stream.ImageOutputStream;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
public class QRCodeUtil {
		/** 日期格式：yyyy-MM-dd HH:mm:ss */
		public static String DF_DATETIME = "yyyyMMdd";
		private static float alpha = 1f;

		/**
		 * 
		 * @Title: toBufferedImage
		 * @Description: 把文本转化成二维码图片对象
		 * @param text
		 *            二维码内容
		 * @param width
		 *            二维码高度
		 * @param height
		 *            二位宽度
		 * @param
		 * @param
		 *
		 * @return BufferedImage 返回类型
		 * @throws
		 */
		public static BufferedImage toBufferedImage(String text, int width,
				int height) throws Exception {
			int BLACK = 0xFF000000;
			int WHITE = 0xFFFFFFFF;
			Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
			hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); // 内容所使用字符集编码
			hints.put(EncodeHintType.MARGIN, 1);
			BitMatrix matrix = new MultiFormatWriter().encode(text,
					BarcodeFormat.QR_CODE, width, height, hints);
			BufferedImage image = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
				}
			}
			return image;
		}


		/**
		 * 制作简单的二维码图片
		 * @param qrText
		 * @param qrPath
		 */
		public static boolean makeSimple(String qrText,String qrPath,String qrName) throws Exception{
			try {
				if(makeDirs(qrPath)) {
					BufferedImage image = toBufferedImage(qrText, 280, 280);
					Graphics graphics = image.getGraphics();
					graphics.drawImage(image, 0, 0, null);
					OutputStream os = new FileOutputStream(qrPath+"/"+qrName);
					ImageIO.write(image, "PNG", os);
				}else{
					return false;
				}
			}catch (Exception e){
				return false;
			}
			return true;
		}
		/**
		 * 
		 * @Title: markImageByCode
		 * @Description: 向图片指定位置增加二维码
		 * @param img
		 *            二维码image对象
		 * @param srcImgPath
		 *            背景图
		 * @param targerPath
		 *            目标图
		 * @param positionWidth
		 *            位置横坐标
		 * @param positionHeight
		 *            位置纵坐标
		 * @return void 返回类型
		 * @throws
		 */
		public static void markImageByCode(Image img, String srcImgPath,
				String targerPath, int positionWidth, int positionHeight) {
			OutputStream  os = null;
			
			try {
				System.out.println(srcImgPath+"=============================="+targerPath);


				Image srcImg = ImageIO.read(new File(srcImgPath));
				BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null),
						srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);
			    // 1、得到画笔对象
				Graphics2D g = buffImg.createGraphics();
				//设置背景透明
				buffImg = g.getDeviceConfiguration().createCompatibleImage(srcImg.getWidth(null), srcImg.getHeight(null), Transparency.TRANSLUCENT);
				g.dispose();
				g = buffImg.createGraphics();
				
				// 2、设置对线段的锯齿状边缘处理
				g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
						RenderingHints.VALUE_INTERPOLATION_BILINEAR);
				
				g.drawImage(
						srcImg.getScaledInstance(srcImg.getWidth(null),
								srcImg.getHeight(null), Image.SCALE_SMOOTH), 0, 0,
						null);

				g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
						alpha));

				// 3、二维码位置
				//g.drawImage(img, 100, 80, null);
				g.drawImage(img, 120, 63, null);
				g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
				// 4、释放资源
				g.dispose();

				// 5、生成图片（建议生成PNG的，jpg会失真）
				
				os = new FileOutputStream(targerPath);
				ImageIO.write(buffImg, "PNG", os);
			
				System.out.println("二维码图片生成成功");

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (null != os)
						os.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		
		/**
		 * 
		 * @Title: pressText
		 * @Description:向图片指定位置加上文字
		 * @param pressText
		 *            文字内容
		 * @param srcImageFile
		 *            原图片
		 * @param destImageFile
		 *            目标图片
		 * @param x
		 *            横坐标
		 * @param y
		 *            纵坐标
		 * @param alpha
		 *            透明度
		 * @return void 返回类型
		 * @throws
		 */
		public final static void pressText(String pressText, String srcImageFile,
				String destImageFile, int x, int y, float alpha) {
			try {
				File img = new File(srcImageFile);
				Image src = ImageIO.read(img);
				int width = src.getWidth(null);
				int height = src.getHeight(null);
				BufferedImage image = new BufferedImage(width, height,
						BufferedImage.TYPE_INT_RGB);
				Graphics2D g = image.createGraphics();
				//设置背景透明
				image = g.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
				g.dispose();
				g = image.createGraphics();
				
				// 开文字抗锯齿 去文字毛刺
				g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
						RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
				//设置位置
				
				g.drawImage(src, 0, 0, width, height, null);
				// 设置颜色
				g.setColor(new Color(00, 00, 00));
				// 设置 Font
				//g.setFont(new Font("Arial", Font.BOLD, 60));//加粗
			    g.setFont(new Font("Arial", Font.PLAIN, 50));
				g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
						alpha));
				// 第一参数->设置的内容，后面两个参数->文字在图片上的坐标位置(x,y) .
				
				FontMetrics metrics = g.getFontMetrics();
				x = 850-(metrics.stringWidth(pressText)/2);
				
				g.drawString(pressText, x, y);
				
				g.dispose();
				
				
				Iterator<ImageWriter> iter = ImageIO.getImageWritersByFormatName("png");  
				if (iter.hasNext()) {  
				    ImageWriter writer = iter.next();  
				    
				    //JPEGImageWriteParam param = (JPEGImageWriteParam) writer.getDefaultWriteParam();  
				    ImageWriteParam param = writer.getDefaultWriteParam();
				  /*  param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);  
				    param.setCompressionQuality(1f);  */
				    ImageTypeSpecifier typeSpecifier = ImageTypeSpecifier.createFromBufferedImageType(BufferedImage.TYPE_INT_RGB);
				    //IIOMetadata metadata = writer.getDefaultImageMetadata(new ImageTypeSpecifier(image), param);
				    IIOMetadata metadata = writer.getDefaultImageMetadata(typeSpecifier, param);
				   /* Element tree = (Element) metadata.getAsTree("javax_imageio_jpeg_image_1.0");  
			        Element jfif = (Element) tree.getElementsByTagName("app0JFIF").item(0); 
			        jfif.setAttribute("Xdensity", "300");
		            jfif.setAttribute("Ydensity", "300");
		            jfif.setAttribute("resUnits", "1"); 
		            metadata.setFromTree("javax_imageio_jpeg_image_1.0", tree);*/
				    setDPI(metadata);
		            ImageOutputStream  out = ImageIO.createImageOutputStream(new File(destImageFile));
			        writer.setOutput(out);
	                writer.write(metadata, new IIOImage(image, null, metadata), param);
	                
	                out.close();
	                writer.dispose();
				}  
				//ImageIO.write((BufferedImage) image, "JPG", new File(destImageFile));// 输出到文件流
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//设置dpi
		private static void setDPI(IIOMetadata metadata) throws IIOInvalidTreeException {  
			  
		    // for PMG, it's dots per millimeter  
		    double dotsPerMilli = 1.0 * 300 / 10 / 2.54d;  
		  
		    IIOMetadataNode horiz = new IIOMetadataNode("HorizontalPixelSize");  
		    horiz.setAttribute("value", Double.toString(dotsPerMilli));  
		  
		    IIOMetadataNode vert = new IIOMetadataNode("VerticalPixelSize");  
		    vert.setAttribute("value", Double.toString(dotsPerMilli));  
		  
		    IIOMetadataNode dim = new IIOMetadataNode("Dimension");  
		    dim.appendChild(horiz);  
		    dim.appendChild(vert);  
		  
		    IIOMetadataNode root = new IIOMetadataNode("javax_imageio_1.0");  
		    root.appendChild(dim);  
		  
		    metadata.mergeTree("javax_imageio_1.0", root);  
		 }  

		// 日期转字符串

		/** 将日期格式化为String，默认格式为yyyy-MM-dd HH:mm:ss，默认日期为当前日期. */
		public static String toStr() {
			return toStr(DF_DATETIME);
		}

		/** 将日期格式化为String，格式由参数format指定，默认日期为当前日期，format值可使用本类常量或自定义. */
		public static String toStr(String format) {
			return toStr(format, new Date());
		}

		/** 将日期格式化为String，默认格式为yyyy-MM-dd HH:mm:ss，日期由参数date指定. */
		public static String toStr(Date date) {
			return toStr(DF_DATETIME, date);
		}

		/** 将日期格式化为String，格式由参数format指定，日期由参数date指定，format值可使用本类常量或自定义. */
		public static String toStr(String format, Date date) {
			return new SimpleDateFormat(format).format(date);
		}

		public static String formateNumber(int num) {
			DecimalFormat df = new DecimalFormat("0000000");
			String str2 = df.format(num);
			return str2;
		}

		public static boolean makeDirs(String filePath) {
			File folder = new File(filePath);
			return (folder.exists() && folder.isDirectory()) ? true : folder
					.mkdirs();
		}

		
			public static void main(String[] args) throws Exception {
//				//String text = "http://weixin.qq.com/r/fUTg_GDEgD3-rSSb9xFv"; // 二维码内容
//				String text = "http://localhost/letu_bike_consumer_cms/cms/tmp/hello.action"; // 二维码内容
//				// 生成二维码
//				//生成图片二维码存放目录
//				String targetPath = "D:/myfile/QR/" + toStr();
//				//创建目录
//				makeDirs(targetPath);
//
//				int begin = 1;//code 开始数字
//				int end = 1;//code结束数字
//				for (int i = begin; i <= end; i++) {
//					//生成含日期的16位数字如20161214000001
//					String code = "060"+(4000+i);
//					//String code = "2222";
//					//获取二维码对象
//					BufferedImage image = toBufferedImage(text
//							+ "?bike=" + code,280,280);
//					//生成含背景图+二维码的立牌的图
//					markImageByCode(image, "D:/myfile/testimg/timg.png",
//							targetPath + "/" + code + ".png", 70, 70);
//					//立牌的图加上code编号
//					pressText(code, targetPath + "/" + code + ".png", targetPath
//							+ "/" + code + ".png", 431, 344, 1f);
//				}
//				// 生成二维码
				String qrText = "hello world";
				String qrPath = "D:/myfile/QR/test.png";
				String qrName = "test.png";
				//makeQR(qrText,qrPath);
				makeSimple(qrText,qrPath,qrName);
			}
}
