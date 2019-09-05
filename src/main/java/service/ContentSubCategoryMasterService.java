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
public interface ContentSubCategoryMasterService {
    
    
    public List<Object[]> readAll();
    
    public String create(String title, String localTitle, int contentCategoryId);

    public String updateStatus(int subCategoryMasterId, int status);

    
    
    
    //New
    public Object[] readContentSubCategoryMaster(int contentSubCategoryMasterId);
    public Object[] readByContentSubCategoryMaster(int contentSubCategoryMasterId);
    //New
    
    
    
    public String update(int contentSubCategoryMasterId, String title, String localTitle);

    public List<Object[]> readByStatus();

    public List<Object[]> readByContentCat(int contentCatId);
    public List<Object[]> readByContentCat2(int contentCatId);
    public List<Object[]> readByContentCatAndStatus(int contentCatId);

    public List<Object[]> readByContentType(int contentTypeId);

//    public List<Object[]> readByContentCategoryMaster(int parseInt);

    public List<Object[]> readByTypeAndContentCategoryMaster(int contentTypeId, int contentCategoryMasterId);

    
}
