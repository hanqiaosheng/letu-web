package org.service.cms.write;

import org.entity.dto.GuideGroupInfos;

public interface GuideGroupServiceWrite {
    /**
     * 删除
     * @param
     * @return
     * @throws Exception
     */
    public int deleteById(Long guideGroupId) throws Exception;

    /**
     * 增加
     * @param GuideGroupInfos
     * @return
     * @throws Exception
     */
    public Long add(GuideGroupInfos GuideGroupInfos) throws Exception;

    /**
     * 编辑
     * @param GuideGroupInfos
     * @return
     * @throws Exception
     */
    public int edit(GuideGroupInfos GuideGroupInfos) throws Exception;

    /**
     * 更新
     * @param GuideGroupInfos
     * @return
     * @throws Exception
     */
    public int update(GuideGroupInfos GuideGroupInfos) throws Exception;
}
