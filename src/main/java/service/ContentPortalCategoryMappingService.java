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
public interface ContentPortalCategoryMappingService {
    
    
    public List<Object[]> readAll();
    
    public String create(int contentPortalMaster, int contentCategoryMaster);

    public String updateStatus(int contentPortalCategoryMappingId, int parseInt0);

    public Object[] readByCategoryPortal(int contentCategoryMasterId,  int contentPortalMasterId);

    public String createUpdate(int categoryId, int contentPortalId, int catPortalMapStatus);

    
}
