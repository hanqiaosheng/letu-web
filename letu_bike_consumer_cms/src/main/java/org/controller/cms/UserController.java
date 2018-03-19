package org.controller.cms;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.component.AppConfig;
import org.entity.dto.Account;
import org.entity.dto.Admin;
import org.entity.dto.Bike;
import org.entity.dto.BikeRentInfo;
import org.entity.dto.Channel;
import org.entity.dto.MoneyLog;
import org.entity.dto.OperateLog;
import org.entity.dto.User;
import org.service.cms.read.AccountServiceRead;
import org.service.cms.read.AdminServiceRead;
import org.service.cms.read.BikeRentInfoServiceRead;
import org.service.cms.read.BikeServiceRead;
import org.service.cms.read.BlockServiceRead;
import org.service.cms.read.ChannelServiceRead;
import org.service.cms.read.UserServiceRead;
import org.service.cms.write.AccountServiceWrite;
import org.service.cms.write.BikeRentInfoServiceWrite;
import org.service.cms.write.MoneyLogServiceWrite;
import org.service.cms.write.OperateServiceWrite;
import org.service.cms.write.UserServiceWrite;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.util.BlockUtil;
import org.util.DateUtil;
import org.util.OperateUtil;
import org.util.PageUtil;
import org.util.ReadWriteExcelUtil;

@Controller
@Scope("prototype")
@RequestMapping("cms/user")
public class UserController {

	@Resource
	AppConfig appConfig;

	@Resource
	UserServiceRead userServiceRead;

	@Resource
	UserServiceWrite userServiceWrite;

	@Resource
	AdminServiceRead adminServiceRead;

	@Resource
	BikeRentInfoServiceRead bikeRentInfoServiceRead;

	@Resource
	BikeRentInfoServiceWrite bikeRentInfoServiceWrite;

	@Resource
	BikeServiceRead bikeServiceRead;

	@Resource
	BlockServiceRead blockServiceRead;
	
	@Resource
	OperateServiceWrite operateServiceWrite;
	
	
	@Resource
	ChannelServiceRead channelServiceRead;
	
	@Resource
	AccountServiceWrite accountServiceWrite;
	
	@Resource
	AccountServiceRead accountServiceRead;
	
	@Resource
	MoneyLogServiceWrite MoneyLogServiceWrite;

	/**
	 * 用户列表
	 * 
	 * @param model
	 * @param pageIndex
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	public String userList(HttpSession session,Model model, @RequestParam(defaultValue = "1") Integer pageIndex, User user,Long channelid)
			throws Exception {
		if(null!=channelid&&channelid==-1){
			channelid=null;
		}
			
		user.setUserChannelId(channelid);
		
		List<User> userList = new ArrayList<User>();
    	
    	List<Channel> channels = new ArrayList<Channel>();
    	
		 userList = userServiceRead.findUserByCondition(user, pageIndex, appConfig.getPage_size_web());
		if(null!=userList&&0!=userList.size()){
			for (User user1 : userList) {
				user1.setChannel(channelServiceRead.findById(user1.getUserChannelId()));
				
			}
		}
		Integer totalPage = 1;
		Integer totalCount = userServiceRead.countAllUser(user);
		if (totalCount > 0) {
			totalPage = PageUtil.getTotalPage(totalCount);
		}
		model.addAttribute("userList", userList);
		model.addAttribute("pageIndex", pageIndex);
		model.addAttribute("pageSize", appConfig.getPage_size_web());
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("user", user);
		model.addAttribute("channels", channels);
		model.addAttribute("channelid", channelid);
		return "user_list";
	}
	
	/**
	 * 会员列表
	 * 
	 * @param model
	 * @param pageIndex
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("villagerlist")
	public String villagerList(HttpSession session,Model model, @RequestParam(defaultValue = "1") Integer pageIndex, User user,Integer failFlag,String phone,Long channelid,Integer flag)
			throws Exception {
		Long channelId  = (Long) session.getAttribute("currChannelId");
		if(null!=channelid&&channelid==-1){
			channelid=null;
		}
			
		user.setUserChannelId(channelid);
		List<User> villagerlist = new ArrayList<User>();
		Integer totalPage = 1;
		List<Channel> channelList = new ArrayList<Channel>();
		if(channelId==null){
			channelList = channelServiceRead.findAllChannelNotfreeze(null);
			 villagerlist = userServiceRead.findVillagerByCondition(user, pageIndex, appConfig.getPage_size_web(),null);
			 Integer totalCount = userServiceRead.countAllVillager(user,null);
			 if (totalCount > 0) {
					totalPage = PageUtil.getTotalPage(totalCount);
				}
    	}
    	else{
    		Channel channel =channelServiceRead.findById(channelId);
			List<Channel> channels1 = channelServiceRead.findSonChannels(channelId);
			channels1.add(channel);
			List<Long> currChannelIds = new ArrayList<Long>();
			for(Channel c:channels1){
				currChannelIds.add(c.getChannelId());
			}
			channelList = channelServiceRead.findAllChannelNotfreeze(currChannelIds);
			villagerlist = userServiceRead.findVillagerByCondition(user, pageIndex, appConfig.getPage_size_web(),currChannelIds);
			Integer totalCount = userServiceRead.countAllVillager(user,currChannelIds);
			if (totalCount > 0) {
				totalPage = PageUtil.getTotalPage(totalCount);
			}
    	}
		if(null!=villagerlist&&0!=villagerlist.size()){
			for (User villager : villagerlist) {
				villager.setChannel(channelServiceRead.findById(villager.getUserChannelId()));
				
			}
		}
		
		
		
		model.addAttribute("villagerlist", villagerlist);
		model.addAttribute("pageIndex", pageIndex);
		model.addAttribute("pageSize", appConfig.getPage_size_web());
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("user", user);
		model.addAttribute("channelList",channelList);
		model.addAttribute("failFlag", failFlag);
		model.addAttribute("phone", phone);
		model.addAttribute("channelid", channelid);
		model.addAttribute("flag", flag);
		return "villager_list";
	}
	
	
	/**
	 * 导入会员
	 * 
	 * @param model
	 * @param session
	 * @param excel
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("importVillager")
	public String importStudent(Model model, HttpSession session, MultipartFile excel,Long channelId,int channelType) throws Exception {
		Admin admin = (Admin) session.getAttribute("admin");
		Admin nowAdmin = adminServiceRead.findAdminId(admin.getAdminId());
		OperateLog operateLog = new OperateLog();
		if (!excel.isEmpty()) {
			InputStream inputStream = excel.getInputStream();
			String filename = excel.getOriginalFilename();
			String extensionName = filename.substring(filename.lastIndexOf(".") + 1, filename.length());
			//List<BikeLockInfo> list = ReadWriteExcelUtil.exportLockListFromExcel(inputStream, extensionName, 0);
			List<User> list = ReadWriteExcelUtil.exportVillagerListExcel(inputStream, extensionName, 0);
			
			if(list.size()<1){
				return "redirect:villagerlist.action?failFlag=0"; //数据格式出错 或 数据数量为0
			}
			else{
			for (User user : list) {
				
				User user1 = userServiceRead.findByIdcard(user.getUserTel(), user.getUserIdcard());
				if(user1==null){
					
					return "redirect:villagerlist.action?failFlag=1&phone="+user.getUserTel();//没有查到记录
					
				}
				
				
			}
			for (User user : list) {
				User user1 = userServiceRead.findByIdcard(user.getUserTel(), user.getUserIdcard());
				user1.setUserChannelId(channelId);
				if(channelType==0){
			    	Calendar curr = Calendar.getInstance();
			    	curr.set(Calendar.YEAR,curr.get(Calendar.YEAR)+1);
			    	Date date=curr.getTime();
			    	user1.setUserVipexpirationdate(date);
			    }
				if(channelType==1){
			    	
			    	Date date=null;
			    	user1.setUserVipexpirationdate(date);
			    }
				userServiceWrite.updateUser(user1);
			
			Account account = accountServiceRead.findPlanByaccountId(user1.getUserAccountId());
			account.setAccountAvailableBalance(account.getAccountAvailableBalance().add(user.getAccountAvailableBalance()));
			account.setAccountTotalmoney(account.getAccountTotalmoney().add(user.getAccountAvailableBalance()));
			accountServiceWrite.updateAccount(account);
			MoneyLog moneyLog = new MoneyLog();
		    moneyLog.setMoneyLogAccountId(account.getAccountId());
		    moneyLog.setMoneyLogStreamType(4);
		    moneyLog.setMoneyLogOpreateMoney(user.getAccountAvailableBalance());
		    moneyLog.setMoneyLogCreateTime(new Date());
		    moneyLog.setMoneyLogOpreateId((long)5);
		    moneyLog.setMoneyLogState(1);
		    moneyLog.setMoneyLogIsvillager(1);
		    moneyLog.setMoneyLogRefundState(0);
		    moneyLog.setMoneyLogChannelId(channelId);
		    MoneyLogServiceWrite.add(moneyLog);
			}
			}
			
			String remark = OperateUtil.operateVip(nowAdmin.getAdminRealname(), 6, null, null);
			operateLog.setOperateRemark(remark);
			operateLog.setOperateTime(new Date());
			operateLog.setOperateAdminId(nowAdmin.getAdminId());
			operateServiceWrite.addOperateLogs(operateLog);
			return "redirect:villagerlist.action?failFlag=2";

		}
		
		
		
		
		return "redirect:villagerlist.action";
	}
	
	/**
	 * 修改渠道页面
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("editChannelJsp")
	public String editChannelJsp(Long userId,Model mdoel) throws Exception{
		User user = userServiceRead.findById(userId);
		//Channel channel = channelServiceRead.findById(user.getUserChannelId());
		List<Channel> channelList = channelServiceRead.findAllChannelNotfreeze(null);
		mdoel.addAttribute("user", user);
		//mdoel.addAttribute("channel", channel);
		mdoel.addAttribute("channelList", channelList);
		return "detail/userChannel_edit";
	}
	
	
	/**
	 * 修改用户渠道
	 * @param user
	 * @param session
	 * @param channelName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("editChannel")
	public String editChannel(User user,HttpSession session,Long channelId) throws Exception{
		/*Admin admin = (Admin) session.getAttribute("admin");
		Admin nowAdmin = adminServiceRead.findAdminId(admin.getAdminId());
		User nowUser = userServiceRead.findById(user.getUserId());
		OperateLog operateLog = new OperateLog();
		String userStateStr = null;
		if(user.getUserState()==0){
			userStateStr = "空闲";
		}else if(user.getUserState()==1){
			userStateStr = "租借";
		}
		String remark = OperateUtil.operateUser(nowAdmin.getAdminRealname(), 1,nowUser.getUserRealname(),userStateStr,null,null);
		operateLog.setOperateRemark(remark);
		operateLog.setOperateTime(new Date());
		operateLog.setOperateAdminId(nowAdmin.getAdminId());
		operateServiceWrite.addOperateLogs(operateLog);*/
		Admin admin = (Admin) session.getAttribute("admin");
		Admin nowAdmin = adminServiceRead.findAdminId(admin.getAdminId());
		User nowUser = userServiceRead.findById(user.getUserId());
		OperateLog operateLog = new OperateLog();
		Channel channel = channelServiceRead.findById(channelId);
		user.setUserChannelId(channelId);
		userServiceWrite.updateUser(user);
		String remark = null;
		if(channel!=null){
		 remark = OperateUtil.operateVip(nowAdmin.getAdminRealname(), 1,nowUser.getUserRealname(),channel.getChannelName());
		}else{
			remark = OperateUtil.operateVip(nowAdmin.getAdminRealname(), 1,nowUser.getUserRealname(),"非会员");
		}
		operateLog.setOperateRemark(remark);
		operateLog.setOperateTime(new Date());
		operateLog.setOperateAdminId(nowAdmin.getAdminId());
		operateServiceWrite.addOperateLogs(operateLog);
		return "redirect:villagerlist.action";
	}
	
	/**
     * 添加页面
     * @param model
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping("addVipJsp")
    public String addVipJsp(Model model,HttpSession session) throws Exception{
    	
    	List<Channel> channelList = channelServiceRead.findAllChannelNotfreeze(null);
    	model.addAttribute("channelList",channelList);
    	return "detail/vip_add";
    }
	
	 /**
     * 添加
     * @param bike
     * @param putAddress
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping("addVip")
    public String addVip(User user,HttpSession session,Long channelId,Model model,int modelsIsfixedPoint) throws Exception{
    	Admin admin = (Admin) session.getAttribute("admin");
    	Admin nowAdmin = adminServiceRead.findAdminId(admin.getAdminId());
    	/*if(modelsIsfixedPoint==0){
    		user.setAccountAvailableBalance(BigDecimal.valueOf(0));
    	}*/
    	User user1 = userServiceRead.findByIdcard(user.getUserTel(), user.getUserIdcard());
		
		user1.setUserChannelId(channelId);
		if(modelsIsfixedPoint==0){
	    	Calendar curr = Calendar.getInstance();
	    	curr.set(Calendar.YEAR,curr.get(Calendar.YEAR)+1);
	    	Date date=curr.getTime();
	    	user1.setUserVipexpirationdate(date);
	    }
		if(modelsIsfixedPoint==1){
	    	
	    	Date date=null;
	    	user1.setUserVipexpirationdate(date);
	    }
		userServiceWrite.updateUser(user1);
	
	    Account account = accountServiceRead.findPlanByaccountId(user1.getUserAccountId());
	    account.setAccountAvailableBalance(account.getAccountAvailableBalance().add(user.getAccountAvailableBalance()));
	    accountServiceWrite.updateAccount(account);
	    MoneyLog moneyLog = new MoneyLog();
	    moneyLog.setMoneyLogAccountId(account.getAccountId());
	    moneyLog.setMoneyLogStreamType(4);
	    moneyLog.setMoneyLogOpreateMoney(user.getAccountAvailableBalance());
	    moneyLog.setMoneyLogCreateTime(new Date());
	    moneyLog.setMoneyLogOpreateId((long)5);
	    moneyLog.setMoneyLogState(1);
	    moneyLog.setMoneyLogIsvillager(1);
	    moneyLog.setMoneyLogRefundState(0);
	    moneyLog.setMoneyLogChannelId(channelId);
	    MoneyLogServiceWrite.add(moneyLog);
		OperateLog operateLog = new OperateLog();
		String remark = OperateUtil.operateVip(nowAdmin.getAdminRealname(), 7, user1.getUserRealname(), null);
		operateLog.setOperateRemark(remark);
		operateLog.setOperateTime(new Date());
		operateLog.setOperateAdminId(nowAdmin.getAdminId());
		operateServiceWrite.addOperateLogs(operateLog);
    	return "redirect:villagerlist.action";
    }
    
	
    /**
     * 检验会否是会员
     * @param userTel
     * @param userIdcard
     * @return
     * @throws Exception
     */
    @RequestMapping("checkVip")
    public @ResponseBody Integer checkVip(String userTel,String userIdcard) throws Exception{
    	
    	User user1 = userServiceRead.findByIdcard(userTel, userIdcard);
		if(user1==null){
			return 1;
			
			
			
		}
		if(user1.getUserChannelId()!=null&&user1.getUserChannelId()!=0){
			return 2;
			
			
		}
    	return 0;
    }
	/**
	 * 会员租赁记录列表
	 * 
	 * @param model
	 * @param bikeRentInfo
	 * @param pageIndex
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("vipBikeRentInfoList")
	public String vipBikeRentInfoList(Model model, BikeRentInfo bikeRentInfo,
			@RequestParam(defaultValue = "1") Integer pageIndex) throws Exception {
		// 地点搜索 起点
		if (null != bikeRentInfo.getRentLatLng() && !"".equals(bikeRentInfo.getRentLatLng())) {
			String blockCode = BlockUtil.getBlockCode5(bikeRentInfo.getRentLatLng());
			List<Long> blockIds=blockServiceRead.findBlockIds(blockCode);
			if (null != blockIds) {
				bikeRentInfo.setRentBlockIds(blockIds);
			} else
				bikeRentInfo.setRentStartBlockId((long) -1);// 输入了地点，没有对应的区域方格
		} // 终点
		if (null != bikeRentInfo.getReturnLatLng() && !"".equals(bikeRentInfo.getReturnLatLng())) {
			String blockCode = BlockUtil.getBlockCode5(bikeRentInfo.getReturnLatLng());
			List<Long> blockIds=blockServiceRead.findBlockIds(blockCode);
			if (null != blockIds) {
				bikeRentInfo.setReturnBlockIds(blockIds);
			} else
				bikeRentInfo.setRentStartBlockId((long) -1);
		}
		User user = userServiceRead.findById(bikeRentInfo.getRentInfoUserId());
		if (null != bikeRentInfo.getbBikeCode() && !"".equals(bikeRentInfo.getbBikeCode())) {// 根据车code搜索车ids
			List<Bike> bikeList = bikeServiceRead.findBikeByCode(bikeRentInfo.getbBikeCode());
			if (null != bikeList) {
				List<Long> bikeIds = new ArrayList<Long>();
				for (Bike b : bikeList)
					bikeIds.add(b.getBikeId());
				bikeRentInfo.setBikeIds(bikeIds);
			} else
				bikeRentInfo.setRentInfoBikeId((long) -1);
		}
		List<BikeRentInfo> bikeRentInfoList = bikeRentInfoServiceRead.findVipUserBikeRentinfo(bikeRentInfo, pageIndex,
				appConfig.getPage_size_web());
		if(null!=bikeRentInfoList&&0!=bikeRentInfoList.size()){
			for(BikeRentInfo br:bikeRentInfoList){
				br.setBike(bikeServiceRead.findBikeByBikeId(br.getRentInfoBikeId()));
			}
		}
		Integer totalPage = 1;
		Integer totalCount = bikeRentInfoServiceRead.findVipUserBikeRentinfoCount(bikeRentInfo);
		if (totalCount > 0) {
			totalPage = PageUtil.getTotalPage(totalCount);
		}
		model.addAttribute("pageIndex", pageIndex);
		model.addAttribute("pageSize", appConfig.getPage_size_web());
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("user", user);
		model.addAttribute("bikeRentInfo", bikeRentInfo);
		model.addAttribute("bikeRentInfoList", bikeRentInfoList);
		return "user_rentRecord_list";
	}

	/**
	 * 删除用户租赁记录（含批量）
	 * 
	 * @param rentInfoId
	 * @param userId
	 * @param rentInfoIds
	 * @return
	 * @throws Exception
	 *//*
	@RequestMapping("deleteBikeRentInfo")
	public String deleteBikeRentInfo(Long rentInfoId, Long userId, Long[] rentInfoIds) throws Exception {
		BikeRentInfo bikeRentInfo = new BikeRentInfo();
		if (null != rentInfoIds && 0 != rentInfoIds.length) {
			for (int i = 0; i < rentInfoIds.length; i++) {
				bikeRentInfo.setRentInfoId(rentInfoIds[i]);
				bikeRentInfoServiceWrite.deleteBikeRentInfoById(bikeRentInfo);
			}
		} else if (null != rentInfoId && 0 != rentInfoId) {
			bikeRentInfo.setRentInfoId(rentInfoId);
			bikeRentInfoServiceWrite.deleteBikeRentInfoById(bikeRentInfo);
		}
		return "redirect:bikeRentInfoList.action?rentInfoUserId=" + userId;
	}

	*//**
	 * 用户租赁记录详情
	 * 
	 * @param model
	 * @param userId
	 * @param rentInfoId
	 * @return
	 * @throws Exception
	 *//*
	@RequestMapping("bikeRentInfoDetail")
	public String bikeRentInfoDetail(Model model, Long userId, Long rentInfoId,Integer flag) throws Exception {
		User user = userServiceRead.findById(userId);
		BikeRentInfo bikeRentInfo = bikeRentInfoServiceRead.findBikeRentInfoById(rentInfoId);
		model.addAttribute("user", user);
		model.addAttribute("flag", flag);
		model.addAttribute("bikeRentInfo", bikeRentInfo);
		return "detail/user_rentOrder_detail";

	}*/
	
	/**
	 * 修改页面
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("editJsp")
	public String editJsp(Long userId,Model mdoel) throws Exception{
		User user = userServiceRead.findById(userId);
		mdoel.addAttribute("user", user);
		return "detail/userState_edit";
	}
	
	/**
	 * 修改
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("editState")
	public String editState(User user,HttpSession session) throws Exception{
		Admin admin = (Admin) session.getAttribute("admin");
		Admin nowAdmin = adminServiceRead.findAdminId(admin.getAdminId());
		User nowUser = userServiceRead.findById(user.getUserId());
		OperateLog operateLog = new OperateLog();
		String userStateStr = null;
		if(user.getUserState()==0){
			userStateStr = "空闲";
		}else if(user.getUserState()==1){
			userStateStr = "租借";
		}
		String remark = OperateUtil.operateUser(nowAdmin.getAdminRealname(), 1,nowUser.getUserRealname(),userStateStr,null,null);
		operateLog.setOperateRemark(remark);
		operateLog.setOperateTime(new Date());
		operateLog.setOperateAdminId(nowAdmin.getAdminId());
		operateServiceWrite.addOperateLogs(operateLog);
		userServiceWrite.updateUser(user);
		return "redirect:list.action";
	}
	
	
	/**
	 * 黑名单列表
	 * 
	 * @param model
	 * @param pageIndex
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("blackList")
	public String userBlackList(Model model, @RequestParam(defaultValue = "1") Integer pageIndex, User user)
			throws Exception {
		List<User> userList = userServiceRead.findUserByCondition(user, pageIndex, appConfig.getPage_size_web());
		if(null!=userList&&0!=userList.size()){
			for (User u : userList) {
				if (null == u.getDefriendAdminId())
					continue;
				else {
					String adminRealName = adminServiceRead.findAdminId(u.getDefriendAdminId()).getAdminRealname();
					u.setDefriendAdminName(adminRealName);
				}
			}
		}
		Integer totalPage = 1;
		Integer totalCount = userServiceRead.countAllUser(user);
		if (totalCount > 0) {
			totalPage = PageUtil.getTotalPage(totalCount);
		}
		model.addAttribute("userList", userList);
		model.addAttribute("pageIndex", pageIndex);
		model.addAttribute("pageSize", appConfig.getPage_size_web());
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("user", user);
		return "user_blacklist_list";
	}

	

	
	/**
	 * 
	 * 删除用户（区分在用户页面、黑名单页面删除、含批量）
	 * 
	 * @param model
	 * @param user
	 * @param userIds
	 * @param flag
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("update")
	public @ResponseBody String updateUser(HttpSession session, Model model, User user, Long[] userIds, Integer flag) throws Exception {
		Admin admin = (Admin) session.getAttribute("admin");
		Admin nowAdmin = adminServiceRead.findAdminId(admin.getAdminId());
		if (null != userIds && 0 != userIds.length) {
			OperateLog operateLog = new OperateLog();
			operateLog.setOperateTime(new Date());
			operateLog.setOperateAdminId(nowAdmin.getAdminId());
			String userRealName = "";
			for (int i = 0; i < userIds.length; i++) {
				user.setUserId(userIds[i]);
				userServiceWrite.updateUser(user);
				User nowUser = userServiceRead.findById(userIds[i]);
				userRealName = userRealName +"  "+ nowUser.getUserRealname();
			}
			
			if(flag==null){
				String remark = OperateUtil.operateUser(nowAdmin.getAdminRealname(), 5,userRealName,null,null,null);
				operateLog.setOperateRemark(remark);
			}
			if (null != flag && flag == 1) {
				String remark = OperateUtil.operateUser(nowAdmin.getAdminRealname(), 10,userRealName,null,null,null);
				operateLog.setOperateRemark(remark);
			}
			
			operateServiceWrite.addOperateLogs(operateLog);
			
		} else if (null != user.getUserId() && 0 != user.getUserId()){
			OperateLog operateLog = new OperateLog();
			operateLog.setOperateTime(new Date());
			operateLog.setOperateAdminId(nowAdmin.getAdminId());
			userServiceWrite.updateUser(user);
			User nowUser = userServiceRead.findById(user.getUserId());
			if(flag==null){
				String remark = OperateUtil.operateUser(nowAdmin.getAdminRealname(), 4,nowUser.getUserRealname(),null,null,null);
				operateLog.setOperateRemark(remark);
			}
			if (null != flag && flag == 1) {
				String remark = OperateUtil.operateUser(nowAdmin.getAdminRealname(), 9,nowUser.getUserRealname(),null,null,null);
				operateLog.setOperateRemark(remark);
			}
			operateServiceWrite.addOperateLogs(operateLog);
		}
		
		if (null != flag && flag == 1) {
			return "redirect:blackList.action?userIsblacklist=0";
		}
		return "redirect:list.action";
	}

	/**
	 * 拉黑、恢复用户（包含批量）
	 * 
	 * @param model
	 * @param user
	 * @param userIds
	 * @param session
	 * @param flag
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("updateBlacklist")
	public @ResponseBody String updateBlacklistUser(Model model,String defriendReason , User user, Long[] userIds, HttpSession session, Integer flag)
			throws Exception {
		Admin admin = (Admin) session.getAttribute("admin");
		Admin nowAdmin = adminServiceRead.findAdminId(admin.getAdminId());
		if (null != userIds && 0 != userIds.length) {
			OperateLog operateLog = new OperateLog();
			operateLog.setOperateTime(new Date());
			operateLog.setOperateAdminId(nowAdmin.getAdminId());
			String userRealName = "";
			for (int i = 0; i < userIds.length; i++) {
				user.setUserId(userIds[i]);
				userServiceWrite.updateUserBlackList(user, admin.getAdminId(),defriendReason);
				User nowUser = userServiceRead.findById(userIds[i]);
				userRealName = userRealName +"  "+ nowUser.getUserRealname();
			}
			if(flag==null){
				String remark = OperateUtil.operateUser(nowAdmin.getAdminRealname(), 3,userRealName,null,null,null);
				operateLog.setOperateRemark(remark);
			}
			if (null != flag && flag == 1) {
				String remark = OperateUtil.operateUser(nowAdmin.getAdminRealname(), 8,userRealName,null,null,null);
				operateLog.setOperateRemark(remark);
			}
			
			operateServiceWrite.addOperateLogs(operateLog);
			
		} else if (null != user.getUserId() && 0 != user.getUserId()){
			OperateLog operateLog = new OperateLog();
			operateLog.setOperateTime(new Date());
			operateLog.setOperateAdminId(nowAdmin.getAdminId());
			userServiceWrite.updateUserBlackList(user, admin.getAdminId(),defriendReason);
			User nowUser = userServiceRead.findById(user.getUserId());
			if(flag==null){
				String remark = OperateUtil.operateUser(nowAdmin.getAdminRealname(), 2,nowUser.getUserRealname(),null,null,null);
				operateLog.setOperateRemark(remark);
			}
			if (null != flag && flag == 1) {
				String remark = OperateUtil.operateUser(nowAdmin.getAdminRealname(), 7,nowUser.getUserRealname(),null,null,null);
				operateLog.setOperateRemark(remark);
			}
			operateServiceWrite.addOperateLogs(operateLog);
		}
		
		return "success";
	}

	

	

	

	
	/**
	 * 用户租赁记录列表
	 * 
	 * @param model
	 * @param bikeRentInfo
	 * @param pageIndex
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("bikeRentInfoList")
	public String bikeRentInfoList(Model model, BikeRentInfo bikeRentInfo,
			@RequestParam(defaultValue = "1") Integer pageIndex) throws Exception {
		// 地点搜索 起点
		if (null != bikeRentInfo.getRentLatLng() && !"".equals(bikeRentInfo.getRentLatLng())) {
			String blockCode = BlockUtil.getBlockCode5(bikeRentInfo.getRentLatLng());
			List<Long> blockIds=blockServiceRead.findBlockIds(blockCode);
			if (null != blockIds) {
				bikeRentInfo.setRentBlockIds(blockIds);
			} else
				bikeRentInfo.setRentStartBlockId((long) -1);// 输入了地点，没有对应的区域方格
		} // 终点
		if (null != bikeRentInfo.getReturnLatLng() && !"".equals(bikeRentInfo.getReturnLatLng())) {
			String blockCode = BlockUtil.getBlockCode5(bikeRentInfo.getReturnLatLng());
			List<Long> blockIds=blockServiceRead.findBlockIds(blockCode);
			if (null != blockIds) {
				bikeRentInfo.setReturnBlockIds(blockIds);
			} else
				bikeRentInfo.setRentStartBlockId((long) -1);
		}
		User user = userServiceRead.findById(bikeRentInfo.getRentInfoUserId());
		if (null != bikeRentInfo.getbBikeCode() && !"".equals(bikeRentInfo.getbBikeCode())) {// 根据车code搜索车ids
			List<Bike> bikeList = bikeServiceRead.findBikeByCode(bikeRentInfo.getbBikeCode());
			if (null != bikeList) {
				List<Long> bikeIds = new ArrayList<Long>();
				for (Bike b : bikeList)
					bikeIds.add(b.getBikeId());
				bikeRentInfo.setBikeIds(bikeIds);
			} else
				bikeRentInfo.setRentInfoBikeId((long) -1);
		}
		List<BikeRentInfo> bikeRentInfoList = bikeRentInfoServiceRead.findUserBikeRentinfo(bikeRentInfo, pageIndex,
				appConfig.getPage_size_web());
		if(null!=bikeRentInfoList&&0!=bikeRentInfoList.size()){
			for(BikeRentInfo br:bikeRentInfoList){
				br.setBike(bikeServiceRead.findBikeByBikeId(br.getRentInfoBikeId()));
			}
		}
		Integer totalPage = 1;
		Integer totalCount = bikeRentInfoServiceRead.findUserBikeRentinfoCount(bikeRentInfo);
		if (totalCount > 0) {
			totalPage = PageUtil.getTotalPage(totalCount);
		}
		model.addAttribute("pageIndex", pageIndex);
		model.addAttribute("pageSize", appConfig.getPage_size_web());
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("user", user);
		model.addAttribute("bikeRentInfo", bikeRentInfo);
		model.addAttribute("bikeRentInfoList", bikeRentInfoList);
		return "user_rentRecord_list";
	}

	/**
	 * 删除用户租赁记录（含批量）
	 * 
	 * @param rentInfoId
	 * @param userId
	 * @param rentInfoIds
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("deleteBikeRentInfo")
	public String deleteBikeRentInfo(Long rentInfoId, Long userId, Long[] rentInfoIds) throws Exception {
		BikeRentInfo bikeRentInfo = new BikeRentInfo();
		if (null != rentInfoIds && 0 != rentInfoIds.length) {
			for (int i = 0; i < rentInfoIds.length; i++) {
				bikeRentInfo.setRentInfoId(rentInfoIds[i]);
				bikeRentInfoServiceWrite.deleteBikeRentInfoById(bikeRentInfo);
			}
		} else if (null != rentInfoId && 0 != rentInfoId) {
			bikeRentInfo.setRentInfoId(rentInfoId);
			bikeRentInfoServiceWrite.deleteBikeRentInfoById(bikeRentInfo);
		}
		return "redirect:bikeRentInfoList.action?rentInfoUserId=" + userId;
	}

	/**
	 * 用户租赁记录详情
	 * 
	 * @param model
	 * @param userId
	 * @param rentInfoId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("bikeRentInfoDetail")
	public String bikeRentInfoDetail(Model model, Long userId, Long rentInfoId,Integer flag) throws Exception {
		User user = userServiceRead.findById(userId);
		BikeRentInfo bikeRentInfo = bikeRentInfoServiceRead.findBikeRentInfoById(rentInfoId);
		model.addAttribute("user", user);
		model.addAttribute("flag", flag);
		model.addAttribute("bikeRentInfo", bikeRentInfo);
		return "detail/user_rentOrder_detail";

	}
	
	/**
	 * 每日用户统计
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("userCount")
	public String userCount(Model model) throws Exception{
		List<Integer> arr = new ArrayList<Integer>();
		Date date = DateUtil.getCurrentDay();//当天零点
		Integer increase = null;
		for(int i = 0; i < 7; i++){
			increase = userServiceRead.userCount(DateUtil.minusDateNum(date, i));
			arr.add(increase);
		}
		Integer newuser = userServiceRead.userCount(date);
		model.addAttribute("arr", arr);
		model.addAttribute("newuser",newuser);
		return "usercount";
	}
	
}
