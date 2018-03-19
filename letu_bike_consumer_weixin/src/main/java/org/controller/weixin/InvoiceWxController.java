package org.controller.weixin;


import java.math.BigDecimal;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.component.AppConfig;
import org.entity.dto.BikeRentInfo;
import org.entity.dto.DataDet;
import org.entity.dto.Invoice;
import org.entity.dto.SysParament;
import org.entity.dto.User;
import org.service.weixin.read.BikeRentInfoWxServiceRead;
import org.service.weixin.read.DataDetWxServiceRead;
import org.service.weixin.read.InvoiceWxServiceRead;
import org.service.weixin.read.SysParamentServiceRead;
import org.service.weixin.read.UserServiceWeixinRead;
import org.service.weixin.write.BikeRentInfoWxServiceWrite;
import org.service.weixin.write.InvoiceWxServiceWrite;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.MessageUtil;
import org.util.PageUtil;
import org.util.PdfUtil;
import org.util.SendEmailUtil;
import org.util.SplitUtil;
import org.util.weixin.WXHttpApi;


@Controller
@Scope("prototype")
@RequestMapping(value = "/invoice", method = RequestMethod.POST)
public class InvoiceWxController {

	@Resource
	InvoiceWxServiceRead invoiceWxServiceRead;
	@Resource
	InvoiceWxServiceWrite invoiceWxServiceWrite;
	@Resource
	UserServiceWeixinRead userServiceWeixinRead;
	@Resource
	SysParamentServiceRead sysParamentServiceRead;
	@Resource
	DataDetWxServiceRead dataDetWxServiceRead;
	@Resource
	BikeRentInfoWxServiceRead bikeRentInfoWxServiceRead;
	@Resource
	BikeRentInfoWxServiceWrite bikeRentInfoWxServiceWrite;
	@Resource
	AppConfig AppConfig;
	
	protected final MessageUtil messageUtil = new MessageUtil();
	
	//当前类的logger日志
	private static Logger loggers = Logger.getLogger(InvoiceWxController.class);

	/**
	 * 获取电子发票
	 * @param header
	 * @param invoice
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getEInvoice", method = RequestMethod.POST)
	public @ResponseBody MessageUtil getEInvoice(@RequestHeader HttpHeaders header,Invoice invoice,HttpSession session,String rentIdstr)throws Exception{
		String fromFlag = header.getFirst("fromFlag");
		Map<String, Object> map = new HashMap<String, Object>();
		User userLogin = new User();
		if("3".equals(fromFlag)){
			String token = header.getFirst("token");
			SysParament sysParament = sysParamentServiceRead.findByName("token");
			userLogin = userServiceWeixinRead.findUserByToken(token,Integer.parseInt(sysParament.getSysParamentValue()));
		}else if("2".equals(fromFlag)){
			userLogin = (User) session.getAttribute("curruser");
		}else if("1".equals(fromFlag)){
			String token = header.getFirst("token");
			SysParament sysParament = sysParamentServiceRead.findByName("token");
			userLogin = userServiceWeixinRead.findUserByToken(token,Integer.parseInt(sysParament.getSysParamentValue()));
		}
		if(null==userLogin){
			messageUtil.setCode(2);
			messageUtil.setMessage("未登录或登录超时");
			return messageUtil;
		}
		if(!rentIdstr.equals("")){
			String[] newStr = SplitUtil.toSplit(rentIdstr);
			String travelUrl = null;
			if(newStr.length>0){
				for(int i = 0;i<newStr.length;i++){
					BikeRentInfo bikeRentInfo = bikeRentInfoWxServiceRead.findByBikeRentId(Long.valueOf(newStr[i]));
					if(null!=bikeRentInfo.getRentInvoiceId()&&!bikeRentInfo.getRentInvoiceId().equals("")){
						messageUtil.setCode(0);
						messageUtil.setMessage("fail");
						return messageUtil;
					}
				}
				travelUrl = PdfUtil.pdfCreate("F:/pdfFile/moban/pdffile.pdf", AppConfig.getUpload_path(), "中国", invoice.getInvoiceMoney(),newStr.length);
			}
			invoice.setInvoiceApplicationTime(new Date());
			invoice.setInvoiceUserId(userLogin.getUserId());
			invoice.setInvoiceDeliverTypeId((long)34);//统一快递费
			invoice.setInvoiceType(1);
			invoice.setInvoiceState(3);
			invoice.setInvoiceTravelUrl(travelUrl);
			Long invoiceId = invoiceWxServiceWrite.addInvoice(invoice);
			if(newStr.length>0){
				for(int i = 0;i<newStr.length;i++){
					BikeRentInfo bikeRentInfo = bikeRentInfoWxServiceRead.findByBikeRentId(Long.valueOf(newStr[i]));
					bikeRentInfo.setRentInvoiceId(invoiceId);
					bikeRentInfoWxServiceWrite.updateRentInfo(bikeRentInfo);
				}
			}
			map.put("invoiceType", 1);
			messageUtil.setData(map);
			List<String> toAddressList = new ArrayList<String>();
			toAddressList.add(invoice.getInvoiceEmail());
			List<String> fileAddressList = new ArrayList<String>(); 
			fileAddressList.add(AppConfig.getUpload_path()+invoice.getInvoiceTravelUrl());
			SendEmailUtil.sendEmail(toAddressList, "电子发票", "电子发票及行程", fileAddressList);
		}else{
			messageUtil.setCode(0);
			messageUtil.setMessage("fail");
			return messageUtil;
		}
		messageUtil.setCode(1);
		messageUtil.setMessage("success");
		return messageUtil;
	}
	
	/**
	 * 获取发票总金额
	 * @param invoice
	 * @param session
	 * @param rentIdstr
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getInvoiceMoney", method = RequestMethod.POST)
	public @ResponseBody MessageUtil getInvoiceMoney(Invoice invoice,HttpSession session,String rentIdstr)throws Exception{
		if(!rentIdstr.equals("")){
			Map<String,Object> data = new HashMap<String,Object>();
			String[] newStr = SplitUtil.toSplit(rentIdstr);
			Double totalMoney = 0.0;
			if(newStr.length>0){
				for(int i = 0;i<newStr.length;i++){
					BikeRentInfo bikeRentInfo = bikeRentInfoWxServiceRead.findByBikeRentId(Long.valueOf(newStr[i]));
					totalMoney += bikeRentInfo.getRentPrice().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				}
			}
			data.put("totalMoney", totalMoney);
			messageUtil.setCode(1);
			messageUtil.setData(data);
			messageUtil.setMessage("success");
		}else{
			messageUtil.setCode(0);
			messageUtil.setMessage("fail");
		}
		
		return messageUtil;
	}
	
	/**
	 * 获取纸质发票
	 * @param header
	 * @param invoice
	 * @param session
	 * @param rentIdstr
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getPInvoice", method = RequestMethod.POST)
	public @ResponseBody MessageUtil getPInvoice(@RequestHeader HttpHeaders header,Invoice invoice,HttpSession session,String rentIdstr)throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		String fromFlag = header.getFirst("fromFlag");
		User userLogin = new User();
		if("3".equals(fromFlag)){
			String token = header.getFirst("token");
			SysParament sysParament = sysParamentServiceRead.findByName("token");
			userLogin = userServiceWeixinRead.findUserByToken(token,Integer.parseInt(sysParament.getSysParamentValue()));
		}else if("2".equals(fromFlag)){
			userLogin = (User) session.getAttribute("curruser");
		}else if("1".equals(fromFlag)){
			String token = header.getFirst("token");
			SysParament sysParament = sysParamentServiceRead.findByName("token");
			userLogin = userServiceWeixinRead.findUserByToken(token,Integer.parseInt(sysParament.getSysParamentValue()));
		}
		if(null==userLogin){
			messageUtil.setCode(2);
			messageUtil.setMessage("未登录或登录超时");
			return messageUtil;
		}
		loggers.info("传来的订单字符串为"+rentIdstr);
		if(!rentIdstr.equals("")&&null!=rentIdstr){
			String[] newStr = SplitUtil.toSplit(rentIdstr);
			//String travelUrl = null;
			if(newStr.length>0){
				for(int i = 0;i<newStr.length;i++){
					BikeRentInfo bikeRentInfo = bikeRentInfoWxServiceRead.findByBikeRentId(Long.valueOf(newStr[i]));
					if(null!=bikeRentInfo){
						if(null!=bikeRentInfo.getRentInvoiceId()&&!bikeRentInfo.getRentInvoiceId().equals("")){
							
							if("2".equals(fromFlag)){
								map.put("gotoaction", AppConfig.getBase_path_weixin());
								messageUtil.setData(map);
								messageUtil.setCode(4);
								messageUtil.setMessage("fail");
							}else{
								messageUtil.setCode(0);
								messageUtil.setMessage("请求失败");
							}
							return messageUtil;
						}
					}
					
				}
				//travelUrl = PdfUtil.pdfCreate("F:/pdfFile/moban/pdffile.pdf", AppConfig.getUpload_path(), "中国", invoice.getInvoiceMoney(),newStr.length);
			}
			
			invoice.setInvoiceApplicationTime(new Date());
			invoice.setInvoiceUserId(userLogin.getUserId());
			invoice.setInvoiceState(5);//未激活
			invoice.setInvoiceType(2);
			//invoice.setInvoiceTravelUrl(travelUrl);
			invoice.setInvoiceDeliverTypeId((long)34);//统一快递费
			Long invoiceId = invoiceWxServiceWrite.addInvoice(invoice);
			loggers.info("添加发票信息");
			loggers.info("发票费用为"+invoice.getInvoiceMoney().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			if(invoice.getInvoiceMoney().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()<200){
				DataDet dataDet = dataDetWxServiceRead.findDataDetByDataDetId((long)34);
				Double payMoneys = Double.valueOf(dataDet.getDataDetVal());
				String rentIdAndInvoiceIdstr = rentIdstr + invoiceId;
				//payMoneys = 0.01;//测试
				if("2".equals(fromFlag)){
					map = WXHttpApi.payPostage(userLogin.getUserId(), userLogin.getUserOpenid(), AppConfig.getApp_id(),
							AppConfig.getApp_domain(), AppConfig.getApp_partner(), AppConfig.getApp_partnerkey(),
							InetAddress.getLocalHost().getHostAddress(), payMoneys,rentIdAndInvoiceIdstr);
					messageUtil.setCode(1);
				}else if("1".equals(fromFlag)){
					map.put("rentIdstr", rentIdstr);
					map.put("invoiceId", invoiceId);
					messageUtil.setCode(1);
					messageUtil.setData(map);
					messageUtil.setMessage("success");
					return messageUtil;
				}
			}else{
				Invoice nowinvoice = invoiceWxServiceRead.findById(invoiceId);
				nowinvoice.setInvoiceState(0);//未处理
				invoiceWxServiceWrite.updateInvoice(nowinvoice);
				if(newStr.length>0){
					for(int i = 0;i<newStr.length;i++){
						BikeRentInfo bikeRentInfo = bikeRentInfoWxServiceRead.findByBikeRentId(Long.valueOf(newStr[i]));
						bikeRentInfo.setRentInvoiceId(invoiceId);
						bikeRentInfoWxServiceWrite.updateRentInfo(bikeRentInfo);
						loggers.info("更新订单的发票id");
					}
					/*List<String> toAddressList = new ArrayList<String>();
					toAddressList.add(invoice.getInvoiceEmail());
					List<String> fileAddressList = new ArrayList<String>(); 
					fileAddressList.add(AppConfig.getUpload_path()+invoice.getInvoiceTravelUrl());
					SendEmailUtil.sendEmail(toAddressList, "行程单", "行程单", fileAddressList);*/
				}
				if("2".equals(fromFlag)){
					messageUtil.setCode(3);
				}else{
					messageUtil.setCode(1);
				}
			}
			
		}else{
			messageUtil.setCode(0);
			if("2".equals(fromFlag)){
				messageUtil.setMessage("fail");
			}else{
				messageUtil.setMessage("请求失败");
			}
			return messageUtil;
		}
		map.put("gotoaction", AppConfig.getBase_path_weixin());
		messageUtil.setData(map);
		if("2".equals(fromFlag)){
			messageUtil.setMessage("success");
		}else{
			messageUtil.setMessage("提交成功");
		}
		return messageUtil;
	}
	
	
	/**
	 * 查询电子发票
	 * @param header
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getinvoiceById", method = RequestMethod.POST)
	public @ResponseBody MessageUtil getinvoiceById(Long invoiceId)throws Exception{
		Map<String, Object> data = new HashMap<String, Object>();
		Invoice invoice = invoiceWxServiceRead.findById(invoiceId);
		List<BikeRentInfo> bikeRentInfos = bikeRentInfoWxServiceRead.findByInvoiceId(invoiceId);
		if(bikeRentInfos.size()>1){
			data.put("firstTime", bikeRentInfos.get(0).getRentStarttime());
			data.put("lastTime", bikeRentInfos.get(bikeRentInfos.size()-1).getRentStarttime());
		}else{
			if(bikeRentInfos.size()==1){
				data.put("firstTime", bikeRentInfos.get(0).getRentStarttime());
			}
		}
		data.put("rentNum", bikeRentInfos.size());
		data.put("invoice", invoice);
		data.put("bikeRentInfos", bikeRentInfos);
		messageUtil.setData(data);
		messageUtil.setCode(1);
		messageUtil.setMessage("success");
		return messageUtil;
	}
	
	/**
	 * 获取用户发票列表
	 * @param header
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getinvoiceList", method = RequestMethod.POST)
	public @ResponseBody MessageUtil getinvoiceList(@RequestHeader HttpHeaders header,HttpSession session,Integer pageIndex)throws Exception{
		Map<String, Object> datamap = new HashMap<String, Object>();
		String fromFlag = header.getFirst("fromFlag");
		User userLogin = new User();
		if("3".equals(fromFlag)){
			String token = header.getFirst("token");
			SysParament sysParament = sysParamentServiceRead.findByName("token");
			userLogin = userServiceWeixinRead.findUserByToken(token,Integer.parseInt(sysParament.getSysParamentValue()));
		}else if("2".equals(fromFlag)){
			userLogin = (User) session.getAttribute("curruser");
		}else if("1".equals(fromFlag)){
			String token = header.getFirst("token");
			SysParament sysParament = sysParamentServiceRead.findByName("token");
			userLogin = userServiceWeixinRead.findUserByToken(token,Integer.parseInt(sysParament.getSysParamentValue()));
			if(null==userLogin){
				messageUtil.setCode(2);
				messageUtil.setMessage("未登录或登录超时");
				return messageUtil;
			}
		}
		List<Invoice> invoiceList = invoiceWxServiceRead.findByUserId(userLogin.getUserId(), pageIndex, AppConfig.getPage_size_weixin());
		Integer totalCount = invoiceWxServiceRead.findCountByUserId(userLogin.getUserId());
		Integer totalPage = PageUtil.getWxTotalPage(totalCount);
		datamap.put("invoiceList",invoiceList);
		datamap.put("totalCount",totalCount);
		datamap.put("totalPage",totalPage);
		datamap.put("pageIndex",pageIndex);
		messageUtil.setData(datamap);
		messageUtil.setCode(1);
		messageUtil.setMessage("success");
		return messageUtil;
	}
	
	/**
	 * 重新发送发票
	 * @param header
	 * @param invoiceId
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sendInvoiceAgain", method = RequestMethod.POST)
	public @ResponseBody MessageUtil sendInvoiceAgain(@RequestHeader HttpHeaders header,Long invoiceId,HttpSession session) throws Exception{
		String fromFlag = header.getFirst("fromFlag");
		User userLogin = new User();
		if("3".equals(fromFlag)){
			String token = header.getFirst("token");
			SysParament sysParament = sysParamentServiceRead.findByName("token");
			userLogin = userServiceWeixinRead.findUserByToken(token,Integer.parseInt(sysParament.getSysParamentValue()));
		}else if("2".equals(fromFlag)){
			userLogin = (User) session.getAttribute("curruser");
		}else if("1".equals(fromFlag)){
			String token = header.getFirst("token");
			SysParament sysParament = sysParamentServiceRead.findByName("token");
			userLogin = userServiceWeixinRead.findUserByToken(token,Integer.parseInt(sysParament.getSysParamentValue()));
		}
		Invoice invoice = invoiceWxServiceRead.findById(invoiceId);
		if(!userLogin.getUserId().equals(invoice.getInvoiceUserId())){
			messageUtil.setCode(0);
			messageUtil.setMessage("fail");
			return messageUtil;
		}
		List<String> toAddressList = new ArrayList<String>();
		toAddressList.add(invoice.getInvoiceEmail());
		List<String> fileAddressList = new ArrayList<String>(); 
		fileAddressList.add(AppConfig.getUpload_path()+invoice.getInvoiceTravelUrl());
		SendEmailUtil.sendEmail(toAddressList, "电子发票", "电子发票及行程", fileAddressList);
		messageUtil.setCode(1);
		messageUtil.setMessage("success");
		return messageUtil;
	}
	
}
