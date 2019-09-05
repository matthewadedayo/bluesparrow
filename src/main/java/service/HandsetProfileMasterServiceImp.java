/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.ContentCategoryMasterDao;
import dao.ContentMasterDao;
import dao.HandsetProfileMasterDao;
import dao.MenuChildDao;
import dao.MenuMappingDao;
import dao.MenuMasterDao;
import dao.UserMasterDao;
import java.util.List;

/**
 *
 * @author OLAWALE
 */
public class HandsetProfileMasterServiceImp implements HandsetProfileMasterService{
    private HandsetProfileMasterDao handsetProfileMasterDao;

    
    @Override
    public List<Object[]> readAll() {
        return handsetProfileMasterDao.readAll();
    }

    public void setHandsetProfileMasterDao(HandsetProfileMasterDao handsetProfileMasterDao) {
        this.handsetProfileMasterDao = handsetProfileMasterDao;
    }

    

    @Override
    public String updateStatus(int contentCategoryMasterId, int status) {
        return handsetProfileMasterDao.updateStatus(contentCategoryMasterId, status);
    }

    @Override
    public String create(String userAgent, String make, String handsetModel, String os, String osVersion, String widthHeight) {
        return handsetProfileMasterDao.create(userAgent, make, handsetModel, os, osVersion, widthHeight);
    }

    @Override
    public Object[] readHandsetProfileMaster(int handsetProfileManagerId) {
        return handsetProfileMasterDao.readHandsetProfileMaster(handsetProfileManagerId);
    }

    @Override
    public String update(int handsetProfileMasterId, String userAgent, String make, String handsetModel, String os, String osVersion, String widthHeight) {
        return handsetProfileMasterDao.update(handsetProfileMasterId, userAgent, make, handsetModel, os, osVersion, widthHeight);
    }

    

    

    

    

    

    

    

}
