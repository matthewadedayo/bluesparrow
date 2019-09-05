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
public interface ContentMasterDao {

    //New Update Begins....
    public List<Object[]> readAll();
    public List<Object[]> readById(int userId);
    public List<Object[]> readByIdByType(int userId, int contentTypeId);
    public List<Object[]> readByType(int contentTypeId);
    public List<Object[]> readByTypeStatus(int contentTypeId, int contentMasterStatus);
    public List<Object[]> readByStatus2(int contentMasterStatus);
    public List<Object[]> readByIdByTypeStatus(int userId, int contentTypeId, int contentMasterStatus);
    public List<Object[]> readByIdByStatus2(int userId, int contentMasterStatus);
    public String update(int contentMasterId, String title, String desc, String smallRelativePath, String middleRelativePath, String largeRelativePath, String storeType);
    
    public List<Object[]> readAllForPortalMapping();
    
    
    
    
    
    //New Update Ends......
    public String updateStatus(int contentMasterId, int status);

//    public String create(int userId, String title, String desc, String type, String smallDirectory, String middleDirectory, String largeDirectory, String storeType, int contentCategoryMasterId);
    public String create(int userId, String title, String desc, int type, String smallDirectory, String middleDirectory, String largeDirectory, String storeType, int contentCategoryMasterId, int contentSubCategoryMasterId);

    public Object[] readContentMaster(int contentMasterId);

//    public String update(int contentMasterId, String title, String desc, String type, String smallRelativePath, String middleRelativePath, String largeRelativePath, String storeType, int contentCategoryMasterId);

    

    public List<Object[]> readByTitle(String title);

    public String updateStatusByReviewer(int contentMasterId, int option, String comment);

    public List<Object[]> readByStatus(String status);

    

    public List<Object[]> readByDescription(String desc);

    public Object[] readByOthers(int userId, String title, String desc, int type, String smallDirectory, String middleDirectory, String largeDirectory, String storeType, int contentCategoryMasterId);

    public List<Object[]> readAllModified();

    public List<Object[]> readByIdModified(int userId);

    public List<Object[]> readAllForPortalMappingByUserTypeCategory(int userMaster, int contentTypeMaster, int contentCategoryMaster);

    public List<Object[]> readAllForPortalMappingByUserType(int userMaster, int contentTypeMaster);

    public List<Object[]> readAllForPortalMappingByUserCategory(int userMaster, int contentCategoryMaster);

    public List<Object[]> readAllForPortalMappingByUser(int userMaster);

    public List<Object[]> readAllForPortalMappingByTypeCategory(int contentTypeMaster, int contentCategoryMaster);

    public List<Object[]> readAllForPortalMappingByType(int contentTypeMaster);

    public List<Object[]> readAllForPortalMappingByCategory(int contentCategoryMaster);

    

    

    

    
    


    
}
