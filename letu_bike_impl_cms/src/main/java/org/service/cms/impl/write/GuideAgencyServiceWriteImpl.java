package org.service.cms.impl.write;
import javax.annotation.Resource;

import org.dao.GuideAgencyInfosMapper;
import org.entity.dto.GuideAgencyInfos;
import org.service.cms.write.GuideAgencyServiceWrite;
import org.springframework.stereotype.Service;

/**
 * 旅行社页面写入操作类
 */
@Service("guideAgencyServiceWrite")
public class GuideAgencyServiceWriteImpl implements GuideAgencyServiceWrite{
    @Resource
    GuideAgencyInfosMapper agencyInfosMapper;

    @Override
    public int deleteById(Long guideAgencyId) throws Exception {
        agencyInfosMapper.deleteByPrimaryKey(guideAgencyId);
        return 0;
    }

    @Override
    public Long add(GuideAgencyInfos guideAgencyInfos) throws Exception {
        agencyInfosMapper.insertSelective(guideAgencyInfos);
        return guideAgencyInfos.getGuideAgencyId();
    }

    @Override
    public int edit(GuideAgencyInfos guideAgencyInfos) throws Exception {
        return 0;
    }

    @Override
    public int update(GuideAgencyInfos guideAgencyInfos) throws Exception {
        agencyInfosMapper.updateByPrimaryKeySelective(guideAgencyInfos);
        return 0;
    }
}
