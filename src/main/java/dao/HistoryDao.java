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
public interface HistoryDao {

    public List<Object[]> readAll();

    public String create(String userId, String remoteAddr, String loginTime);

    


    
}
