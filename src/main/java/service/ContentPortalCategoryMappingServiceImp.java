/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.ContentCategoryMappingDao;
import dao.ContentCategoryMasterDao;
import dao.ContentMasterDao;
import dao.ContentPortalCategoryMappingDao;
import dao.MenuChildDao;
import dao.MenuMappingDao;
import dao.MenuMasterDao;
import dao.UserMasterDao;
import java.util.List;

/**
 *
 * @author OLAWALE
 */
public class ContentPortalCategoryMappingServiceImp implements ContentPortalCategoryMappingService{
    private ContentPortalCategoryMappingDao contentPortalCategoryMappingDao;

    
    
    @Override
    public List<Object[]> readAll() {
        return contentPortalCategoryMappingDao.readAll();
    }

    public void setContentPortalCategoryMappingDao(ContentPortalCategoryMappingDao contentPortalCategoryMappingDao) {
        this.contentPortalCategoryMappingDao = contentPortalCategoryMappingDao;
    }

    @Override
    public String updateStatus(int contentPortalCategoryMappingId, int status) {
        return contentPortalCategoryMappingDao.updateStatus(contentPortalCategoryMappingId, status);
    }

    @Override
    public String create(int contentPortalMaster, int contentCategoryMaster) {
        return contentPortalCategoryMappingDao.create(contentPortalMaster, contentCategoryMaster);
    }

    @Override
    public Object[] readByCategoryPortal(int contentCategoryMasterId, int contentPortalMasterId) {
        return contentPortalCategoryMappingDao.readByCategoryPortal(contentCategoryMasterId, contentPortalMasterId);
    }

    @Override
    public String createUpdate(int categoryId, int contentPortalId, int catPortalMapStatus) {
        return contentPortalCategoryMappingDao.createUpdate(categoryId, contentPortalId, catPortalMapStatus);
    }

    

    

    

    

    

    

    

    

    

}
