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
public interface ContentMetaDetailsService {
    
    //New Update
    
    public String updateStatus(int contentMetaDetailsId, int status);
    
    
    
    //New Update End
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public List<Object[]> readAll();

    public String create(int contentMasterId, String title, String value);

//    public String updateStatus(int contentFileId, int status);

//    public String update(int contentFileId, int contentMasterId, String fullPath, String widthHeight, String osName, String osVersion);

//    public List<Object[]> readByContentMasterId(int contentMasterId);

    public List<Object[]> readByContentMaster(int contentMasterId);

    public String update(int contentMetaDetailId, String contentMetaMasterTitle, String contentMstDetailValue);

    public Object[] readByContentMetaDetail(int contentMetaDetailId, int contentMasterId);

    
}
