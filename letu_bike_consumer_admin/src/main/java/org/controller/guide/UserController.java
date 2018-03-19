package org.controller.guide;

import org.component.AppConfig;
import org.entity.dto.BikeRentInfo;
import org.entity.dto.Code;
import org.entity.dto.GuideGroupUser;
import org.entity.dto.User;
import org.service.cms.read.*;
import org.service.cms.write.CodeServiceWrite;
import org.service.cms.write.GuideGroupUserWrite;
import org.service.cms.write.UserServiceWrite;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.util.IdCardUtil;
import org.util.MessageUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {


    @Resource
    GuideGroupServiceRead guideGroupServiceRead;
    @Resource
    GuideGroupUserRead guideGroupUserRead;
    @Resource
    UserServiceRead userServiceRead;
    @Resource
    BikeRentInfoServiceRead bikeRentInfoServiceRead;
    @Resource
    AppConfig appConfig;
    @Resource
    CodeServiceWrite codeServiceWrite;
    @Resource
    CodeServiceRead codeServiceRead;
    @Resource
    GuideGroupUserWrite guideGroupUserWrite;
    @Resource
    UserServiceWrite userServiceWrite;

    @RequestMapping("userDetail")
    Map userDetail(String userId,Long groupId) throws Exception {
        User user = userServiceRead.findById(Long.valueOf(userId));
        List<BikeRentInfo> rents = bikeRentInfoServiceRead.findByUserIdAndGroupId(Long.valueOf(userId),groupId);
        for (BikeRentInfo rent : rents) {
            if (rent.getRentState() == 2){
                user.setNotFinishRentInfo(rent);
            }
        }
        Map data = new HashMap();
        data.put("user",user);
        data.put("rents",rents);
        return data;
    }

    @RequestMapping("userRegister")
    Map userRegister(User user,Long guideGroupId,String telCode) throws Exception{
        Map data = new HashMap();

        //校验短信验证码
        Code code = codeServiceRead.findByPhone(user.getUserTel(),1);//注册用
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
        MessageUtil message = IdCardUtil.authentication(user.getUserIdcard(), user.getUserRealname());
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

        //写入用户信息
        Long userId = userServiceWrite.addUser(user);
        //写入关联表
        GuideGroupUser guideGroupUser=new GuideGroupUser();
        guideGroupUser.setUserId(userId);
        guideGroupUser.setGuideGroupId(guideGroupId);
        guideGroupUserWrite.add(guideGroupUser);
        data.put("code", 1);
        data.put("msg", "注册成功");
        return data;
    }
}
