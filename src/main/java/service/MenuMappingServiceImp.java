/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.MenuMappingDao;
import dao.MenuMasterDao;
import dao.UserMasterDao;
import java.util.List;

/**
 *
 * @author OLAWALE
 */
public class MenuMappingServiceImp implements MenuMappingService{
    private MenuMappingDao menuMappingDao;

//    @Override
//    public String readUserMenuMapping(String username, String password) {
//        return null;
////        return menuMappingDao.readUserMenuMapping;
//        
//    }

    public void setMenuMappingDao(MenuMappingDao menuMappingDao) {
        this.menuMappingDao = menuMappingDao;
    }

    @Override
    public List<Object[]> readAllMenuMapping() {
        return menuMappingDao.readAllMenuMapping();
    }

//    @Override
//    public List<Object[]> readUserMenuMapping(Object object) {
//        System.out.println("pk_s: " + object);
//        return menuMappingDao.readUserMenuMapping(object);
//    }

    @Override
    public List<Object[]> readAll() {
        return menuMappingDao.readAll();
    }

    @Override
    public String updateStatus(int menuMappingId, int status) {
        return menuMappingDao.updateStatus(menuMappingId, status);
    }

    @Override
    public List<Object[]> readUserMenuMapping(int userId, int menuId) {
        return menuMappingDao.readUserMenuMapping(userId, menuId);
    }

    @Override
    public String addPriviledge(int userId, int menuId, int menuItem) {
        return menuMappingDao.addPriviledge(userId, menuId, menuItem);
    }

    @Override
    public Object[] readByRoleMenu(int menuMasterId, String roleType) {
        return menuMappingDao.readByRoleMenu(menuMasterId, roleType);
    }

    @Override
    public String addPriviledge(int menuId, String roleType, int menuMappingStatus) {
        return menuMappingDao.addPriviledge(menuId, roleType, menuMappingStatus);
    }

    @Override
    public List<String> readByRole(String roleType) {
        return menuMappingDao.readByRole(roleType);
    }

    @Override
    public Object[] readByRoleMenuMenuChild(int menuChildId, String roleType, int menuId) {
        return menuMappingDao.readByRoleMenuMenuChild(menuChildId, roleType, menuId);
    }

    @Override
    public String addPriviledgeChild(int menuId, int menuChildId, String roleType, int menuChildMappingStatus) {
        return menuMappingDao.addPriviledgeChild(menuId, menuChildId, roleType, menuChildMappingStatus);
    }

    @Override
    public List<Object[]> readMenuChildByRole(String roleType, int menuMasterId) {
        return menuMappingDao.readMenuChildByRole(roleType, menuMasterId);
    }

    @Override
    public Object[] readByRoleMenuChild(int menuChildId, String roleType) {
        return menuMappingDao.readByRoleMenuChild(menuChildId, roleType);
    }

    @Override
    public List<Object[]> readMenuByRole(String roleType) {
        return menuMappingDao.readMenuByRole(roleType);
    }

    @Override
    public List<Object[]> readAllMenuAccess() {
        return menuMappingDao.readAllMenuAccess();
    }

    @Override
    public List<Object[]> readAllMenuChildAccess(int menuMasterId) {
        return menuMappingDao.readAllMenuChildAccess(menuMasterId);
    }

    

    

    

    

}
