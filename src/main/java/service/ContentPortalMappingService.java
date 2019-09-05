/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;

/**
 *
 * @author OLAWALE
 */
public interface ContentPortalMappingService {
    
    
    public List<Object[]> readAll();
    
    public String create(int contentPortalMasterId,  int contentMasterId);

    public String updateStatus(int contentPortalCategoryMappingId, int parseInt0);

    public Object[] readByContentPortal(int contentMasterId, int contentPortalId);

    public String createUpdate(int categoryMasterId, int contentPortalId, int contentPortalMapStatus);

    
}
