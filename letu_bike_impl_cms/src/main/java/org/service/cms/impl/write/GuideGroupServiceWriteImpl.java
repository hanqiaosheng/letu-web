package org.service.cms.impl.write;
import javax.annotation.Resource;

import org.dao.GuideGroupInfosMapper;
import org.entity.dto.GuideGroupInfos;
import org.service.cms.write.GuideGroupServiceWrite;
import org.springframework.stereotype.Service;


@Service("guideGroupServiceWrite")
public class GuideGroupServiceWriteImpl implements GuideGroupServiceWrite{
    @Resource
    GuideGroupInfosMapper guideGroupInfosMapper;
    @Override
    public int deleteById(Long guideGroupId) throws Exception {
        guideGroupInfosMapper.deleteByPrimaryKey(guideGroupId);
        return 0;
    }

    @Override
    public Long add(GuideGroupInfos guideGroupInfos) throws Exception {
        guideGroupInfosMapper.insertSelective(guideGroupInfos);
        return guideGroupInfos.getGuideGroupId();
    }

    @Override
    public int edit(GuideGroupInfos guideGroupInfos) throws Exception {
        return 0;
    }

    @Override
    public int update(GuideGroupInfos guideGroupInfos) throws Exception {
        guideGroupInfosMapper.updateByPrimaryKeySelective(guideGroupInfos);
        return 0;
    }
}
