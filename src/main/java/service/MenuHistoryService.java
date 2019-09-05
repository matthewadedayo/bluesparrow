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
public interface MenuHistoryService {
    
    
    public List<Object[]> readAll();
    
    public String create(int userId, int menuMasterId, int MenuChildId);

    public List<Object[]> readAllByUserMaster(int userMasterId);


    
}
