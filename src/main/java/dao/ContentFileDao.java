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
public interface ContentFileDao {

    public List<Object[]> readAll();

    public String create(int contentMasterId, String fileName, String widthHeight, String osName, String osVersion);

    public String updateStatus(int contentFileId, int status);

    public Object[] readContentFiles(int contentFileId);

    public String update(int contentFileId, int contentMasterId, String fullPath, String widthHeight, String osName, String osVersion);

    public List<Object[]> readByContentMasterId(int contentMasterId);
    


    
}
