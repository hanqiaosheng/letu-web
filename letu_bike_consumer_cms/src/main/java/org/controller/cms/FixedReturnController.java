package org.controller.cms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.component.AppConfig;
import org.entity.dto.Admin;
import org.entity.dto.Channel;
import org.entity.dto.FixedReturn;
import org.entity.dto.Models;
import org.entity.dto.OperateLog;
import org.entity.dto.RentPrice;
import org.service.cms.read.AdminServiceRead;
import org.service.cms.read.BlockServiceRead;
import org.service.cms.read.ChannelServiceRead;
import org.service.cms.read.FixedReturnServiceRead;
import org.service.cms.read.ModelsServiceRead;
import org.service.cms.read.RentPriceServiceRead;
import org.service.cms.write.BlockServiceWrite;
import org.service.cms.write.FixedReturnServiceWrite;
import org.service.cms.write.ModelsServiceWrite;
import org.service.cms.write.OperateServiceWrite;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.BlockUtil;
import org.util.OperateUtil;
import org.util.SplitUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**管理员
 *
 * @author Administrator
 *
 */

@Controller
@Scope("prototype")
@RequestMapping("cms/fixedReturn")
public class FixedReturnController {
	@Resource
	FixedReturnServiceRead fixedReturnServiceRead;
	
	@Resource
	FixedReturnServiceWrite fixedReturnServiceWrite;
	
	@Resource
	ChannelServiceRead channelServiceRead;
	
	@Resource
	ModelsServiceRead modelsServiceRead;
	
	@Resource
	ModelsServiceWrite modelsServiceWrite;
	
	@Resource
	BlockServiceWrite blockServiceWrite;
	
	@Resource
	AdminServiceRead adminServiceRead;
	
	@Resource
	BlockServiceRead blockServiceRead;
	
	@Resource
	OperateServiceWrite operateServiceWrite;
	
	@Resource
	RentPriceServiceRead rentPriceServiceRead;
	
	@Resource
	AppConfig appConfig;
	
	/**
	 * 还车点列表
	 * @param session
	 * @param model
	 * @param pageIndex
	 * @param name
	 * @param channelName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("fixedReturnList")
	public String fixedReturnList(HttpSession session,Model model,@RequestParam(defaultValue="1")Integer pageIndex,String name, String channelName) throws Exception{
		Long channelId  = (Long) session.getAttribute("currChannelId");
		List<FixedReturn> fixedReturnList = new ArrayList<FixedReturn>();
		Integer totalPage = 0;
		if(channelId==null){
			fixedReturnList = fixedReturnServiceRead.findAllFixed(pageIndex,appConfig.getPage_size_web(),name,channelName,null);
			totalPage = fixedReturnServiceRead.countAllFixed(name,channelName,null);
		}else{
			Channel channel =channelServiceRead.findById(channelId);
			List<Channel> channels1 = channelServiceRead.findSonChannels(channelId);
			channels1.add(channel);
			List<Long> currChannelIds = new ArrayList<Long>();
			for(Channel c:channels1){
				currChannelIds.add(c.getChannelId());
			}
			fixedReturnList = fixedReturnServiceRead.findAllFixed(pageIndex,appConfig.getPage_size_web(),name,channelName,currChannelIds);
			totalPage = fixedReturnServiceRead.countAllFixed(name,channelName,currChannelIds);
		}
		if(totalPage==0){
			totalPage = 1;
		}
		model.addAttribute("pageIndex", pageIndex);
		model.addAttribute("fixedReturnList", fixedReturnList);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("channelName", channelName);
		model.addAttribute("name", name);
		return "fixedReturn_list";
	}
	
	/**
	 * 还车点详情
	 * @param model
	 * @param fixedReturnId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("fixedReturnDetail")
	public String fixedReturnDetail(Model model,Long fixedReturnId) throws Exception{
		FixedReturn fixedReturn = fixedReturnServiceRead.findFixedById(fixedReturnId);
		List<RentPrice> prices = new ArrayList<RentPrice>();
		if(null!=fixedReturn.getFixedReturnModelsId()&&!fixedReturn.getFixedReturnModelsId().equals("")){
			String[] split = SplitUtil.toSplit(fixedReturn.getFixedReturnModelsId());
			List<Models> list = new ArrayList<Models>();
			for(int i=0;i<split.length;i++){
				if(!split[i].equals("null")){
					Models models = modelsServiceRead.findModelsById(Long.valueOf(split[i]));
					if(null!=models){
						prices = rentPriceServiceRead.findRentPriceByModelsId(models.getModelsId());
						if(prices.size()>0){
							for(RentPrice rp : prices){
								List<JSONObject> strList = new ArrayList<JSONObject>();//租赁费用列表
								JSONArray jsonArray = JSONArray.fromObject(rp.getRentPrice());
								for (int j = 0; j < jsonArray.size(); j++) {
									JSONObject jsonObject = (JSONObject) jsonArray.get(j);
									if(jsonObject.optDouble("fromTime",-1)!=-1){
										strList.add(jsonObject);
									}else{
										rp.setLastPrice(jsonObject.getDouble("rentPrice"));//最后时间段
									}
								}
								rp.setPriceList(strList);
								
							}
						}
						models.setModelRentPriceList(prices);
						list.add(models);
					}
				}
				
				
			}
			fixedReturn.setModels(list);
		}
		Channel channel = channelServiceRead.findById(fixedReturn.getFixedReturnChannelId());
		model.addAttribute("fixedReturn", fixedReturn);
		model.addAttribute("channel", channel);
		return "detail/fixedReturn_detail";
	}
	
	/**
	 * 跳转添加页面
	 * @param model
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("fixedReturnAddJsp")
	public String fixedReturnAddJsp(Model model,HttpSession session) throws Exception{
		Long currChannelId  = (Long) session.getAttribute("currChannelId");
		List<Channel> channelList = new ArrayList<Channel>();
		if(currChannelId==null){   //如果是管理员
			channelList = channelServiceRead.findAllChannelNotfreeze(null);
		}else{
			Channel channel =channelServiceRead.findById(currChannelId);
			List<Channel> channels = channelServiceRead.findSonChannels(currChannelId);
			channels.add(channel);
			List<Long> currChannelIds = new ArrayList<Long>();
			for(Channel c:channels){
				currChannelIds.add(c.getChannelId());
			}
			channelList = channelServiceRead.findAllChannelNotfreeze(currChannelIds);
		}
		model.addAttribute("url", appConfig.getBase_path_web());
		model.addAttribute("channelList", channelList);
		return "detail/fixedReturn_add";
	}
	
	/**
	 * 添加
	 * @param session
	 * @param fixedReturn
	 * @param latlng
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("fixedReturnAdd")
	public String fixedReturnAdd(HttpSession session ,FixedReturn fixedReturn,String latlng) throws Exception{
		Admin admin = (Admin)session.getAttribute("admin");
		Admin nowAdmin = adminServiceRead.findAdminId(admin.getAdminId());
		if(null!=latlng&&!latlng.equals(",")&&!latlng.equals("")){
			String blockCode=BlockUtil.getBlockCode(latlng);
			/*Long blockputId = blockServiceRead.findBlockId(blockCode);
			if(blockputId!=null){
				fixedReturn.setFixedReturnBlockId(blockputId);
			}*/
			fixedReturn.setFixedReturnBlock(blockCode);
			String [] splitLatLng=latlng.split(",|，");
			double lng =Double.parseDouble(splitLatLng[0]);
			double lat =Double.parseDouble(splitLatLng[1]);
			fixedReturn.setFixedReturnLat(lat);
			fixedReturn.setFixedReturnLng(lng);
		}
		fixedReturn.setFixedReturnCreatetime(new Date());
		fixedReturn.setFixedReturnAdminId(admin.getAdminId());
		Long fixedReturnId = fixedReturnServiceWrite.addFixedReturn(fixedReturn);
		
		OperateLog operateLog = new OperateLog();
		operateLog.setOperateTime(new Date());
		operateLog.setOperateAdminId(nowAdmin.getAdminId());
		String remark = OperateUtil.operateBike(nowAdmin.getAdminRealname(), 12, null, null, fixedReturnId);
		operateLog.setOperateRemark(remark);
		operateServiceWrite.addOperateLogs(operateLog);//操作日志
		
		return "redirect:fixedReturnList.action";
	}
	
	/**
	 * 跳转编辑页面
	 * @param session
	 * @param model
	 * @param fixedReturnId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("fixedReturnEditJsp")
	public String fixedReturnEditJsp(HttpSession session,Model model,Long fixedReturnId) throws Exception{
		FixedReturn fixedReturn = fixedReturnServiceRead.findFixedById(fixedReturnId);
		Long currChannelId  = (Long) session.getAttribute("currChannelId");
		List<Channel> channelList = new ArrayList<Channel>();
		if(currChannelId==null){   //如果是管理员
			channelList = channelServiceRead.findAllChannelNotfreeze(null);
		}else{
			Channel channel =channelServiceRead.findById(currChannelId);
			List<Channel> channels = channelServiceRead.findSonChannels(currChannelId);
			channels.add(channel);
			List<Long> currChannelIds = new ArrayList<Long>();
			for(Channel c:channels){
				currChannelIds.add(c.getChannelId());
			}
			channelList = channelServiceRead.findAllChannelNotfreeze(currChannelIds);
		}
		model.addAttribute("channelList", channelList);
		model.addAttribute("fixedReturn", fixedReturn);
		return "detail/fixedReturn_edit";
	}
	
	/**
	 * 编辑
	 * @param fixedReturn
	 * @param latlng
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("fixedReturnEdit")
	public String fixedReturnEdit(FixedReturn fixedReturn,String latlng,HttpSession session) throws Exception{
		Admin admin = (Admin)session.getAttribute("admin");
		Admin nowAdmin = adminServiceRead.findAdminId(admin.getAdminId());
		if(null!=latlng&&!latlng.equals(",")&&!latlng.equals("")){
			String blockCode=BlockUtil.getBlockCode(latlng);
			/*Long blockputId = blockServiceRead.findBlockId(blockCode);
			if(blockputId!=null){
				fixedReturn.setFixedReturnBlockId(blockputId);
			}*/
			fixedReturn.setFixedReturnBlock(blockCode);
			String [] splitLatLng=latlng.split(",|，");
			double lng =Double.parseDouble(splitLatLng[0]);
			double lat =Double.parseDouble(splitLatLng[1]);
			fixedReturn.setFixedReturnLat(lat);
			fixedReturn.setFixedReturnLng(lng);
		}
		fixedReturnServiceWrite.editFixedReturn(fixedReturn);
		
		OperateLog operateLog = new OperateLog();
		operateLog.setOperateTime(new Date());
		operateLog.setOperateAdminId(nowAdmin.getAdminId());
		String remark = OperateUtil.operateBike(nowAdmin.getAdminRealname(), 13, null, null, fixedReturn.getFixedReturnId());
		operateLog.setOperateRemark(remark);
		operateServiceWrite.addOperateLogs(operateLog);//操作日志
		
		return "redirect:fixedReturnList.action";
	}	
	
	/**
	 * 删除
	 * @param fixedReturn
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("fixedReturnDelete")
	public @ResponseBody String fixedReturnDelete(FixedReturn fixedReturn,HttpSession session) throws Exception{
		Admin admin = (Admin)session.getAttribute("admin");
		Admin nowAdmin = adminServiceRead.findAdminId(admin.getAdminId());
		FixedReturn fixedReturn1 = fixedReturnServiceRead.findFixedById(fixedReturn.getFixedReturnId());
		if(null!=fixedReturn1.getFixedReturnModelsId()&&!fixedReturn1.getFixedReturnModelsId().equals("")){
			String[] ModelsIds = SplitUtil.toSplit(fixedReturn1.getFixedReturnModelsId());
			for(int k=0;k<ModelsIds.length;k++){
				Models models = modelsServiceRead.findModelsById(Long.valueOf(ModelsIds[k]));
				String fixedStr = "";
				String[] split = SplitUtil.toSplit(models.getModelsFixedReturn());
				List<String> list = new ArrayList<String>();
				for(int i=0;i<split.length;i++){
					list.add(split[i]);
				}
				for(int j=0;j<list.size();j++){
					if(Long.valueOf(list.get(j)).equals(fixedReturn.getFixedReturnId())){
						list.remove(j);
						j--; 
					}
				}
				for(String str : list){
					fixedStr = fixedStr +","+ str;
				}
				if(!fixedStr.equals("")){
					fixedStr = fixedStr.substring(1);
				}
				models.setModelsFixedReturn(fixedStr);
				modelsServiceWrite.updateModels(models);
			}
		}
		
		fixedReturn.setFixedReturnState(0);
		fixedReturnServiceWrite.editFixedReturn(fixedReturn);
		
		OperateLog operateLog = new OperateLog();
		operateLog.setOperateTime(new Date());
		operateLog.setOperateAdminId(nowAdmin.getAdminId());
		String remark = OperateUtil.operateBike(nowAdmin.getAdminRealname(), 14, null, null, fixedReturn.getFixedReturnId());
		operateLog.setOperateRemark(remark);
		operateServiceWrite.addOperateLogs(operateLog);//操作日志
		
		return "success";
	}	
	
	/**
	 * 还车点审核列表
	 * @param session
	 * @param model
	 * @param pageIndex
	 * @param name
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("fixedCheckList")
	public String fixedCheckList(HttpSession session,Model model,@RequestParam(defaultValue="1")Integer pageIndex,String name) throws Exception{
		Long channelId  = (Long) session.getAttribute("currChannelId");
		List<FixedReturn> fixedCheckList = new ArrayList<FixedReturn>();
		Integer totalPage = 0;
		if(channelId==null){
			fixedCheckList = fixedReturnServiceRead.findAllCheckFixed(pageIndex,appConfig.getPage_size_web(),name,null);
			totalPage = fixedReturnServiceRead.countAllCheckFixed(name,null);
		}else{
			Channel channel =channelServiceRead.findById(channelId);
			List<Channel> channels1 = channelServiceRead.findSonChannels(channelId);
			channels1.add(channel);
			List<Long> currChannelIds = new ArrayList<Long>();
			for(Channel c:channels1){
				currChannelIds.add(c.getChannelId());
			}
			fixedCheckList = fixedReturnServiceRead.findAllCheckFixed(pageIndex,appConfig.getPage_size_web(),name,currChannelIds);
			totalPage = fixedReturnServiceRead.countAllCheckFixed(name,currChannelIds);
		}
		if(totalPage==0){
			totalPage = 1;
		}
		for(FixedReturn fr : fixedCheckList){
			Admin admin = adminServiceRead.findAdminId(fr.getFixedReturnOriginatorId());
			fr.setFixedReturnOriginator(admin.getAdminRealname());
		}
		model.addAttribute("pageIndex", pageIndex);
		model.addAttribute("fixedCheckList", fixedCheckList);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("name", name);
		return "fixedCheck_list";
	}	
	
	/**
	 * 位置
	 * @param fixedReturnId
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("fixedPosition")
	public String fixedPosition(Long fixedReturnId,Model model)throws Exception{
		FixedReturn fixedReturn = fixedReturnServiceRead.findFixedById(fixedReturnId);
		model.addAttribute("fixedReturn", fixedReturn);
		return "detail/fixed_position";
	}
	
	/**
	 * 审核
	 * @param fixedReturnId
	 * @param model
	 * @param state
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("toCheck")
	public @ResponseBody String toCheck(Long fixedReturnId,Model model,Integer state,HttpSession session)throws Exception{
		Admin admin = (Admin)session.getAttribute("admin");
		Admin nowAdmin = adminServiceRead.findAdminId(admin.getAdminId());
		FixedReturn fixedReturn = fixedReturnServiceRead.findFixedById(fixedReturnId);
		fixedReturn.setFixedReturnState(state);
		fixedReturnServiceWrite.editFixedReturn(fixedReturn);
		
		OperateLog operateLog = new OperateLog();
		operateLog.setOperateTime(new Date());
		operateLog.setOperateAdminId(nowAdmin.getAdminId());
		String remark = OperateUtil.operateBike(nowAdmin.getAdminRealname(), 16, null, null, fixedReturn.getFixedReturnId());
		operateLog.setOperateRemark(remark);
		operateServiceWrite.addOperateLogs(operateLog);//操作日志
		
		return "success";
	}
	
	/**
	 * 跳转完善页面
	 * @param fixedReturnId
	 * @param model
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("perfectDetailJsp")
	public String perfectDetailJsp(Long fixedReturnId,Model model,HttpSession session)throws Exception{
		FixedReturn fixedReturn = fixedReturnServiceRead.findFixedById(fixedReturnId);
		Long currChannelId  = (Long) session.getAttribute("currChannelId");
		List<Channel> channelList = new ArrayList<Channel>();
		if(currChannelId==null){   //如果是管理员
			channelList = channelServiceRead.findAllChannelNotfreeze(null);
		}else{
			Channel channel =channelServiceRead.findById(currChannelId);
			List<Channel> channels = channelServiceRead.findSonChannels(currChannelId);
			channels.add(channel);
			List<Long> currChannelIds = new ArrayList<Long>();
			for(Channel c:channels){
				currChannelIds.add(c.getChannelId());
			}
			channelList = channelServiceRead.findAllChannelNotfreeze(currChannelIds);
		}
		model.addAttribute("channelList", channelList);
		model.addAttribute("fixedReturn", fixedReturn);
		return "detail/perfect_fixed_edit";
	}
	/**
	 * 完善信息
	 * @param fixedReturn
	 * @param model
	 * @param session
	 * @param latlng
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("perfectDetail")
	public String perfectDetail(FixedReturn fixedReturn,Model model,HttpSession session,String latlng)throws Exception{
		Admin admin = (Admin)session.getAttribute("admin");
		Admin nowAdmin = adminServiceRead.findAdminId(admin.getAdminId());
		if(null!=latlng&&!latlng.equals(",")&&!latlng.equals("")){
			String blockCode=BlockUtil.getBlockCode(latlng);
			Long blockputId = blockServiceRead.findBlockId(blockCode);
			if(blockputId!=null){
				fixedReturn.setFixedReturnBlockId(blockputId);
			}
			String [] splitLatLng=latlng.split(",|，");
			double lng =Double.parseDouble(splitLatLng[0]);
			double lat =Double.parseDouble(splitLatLng[1]);
			fixedReturn.setFixedReturnLat(lat);
			fixedReturn.setFixedReturnLng(lng);
		}
		fixedReturn.setFixedReturnAdminId(admin.getAdminId());
		fixedReturn.setFixedReturnCreatetime(new Date());
		fixedReturn.setFixedReturnState(1);
		fixedReturnServiceWrite.editFixedReturn(fixedReturn);
		
		OperateLog operateLog = new OperateLog();
		operateLog.setOperateTime(new Date());
		operateLog.setOperateAdminId(nowAdmin.getAdminId());
		String remark = OperateUtil.operateBike(nowAdmin.getAdminRealname(), 15, null, null, fixedReturn.getFixedReturnId());
		operateLog.setOperateRemark(remark);
		operateServiceWrite.addOperateLogs(operateLog);//操作日志
		
		return "redirect:fixedCheckList.action";
	}
	
	/**
	 * 获取站点
	 * @param modelsId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getFixed")
	public @ResponseBody List<FixedReturn> getFixed(Long modelsId)throws Exception{
		if(modelsId!=-1){
			Models models = modelsServiceRead.findModelsById(modelsId);
			List<FixedReturn> fixedReturns = new ArrayList<FixedReturn>();
			if(null!=models.getModelsFixedReturn()&&!models.getModelsFixedReturn().equals("")){
				String[] split = SplitUtil.toSplit(models.getModelsFixedReturn());
				for(int i=0;i<split.length;i++){
					FixedReturn fixedReturn = fixedReturnServiceRead.findFixedById(Long.valueOf(split[i]));
					fixedReturns.add(fixedReturn);
				}		
			}
			if(fixedReturns.size()>0){
				return fixedReturns;
			}
		}
		return null;
	}
	
	/**
	 * 通过渠道获取站点
	 * @param channelId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getFixedByChannelId")
	public @ResponseBody List<FixedReturn> getFixedByChannelId(Long channelId)throws Exception{
		if(channelId!=-1){
			List<FixedReturn> fixedReturnList = fixedReturnServiceRead.findFixedByChannelId(channelId);
			if(fixedReturnList.size()>0){
				return fixedReturnList;
			}
		}
		return null;
	}
}
