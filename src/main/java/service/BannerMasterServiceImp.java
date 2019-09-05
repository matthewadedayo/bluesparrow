/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.BannerMasterDao;
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
public class BannerMasterServiceImp implements BannerMasterService{
    private BannerMasterDao bannerMasterDao;

    //Update Gegins...
    public void setBannerMasterDao(BannerMasterDao bannerMasterDao) {
        this.bannerMasterDao = bannerMasterDao;
    }

    
    
    @Override
    public String create(int contentTypeMasterId, String title, String bannerImageDir, String size, String url, String displayTitle, String displayDescription, double displayAmount, double displayDiscountPercentage, String startDate, String endDate) {
        return bannerMasterDao.create(contentTypeMasterId, title, bannerImageDir, size, url, displayTitle, displayDescription, displayAmount, displayDiscountPercentage, startDate, endDate);
    }
    
    
    //Update Ends...
    @Override
    public List<Object[]> readAll() {
        return bannerMasterDao.readAll();
    }

    

    @Override
    public String update(int bannerId, int contentTypeMasterId, String title, String bannerImageDir, String size, String url, String displayTitle, String displayDescription, double displayAmount, double displayDiscountPercentage, String startDate, String endDate) {
        return bannerMasterDao.update(bannerId, contentTypeMasterId, title, bannerImageDir, size, url, displayTitle, displayDescription, displayAmount, displayDiscountPercentage, startDate, endDate);
    }

    @Override
    public List<Object[]> readByTitle(String title) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Object[]> readByStatus(int status) {
        return bannerMasterDao.readByStatus(status);
    }

    @Override
    public String updateStatus(int bannerId, int status) {
        return bannerMasterDao.updateStatus(bannerId, status);
    }

    @Override
    public Object[] readBanner(int id) {
        return bannerMasterDao.readBanner(id);
    }

    @Override
    public List<Object[]> readByType(int contentTypeId) {
        return bannerMasterDao.readByType(contentTypeId);
    }

    @Override
    public List<Object[]> readByTypeStatus(int contentTypeId, int status) {
        return bannerMasterDao.readByTypeStatus(contentTypeId, status);
    }

    
    
    
    

    

    

    

    

    

}
