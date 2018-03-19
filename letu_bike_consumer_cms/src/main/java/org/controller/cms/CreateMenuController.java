package org.controller.cms;


import javax.annotation.Resource;

import org.component.AppConfig;
import org.entity.weixin.menu.Button;
import org.entity.weixin.menu.ClickButton;
import org.entity.weixin.menu.ComplexButton;
import org.entity.weixin.menu.Menu;
import org.entity.weixin.menu.ViewButton;
import org.entity.weixin.pojo.Token;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.util.weixin.CommonUtil;
import org.util.weixin.MenuUtil;


/**
 * 生成微信底部栏
 * @author Administrator
 *
 */
@Controller
public class CreateMenuController {
	
	
	@Resource
	AppConfig AppConfig;
	@RequestMapping("createMenu")
	public String createMenu(Model model){
		// 调用接口获取凭证
		Token token = CommonUtil.getToken(AppConfig.getApp_id(), AppConfig.getApp_secret());
		if (null != token) {
			// 创建菜单
			boolean result = MenuUtil.createMenu(getMenu(), token.getAccess_token());
			// 判断菜单创建结果
			if (result){
				model.addAttribute("message", "菜单创建成功");
				System.out.println("菜单创建成功！");
			}
			else{
				model.addAttribute("message", "菜单创建失败");
				System.out.println("菜单创建失败！");
			}
		}
		return "message";
	}
	
	private Menu getMenu() {
		ViewButton btn21 = new ViewButton();
		btn21.setName("点我用车");
		btn21.setType("view");
		btn21.setUrl(AppConfig.getBase_path_weixin() + "/index/goIndex.action");
		System.out.println(AppConfig.getBase_path_weixin() + "/index/goIndex.action");
		ViewButton btn22 = new ViewButton();
		btn22.setName("个人中心");
		btn22.setType("view");
		btn22.setUrl(AppConfig.getBase_path_weixin() + "/index/goIndex.action?otherFlag=center");
		
		
		ComplexButton btn23 = new ComplexButton(); 
		btn23.setName("更多");
		
		ViewButton btn23_1 = new ViewButton();
		btn23_1.setName("乐途官网");
		btn23_1.setType("view");
		btn23_1.setUrl("http://www.letulife.com");
		
		
		ClickButton btn23_2 = new ClickButton();
		btn23_2.setName("联系我们");
		btn23_2.setType("click");
		btn23_2.setKey("contact_us");
		
		ViewButton btn23_3 = new ViewButton();
		btn23_3.setName("管理员登录");
		btn23_3.setType("view");
		btn23_3.setUrl(AppConfig.getBase_path_admin() + "/index.html");
		
		btn23.setSub_button(new Button[] { btn23_1, btn23_2 ,btn23_3 });
		

		Menu menu = new Menu();
		menu.setButton(new Button[] { btn21, btn22, btn23 });

		return menu;
	}
}
