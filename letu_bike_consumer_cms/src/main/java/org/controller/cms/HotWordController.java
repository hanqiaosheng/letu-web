package org.controller.cms;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.component.AppConfig;
import org.entity.dto.HotWord;
import org.service.cms.read.HotWordServiceRead;
import org.service.cms.write.HotWordServiceWrite;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.PageUtil;

@Controller
@Scope("prototype")
@RequestMapping("cms/hotword")
public class HotWordController {

	@Resource
	AppConfig appConfig;
	@Resource
	HotWordServiceRead hotWordServiceRead;
	@Resource
	HotWordServiceWrite hotWordServiceWrite;

	/**
	 * 热词列表
	 * @param model
	 * @param pageIndex
	 * @param hotWord
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	public String list(Model model, @RequestParam(defaultValue = "1") Integer pageIndex, HotWord hotWord,String hotWordName)
			throws Exception {
		List<HotWord> hotWordList = hotWordServiceRead.findByCondition(pageIndex, appConfig.getPage_size_web(), hotWordName);
		Integer totalPage = 1;
		Integer totalCount = hotWordServiceRead.countByCondition(hotWordName);
		if (totalCount > 0) {
			totalPage = PageUtil.getTotalPage(totalCount);
		}
		model.addAttribute("hotWordList", hotWordList);
		model.addAttribute("pageIndex", pageIndex);
		model.addAttribute("pageSize", appConfig.getPage_size_web());
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("hotWordName", hotWordName);
		return "hotword/hotword_list";
	}

	
	/**
	 * 热词详情
	 * @param model
	 * @param bannerId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("detail")
	public String bannerDetail(Model model, Long hotWordId) throws Exception {
		HotWord hotWord = hotWordServiceRead.findById(hotWordId);
		model.addAttribute("hotWord", hotWord);
		return "hotword/hotword_detail";
	}

	/**
	 * 跳转添加页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("addJsp")
	public String addJsp(Model model) throws Exception {
		return "hotword/hotword_add";
	}

	/**
	 * 新增
	 * @param banner
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("add")
	public String add(HotWord hotWord) throws Exception {
		hotWord.setHotWordCreateTime(new Date());
		hotWordServiceWrite.add(hotWord);
		return "redirect:list.action";
	}
	
	/**
	 * 编辑页面
	 * @param bannerId
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("editJsp")
	public String editJsp(Long hotWordId,Model model) throws Exception {
		HotWord hotWord = hotWordServiceRead.findById(hotWordId);
		model.addAttribute("hotWord", hotWord);
		return "hotword/hotword_edit";
	}
	
	/**
	 * 横幅编辑
	 * @param banner
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("edit")
	public String edit(HotWord hotWord) throws Exception {
		hotWord.setHotWordUpdateTime(new Date());;
		hotWordServiceWrite.update(hotWord);
		return "redirect:list.action";
	}
	
	/**
	 * 横幅上线/下线/删除
	 * @param hotWordId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("updateState")
	public @ResponseBody String updateState(Long hotWordId,Integer hotWordState) throws Exception{
		HotWord hotWord = hotWordServiceRead.findById(hotWordId);
		if(hotWordState==-1){
			if(hotWord.getHotWordState()==2){
				return "fail";
			}
		}
		hotWord.setHotWordState(hotWordState);
		hotWordServiceWrite.update(hotWord);
		return "success";
	}
	
	/**
	 * 横幅检验
	 * @param hotWordId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("checkState")
	public @ResponseBody String checkState(Long hotWordId) throws Exception{
		HotWord hotWord = hotWordServiceRead.findById(hotWordId);
		if(hotWord.getHotWordState()==2){
			return "fail";
		}
		return "success";
	}
	
}
