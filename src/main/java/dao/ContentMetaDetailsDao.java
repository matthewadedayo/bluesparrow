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
public interface ContentMetaDetailsDao {

    //Update Begins...
    
    public String updateStatus(int contentMetaDetailsId, int status);
    public List<Object[]> readByContentMaster(int contentMasterId);

    
    
    //Update Ends...
    
    public List<Object[]> readAll();
    public String create(int contentMasterId, String title, String value);

    

    public String update(int contentMetaDetailId, String contentMetaMasterTitle, String contentMstDetailValue);

    public Object[] readByContentMetaDetail(int contentMetaDetailId, int contentMasterId);

    
    
}
