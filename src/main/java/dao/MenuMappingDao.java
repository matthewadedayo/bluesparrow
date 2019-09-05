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
public interface MenuMappingDao {

    public List<Object[]> readAllMenuMapping();
//    public String readUserMenuMapping;

//    public List<Object[]> readUserMenuMapping(Object object);

    public List<Object[]> readAll();
    
    public String create(int menuId, String roleType);

    public String updateStatus(int menuMappingId, int status);

    public List<Object[]> readUserMenuMapping(int userId, int menuId);

    public String addPriviledge(int userId, int menuId, int menuItem);

    public Object[] readByRoleMenu(int menuMasterId, String roleType);

    public String addPriviledge(int menuId, String roleType, int menuMappingStatus);

    public List<String> readByRole(String roleType);

    public Object[] readByRoleMenuMenuChild(int menuChildId, String roleType, int menuId);

    public String addPriviledgeChild(int menuId, int menuChildId, String roleType, int menuChildMappingStatus);

    public List<Object[]> readMenuChildByRole(String roleType, int menuMasterId);

    public Object[] readByRoleMenuChild(int menuChildId, String roleType);

    public List<Object[]> readMenuByRole(String roleType);

    public List<Object[]> readAllMenuAccess();

    public List<Object[]> readAllMenuChildAccess(int menuMasterId);
    


    
}
