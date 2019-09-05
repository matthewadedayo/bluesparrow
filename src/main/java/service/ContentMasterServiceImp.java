/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.ContentMasterDao;
import dao.MenuChildDao;
import dao.MenuMappingDao;
import dao.MenuMasterDao;
import dao.UserMasterDao;
import java.util.List;

/**
 *
 * @author OLAWALE
 */
public class ContentMasterServiceImp implements ContentMasterService{
    private ContentMasterDao contentMasterDao;

    //New Update Start
    @Override
    public List<Object[]> readAll() {
        return contentMasterDao.readAll();
    }
    
    @Override
    public List<Object[]> readById(int userId) {
        return contentMasterDao.readById(userId);
    }
    
     @Override
    public List<Object[]> readByIdByType(int userId, int contentTypeId) {
        return contentMasterDao.readByIdByType(userId, contentTypeId);
    }
    @Override
    public List<Object[]> readByType(int contentTypeId) {
        return contentMasterDao.readByType(contentTypeId);
    }
    @Override
    public List<Object[]> readByTypeStatus(int contentTypeId, int contentMasterStatus) {
        return contentMasterDao.readByTypeStatus(contentTypeId, contentMasterStatus );
    }
    
     @Override
    public List<Object[]> readByStatus2(int contentMasterStatus) {
        return contentMasterDao.readByStatus2(contentMasterStatus );
    }
    @Override
    public List<Object[]> readByIdByTypeStatus(int userId, int contentTypeId, int contentMasterStatus) {
        return contentMasterDao.readByIdByTypeStatus(userId, contentTypeId, contentMasterStatus );
    }
    @Override
    public List<Object[]> readByIdByStatus2(int userId, int contentMasterStatus) {
        return contentMasterDao.readByIdByStatus2(userId, contentMasterStatus );
    }
    
    @Override
    public String update(int contentMasterId, String title, String desc, String smallRelativePath, String middleRelativePath, String largeRelativePath, String storeType) {
        return contentMasterDao.update(contentMasterId, title, desc, smallRelativePath, middleRelativePath, largeRelativePath, storeType);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //New Update Ends
    public void setContentMasterDao(ContentMasterDao contentMasterDao) {
        this.contentMasterDao = contentMasterDao;
    }

    @Override
    public String updateStatus(int contentMasterId, int status) {
        return contentMasterDao.updateStatus(contentMasterId, status);
    }

//    @Override
//    public String create(int userId, String title, String desc, String type, String smallDirectory, String middleDirectory, String largeDirectory, String storeType, int contentCategoryMasterId) {
//        return contentMasterDao.create(userId, title, desc, type, smallDirectory, middleDirectory, largeDirectory, storeType, contentCategoryMasterId);
//    }
    
    @Override
    public String create(int userId, String title, String desc, int type, String smallDirectory, String middleDirectory, String largeDirectory, String storeType, int contentCategoryMasterId, int contentSubCategoryMasterId) {
        return contentMasterDao.create(userId, title, desc, type, smallDirectory, middleDirectory, largeDirectory, storeType, contentCategoryMasterId, contentSubCategoryMasterId);
    }

    @Override
    public Object[] readContentMaster(int contentMasterId) {
        return contentMasterDao.readContentMaster(contentMasterId);
    }

//    @Override
//    public String update(int contentMasterId, String title, String desc, String type, String smallRelativePath, String middleRelativePath, String largeRelativePath, String storeType, int contentCategoryMasterId) {
//        return contentMasterDao.update(contentMasterId, title, desc, type, smallRelativePath, middleRelativePath, largeRelativePath, storeType, contentCategoryMasterId);
//    }

    

    

    @Override
    public String updateStatusByReviewer(int contentMasterId, int option, String comment) {
        return contentMasterDao.updateStatusByReviewer(contentMasterId, option, comment);
    }

    @Override
    public List<Object[]> readByTitle(String title) {
        return contentMasterDao.readByTitle(title);
    }
    
    @Override
    public List<Object[]> readByStatus(String status) {
        return contentMasterDao.readByStatus(status);
    }

    

    @Override
    public List<Object[]> readByDescription(String desc) {
        return contentMasterDao.readByDescription(desc);
    }

    @Override
    public Object[] readByOthers(int userId, String title, String desc, int type, String smallDirectory, String middleDirectory, String largeDirectory, String storeType, int contentCategoryMasterId) {
        return contentMasterDao.readByOthers(userId, title, desc, type, smallDirectory, middleDirectory, largeDirectory, storeType, contentCategoryMasterId);
    }

    @Override
    public List<Object[]> readAllModified() {
        return contentMasterDao.readAllModified();
    }

    @Override
    public List<Object[]> readByIdModified(int userId) {
        return contentMasterDao.readByIdModified(userId);
    }

    @Override
    public List<Object[]> readAllForPortalMapping() {
        return contentMasterDao.readAllForPortalMapping();
    }

    @Override
    public List<Object[]> readAllForPortalMappingByUserTypeCategory(int userMaster, int contentTypeMaster, int contentCategoryMaster) {
        return contentMasterDao.readAllForPortalMappingByUserTypeCategory(userMaster, contentTypeMaster, contentCategoryMaster);
    }

    @Override
    public List<Object[]> readAllForPortalMappingByUserType(int userMaster, int contentTypeMaster) {
        return contentMasterDao.readAllForPortalMappingByUserType(userMaster, contentTypeMaster);
    }

    @Override
    public List<Object[]> readAllForPortalMappingByUserCategory(int userMaster, int contentCategoryMaster) {
        return contentMasterDao.readAllForPortalMappingByUserCategory(userMaster, contentCategoryMaster);
    }

    @Override
    public List<Object[]> readAllForPortalMappingByUser(int userMaster) {
        return contentMasterDao.readAllForPortalMappingByUser(userMaster);
    }

    @Override
    public List<Object[]> readAllForPortalMappingByTypeCategory(int contentTypeMaster, int contentCategoryMaster) {
        return contentMasterDao.readAllForPortalMappingByTypeCategory(contentTypeMaster, contentCategoryMaster);
    }

    @Override
    public List<Object[]> readAllForPortalMappingByType(int contentTypeMaster) {
        return contentMasterDao.readAllForPortalMappingByType(contentTypeMaster);
    }

    @Override
    public List<Object[]> readAllForPortalMappingByCategory(int contentCategoryMaster) {
        return contentMasterDao.readAllForPortalMappingByCategory(contentCategoryMaster);
    }

    

    

    

    

   

    

    

    

    

    

}
