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
public interface ContentTypePortalMappingService {
    
    
    public List<Object[]> readAll();
    
    public String create(int contentPortalMaster, int contentCategoryMaster);

    public String updateStatus(int contentPortalCategoryMappingId, int parseInt0);

    public List<Object[]> readAllWithType();

//    public List<Object[]> readAllWithTypeByContentPortal(int contentPortalMaster);

    //new cms
    public String createUpdate(int typeId, int contentPortalId, int typePortalMapStatus);

    public Object[] readByTypePortal(int contentTypeMasterId, int contentPortalMasterId);

    public Object[] readByTypePortalStatus(int contentTypeMasterId, int contentPortalMasterId, int contentTypePortalMappingStatus);

    
}
