package org.service.weixin.impl.read;

import org.dao.GuideGroupInfosMapper;
import org.entity.dto.GuideGroupInfos;
import org.entity.dto.GuideGroupInfosExample;
import org.service.weixin.read.GuideGroupWxServiceRead;
import org.springframework.stereotype.Service;
import org.util.Condition;
import org.util.DataServiceUtil;

import javax.annotation.Resource;
import java.util.*;

/**
 * 旅游团页面读取操作类
 */
@Service("guideGroupWxServiceRead")
public class GuideGroupWxServiceReadImpl implements GuideGroupWxServiceRead {

    @Resource
    GuideGroupInfosMapper guideGroupInfosMapper;
    @Override
    public GuideGroupInfos findById(Long groupId) throws Exception {
        return guideGroupInfosMapper.selectByPrimaryKey(groupId);
    }



    @Override
    public List<GuideGroupInfos> findByGuideId(Long guideId) throws Exception {
        return guideGroupInfosMapper.selectByGuideId(guideId);
    }

    @Override
    public GuideGroupInfos findByGroupId(Long groupId) throws Exception {
        return guideGroupInfosMapper.selectByGroupId(groupId);
    }

    @Override
    public List<GuideGroupInfos> findByGroupIds(List<Long> groupIds) throws Exception {
        return guideGroupInfosMapper.selectByGroupIds(groupIds);
    }

    @Override
    public List<GuideGroupInfos> findGroupingByGroupIds(List<Long> groupIds,Date date) throws Exception {
        if(groupIds.isEmpty()){
            return Collections.emptyList();
        }
        GuideGroupInfosExample example = new GuideGroupInfosExample();
        GuideGroupInfosExample.Criteria criteria = example.createCriteria();
        criteria.andGuideGroupIdIn(groupIds);
        criteria.andGuideGroupStartTimeLessThan(date);
        criteria.andGuideGroupEndTimeGreaterThan(date);
        return guideGroupInfosMapper.selectByExample(example);
    }

    @Override
    public Integer count(List<Condition> conditions) throws Exception {
        GuideGroupInfosExample example = new GuideGroupInfosExample();
        GuideGroupInfosExample.Criteria criteria = example.createCriteria();
        return (Integer)DataServiceUtil.count(guideGroupInfosMapper,example,criteria,conditions);
    }

    @Override
    public List<GuideGroupInfos> find(List<Condition> conditions) throws Exception {
        //构造数据操作类
        GuideGroupInfosExample example = new GuideGroupInfosExample();
        GuideGroupInfosExample.Criteria criteria = example.createCriteria();
        return (List<GuideGroupInfos>) DataServiceUtil.find(guideGroupInfosMapper,example,criteria,conditions);
    }

    @Override
    public List<GuideGroupInfos> findAll(GuideGroupInfos guideGroupInfos,List<Long> agencyIds,
                                         Date lgroupStartTime, Date rgroupStartTime,
                                         Integer lheadCount, Integer rheadCount,
                                         Integer lridingCount, Integer rridingCount) throws Exception {
        GuideGroupInfosExample example = new GuideGroupInfosExample();
        GuideGroupInfosExample.Criteria criteria = example.createCriteria();
        //导游查询条件
        if(guideGroupInfos.getGuideId()!=null
                && !"".equals(guideGroupInfos.getGuideId())){
            criteria.andGuideIdEqualTo(guideGroupInfos.getGuideId());
        }

        //旅行社查询条件
        if(guideGroupInfos.getGuideGroupAgencyId()!=null
                && !"".equals(guideGroupInfos.getGuideGroupAgencyId())){
            criteria.andGuideGroupAgencyIdEqualTo(guideGroupInfos.getGuideGroupAgencyId());
        }

        if(guideGroupInfos.getGuideGroupAgencyId()!=null){
            criteria.andGuideGroupAgencyIdEqualTo(guideGroupInfos.getGuideGroupAgencyId());
        }else if(agencyIds!=null && agencyIds.size()!=0){
            criteria.andGuideGroupAgencyIdIn(agencyIds);
        }else if(agencyIds!=null && agencyIds.size()==0){
            return new LinkedList<>();
        }

        //景区查询条件
        if(guideGroupInfos.getGuideGroupScenic()!=null && !"".equals(guideGroupInfos.getGuideGroupScenic())){
            criteria.andGuideGroupScenicLike("%"+guideGroupInfos.getGuideGroupScenic().trim()+"%");
        }

//        //出团状态查询条件
//        if(guideGroupInfos.getGuideGroupState()!=null && guideGroupInfos.getGuideGroupState()!=-1){
//            criteria.andGuideGroupStateEqualTo(guideGroupInfos.getGuideGroupState());
//        }

        //发券状态查询条件
        if(guideGroupInfos.getGuideGroupCouponState()!=null && guideGroupInfos.getGuideGroupCouponState()!=-1){
            criteria.andGuideGroupCouponStateEqualTo(guideGroupInfos.getGuideGroupCouponState());
        }

        //出团日期查询
        if(lgroupStartTime!=null || rgroupStartTime!=null) {
            if (lgroupStartTime != null && rgroupStartTime == null) {
                criteria.andGuideGroupStartTimeGreaterThanOrEqualTo(lgroupStartTime);
            }else if (lgroupStartTime == null && rgroupStartTime != null ) {
                criteria.andGuideGroupStartTimeLessThanOrEqualTo(rgroupStartTime);
            }else{
                criteria.andGuideGroupStartTimeBetween(lgroupStartTime,rgroupStartTime);
            }
        }

        //游客人数查询
        if(lheadCount!=null || rheadCount!=null){
            if(lheadCount!=null && rheadCount==null){
                criteria.andGuideGroupHeadcountGreaterThanOrEqualTo(lheadCount);
            }else if(lheadCount==null && rheadCount!=null){
                criteria.andGuideGroupHeadcountLessThanOrEqualTo(rheadCount);
            }else{
                criteria.andGuideGroupHeadcountBetween(lheadCount,rheadCount);
            }
        }

        //骑行人数查询
        if(lridingCount!=null || rridingCount!=null){
            if(lridingCount!=null && rridingCount==null){
                criteria.andGuideGroupRidingcountGreaterThanOrEqualTo(lridingCount);
            }else if(lridingCount==null && rridingCount!=null){
                criteria.andGuideGroupRidingcountLessThanOrEqualTo(rridingCount);
            }else{
                criteria.andGuideGroupRidingcountBetween(lridingCount,rridingCount);
            }
        }
        return guideGroupInfosMapper.selectUnionByExample(example);
    }


}
