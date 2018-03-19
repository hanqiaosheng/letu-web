package org.controller.guide;

import org.entity.dto.Bike;
import org.entity.dto.BikeRentInfo;
import org.entity.dto.GuideGroupInfos;
import org.entity.dto.User;
import org.service.cms.read.BikeRentInfoServiceRead;
import org.service.cms.read.GuideGroupServiceRead;
import org.service.cms.read.GuideGroupUserRead;
import org.service.cms.read.UserServiceRead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.util.MessageUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("group")
public class GroupController {

    @Resource
    GuideGroupServiceRead guideGroupServiceRead;
    @Resource
    GuideGroupUserRead guideGroupUserRead;
    @Resource
    UserServiceRead userServiceRead;
    @Resource
    BikeRentInfoServiceRead bikeRentInfoServiceRead;

    protected final MessageUtil messageUtil = new MessageUtil();

    @RequestMapping("groupList")
    Map groupList(String guideId) throws Exception {
        Map data = new HashMap();

        //查找导游名下所有团
        List<GuideGroupInfos> groups = guideGroupServiceRead.findByGuideId(Long.valueOf(guideId));

        data.put("groupList", groups);
        return data;
    }

    @RequestMapping("groupDetail")
    Map groupDetail(String guideId,Long groupId) throws Exception {
        GuideGroupInfos group = guideGroupServiceRead.findByGroupId(groupId);

        //查找团下所有游客及
        List<Long> userIds = guideGroupUserRead.findByGuideGroupId(groupId);
        List<User> users = userServiceRead.findByIds(userIds);
        //该游客团下所有游客的未完成的租赁订单
        List<BikeRentInfo> bikeRentInfos = bikeRentInfoServiceRead.findByUserIdsAndState(userIds,2);
        //关联用户和未完成订单
        for (BikeRentInfo rent : bikeRentInfos) {
            for (User user : users) {
                if (rent.getRentInfoUserId() == user.getUserId()) {
                    user.setNotFinishRentInfo(rent);
                }
            }
        }
        Map data = new HashMap();
        data.put("group", group);
        data.put("userList", users);
        return data;
    }
}
