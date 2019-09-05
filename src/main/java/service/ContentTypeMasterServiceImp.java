/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.ContentMasterDao;
import dao.ContentTypeMasterDao;
import dao.MenuChildDao;
import dao.MenuMappingDao;
import dao.MenuMasterDao;
import dao.UserMasterDao;
import java.util.List;

/**
 *
 * @author OLAWALE
 */
public class ContentTypeMasterServiceImp implements ContentTypeMasterService{
    private ContentTypeMasterDao contentTypeMasterDao;

    
    @Override
    public List<Object[]> readAll() {
        return contentTypeMasterDao.readAll();
    }
    
    @Override
    public Object[] readContentTypeMaster(int contentTypeMasterId) {
        return contentTypeMasterDao.readContentTypeMaster(contentTypeMasterId);
    }
    
    
    
    
    
    
    
    
    

    public void setContentTypeMasterDao(ContentTypeMasterDao contentTypeMasterDao) {
        this.contentTypeMasterDao = contentTypeMasterDao;
    }

//    @Override
//    public String updateStatus(int contentMasterId, int status) {
//        return contentMasterDao.updateStatus(contentMasterId, status);
//    }

    @Override
    public String create(int userId, String title, String desc, String type, String smallDirectory, String middleDirectory, String largeDirectory, String storeType, int contentCategoryMasterId) {
        return contentTypeMasterDao.create(userId, title, desc, type, smallDirectory, middleDirectory, largeDirectory, storeType, contentCategoryMasterId);
    }

    

    @Override
    public String update(int contentMasterId, String title, String desc, String type, String smallRelativePath, String middleRelativePath, String largeRelativePath, String storeType, int contentCategoryMasterId) {
        return contentTypeMasterDao.update(contentMasterId, title, desc, type, smallRelativePath, middleRelativePath, largeRelativePath, storeType, contentCategoryMasterId);
    }

    @Override
    public List<Object[]> readById(int userId) {
        return contentTypeMasterDao.readById(userId);
    }

    

//    @Override
//    public String updateStatusByReviewer(int contentMasterId, int option, String comment) {
//        return contentMasterDao.updateStatusByReviewer(contentMasterId, option, comment);
//    }

    @Override
    public List<Object[]> readByTitle(String title) {
        return contentTypeMasterDao.readByTitle(title);
    }
    
//    @Override
//    public List<Object[]> readByStatus(String status) {
//        return contentMasterDao.readByStatus(status);
//    }

//    @Override
//    public List<Object[]> readByType(String type) {
//        return contentMasterDao.readByType(type);
//    }

    @Override
    public List<Object[]> readByDescription(String desc) {
        return contentTypeMasterDao.readByDescription(desc);
    }

    @Override
    public List<Object[]> readByType(int contentTypeId) {
        return contentTypeMasterDao.readByType(contentTypeId);
    }

    @Override
    public List<Object[]> readByStatus(int contentTypeMasterStatus) {
        return contentTypeMasterDao.readByStatus(contentTypeMasterStatus);
    }

    @Override
    public List<Object[]> readByTypeStatus(int contentTypeId, int contentTypeMasterStatus) {
        return contentTypeMasterDao.readByTypeStatus(contentTypeId, contentTypeMasterStatus);
    }

    @Override
    public List<Object[]> readAllFromContentMaster() {
        return contentTypeMasterDao.readAllFromContentMaster();
    }

    

    

    

    

    

}
