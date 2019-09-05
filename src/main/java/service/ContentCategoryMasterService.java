/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

//import entity.ContentCategoryMaster;
import java.util.List;

/**
 *
 * @author OLAWALE
 */
public interface ContentCategoryMasterService {
    
    
    public List<Object[]> readAll();
    
    public String create(String title, String localTitle, int contentTypeId);

    public String updateStatus(int contentCategoryMasterId, int status);

    public Object[] readContentCategoryMaster(int contentCategoryMasterId);

    public String update(int contentCategoryMasterId, String title, String localTitle);

    public List<Object[]> readByStatus();

    public List<Object[]> readByContentType(int contentTypeId);

    public List<Object[]> readAllFromContentMaster();

    
}
