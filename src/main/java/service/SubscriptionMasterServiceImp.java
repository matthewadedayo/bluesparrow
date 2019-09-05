/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.ContentCategoryMappingDao;
import dao.ContentCategoryMasterDao;
import dao.ContentMasterDao;
import dao.FaqsMasterDao;
import dao.SubscriptionMasterDao;
import dao.HistoryDao;
import dao.MenuChildDao;
import dao.MenuMappingDao;
import dao.MenuMasterDao;
import dao.UserMasterDao;
import java.util.List;

/**
 *
 * @author OLAWALE
 */
public class SubscriptionMasterServiceImp implements SubscriptionMasterService{
    private SubscriptionMasterDao subscriptionMasterDao;

    
    
    @Override
    public List<Object[]> readAll() {
        return subscriptionMasterDao.readAll();
    }

    public void setSubscriptionMasterDao(SubscriptionMasterDao subscriptionMasterDao) {
        this.subscriptionMasterDao = subscriptionMasterDao;
    }

    

    

    

    @Override
    public List<Object[]> readAllByUserMaster(int userMasterId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

    @Override
    public Object[] readByContent(String fQuestion, String fAnswer, String createDate) {
        return subscriptionMasterDao.readByContent(fQuestion, fAnswer, createDate);
    }

    @Override
    public Object[] read(int id) {
        return subscriptionMasterDao.read(id);
    }

    

    @Override
    public List<Object[]> readAllByPortalId(int portalId) {
        return subscriptionMasterDao.readAllByPortalId(portalId);
    }

    @Override
    public String create(int portalId, String subName, String subPrice, String subPackage, String subCurrency) {
        return subscriptionMasterDao.create(portalId, subName, subPrice, subPackage, subCurrency);
    }

    @Override
    public Object[] readByAll(int portalId, String subName, String subPrice, String subPackage) {
        return subscriptionMasterDao.readByAll(portalId, subName, subPrice, subPackage);
    }

    @Override
    public String update(int subId, int portalId, String subName, String subPrice, String subPackage, String subCurrency) {
        return subscriptionMasterDao.update(subId, portalId, subName, subPrice, subPackage, subCurrency);
    }

    


    

    

    

    

    

    

    

    

}
