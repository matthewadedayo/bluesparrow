/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.ContentMetaDetailsDao;
import java.util.List;

/**
 *
 * @author OLAWALE
 */
public class ContentMetaDetailsServiceImp implements ContentMetaDetailsService{

    private ContentMetaDetailsDao contentMetaDetailsDao;

    public void setContentMetaDetailsDao(ContentMetaDetailsDao contentMetaDetailsDao) {
        this.contentMetaDetailsDao = contentMetaDetailsDao;
    }
    
    @Override
    public String updateStatus(int contentMetaDetailsId, int status) {
       return contentMetaDetailsDao.updateStatus(contentMetaDetailsId, status);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Override
    public List<Object[]> readAll() {
        return contentMetaDetailsDao.readAll();
    }

    @Override
    public String create(int contentMasterId, String title, String value) {
        return contentMetaDetailsDao.create(contentMasterId, title, value);
    }

    @Override
    public List<Object[]> readByContentMaster(int contentMasterId) {
        return contentMetaDetailsDao.readByContentMaster(contentMasterId);
    }

    @Override
    public String update(int contentMetaDetailId, String contentMetaMasterTitle, String contentMstDetailValue) {
        return contentMetaDetailsDao.update(contentMetaDetailId, contentMetaMasterTitle, contentMstDetailValue);
    }

    @Override
    public Object[] readByContentMetaDetail(int contentMetaDetailId, int contentMasterId) {
        return contentMetaDetailsDao.readByContentMetaDetail(contentMetaDetailId, contentMasterId);
    }

    
    
    
}
