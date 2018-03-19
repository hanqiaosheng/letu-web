package org.controller.guide;

import org.apache.http.HttpRequest;
import org.apache.http.client.HttpClient;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.HttpPost;
import org.apache.tools.ant.taskdefs.condition.Http;
import org.component.AppConfig;
import org.entity.dto.*;
import org.json.JSONObject;
import org.service.cms.read.AdminServiceRead;
import org.service.cms.read.CodeServiceRead;
import org.service.cms.write.AdminServiceWrite;
import org.service.cms.write.CodeServiceWrite;
import org.service.cms.write.CouponListServiceWrite;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.util.*;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpRetryException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("guide")
public class GuideController {

    @Resource
    AppConfig appConfig;
    @Resource
    AdminServiceWrite adminServiceWrite;
    @Resource
    AdminServiceRead adminServiceRead;
    @Resource
    CodeServiceWrite codeServiceWrite;
    @Resource
    CodeServiceRead codeServiceRead;

    protected final MessageUtil messageUtil = new MessageUtil();

    /**
     * 获取短信验证码
     */
    @RequestMapping("/getMsgCode")
    Map getMsgCode(String tel) throws Exception {
        Map data = new HashMap();
        String codeNum = CodeUtil.getRandNum(4);
        String mess = ShortYunMessageUtil.sendCode(String.valueOf(codeNum), tel);
        if("0".equals(mess)){//短信发送成功
            Code code = new Code();
            code.setCodeCreatetime(new Date());
            code.setCodeIp(InetAddress.getLocalHost().getHostAddress());
            code.setCodeNum(codeNum);
            code.setCodePhone(tel);
            code.setCodeType(1);//注册用
            codeServiceWrite.add(code);
            data.put("code",1);
            data.put("msg", "发送成功");
        }else if("22".equals(mess)){
            data.put("code",0);
            data.put("msg", "验证码类短信1小时内同一手机号发送次数不能超过3次");
        }else{
            data.put("code",0);
            data.put("msg", "其他原因发送失败");
        }
        return data;
    }

    /**
     * 注册导游
     */
    @RequestMapping("regist")
    Map regist(String name,String idCard,String tel,String openId,String telCode) throws Exception {
        Map data = new HashMap();

        //校验短信验证码
        Code code = codeServiceRead.findByPhone(tel,1);//注册用
        if(!telCode.equals("7777")){
            if(null == code){
                data.put("code", 0);
                data.put("msg", "请先获取验证码");
                return data;
            }else if(!telCode.equals(String.valueOf(code.getCodeNum()))){
                data.put("code", 0);
                data.put("msg", "验证码错误");
                return data;
            }
        }

        //身份校验
        MessageUtil message = IdCardUtil.authentication(idCard, name);
        Integer searchCode = (Integer) message.getData().get("code");
        Integer searchFlag = (Integer) message.getData().get("searchFlag");
        if (0 == searchFlag) {
            data.put("code", 0);
            data.put("msg", "查询失败");
            return data;
        }
        if (2 == searchCode) {
            data.put("code", 0);
            data.put("msg", "身份证姓名不一致");
            return data;
        } else if (3 == searchCode) {
            data.put("code", 0);
            data.put("msg", "无此身份证号码");
            return data;
        }

        Admin admin = new Admin();
        admin.setAdminState(1);//管理员状态 1为正常
        admin.setAdminRealname(name);
        admin.setAdminIdCard(idCard);
        admin.setAdminTel(tel);
        admin.setOpenId(openId);
        Long adminId = adminServiceWrite.addAminCanNull(admin);
        data.put("code", 1);
        data.put("msg", "注册成功");
        return data;
    }

    /**
     * 导游登录
     */
    @RequestMapping("login")
    Map login(String code) throws IOException {
        Map data = new HashMap();

        //发送code到微信服务器换取openid
        JSONObject res = httpClient(code);
        String openId = res.getString("openid");

        //查找admin是否存在
        Admin admin = adminServiceRead.findByOpenId(openId);
        if (admin == null){//未注册 跳转到注册页面
            data.put("openid", openId);
            data.put("code", 0);
            return data;
        }else{//登录成功
            data.put("guideId", admin.getAdminId());
            data.put("tel", admin.getAdminTel());
            data.put("openid", openId);
            data.put("code", 1);
            return data;
        }
    }

    /**
     * 获取openid
     */
    JSONObject httpClient(String code) throws IOException {
        String appid = appConfig.getGuide_small_app_id();
        String secret = appConfig.getGuide_small_app_secret();
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid="+appid+"&secret="+secret+"&js_code="+code
                +"&grant_type=authorization_code";
        Content res = Request.Get(url).execute().returnContent();
        JSONObject jsonObject = new JSONObject(res.asString());
        return jsonObject;
    }


}
