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
public interface MenuMasterDao {
    

    public String login(String username, String password);

    public List<Object[]> readAll();

    public List<Object[]> readUserMenuMaster(int userMasterId);

    public String create(String title, String url);

    public String updateStatus(int menuId, int status);

    public Object[] readMenu(int menuMasterId);

    public String update(int menuMasterId, String title, String url);

    public List<Object[]> readByStatus(int menuMasterStatus);

    
}
