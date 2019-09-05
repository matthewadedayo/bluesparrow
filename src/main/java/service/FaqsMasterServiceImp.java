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
public class FaqsMasterServiceImp implements FaqsMasterService{
    private FaqsMasterDao faqsMasterDao;

    
    
    @Override
    public List<Object[]> readAll() {
        return faqsMasterDao.readAll();
    }

    public void setFaqsMasterDao(FaqsMasterDao faqsMasterDao) {
        this.faqsMasterDao = faqsMasterDao;
    }

    @Override
    public List<Object[]> readAllByUserMaster(int userMasterId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String create(String question, String answer, String contVisibility, String createDate, int status) {
        return faqsMasterDao.create(question, answer, contVisibility, createDate, status);
    }

    @Override
    public Object[] readByContent(String fQuestion, String fAnswer, String createDate) {
        return faqsMasterDao.readByContent(fQuestion, fAnswer, createDate);
    }

    @Override
    public Object[] read(int id) {
        return faqsMasterDao.read(id);
    }

    @Override
    public String update(int faqsMasterId, String question, String answer, String contVisibility, String createDate, int status) {
        return faqsMasterDao.update(faqsMasterId, question, answer, contVisibility, createDate, status);
    }

    


    

    

    

    

    

    

    

    

}
