/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.ContentCategoryMappingDao;
import dao.ContentCategoryMasterDao;
import dao.ContentMasterDao;
import dao.FaqPortalMappingDao;
import dao.FaqsMasterDao;
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
public class FaqPortalMappingServiceImp implements FaqPortalMappingService{
    private FaqPortalMappingDao faqPortalMappingDao;

    
    
    @Override
    public List<Object[]> readAll() {
        return faqPortalMappingDao.readAll();
    }

    public void setFaqPortalMappingDao(FaqPortalMappingDao faqPortalMappingDao) {
        this.faqPortalMappingDao = faqPortalMappingDao;
    }

   

    @Override
    public List<Object[]> readAllByUserMaster(int userMasterId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  

    @Override
    public String create(int portalId, int faqsId) {
        return faqPortalMappingDao.create(portalId, faqsId);
    }

    @Override
    public List<Object[]> readByFaqsMasterId(int faqsMasterId) {
        return faqPortalMappingDao.readByFaqsMasterId(faqsMasterId);
    }

    @Override
    public List<Object> readPortalIdByFaqsMasterId(int faqsMasterId) {
        return faqPortalMappingDao.readPortalIdByFaqsMasterId(faqsMasterId);
    }

    @Override
    public String remove(int faqsMasterId) {
        return faqPortalMappingDao.remove(faqsMasterId);
    }

    


    

    

    

    

    

    

    

    

}
