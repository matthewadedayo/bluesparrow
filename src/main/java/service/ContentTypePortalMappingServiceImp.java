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

/**
 *
 * @author OLAWALE
 */
public class ContentTypePortalMappingServiceImp implements ContentTypePortalMappingService{
    private ContentTypePortalMappingDao contentTypePortalMappingDao;

    
    
    @Override
    public List<Object[]> readAll() {
        return contentTypePortalMappingDao.readAll();
    }

    public void setContentTypePortalMappingDao(ContentTypePortalMappingDao contentTypePortalMappingDao) {
        this.contentTypePortalMappingDao = contentTypePortalMappingDao;
    }

    

    @Override
    public String updateStatus(int contentPortalTypeMappingId, int status) {
        return contentTypePortalMappingDao.updateStatus(contentPortalTypeMappingId, status);
    }

    @Override
    public String create(int contentPortalMaster, int contentTypeMaster) {
        return contentTypePortalMappingDao.create(contentPortalMaster, contentTypeMaster);
    }

    //new cms
    @Override
    public List<Object[]> readAllWithType() {
        return contentTypePortalMappingDao.readAllWithType();
    }

    //new cms
//    @Override
//    public List<Object[]> readAllWithTypeByContentPortal(int contentPortalMaster) {
//        return contentTypePortalMappingDao.readAllWithTypeByContentPortal(contentPortalMaster);
//    }

    @Override
    public String createUpdate(int typeId, int contentPortalId, int typePortalMapStatus) {
        return contentTypePortalMappingDao.createUpdate(typeId, contentPortalId, typePortalMapStatus);
    }

    @Override
    public Object[] readByTypePortal(int contentTypeMasterId, int contentPortalMasterId) {
        return contentTypePortalMappingDao.readByTypePortal(contentTypeMasterId, contentPortalMasterId);
    }

    @Override
    public Object[] readByTypePortalStatus(int contentTypeMasterId, int contentPortalMasterId, int contentTypePortalMappingStatus) {
        return contentTypePortalMappingDao.readByTypePortalStatus(contentTypeMasterId, contentPortalMasterId, contentTypePortalMappingStatus);
    }

    

    

    

    

    

    

    

    

    

}
