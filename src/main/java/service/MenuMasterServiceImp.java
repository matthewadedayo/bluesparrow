/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.MenuMasterDao;
import dao.UserMasterDao;
import java.util.List;

/**
 *
 * @author OLAWALE
 */
public class MenuMasterServiceImp implements MenuMasterService{
    private MenuMasterDao menuMasterDao;

    @Override
    public String readUserMenuMaster(String username, String password) {
        return null;
        
    }

    public void setMenuMasterDao(MenuMasterDao menuMasterDao) {
        this.menuMasterDao = menuMasterDao;
    }

    

    @Override
    public List<Object[]> readAll() {
        return menuMasterDao.readAll();
    }

    @Override
    public List<Object[]> readUserMenuMaster(int userMasterId) {
        return menuMasterDao.readUserMenuMaster(userMasterId);
    }

    @Override
    public String create(String title, String url) {
        return menuMasterDao.create(title, url);
    }

    @Override
    public String updateStatus(int parseInt, int status) {
        return menuMasterDao.updateStatus(parseInt, status);
    }

    @Override
    public Object[] readMenu(int menuMasterId) {
        return menuMasterDao.readMenu(menuMasterId);
    }

    @Override
    public String update(int menuMasterId, String title, String url) {
        return menuMasterDao.update(menuMasterId, title, url);
    }

    @Override
    public List<Object[]> readByStatus(int menuMasterStatus) {
        return menuMasterDao.readByStatus(menuMasterStatus);
    }

    

    

}
