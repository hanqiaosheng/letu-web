package org.service.cms.write;

import org.entity.dto.GuideAgencyInfos;

public interface GuideAgencyServiceWrite {
    /**
     * 删除
     * @param guideAgencyId
     * @return
     * @throws Exception
     */
    public int deleteById(Long guideAgencyId) throws Exception;

    /**
     * 增加
     * @param guideAgencyInfos
     * @return
     * @throws Exception
     */
    public Long add(GuideAgencyInfos guideAgencyInfos) throws Exception;

    /**
     * 编辑
     * @param guideAgencyInfos
     * @return
     * @throws Exception
     */
    public int edit(GuideAgencyInfos guideAgencyInfos) throws Exception;

    /**
     * 更新
     * @param guideAgencyInfos
     * @return
     * @throws Exception
     */
    public int update(GuideAgencyInfos guideAgencyInfos) throws Exception;
}
