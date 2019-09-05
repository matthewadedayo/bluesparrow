/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.ContentFileDao;
import dao.ContentMasterDao;
import dao.MenuChildDao;
import dao.MenuMappingDao;
import dao.MenuMasterDao;
import dao.UserMasterDao;
import java.util.List;

/**
 *
 * @author OLAWALE
 */
public class ContentFileServiceImp implements ContentFileService{
    private ContentFileDao contentFileDao;

    
    @Override
    public List<Object[]> readAll() {
        return contentFileDao.readAll();
    }

    public void setContentFileDao(ContentFileDao contentFileDao) {
        this.contentFileDao = contentFileDao;
    }

    @Override
    public String create(int contentMasterId, String fileName, String widthHeight, String osName, String osVersion) {
        return contentFileDao.create(contentMasterId, fileName, widthHeight, osName, osVersion);
    }

    @Override
    public String updateStatus(int contentFileId, int status) {
        return contentFileDao.updateStatus(contentFileId, status);
    }

    @Override
    public Object[] readContentFiles(int contentFileId) {
        return contentFileDao.readContentFiles(contentFileId);
    }

    @Override
    public String update(int contentFileId, int contentMasterId, String fullPath, String widthHeight, String osName, String osVersion) {
        return contentFileDao.update(contentFileId, contentMasterId, fullPath, widthHeight, osName, osVersion);
    }

    @Override
    public List<Object[]> readByContentMasterId(int contentMasterId) {
        return contentFileDao.readByContentMasterId(contentMasterId);
    }

    

    

    

    

    

    

}
