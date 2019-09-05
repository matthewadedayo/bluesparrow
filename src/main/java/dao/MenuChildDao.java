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
public interface MenuChildDao {

    public List<Object[]> readAll();

    public List<Object[]> readUserMenuChild(int userMasterId, int menuMasterId);

    public String create(int menuId, String title, String url);

    public String updateStatus(int menuChildId, int status);

    public List<Object[]> readMenuChildMenus(int menuId);

    public Object[] readMenuChild(int menuChildId);

    public String update(int menuChildId, String title, String url);

    public List<Object[]> readByMenu(int menuId);

    public List<Object[]> readByStatus(int status);

    public List<Object[]> readByMenuStatus(int menuId, int menuChildMasterStatus);
    


    
}
