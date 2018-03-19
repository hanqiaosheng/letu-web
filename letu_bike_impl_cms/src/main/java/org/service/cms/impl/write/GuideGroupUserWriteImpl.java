package org.service.cms.impl.write;

import org.dao.GuideGroupUserMapper;
import org.entity.dto.GuideGroupUser;
import org.service.cms.write.GuideGroupUserWrite;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("guideGroupUserWrite")
public class GuideGroupUserWriteImpl implements GuideGroupUserWrite {
    @Resource
    GuideGroupUserMapper guideGroupUserMapper;
    @Override
    public void add(GuideGroupUser guideGroupUser) {
        guideGroupUserMapper.insertSelective(guideGroupUser);
    }
}
