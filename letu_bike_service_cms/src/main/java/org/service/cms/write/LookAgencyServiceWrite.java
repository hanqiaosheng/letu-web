package org.service.cms.write;

import org.entity.dto.AdminLookAgencyKey;

public interface LookAgencyServiceWrite {

    /**
     *
     * @param adminLookAgency
     * @throws Exception
     */
    public void add(AdminLookAgencyKey adminLookAgency) throws Exception;

    /**
     *
     * @param adminLookAgency
     * @throws Exception
     */
    public void del(AdminLookAgencyKey adminLookAgency) throws Exception;

    /**
     *
     * @param adminLookAgency
     * @throws Exception
     */
    public void update(AdminLookAgencyKey adminLookAgency) throws Exception;
}
