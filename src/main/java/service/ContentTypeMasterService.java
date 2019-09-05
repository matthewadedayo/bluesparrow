/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author OLAWALE
 */
public interface ContentTypeMasterService {
    
    
    public List<Object[]> readAll();

//    public String updateStatus(int contentMasterId, int status);

    public String create(int userId, String title, String desc, String type, String smallDirectory, String middleDirectory, String largeDirectory, String storeType, int contentCategoryMasterId);

    public Object[] readContentTypeMaster(int contentMasterId);

    public String update(int contentMasterId, String title, String desc, String type, String smallRelativePath, String middleRelativePath, String largeRelativePath, String storeType, int contentCategoryMasterId);

    public List<Object[]> readById(int userId);

    public List<Object[]> readByTitle(String title);

//    public String updateStatusByReviewer(int contentMasterId, int option, String comment);

//    public List<Object[]> readByStatus(String status);

//    public List<Object[]> readByType(String type);

    public List<Object[]> readByDescription(String desc);

    public List<Object[]> readByType(int contentTypeId);

    public List<Object[]> readByStatus(int contentTypeMasterStatus);

    public List<Object[]> readByTypeStatus(int contentTypeId, int contentTypeMasterStatus);

    public List<Object[]> readAllFromContentMaster();

    
}
