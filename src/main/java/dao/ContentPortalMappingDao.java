/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

/**
 *
 * @author OLAWALE
 */
public interface ContentPortalMappingDao {

    public List<Object[]> readAll();

    public String updateStatus(int portalContentMappingId, int status);

    public String create(int contentPortalMasterId,  int contentMasterId);

    public Object[] readByContentPortal(int contentMasterId, int contentPortalId);

    public String createUpdate(int categoryMasterId, int contentPortalId, int contentPortalMapStatus);
    


    
}
