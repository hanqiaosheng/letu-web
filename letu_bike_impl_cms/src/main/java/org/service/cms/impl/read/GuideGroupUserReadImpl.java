package org.service.cms.impl.read;

import org.dao.GuideGroupUserMapper;
import org.entity.dto.GuideGroupUser;
import org.entity.dto.GuideGroupUserExample;
import org.service.cms.read.GuideGroupUserRead;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Service("guideGroupUserRead")
public class GuideGroupUserReadImpl implements GuideGroupUserRead{
    @Resource
    GuideGroupUserMapper guideGroupUserMapper;

    @Override
    public List<Long> findByGuideGroupId(Long guideGroupId) {
         GuideGroupUserExample example = new GuideGroupUserExample();
         GuideGroupUserExample.Criteria criteria = example.createCriteria();
         criteria.andGuideGroupIdEqualTo(guideGroupId);
         List<GuideGroupUser> guideGroupUsers = guideGroupUserMapper.selectByExample(example);
         List<Long> userIds = new LinkedList<>();
         for(GuideGroupUser guideGroupUser:guideGroupUsers){
             userIds.add(guideGroupUser.getUserId());
         }
        return userIds;
    }

    @Override
    public List<Long> findByUserId(Long userId) {
        GuideGroupUserExample example = new GuideGroupUserExample();
        GuideGroupUserExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        List<GuideGroupUser> guideGroupUsers = guideGroupUserMapper.selectByExample(example);
        List<Long> groupIds = new LinkedList<>();
        for(GuideGroupUser guideGroupUser:guideGroupUsers){
            groupIds.add(guideGroupUser.getGuideGroupId());
        }
        return groupIds;
    }

    @Override
    public int countByGuideGroupId(Long guideGroupId) {
        GuideGroupUserExample example = new GuideGroupUserExample();
        GuideGroupUserExample.Criteria criteria = example.createCriteria();
        criteria.andGuideGroupIdEqualTo(guideGroupId);
        return guideGroupUserMapper.countByExample(example);
    }

    @Override
    public int countByUserId(Long userId) {
        GuideGroupUserExample example = new GuideGroupUserExample();
        GuideGroupUserExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        return guideGroupUserMapper.countByExample(example);
    }
}
