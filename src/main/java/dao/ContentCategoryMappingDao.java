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
public interface ContentCategoryMappingDao {

    public List<Object[]> readAll();

    public String create(int contentMaster, int contentCategoryMaster);

    public String updateStatus(int contentCategoryMappingId, int status);

    public List<Object[]> readByUserId(int userId);
    


    
}
