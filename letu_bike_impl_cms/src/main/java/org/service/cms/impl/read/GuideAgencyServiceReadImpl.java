package org.service.cms.impl.read;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.dao.GuideAgencyInfosMapper;
import org.entity.dto.GuideAgencyInfos;
import org.entity.dto.GuideAgencyInfosExample;
import org.service.cms.read.GuideAgencyServiceRead;
import org.springframework.stereotype.Service;
import org.util.Condition;
import org.util.DataServiceUtil;

/**
 * 旅行社页面读取操作类
 */
@Service("guideAgencyServiceRead")
public class GuideAgencyServiceReadImpl implements GuideAgencyServiceRead{
    @Resource
    GuideAgencyInfosMapper guideAgencyInfosMapper;


    @Override
    public GuideAgencyInfos findById(Long guideAgencyId) throws Exception {
        GuideAgencyInfos guideAgencyInfos = guideAgencyInfosMapper.selectByPrimaryKey(guideAgencyId);
        return guideAgencyInfos;
    }

    @Override
    public Integer count(List<Condition> conditions) throws Exception {
        //构造数据操作类
        GuideAgencyInfosExample example = new GuideAgencyInfosExample();
        GuideAgencyInfosExample.Criteria criteria = example.createCriteria();
        return (Integer) DataServiceUtil.count(guideAgencyInfosMapper,example,criteria,conditions);
    }

    @Override
    public List<GuideAgencyInfos> find(List<Condition> conditions) throws Exception {

        //构造数据操作类
        GuideAgencyInfosExample example = new GuideAgencyInfosExample();
        GuideAgencyInfosExample.Criteria criteria = example.createCriteria();
        return (List<GuideAgencyInfos>)DataServiceUtil.find(guideAgencyInfosMapper,example,criteria,conditions);
    }

    @Override
    public List<GuideAgencyInfos> findAll(GuideAgencyInfos guideAgencyInfos,List<Long> agencyIds,
                                          Long lRidingCount,Long rRidingCount) throws Exception {
        //构造数据操作类
        GuideAgencyInfosExample example = new GuideAgencyInfosExample();
        GuideAgencyInfosExample.Criteria criteria = example.createCriteria();


        if(guideAgencyInfos!=null) {
            //旅行社名字查询
            if (guideAgencyInfos.getGuideAgencyName() != null &&
                    !"".equals(guideAgencyInfos.getGuideAgencyName())) {
                criteria.andGuideAgencyNameLike("%" + guideAgencyInfos.getGuideAgencyName() + "%");
            }

        /*    //省份查询
            if(guideAgencyInfos.getGuideAgencyProvinceId()!=null &&
                    !"".equals(guideAgencyInfos.getGuideAgencyProvinceId())){
                criteria.andGuideAgencyProvinceIdEqualTo(guideAgencyInfos.getGuideAgencyProvinceId());
            }

            //城市查询
            if(guideAgencyInfos.getGuideAgencyCityId()!=null &&
                    !"".equals(guideAgencyInfos.getGuideAgencyCityId())){
                criteria.andGuideAgencyCityIdEqualTo(guideAgencyInfos.getGuideAgencyCityId());
            }

            //负责人名字查询
            if(guideAgencyInfos.getChargeAdmin()!=null){

            }

            //创建人名字查询
            if(guideAgencyInfos.getCreateAdmin()!=null){

            }*/
        }

        if(agencyIds!=null && agencyIds.size()!=0){
            criteria.andGuideAgencyIdIn(agencyIds);
        }else if(agencyIds!=null && agencyIds.size()==0){
            return new LinkedList<>();
        }

        //骑行人数查询
        if(lRidingCount!=null || rRidingCount!=null){
            if(lRidingCount!=null && rRidingCount==null){
                criteria.andGuideAgencyRidingCountGreaterThanOrEqualTo(lRidingCount);
            }else if(lRidingCount==null && rRidingCount!=null){
                criteria.andGuideAgencyRidingCountLessThanOrEqualTo(rRidingCount);
            }else{
                criteria.andGuideAgencyRidingCountBetween(lRidingCount,rRidingCount);
            }
        }
        return guideAgencyInfosMapper.selectByExample(example);
    }

    @Override
    public List<GuideAgencyInfos> findByAdminId(Long createAdminId, Long principalAdminId,Long manageAdminId,boolean not_type) throws Exception {
        //构造数据操作类
        GuideAgencyInfosExample example = new GuideAgencyInfosExample();
        GuideAgencyInfosExample.Criteria criteria = example.createCriteria();
        if(createAdminId!=null){
            if(not_type){
                criteria.andGuideAgencyCreatorIdNotEqualTo(createAdminId);
            }else {
                criteria.andGuideAgencyCreatorIdEqualTo(createAdminId);
            }
        }

        GuideAgencyInfosExample.Criteria criteria2 = example.createCriteria();
        if(principalAdminId!=null){
            if(not_type){
                criteria2.andGuideAgencyPrincipalIdNotEqualTo(principalAdminId);
            }else {
                criteria2.andGuideAgencyPrincipalIdEqualTo(principalAdminId);
            }
        }

        GuideAgencyInfosExample.Criteria criteria3 = example.createCriteria();
        if(manageAdminId!=null){
            if(not_type){
                criteria3.andGuideAgencyManagerIdNotEqualTo(manageAdminId);
            }else {
                criteria3.andGuideAgencyManagerIdEqualTo(manageAdminId);
            }
        }
        //将这几个条件用 或 连接
        example.or(criteria2);
        example.or(criteria3);
        return guideAgencyInfosMapper.selectByExample(example);
    }

    @Override
    public List<Long> findIdsByAdminId(Long createAdminId, Long chargeAdminId, Long manageAdminId, boolean not_type) throws Exception {
        List<GuideAgencyInfos> guideAgencyInfosList = findByAdminId(createAdminId,chargeAdminId,manageAdminId,not_type);
        List<Long> agencyIds = new ArrayList<>();
        for(GuideAgencyInfos guideAgencyInfos:guideAgencyInfosList){
            agencyIds.add(guideAgencyInfos.getGuideAgencyId());
        }
        return agencyIds;
    }


    @Override
    public int countByCreatorId(Long creatorAdminId) {
        if(creatorAdminId==null){
            return -1;
        }
        //构造数据操作类
        GuideAgencyInfosExample example = new GuideAgencyInfosExample();
        GuideAgencyInfosExample.Criteria criteria = example.createCriteria();
        criteria.andGuideAgencyCreatorIdEqualTo(creatorAdminId);
        return guideAgencyInfosMapper.countByExample(example);
    }

    @Override
    public int countByPrincipalId(Long principalId) {
        if(principalId==null){
            return -1;
        }
        //构造数据操作类
        GuideAgencyInfosExample example = new GuideAgencyInfosExample();
        GuideAgencyInfosExample.Criteria criteria = example.createCriteria();
        criteria.andGuideAgencyPrincipalIdEqualTo(principalId);
        return guideAgencyInfosMapper.countByExample(example);
    }

    @Override
    public int countByManagerId(Long managerId) {
        if(managerId==null){
            return -1;
        }
        //构造数据操作类
        GuideAgencyInfosExample example = new GuideAgencyInfosExample();
        GuideAgencyInfosExample.Criteria criteria = example.createCriteria();
        criteria.andGuideAgencyManagerIdEqualTo(managerId);
        return guideAgencyInfosMapper.countByExample(example);
    }

}
