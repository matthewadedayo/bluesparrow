/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.ContentCategoryMasterDao;
import dao.ContentMasterDao;
import dao.ContentSubCategoryMasterDao;
import dao.MenuChildDao;
import dao.MenuMappingDao;
import dao.MenuMasterDao;
import dao.UserMasterDao;
//import entity.ContentCategoryMaster;
import java.util.List;

/**
 *
 * @author OLAWALE
 */
public class ContentSubCategoryMasterServiceImp implements ContentSubCategoryMasterService{
    private ContentSubCategoryMasterDao contentSubCategoryMasterDao;

    @Override
    public String create(String title, String localTitle, int contentCategoryId) {
        return contentSubCategoryMasterDao.create(title, localTitle, contentCategoryId);
    }
    
    @Override
    public List<Object[]> readAll() {
        return contentSubCategoryMasterDao.readAll();
    }

    public void setContentSubCategoryMasterDao(ContentSubCategoryMasterDao contentSubCategoryMasterDao) {
        this.contentSubCategoryMasterDao = contentSubCategoryMasterDao;
    }

    

    @Override
    public String updateStatus(int subCategoryMasterId, int status) {
        return contentSubCategoryMasterDao.updateStatus(subCategoryMasterId, status);
    }

    
    
    
    
    @Override
    public Object[] readContentSubCategoryMaster(int contentSubCategoryMasterId) {
        return contentSubCategoryMasterDao.readContentSubCategoryMaster(contentSubCategoryMasterId);
    }
    
    @Override
    public Object[] readByContentSubCategoryMaster(int contentSubCategoryMasterId) {
        return contentSubCategoryMasterDao.readByContentSubCategoryMaster(contentSubCategoryMasterId);
    }
    
    
    
    
    
    

    @Override
    public String update(int contentSubCategoryMasterId, String title, String localTitle) {
        return contentSubCategoryMasterDao.update(contentSubCategoryMasterId, title, localTitle);
    }

    @Override
    public List<Object[]> readByStatus() {
        return contentSubCategoryMasterDao.readByStatus();
    }


    
    @Override
    public List<Object[]> readByContentCat(int contentCatId) {
        return contentSubCategoryMasterDao.readByContentCat(contentCatId);
    }
    
    @Override
    public List<Object[]> readByContentCat2(int contentCatId) {
        return contentSubCategoryMasterDao.readByContentCat2(contentCatId);
    }
    
    @Override
    public List<Object[]> readByContentCatAndStatus(int contentCatId) {
        return contentSubCategoryMasterDao.readByContentCatAndStatus(contentCatId);
    }

    @Override
    public List<Object[]> readByContentType(int contentTypeId) {
        return contentSubCategoryMasterDao.readByContentType(contentTypeId);
    }

    @Override
    public List<Object[]> readByTypeAndContentCategoryMaster(int contentTypeId, int contentCategoryMasterId) {
        return contentSubCategoryMasterDao.readByTypeAndContentCategoryMaster(contentTypeId, contentCategoryMasterId);
    }

    

    

    

    

    

    

    

    

}
