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
import dao.SubscriptionFeatureMappingDao;
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
public class SubscriptionFeatureMappingServiceImp implements SubscriptionFeatureMappingService{
    private SubscriptionFeatureMappingDao subscriptionFeatureMappingDao;

    
    
    @Override
    public List<Object[]> readAll() {
        return subscriptionFeatureMappingDao.readAll();
    }

    public void setSubscriptionFeatureMappingDao(SubscriptionFeatureMappingDao subscriptionFeatureMappingDao) {
        this.subscriptionFeatureMappingDao = subscriptionFeatureMappingDao;
    }

    

    

    

    

    @Override
    public List<Object[]> readAllByUserMaster(int userMasterId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

    @Override
    public Object[] readByContent(String fQuestion, String fAnswer, String createDate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object[] read(int id) {
        return subscriptionFeatureMappingDao.read(id);
    }

    @Override
    public String update(int faqsMasterId, String question, String answer, String contVisibility, String createDate, int status) {
        return subscriptionFeatureMappingDao.update(faqsMasterId, question, answer, contVisibility, createDate, status);
    }

    @Override
    public List<Object[]> readAllByPortalId(int portalId) {
        return subscriptionFeatureMappingDao.readAllByPortalId(portalId);
    }

    @Override
    public String create(int subscriptionId, int featureId) {
        return subscriptionFeatureMappingDao.create(subscriptionId, featureId);
    }

    @Override
    public List<Object[]> readAllBySubscriptionId(int subscriptionId) {
        return subscriptionFeatureMappingDao.readAllBySubscriptionId(subscriptionId);
    }

    @Override
    public String removeAllBySubscriptionId(int subscriptionId) {
        return subscriptionFeatureMappingDao.removeAllBySubscriptionId(subscriptionId);
    }

    


    

    

    

    

    

    

    

    

}
