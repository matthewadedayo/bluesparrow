/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.ContentMetaMasterDao;
import java.util.List;

/**
 *
 * @author OLAWALE
 */
public class ContentMetaMasterServiceImp implements ContentMetaMasterService{

    private ContentMetaMasterDao contentMetaMasterDao;

    public void setContentMetaMasterDao(ContentMetaMasterDao contentMetaMasterDao) {
        this.contentMetaMasterDao = contentMetaMasterDao;
    }
    
    
    
    @Override
    public List<Object[]> readAll() {
        return contentMetaMasterDao.readAll();
    }
    
    
}
