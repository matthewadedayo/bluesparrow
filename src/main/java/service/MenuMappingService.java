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
public interface MenuMappingService {
    
//    public String readUserMenuMapping(String username, String password);

    public List<Object[]> readAllMenuMapping();

//    public List<Object[]> readUserMenuMapping(Object object);

    public List<Object[]> readAll();

    public String updateStatus(int menuMappingId, int status);

    public List<Object[]> readUserMenuMapping(int parseInt, int parseInt0);

    public String addPriviledge(int parseInt, int parseInt0, int parseInt1);

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
