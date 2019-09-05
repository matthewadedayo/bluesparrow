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
import dao.ContentTypePortalMappingDao;
import dao.ContentPortalMappingDao;

/**
 *
 * @author OLAWALE
 */
public class ContentPortalMappingServiceImp implements ContentPortalMappingService{
    private ContentPortalMappingDao contentPortalMappingDao;

    
    
    @Override
    public List<Object[]> readAll() {
        return contentPortalMappingDao.readAll();
    }

    public void setContentPortalMappingDao(ContentPortalMappingDao contentPortalMappingDao) {
        this.contentPortalMappingDao = contentPortalMappingDao;
    }

    

    

    @Override
    public String updateStatus(int portalContentMappingId, int status) {
        return contentPortalMappingDao.updateStatus(portalContentMappingId, status);
    }

    @Override
    public String create(int contentPortalMasterId,  int contentMasterId) {
        return contentPortalMappingDao.create(contentPortalMasterId, contentMasterId);
    }

    @Override
    public Object[] readByContentPortal(int contentMasterId, int contentPortalId) {
        return contentPortalMappingDao.readByContentPortal(contentMasterId, contentPortalId);
    }

    @Override
    public String createUpdate(int categoryMasterId, int contentPortalId, int contentPortalMapStatus) {
        return contentPortalMappingDao.createUpdate(categoryMasterId, contentPortalId, contentPortalMapStatus);
    }

    

    

    

    

    

    

    

    

    

}
