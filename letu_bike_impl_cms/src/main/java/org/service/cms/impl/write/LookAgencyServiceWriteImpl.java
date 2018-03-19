package org.service.cms.impl.write;

import org.dao.AdminLookAgencyMapper;
import org.entity.dto.AdminLookAgencyKey;
import org.service.cms.write.LookAgencyServiceWrite;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("lookAgencyServiceWrite")
public class LookAgencyServiceWriteImpl implements LookAgencyServiceWrite{
    @Resource
    AdminLookAgencyMapper adminLookAgencyMapper;
    @Override
    public void add(AdminLookAgencyKey adminLookAgency) throws Exception {
        adminLookAgencyMapper.insert(adminLookAgency);
    }

    @Override
    public void del(AdminLookAgencyKey adminLookAgency) throws Exception {
        adminLookAgencyMapper.deleteByPrimaryKey(adminLookAgency);
    }

    @Override
    public void update(AdminLookAgencyKey adminLookAgency) throws Exception {
        adminLookAgencyMapper.updateByExample(adminLookAgency,null);
    }
}
