/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.ContentCategoryMappingDao;
import dao.ContentCategoryMasterDao;
import dao.ContentMasterDao;
import dao.HistoryDao;
import dao.MenuChildDao;
import dao.MenuHistoryDao;
import dao.MenuMappingDao;
import dao.MenuMasterDao;
import dao.UserMasterDao;
import java.util.List;

/**
 *
 * @author OLAWALE
 */
public class MenuHistoryServiceImp implements MenuHistoryService{
    private MenuHistoryDao menuHistoryDao;

    
    
    @Override
    public List<Object[]> readAll() {
        return menuHistoryDao.readAll();
    }

    public void setMenuHistoryDao(MenuHistoryDao menuHistoryDao) {
        this.menuHistoryDao = menuHistoryDao;
    }

    

    @Override
    public String create(int userId, int menuMasterId, int MenuChildId) {
        return menuHistoryDao.create(userId, menuMasterId, MenuChildId);
    }

    @Override
    public List<Object[]> readAllByUserMaster(int userMasterId) {
        return menuHistoryDao.readAllByUserMaster(userMasterId);
    }

    


    

    

    

    

    

    

    

    

}
