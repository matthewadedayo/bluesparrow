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
import dao.FeatureMasterDao;
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
public class FeatureMasterServiceImp implements FeatureMasterService{
    private FeatureMasterDao featureMasterDao;

    
    
    @Override
    public List<Object[]> readAll() {
        return featureMasterDao.readAll();
    }

    public void setFeatureMasterDao(FeatureMasterDao featureMasterDao) {
        this.featureMasterDao = featureMasterDao;
    }

    

    @Override
    public List<Object[]> readAllByUserMaster(int userMasterId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String create(int portalId, String featureText) {
        return featureMasterDao.create(portalId, featureText);
    }

    @Override
    public Object[] readByContent(String fQuestion, String fAnswer, String createDate) {
        return featureMasterDao.readByContent(fQuestion, fAnswer, createDate);
    }

    @Override
    public Object[] read(int id) {
        return featureMasterDao.read(id);
    }

    @Override
    public String update(int faqsMasterId, String question, String answer, String contVisibility, String createDate, int status) {
        return featureMasterDao.update(faqsMasterId, question, answer, contVisibility, createDate, status);
    }

    @Override
    public List<Object[]> readAllByPortalId(int portalId) {
        return featureMasterDao.readAllByPortalId(portalId);
    }

    


    

    

    

    

    

    

    

    

}
