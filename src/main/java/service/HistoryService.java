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
public interface HistoryService {
    
    
    public List<Object[]> readAll();
    
    public String create(String userId, String remoteAddr, String loginTime);

    public List<Object[]> readAllByUserMaster(int userMasterId);


    
}
