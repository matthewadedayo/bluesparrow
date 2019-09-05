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
public interface ContentPortalCategoryMappingDao {

    public List<Object[]> readAll();

    public String updateStatus(int contentPortalCategoryMappingId, int status);

    public String create(int contentPortalMaster, int contentCategoryMaster);

    public Object[] readByCategoryPortal(int contentCategoryMasterId, int contentPortalMasterId);

    public String createUpdate(int categoryId, int contentPortalId, int catPortalMapStatus);
    


    
}
