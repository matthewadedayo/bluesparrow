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
public interface MenuMasterService {
    
    public String readUserMenuMaster(String username, String password);

    public List<Object[]> readAll();

    public List<Object[]> readUserMenuMaster(int userMasterId);

    public String create(String title, String url);

    public String updateStatus(int parseInt, int parseInt0);

    public Object[] readMenu(int menuMasterId);

    public String update(int menuMasterId, String title, String url);

    public List<Object[]> readByStatus(int menuMasterStatus);

    
}
