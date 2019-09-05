/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.ContentCategoryMasterDao;
import dao.ContentMasterDao;
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
public class ContentCategoryMasterServiceImp implements ContentCategoryMasterService{
    private ContentCategoryMasterDao contentCategoryMasterDao;

    @Override
    public String create(String title, String localTitle, int contentTypeId) {
        return contentCategoryMasterDao.create(title, localTitle, contentTypeId);
    }
    
    @Override
    public List<Object[]> readAll() {
        return contentCategoryMasterDao.readAll();
    }

    public void setContentCategoryMasterDao(ContentCategoryMasterDao contentCategoryMasterDao) {
        this.contentCategoryMasterDao = contentCategoryMasterDao;
    }

    @Override
    public String updateStatus(int contentCategoryMasterId, int status) {
        return contentCategoryMasterDao.updateStatus(contentCategoryMasterId, status);
    }

    @Override
    public Object[] readContentCategoryMaster(int contentCategoryMasterId) {
        return contentCategoryMasterDao.readContentCategoryMaster(contentCategoryMasterId);
    }

    @Override
    public String update(int contentCategoryMasterId, String title, String localTitle) {
        return contentCategoryMasterDao.update(contentCategoryMasterId, title, localTitle);
    }

    @Override
    public List<Object[]> readByStatus() {
        return contentCategoryMasterDao.readByStatus();
    }

    @Override
    public List<Object[]> readByContentType(int contentTypeId) {
        return contentCategoryMasterDao.readByContentType(contentTypeId);
    }

    @Override
    public List<Object[]> readAllFromContentMaster() {
        return contentCategoryMasterDao.readAllFromContentMaster();
    }

    

    

    

    

    

    

    

}
