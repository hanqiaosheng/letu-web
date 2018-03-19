package org.controller.cms;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.component.AppConfig;
import org.entity.dto.Admin;
import org.entity.dto.BikeRentInfo;
import org.entity.dto.Invoice;
import org.entity.dto.Message;
import org.entity.dto.User;
import org.service.cms.read.BikeRentInfoServiceRead;
import org.service.cms.read.InvoiceServiceRead;
import org.service.cms.read.UserServiceRead;
import org.service.cms.write.InvoiceServiceWrite;
import org.service.cms.write.MessageServiceWrite;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.PageUtil;
import org.util.DateUtil;

@Controller
@Scope("prototype")
@RequestMapping("cms/invoice")
public class InvoiceController {
	
	@Resource
	AppConfig appConfig;
	
	@Resource
	InvoiceServiceRead invoiceServiceRead;
	
	@Resource
	UserServiceRead userServiceRead;

	@Resource
	InvoiceServiceWrite invoiceServiceWrite;
	
	@Resource
	MessageServiceWrite messageServiceWrite;
	
	@Resource
	BikeRentInfoServiceRead bikeRentInfoServiceRead;
	
	/**
	 * 发票列表
	 * @param session
	 * @param model
	 * @param pageIndex
	 * @param invoice
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	public String invoiceList(HttpSession session,Model model, @RequestParam(defaultValue = "1") Integer pageIndex,Invoice invoice ) throws Exception{
		List<Invoice> invoiceList = invoiceServiceRead.findInvoiceByCondition(invoice, pageIndex, appConfig.getPage_size_web());
		if(null!=invoiceList&&invoiceList.size()>0){
			for(Invoice invoice1:invoiceList){
				User user = userServiceRead.findById(invoice1.getInvoiceUserId());
				if(null!=user){
					invoice1.setUser(user);
				}
			}
		}
		Integer totalPage = 1;
		Integer totalCount = invoiceServiceRead.countInvoiceByCondition(invoice);
		if (totalCount > 0) {
			totalPage = PageUtil.getTotalPage(totalCount);
		}
		model.addAttribute("invoiceList",invoiceList);
		model.addAttribute("pageIndex", pageIndex);
		model.addAttribute("pageSize", appConfig.getPage_size_web());
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("invoice", invoice);
		
		return "invoice_list";
	}
	
	/**
	 * 更新发票状态
	 * @param session
	 * @param model
	 * @param invoice
	 * @param invoiceIds
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("update")
	public @ResponseBody String updateInvoice(HttpSession session, Model model,Invoice invoice,Long[] invoiceIds)throws Exception{
		Message message = new Message();
		Admin admin = (Admin) session.getAttribute("admin");
		if (null != invoiceIds && 0 != invoiceIds.length) {
			for (int i = 0; i < invoiceIds.length; i++) {
				invoice.setInvoiceUserId(invoiceIds[i]);
				invoiceServiceWrite.updateInvoice(invoice);
				
				
			}
		}
		else if (null != invoice.getInvoiceId() && 0 != invoice.getInvoiceId()){
			
			Invoice invoice1 = invoiceServiceRead.findInvoiceById(invoice.getInvoiceId());
			if(1==invoice.getInvoiceState()){
				message.setMessageUserId(invoice1.getInvoiceUserId());
				message.setMessageAdminId(admin.getAdminId());
				message.setMessageContent("您的纸质发票已被客服处理，即将发货");
				message.setMessageSendTime(new Date());
				message.setMessageTitle("发票处理结果");
				messageServiceWrite.addMessage(message);
			}
			if(2==invoice.getInvoiceState()){
				message.setMessageUserId(invoice1.getInvoiceUserId());
				message.setMessageAdminId(admin.getAdminId());
				message.setMessageContent("您的纸质发票已发货，请注意查收");
				message.setMessageSendTime(new Date());
				message.setMessageTitle("发票处理结果");
				messageServiceWrite.addMessage(message);
			}
			invoiceServiceWrite.updateInvoice(invoice);
		
		
		
	}
		return "redirect:list.action";

	}
	
	/**
	 * 发票详情
	 * @param model
	 * @param pageIndex
	 * @param invoice
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("detail")
	public String feedbackDetail(Model model, @RequestParam(defaultValue = "1") Integer pageIndex,Invoice invoice)throws Exception{
		invoice = invoiceServiceRead.findInvoiceById(invoice.getInvoiceId());
		model.addAttribute("invoice", invoice);
		return "detail/invoice_detail";
	}
	
	/**
	 * 跳转编辑页面
	 * @param model
	 * @param session
	 * @param invoice
	 * @return
	 * @throws Exception
	 */
	 @RequestMapping("editJsp")
	 public String editJsp(Model model,HttpSession session,Invoice invoice) throws Exception{
		 invoice = invoiceServiceRead.findInvoiceById(invoice.getInvoiceId());
		 model.addAttribute("invoice", invoice);
		 return "detail/invoice_edit";
	 }
	 
	 /**
	  * 发票行程详情
	  * @param model
	  * @param invoiceId
	  * @param pageIndex
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping("bikeRentInfo")
	 public String bikeRentInfo(Model model,Long invoiceId,@RequestParam(defaultValue="1")Integer pageIndex) throws Exception{
		 Invoice invoice = invoiceServiceRead.findInvoiceById(invoiceId);
		 List<BikeRentInfo> bikeRentInfoList=bikeRentInfoServiceRead.findByInvoiceId(invoiceId,pageIndex,appConfig.getPage_size_web());
		 if(bikeRentInfoList.size()>0){
			 for(BikeRentInfo bikeRentInfo:bikeRentInfoList){
				 if(bikeRentInfo.getRentEndtime().getTime()>bikeRentInfo.getRentStarttime().getTime()){
					 Long time = DateUtil.minuteDiff(bikeRentInfo.getRentStarttime(),bikeRentInfo.getRentEndtime());
					 bikeRentInfo.setTime(time);
				 }
				 else{
					 bikeRentInfo.setTime((long)0);
				 }
			 }
			 
		 }
		 Integer totalPage = 1;
		 Integer totalCount = bikeRentInfoServiceRead.findfindByInvoiceIdCount(invoiceId, pageIndex, appConfig.getPage_size_web());
		 
		 if(totalCount>1){
			 totalPage = PageUtil.getTotalPage(totalCount);
	        }
		 User user = userServiceRead.findById(invoice.getInvoiceUserId());
		 model.addAttribute("bikeRentInfoList", bikeRentInfoList);
		 model.addAttribute("user", user);
		 model.addAttribute("totalPage", totalPage);
		 model.addAttribute("pageIndex",pageIndex);
		 model.addAttribute("invoiceId",invoiceId);
		 return "invoice_rentDetail";
	 }
	 
	
	
}