package org.controller.cms;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.component.AppConfig;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.util.UploadImageUtil;

import net.sf.json.JSONObject;
/**
 * ueditor 上传图片复写
 * @author Administrator
 *
 */


@Controller
@Scope("prototype")
@RequestMapping("cms/upload")
public class UploadController {
	@Resource
	AppConfig appConfig;

	/**
	 * 
	 * SpringMVC 用的是 的MultipartFile来进行文件上传
	 * 
	 * 这里用@RequestParam()来指定上传文件为MultipartFile
	 * @throws Exception 
	 * 
	 */

	@RequestMapping("uploadImage")
	@ResponseBody // 这里upfile是config.json中图片提交的表单名称
	public String uploadImage(@RequestParam("upfile") CommonsMultipartFile upfile,
			HttpServletRequest request,HttpServletResponse response) throws Exception {
		// 文件原名称
		String fileName = upfile.getOriginalFilename();
		// 为了避免重复简单处理
		String nowName = "";
		if (!upfile.isEmpty()) {
			// 上传位置路径
		//	String path0 = appConfig.getImg_upload() + nowName;
			// 按照路径新建文件
		//	File newFile = new File(path0);
			// 复制
		//	FileCopyUtils.copy(upfile.getBytes(), newFile);
			nowName = UploadImageUtil.upload(appConfig.getUpload_path(),upfile);
		}
		// 返回结果信息(UEditor需要)
		Map<String, String> map = new HashMap<String, String>();
		// 是否上传成功
		map.put("state", "SUCCESS");
		// 现在文件名称
		map.put("title", nowName);
		// 文件原名称
		map.put("original", fileName);
		// 文件类型 .+后缀名
		map.put("type", fileName.substring(upfile.getOriginalFilename().lastIndexOf(".")));
		// 文件路径
		map.put("url", appConfig.getBase_path_web()+"upload/"+ nowName );
		// 文件大小（字节数）
		map.put("size", upfile.getSize() + "");
		JSONObject jsonObject = JSONObject.fromObject(map);
		response.getWriter().write(jsonObject.toString());
		return null;
	}

	/**
	 * 
	 * 读取文件
	 * 
	 */

	@RequestMapping("{imgName}/getImage")
	public void readImg(@PathVariable("imgName") String imgName, HttpServletResponse response)
			throws Exception {
		// 设置文件的返回类型
		response.setContentType("image/*");
		// 文件路径(windows下是\\，linux下是//，都必须是绝对路径)
		String imgPath = appConfig.getUpload_path() + "/"+imgName;
		// java中用File类来表示一个文件
		File image = new File(imgPath);
		// 测试这个文件路径是否存在（也就是这个文件是否存在）
		if (!image.exists()) {
			return;
		}
		// FileUtils.readFileToByteArray(File file)把一个文件转换成字节数组返回
		response.getOutputStream().write(FileUtils.readFileToByteArray(image));
		// java在使用流时,都会有一个缓冲区,按一种它认为比较高效的方法来发数据:
		// 把要发的数据先放到缓冲区,缓冲区放满以后再一次性发过去,而不是分开一次一次地发.
		// 而flush()表示强制将缓冲区中的数据发送出去,不必等到缓冲区满.
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}
}
