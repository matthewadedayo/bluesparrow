/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.ContentCategoryMasterDao;
import dao.ContentMasterDao;
import dao.ContentPortalMasterDao;
import dao.MenuChildDao;
import dao.MenuMappingDao;
import dao.MenuMasterDao;
import dao.UserMasterDao;
import java.util.List;

/**
 *
 * @author OLAWALE
 */
public class ContentPortalMasterServiceImp implements ContentPortalMasterService{
    private ContentPortalMasterDao contentPortalMasterDao;

    @Override
    public String create(String title, String desc) {
        return contentPortalMasterDao.create(title, desc);
    }
    
    @Override
    public List<Object[]> readAll() {
        return contentPortalMasterDao.readAll();
    }

    public void setContentPortalMasterDao(ContentPortalMasterDao contentPortalMasterDao) {
        this.contentPortalMasterDao = contentPortalMasterDao;
    }

    @Override
    public String updateStatus(int contentPortalMasterId, int status) {
        return contentPortalMasterDao.updateStatus(contentPortalMasterId, status);
    }

    @Override
    public Object[] readContentPortalMaster(int contentPortalMasterId) {
        return contentPortalMasterDao.readContentPortalMaster(contentPortalMasterId);
    }

    @Override
    public String update(int contentPortalMasterId, String title, String desc) {
        return contentPortalMasterDao.update(contentPortalMasterId, title, desc);
    }

    @Override
    public List<Object[]> readByStatus(int contentPortalStatus) {
        return contentPortalMasterDao.readByStatus(contentPortalStatus);
    }

    

    

    

    

    

    

    

    

}
