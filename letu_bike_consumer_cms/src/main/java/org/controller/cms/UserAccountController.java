package org.controller.cms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.component.AppConfig;
import org.entity.dto.Account;
import org.entity.dto.Admin;
import org.entity.dto.MoneyLog;
import org.entity.dto.OperateLog;
import org.entity.dto.User;
import org.service.cms.read.AdminServiceRead;
import org.service.cms.read.UserAccountServiceRead;
import org.service.cms.read.UserServiceRead;
import org.service.cms.write.OperateServiceWrite;
import org.service.cms.write.UserAccountServiceWrite;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.OperateUtil;
import org.util.PageUtil;

@Controller
@Scope("prototype")
@RequestMapping("cms/user/account")
public class UserAccountController {

	@Resource
	AppConfig appConfig;

	@Resource
	UserServiceRead userServiceRead;

	@Resource
	UserAccountServiceRead userAccountServiceRead;

	@Resource
	UserAccountServiceWrite userAccountServiceWrite;
	
	@Resource
	AdminServiceRead adminServiceRead;
	
	@Resource
	OperateServiceWrite operateServiceWrite;


	/**
	 * 账户列表
	 * 
	 * @param model
	 * @param pageIndex
	 * @param account
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	public String accountList(Model model, @RequestParam(defaultValue = "1") Integer pageIndex, Account account,String aUserName)
			throws Exception {
		List<Account> accountList = userAccountServiceRead.findAccountByCondition(account, pageIndex,
				appConfig.getPage_size_web(),aUserName);
		if(null!=accountList&&0!=accountList.size()){
			for (Account a : accountList) {
				a.setaUser(userServiceRead.findById(a.getAccountUserId()));// 设置账户的用户信息
			}
		}
		Integer totalPage = 1;
		Integer totalCount = userAccountServiceRead.countAllAccount(account,aUserName);
		if (totalCount > 0) {
			totalPage = PageUtil.getTotalPage(totalCount);
		}
		model.addAttribute("accountList", accountList);
		model.addAttribute("pageIndex", pageIndex);
		model.addAttribute("aUserName", aUserName);
		model.addAttribute("pageSize", appConfig.getPage_size_web());
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("account", account);
		return "user_account_list";
	}
	
	

	/**
	 * 冻结、解冻账户（包含批量）
	 * 
	 * @param model
	 * @param account
	 * @param accountIds
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("isFreeze")
	public @ResponseBody String isFreezeAccount(Model model, Account account, Long[] accountIds) throws Exception {
		if (null != accountIds && 0 != accountIds.length) {
			for (int i = 0; i < accountIds.length; i++) {
				account.setAccountId(accountIds[i]);
				userAccountServiceWrite.updateUserAccount(account);
			}
		} else if (null != account.getAccountId() && 0 != account.getAccountId())
			userAccountServiceWrite.updateUserAccount(account);
		return "success";
	}
	
	@RequestMapping("editAccountJsp")
	public String editAccountJsp(Long accountId,Model model) throws Exception{
		Account account = userAccountServiceRead.findAccountById(accountId);
		User user = userServiceRead.findById(account.getAccountUserId());
		model.addAttribute("account", account);
		model.addAttribute("user", user);
		return "detail/userAccount_edit";
	}
	/**
	 * 修改押金
	 */
	@RequestMapping("editAccount")
	public String editAccount(HttpSession session,Account account) throws Exception{
		Admin admin = (Admin) session.getAttribute("admin");
		Admin nowAdmin = adminServiceRead.findAdminId(admin.getAdminId());
		Account nowAccount = userAccountServiceRead.findAccountById(account.getAccountId());
		User nowUser = userServiceRead.findById(nowAccount.getAccountUserId());
		OperateLog operateLog = new OperateLog();
		String remark = OperateUtil.operateUser(nowAdmin.getAdminRealname(), 6,nowUser.getUserRealname(),null,nowAccount.getAccountDeposit(),account.getAccountDeposit());
		operateLog.setOperateRemark(remark);
		operateLog.setOperateTime(new Date());
		operateLog.setOperateAdminId(nowAdmin.getAdminId());
		operateServiceWrite.addOperateLogs(operateLog);//添加日志
		userAccountServiceWrite.updateUserAccount(account);
		return "redirect:list.action";
	}

	/**
	 * 对应用户的账户资金日志
	 * 
	 * @param model
	 * @param pageIndex
	 * @param moneyLog
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("detail")
	public String accountDetail(Model model, @RequestParam(defaultValue = "1") Integer pageIndex, MoneyLog moneyLog)
			throws Exception {
		if (null == moneyLog.getMoneyLogAccountId() || 0 == moneyLog.getMoneyLogAccountId())
			return "redirect:list.action";
		Account account = userAccountServiceRead.findAccountById(moneyLog.getMoneyLogAccountId());
		User user = userServiceRead.findById(account.getAccountUserId());
		List<MoneyLog> moneyLogList = userAccountServiceRead.findAccountDetailByCondition(moneyLog, pageIndex,
				appConfig.getPage_size_web());
		Integer totalPage = 1;
		Integer totalCount = userAccountServiceRead.countAllAccountDetail(moneyLog);
		if (totalCount > 0) {
			totalPage = PageUtil.getTotalPage(totalCount);
		}
		model.addAttribute("user", user);
		model.addAttribute("account", account);
		model.addAttribute("moneyLog", moneyLog);
		model.addAttribute("moneyLogList", moneyLogList);
		model.addAttribute("pageIndex", pageIndex);
		model.addAttribute("pageSize", appConfig.getPage_size_web());
		model.addAttribute("totalPage", totalPage);

		return "user_accountDetail_list";
	}
	
	/**
	 * 村民的账户资金日志
	 * 
	 * @param model
	 * @param pageIndex
	 * @param moneyLog
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("accountdetail")
	public String villagerAccountDetail(Model model, @RequestParam(defaultValue = "1") Integer pageIndex, MoneyLog moneyLog)
			throws Exception {
		if (null == moneyLog.getMoneyLogAccountId() || 0 == moneyLog.getMoneyLogAccountId())
			return "redirect:list.action";
		Account account = userAccountServiceRead.findAccountById(moneyLog.getMoneyLogAccountId());
		User user = userServiceRead.findById(account.getAccountUserId());
		List<MoneyLog> moneyLogList = userAccountServiceRead.findVillagerAccountDetailByCondition(moneyLog, pageIndex,
				appConfig.getPage_size_web());
		Integer totalPage = 1;
		Integer totalCount = userAccountServiceRead.countAllVillagerAccountDetail(moneyLog);
		if (totalCount > 0) {
			totalPage = PageUtil.getTotalPage(totalCount);
		}
		model.addAttribute("user", user);
		model.addAttribute("account", account);
		model.addAttribute("moneyLog", moneyLog);
		model.addAttribute("moneyLogList", moneyLogList);
		model.addAttribute("pageIndex", pageIndex);
		model.addAttribute("pageSize", appConfig.getPage_size_web());
		model.addAttribute("totalPage", totalPage);

		return "villager_accountDetail_list";
	}

	/**
	 * 软删除账户资金日志
	 * 
	 * @param model
	 * @param moneyLog
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("delete")
	public @ResponseBody String deleteAccountMoneyLog(Model model, MoneyLog moneyLog) throws Exception {
		moneyLog.setMoneyLogState(0);
		userAccountServiceWrite.updateUserAccountMoneyLog(moneyLog);
		return "redirect:detail.action?moneyLogAccountId=" + moneyLog.getMoneyLogAccountId();
	}
}
