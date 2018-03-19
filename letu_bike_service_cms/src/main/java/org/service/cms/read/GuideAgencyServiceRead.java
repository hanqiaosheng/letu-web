package org.service.cms.read;

import java.util.List;

import org.entity.dto.GuideAgencyInfos;
import org.util.Condition;

public interface GuideAgencyServiceRead {
    /**
     * 根据主键查询
     * @param guideAgencyId
     * @return
     * @throws Exception
     */
    public GuideAgencyInfos findById(Long guideAgencyId) throws Exception;

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
    public List<GuideAgencyInfos> find(List<Condition> conditions) throws Exception;

    /**
     * 查询所有旅行社
     * @param guideAgencyInfos
     * @param lRidingCount
     * @param rRidingCount
     * @return
     * @throws Exception
     */
    public List<GuideAgencyInfos> findAll(GuideAgencyInfos guideAgencyInfos,List<Long> agencyIds,
                                          Long lRidingCount,Long rRidingCount) throws Exception;

    /**
     *
     * @return
     * @throws Exception
     */
    public List<GuideAgencyInfos> findByAdminId(Long createAdminId,Long chargeAdminId,Long manageAdminId,boolean not_type) throws  Exception;


    public List<Long> findIdsByAdminId(Long createAdminId,Long chargeAdminId,Long manageAdminId,boolean not_type) throws Exception;
    /**
     * 根据创建者查询数量
     * @param creatorAdminId
     * @return
     */
    public int countByCreatorId(Long creatorAdminId);

    /**
     * 根据负责人查询数量
     * @param principalId
     * @return
     */
    public int countByPrincipalId(Long principalId);

    /**
     * 根据管理员查询数量
     * @param managerId
     * @return
     */
    public int countByManagerId(Long managerId);
}
