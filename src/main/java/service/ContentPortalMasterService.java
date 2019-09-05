/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;

/**
 *
 * @author OLAWALE
 */
public interface ContentPortalMasterService {
    
    
    public List<Object[]> readAll();
    
    public String create(String title, String desc);

    public String updateStatus(int contentPortalMasterId, int status);

    public Object[] readContentPortalMaster(int contentPortalMasterId);

    public String update(int contentPortalMasterId, String title, String desc);

    public List<Object[]> readByStatus(int contentPortalStatus);

    
}
