package org.service.weixin.read;

import org.entity.dto.GuideGroupInfos;
import org.util.Condition;
import org.util.DateUtil;

import java.util.Date;
import java.util.List;

public interface GuideGroupWxServiceRead {

    /**
     * 跟据id查询
     */
    public GuideGroupInfos findById(Long groupId) throws Exception;



    /**
     * 跟据导游id查询
     */
    public List<GuideGroupInfos> findByGuideId(Long guideId) throws Exception;


    /**
     * 跟据团id查询
     */
    public GuideGroupInfos findByGroupId(Long groupId) throws Exception;

    /**
     * 跟据团id查询
     */
    public List<GuideGroupInfos> findByGroupIds(List<Long> groupIds) throws Exception;

    /**
     * 跟据团ids查询正在进行中的
     */
    public List<GuideGroupInfos> findGroupingByGroupIds(List<Long> groupIds,Date date) throws Exception;

    /**
     * 查询所有数据数量
     * @param
     * @param
     * @return
     * @throws Exception
     */
    public Integer count(List<Condition> conditions) throws Exception;

    /**
     * 根据条件查询
     * @return
     * @throws Exception
     */
    public List<GuideGroupInfos> find(List<Condition> conditions) throws Exception;

    /**
     * 根据参数进行全查询
     * @param guideGroupInfos
     * @param lgroupStartTime  出团日期左范围值
     * @param rgroupStartTime  出团日期右范围值
     * @param lheadCount  旅游团人数左范围值
     * @param rheadCount  旅游团人数右范围值
     * @param lridingCount  骑行人数左范围值
     * @param rridingCount  骑行人数右范围值
     * @return
     * @throws Exception
     */
    public List<GuideGroupInfos> findAll(GuideGroupInfos guideGroupInfos, List<Long> agencyIds,
                                         Date lgroupStartTime, Date rgroupStartTime,
                                         Integer lheadCount, Integer rheadCount,
                                         Integer lridingCount, Integer rridingCount) throws Exception;

}
