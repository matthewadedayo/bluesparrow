/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.MenuChildDao;
import dao.MenuMappingDao;
import dao.MenuMasterDao;
import dao.UserMasterDao;
import java.util.List;

/**
 *
 * @author OLAWALE
 */
public class MenuChildServiceImp implements MenuChildService{
    private MenuChildDao menuChildDao;

    
    @Override
    public List<Object[]> readAll() {
        return menuChildDao.readAll();
    }

    public void setMenuChildDao(MenuChildDao menuChildDao) {
        this.menuChildDao = menuChildDao;
    }

    @Override
    public List<Object[]> readUserMenuChild(int userMasterId, int menuMasterId) {
        return menuChildDao.readUserMenuChild(userMasterId, menuMasterId);
    }

    @Override
    public String create(int menuId, String title, String url) {
        return menuChildDao.create(menuId, title, url);
    }

    @Override
    public String updateStatus(int menuChildId, int status) {
        return menuChildDao.updateStatus(menuChildId, status);
    }

    @Override
    public List<Object[]> readMenuChildMenus(int menuId) {
        return menuChildDao.readMenuChildMenus(menuId);
    }

    @Override
    public Object[] readMenuChild(int menuChildId) {
        return menuChildDao.readMenuChild(menuChildId);
    }

    @Override
    public String update(int menuChildId, String title, String url) {
        return menuChildDao.update(menuChildId, title, url);
    }

    @Override
    public List<Object[]> readByMenu(int menuId) {
        return menuChildDao.readByMenu(menuId);
    }

    @Override
    public List<Object[]> readByStatus(int status) {
        return menuChildDao.readByStatus(status);
    }

    @Override
    public List<Object[]> readByMenuStatus(int menuId, int menuChildMasterStatus) {
        return menuChildDao.readByMenuStatus(menuId, menuChildMasterStatus);
    }

    

    

    

    

}
