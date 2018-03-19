package org.service.cms.impl.read;

import java.util.LinkedList;
import java.util.List;

import org.entity.dto.AdminLookAgencyKey;
import org.dao.AdminLookAgencyMapper;
import org.entity.dto.AdminLookAgencyExample;
import org.service.cms.read.LookAgencyServiceRead;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;


@Service("lookAgencyServiceRead")
public class LookAgencyServiceReadImpl implements LookAgencyServiceRead{
    @Resource
    AdminLookAgencyMapper adminLookAgencyMapper;

    @Override
    public List<Long> findByAdmin(Long admin_id) throws Exception {
        AdminLookAgencyExample example = new AdminLookAgencyExample();
        AdminLookAgencyExample.Criteria criteria = example.createCriteria();
        if(admin_id!=null){
            criteria.andAdminIdEqualTo(admin_id);
        }
        List<AdminLookAgencyKey> adminLookAgencyKeys = adminLookAgencyMapper.selectByExample(example);
        List<Long> agencyIds = new LinkedList<>();
        for(AdminLookAgencyKey adminLookAgencyKey:adminLookAgencyKeys){
            agencyIds.add(adminLookAgencyKey.getAgencyId());
        }
        return agencyIds;
    }

    @Override
    public List<Long> findByAgency(Long agency_id) throws Exception {
        AdminLookAgencyExample example = new AdminLookAgencyExample();
        AdminLookAgencyExample.Criteria criteria = example.createCriteria();
        if(agency_id!=null){
            criteria.andAgencyIdEqualTo(agency_id);
        }
        List<AdminLookAgencyKey> adminLookAgencyKeys = adminLookAgencyMapper.selectByExample(example);
        List<Long> adminIds = new LinkedList<>();
        for(AdminLookAgencyKey adminLookAgencyKey:adminLookAgencyKeys){
            adminIds.add(adminLookAgencyKey.getAdminId());
        }
        return adminIds;
    }

    @Override
    public List<AdminLookAgencyKey> findKeyByAdmin(Long admin_id) throws Exception {
        AdminLookAgencyExample example = new AdminLookAgencyExample();
        AdminLookAgencyExample.Criteria criteria = example.createCriteria();
        if(admin_id!=null){
            criteria.andAdminIdEqualTo(admin_id);
        }
        return adminLookAgencyMapper.selectByExample(example);
    }

    @Override
    public List<AdminLookAgencyKey> findKeyByAgency(Long agency_id) throws Exception {
        AdminLookAgencyExample example = new AdminLookAgencyExample();
        AdminLookAgencyExample.Criteria criteria = example.createCriteria();
        if(agency_id!=null){
            criteria.andAgencyIdEqualTo(agency_id);
        }
        return adminLookAgencyMapper.selectByExample(example);
    }

    @Override
    public List<AdminLookAgencyKey> findAll() throws Exception {
        AdminLookAgencyExample example = new AdminLookAgencyExample();
        AdminLookAgencyExample.Criteria criteria = example.createCriteria();
        return adminLookAgencyMapper.selectByExample(example);
    }


}
