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
public interface HandsetProfileMasterDao {

    public List<Object[]> readAll();


    public String updateStatus(int contentCategoryMasterId, int status);

    public String create(String userAgent, String make, String handsetModel, String os, String osVersion, String widthHeight);

    public Object[] readHandsetProfileMaster(int handsetProfileManagerId);

    public String update(int handsetProfileMasterId, String userAgent, String make, String handsetModel, String os, String osVersion, String widthHeight);
    


    
}
