package org.service.cms.read;

import java.util.List;
import org.entity.dto.AdminLookAgencyKey;

/**
 * 管理员查看旅行社权限读取
 */
public interface LookAgencyServiceRead {

    /**
     * 根据管理员查询可以查看的旅行社
     * @return
     * @throws Exception
     */
    public List<Long> findByAdmin(Long admin_id) throws Exception;

    /**
     * 根据旅行社id找到其可以被谁查看
     * @param agency_id
     * @return
     * @throws Exception
     */
    public List<Long> findByAgency(Long agency_id) throws Exception;


    public List<AdminLookAgencyKey> findKeyByAdmin(Long admin_id) throws Exception;

    public List<AdminLookAgencyKey> findKeyByAgency(Long agency_id) throws Exception;

    public List<AdminLookAgencyKey> findAll() throws Exception;
}
