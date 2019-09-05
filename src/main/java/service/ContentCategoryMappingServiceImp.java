/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.ContentCategoryMappingDao;
import dao.ContentCategoryMasterDao;
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
public class ContentCategoryMappingServiceImp implements ContentCategoryMappingService{
    private ContentCategoryMappingDao contentCategoryMappingDao;

    @Override
    public String create(int contentMaster, int contentCategoryMaster) {
        return contentCategoryMappingDao.create(contentMaster, contentCategoryMaster);
    }
    
    @Override
    public List<Object[]> readAll() {
        return contentCategoryMappingDao.readAll();
    }

    public void setContentCategoryMappingDao(ContentCategoryMappingDao contentCategoryMappingDao) {
        this.contentCategoryMappingDao = contentCategoryMappingDao;
    }

    @Override
    public String updateStatus(int contentCategoryMappingId, int status) {
        return contentCategoryMappingDao.updateStatus(contentCategoryMappingId, status);
    }

    @Override
    public List<Object[]> readByUserId(int userId) {
        return contentCategoryMappingDao.readByUserId(userId);
    }

    

    

    

    

    

    

    

    

}
