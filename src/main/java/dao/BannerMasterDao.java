/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

/**
 *
 * @author OLAWALE
 */
public interface BannerMasterDao {

   public List<Object[]> readAll();

    public String create(int contentTypeMasterId, String title, String bannerImageDir, String size, String url, String displayTitle, String displayDescription, double displayAmount, double displayDiscountPercentage, String startDate, String endDate);

    public String update(int bannerId, int contentTypeMasterId, String title, String bannerImageDir, String size, String url, String displayTitle, String displayDescription, double displayAmount, double displayDiscountPercentage, String startDate, String endDate);

    public List<Object[]> readByTitle(String title);

    public List<Object[]> readByStatus(int status);

    public String updateStatus(int bannerId, int status);

    public Object[] readBanner(int id);

    public List<Object[]> readByType(int contentTypeId);

    public List<Object[]> readByTypeStatus(int contentTypeId, int status);
    


    
}
