/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author OLAWALE
 */
public class ContentMasterDaoImp implements ContentMasterDao{
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    //New Update Begins...

    @Override
    public List<Object[]> readAll() {
        Session session = sessionFactory.openSession();

//        Query query = session.createSQLQuery("SELECT cm_id, cm_title, cm_desc, cm_type, cm_user_ratings, cm_user_rated, cm_thumbnail_small, cm_thumbnail_mid, cm_thumbnail_large, cm_storage_type, cm_status, ccm_title, cm_remarks FROM content_master, content_category_master WHERE cm_ccm_id=ccm_id");
//        Query query = session.createSQLQuery("SELECT cm_id, ctm_title, ccm_title, cscm_title, cm_title, cm_desc, cm_user_ratings, cm_user_rated, cm_storage_type, cm_thumbnail_small, cm_thumbnail_mid, cm_thumbnail_large, cm_status, cm_remarks FROM content_master, content_category_master, content_type_master, content_sub_category_master WHERE cm_ctm_id=ctm_id AND  cm_ccm_id=ccm_id AND cm_cscm_id=cscm_id");
        Query query = session.createSQLQuery("SELECT cm_id, ctm_title, ccm_title, cscm_title, cm_title, cm_desc, cm_user_ratings, cm_user_rated, cm_storage_type, cm_thumbnail_small, cm_thumbnail_mid, cm_thumbnail_large, cm_status, cm_remarks, CONCAT(cm_created_on,'') cm_created_on FROM content_category_master, content_type_master, content_master LEFT OUTER JOIN content_sub_category_master ON content_master.cm_cscm_id=content_sub_category_master.cscm_id WHERE cm_ctm_id=ctm_id AND  cm_ccm_id=ccm_id");
        List<Object[]> hh = query.list();
        System.out.println("OK");
//        System.out.println(hh.get(0)[3]);
//        System.out.println("OK PASS");
//        System.out.println(hh.get(1)[3]);
        return hh;
    }
    
    @Override
    public List<Object[]> readAllForPortalMapping() {
        
        Session session = null;
        try
        {
            session = sessionFactory.openSession();

            Query query = session.createSQLQuery("SELECT cm_id, cm_title, um_user_name, ctm_title, ccm_title, cm_status FROM content_category_master, content_type_master, content_master, user_master WHERE cm_ctm_id=ctm_id AND  cm_ccm_id=ccm_id AND cm_um_id=um_id");
            List<Object[]> hh = query.list();

            return hh;
        }
        catch(Exception ex)
        {
            
        }
        finally
        {
            session.close();
        }
        return null;
    }

    @Override
    public List<Object[]> readById(int userId) {
        Session session = sessionFactory.openSession();
//        Query query = session.createSQLQuery("SELECT cm_id, ctm_title, ccm_title, cscm_title, cm_title, cf_file, cf_width_height, cf_os_name, cf_os_version, cm_status, ccm_local_title FROM content_master, content_category_master, content_sub_category_master, content_files, content_type_master WHERE cm_ccm_id=ccm_id AND cm_ctm_id=ctm_id AND cm_id=cf_cm_id AND cm_cscm_id=cscm_id AND cm_um_id=:cm_um_id");
        Query query = session.createSQLQuery("SELECT cm_id, ctm_title, ccm_title, cscm_title, cm_title, cm_desc, cm_user_ratings, cm_user_rated, cm_storage_type, cm_thumbnail_small, cm_thumbnail_mid, cm_thumbnail_large, cm_status, cm_remarks FROM content_master, content_category_master, content_type_master, content_sub_category_master WHERE cm_ctm_id=ctm_id AND  cm_ccm_id=ccm_id AND cm_cscm_id=cscm_id AND cm_um_id=:cm_um_id");
        query.setParameter("cm_um_id", userId);
        return query.list();
    }
    
    @Override
    public List<Object[]> readByType(int contentTypeId) {
        Session session = sessionFactory.openSession();
        
//        Query query = session.createSQLQuery("SELECT cm_id, cm_title, cm_desc, cm_type, cm_user_ratings, cm_user_rated, cm_thumbnail_small, cm_thumbnail_mid, cm_thumbnail_large, cm_storage_type, cm_status, ccm_title, cm_remarks FROM content_master, content_category_master WHERE cm_ccm_id=ccm_id AND cm_type=:cm_type");
        Query query = session.createSQLQuery("SELECT cm_id, ctm_title, ccm_title, cscm_title, cm_title, cm_desc, cm_user_ratings, cm_user_rated, cm_storage_type, cm_thumbnail_small, cm_thumbnail_mid, cm_thumbnail_large, cm_status, cm_remarks, CONCAT(cm_created_on,'') cm_created_on FROM content_category_master, content_type_master, content_master LEFT OUTER JOIN content_sub_category_master ON content_master.cm_cscm_id=content_sub_category_master.cscm_id WHERE cm_ctm_id=ctm_id AND  cm_ccm_id=ccm_id AND cm_ctm_id=:cm_ctm_id");
//        Query query = session.createSQLQuery("SELECT cm_id, ctm_title, ccm_title, cscm_title, cm_title, cm_desc, cm_user_ratings, cm_user_rated, cm_storage_type, cm_thumbnail_small, cm_thumbnail_mid, cm_thumbnail_large, cm_status, cm_remarks FROM content_master, content_category_master, content_type_master, content_sub_category_master WHERE cm_ctm_id=ctm_id AND  cm_ccm_id=ccm_id AND cm_cscm_id=cscm_id AND cm_ctm_id=:cm_ctm_id");
        query.setParameter("cm_ctm_id", contentTypeId);
        return query.list();
    }
    
    @Override
    public List<Object[]> readByIdByType(int userId, int contentTypeId) {
        Session session = sessionFactory.openSession();
//        Query query = session.createSQLQuery("SELECT cm_id, ctm_title, ccm_title, cscm_title, cm_title, cf_file, cf_width_height, cf_os_name, cf_os_version, cm_status, ccm_local_title FROM content_master, content_category_master, content_sub_category_master, content_files, content_type_master WHERE cm_ccm_id=ccm_id AND cm_ctm_id=ctm_id AND cm_id=cf_cm_id AND cm_cscm_id=cscm_id AND cm_um_id=:cm_um_id");
        Query query = session.createSQLQuery("SELECT cm_id, ctm_title, ccm_title, cscm_title, cm_title, cm_desc, cm_user_ratings, cm_user_rated, cm_storage_type, cm_thumbnail_small, cm_thumbnail_mid, cm_thumbnail_large, cm_status, cm_remarks FROM content_master, content_category_master, content_type_master, content_sub_category_master WHERE cm_ctm_id=ctm_id AND  cm_ccm_id=ccm_id AND cm_cscm_id=cscm_id AND cm_um_id=:cm_um_id AND cm_ctm_id=:cm_ctm_id");
        query.setParameter("cm_um_id", userId);
        query.setParameter("cm_ctm_id", contentTypeId);
        return query.list();
    }
    
    @Override
    public List<Object[]> readByTypeStatus(int contentTypeId, int contentMasterStatus) {
        
        Session session = sessionFactory.openSession();
        
        Query query = session.createSQLQuery("SELECT cm_id, ctm_title, ccm_title, cscm_title, cm_title, cm_desc, cm_user_ratings, cm_user_rated, cm_storage_type, cm_thumbnail_small, cm_thumbnail_mid, cm_thumbnail_large, cm_status, cm_remarks, CONCAT(cm_created_on,'') cm_created_on FROM content_category_master, content_type_master, content_master LEFT OUTER JOIN content_sub_category_master ON content_master.cm_cscm_id=content_sub_category_master.cscm_id WHERE cm_ctm_id=ctm_id AND  cm_ccm_id=ccm_id AND cm_status=:cm_status AND cm_ctm_id=:cm_ctm_id");
//        Query query = session.createSQLQuery("SELECT cm_id, ctm_title, ccm_title, cscm_title, cm_title, cm_desc, cm_user_ratings, cm_user_rated, cm_storage_type, cm_thumbnail_small, cm_thumbnail_mid, cm_thumbnail_large, cm_status, cm_remarks FROM content_master, content_category_master, content_type_master, content_sub_category_master WHERE cm_ctm_id=ctm_id AND  cm_ccm_id=ccm_id AND cm_cscm_id=cscm_id AND cm_ctm_id=:cm_ctm_id AND cm_status=:cm_status");
        query.setParameter("cm_ctm_id", contentTypeId);
        query.setParameter("cm_status", contentMasterStatus);
        return query.list();
    }
    @Override
    public List<Object[]> readByStatus2(int contentMasterStatus) {
        System.out.println("Eror");
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT cm_id, ctm_title, ccm_title, cscm_title, cm_title, cm_desc, cm_user_ratings, cm_user_rated, cm_storage_type, cm_thumbnail_small, cm_thumbnail_mid, cm_thumbnail_large, cm_status, cm_remarks, CONCAT(cm_created_on,'') cm_created_on FROM content_category_master, content_type_master, content_master LEFT OUTER JOIN content_sub_category_master ON content_master.cm_cscm_id=content_sub_category_master.cscm_id WHERE cm_ctm_id=ctm_id AND  cm_ccm_id=ccm_id AND cm_status=:cm_status");
//        Query query = session.createSQLQuery("SELECT cm_id, ctm_title, ccm_title, cscm_title, cm_title, cm_desc, cm_user_ratings, cm_user_rated, cm_storage_type, cm_thumbnail_small, cm_thumbnail_mid, cm_thumbnail_large, cm_status, cm_remarks FROM content_master, content_category_master, content_type_master, content_sub_category_master WHERE cm_ctm_id=ctm_id AND  cm_ccm_id=ccm_id AND cm_cscm_id=cscm_id AND cm_status=:cm_status");
        query.setParameter("cm_status", contentMasterStatus);
        return query.list();
    }
    @Override
    public List<Object[]> readByIdByTypeStatus(int userId, int contentTypeId, int contentMasterStatus) {
        Session session = sessionFactory.openSession();
//        Query query = session.createSQLQuery("SELECT cm_id, cm_title, cm_desc, cm_type, cm_user_ratings, cm_user_rated, cm_thumbnail_small, cm_thumbnail_mid, cm_thumbnail_large, cm_storage_type, cm_status, ccm_title, cm_remarks FROM content_master, content_category_master WHERE cm_ccm_id=ccm_id AND cm_type=:cm_type");
        Query query = session.createSQLQuery("SELECT cm_id, ctm_title, ccm_title, cscm_title, cm_title, cm_desc, cm_user_ratings, cm_user_rated, cm_storage_type, cm_thumbnail_small, cm_thumbnail_mid, cm_thumbnail_large, cm_status, cm_remarks FROM content_master, content_category_master, content_type_master, content_sub_category_master WHERE cm_ctm_id=ctm_id AND  cm_ccm_id=ccm_id AND cm_cscm_id=cscm_id AND cm_ctm_id=:cm_ctm_id AND cm_status=:cm_status AND cm_us_id=:cm_us_id");
        query.setParameter("cm_ctm_id", contentTypeId);
        query.setParameter("cm_status", contentMasterStatus);
        query.setParameter("cm_us_id", userId);
        return query.list();
    }
    @Override
    public List<Object[]> readByIdByStatus2(int userId, int contentMasterStatus) {
        Session session = sessionFactory.openSession();
//        Query query = session.createSQLQuery("SELECT cm_id, cm_title, cm_desc, cm_type, cm_user_ratings, cm_user_rated, cm_thumbnail_small, cm_thumbnail_mid, cm_thumbnail_large, cm_storage_type, cm_status, ccm_title, cm_remarks FROM content_master, content_category_master WHERE cm_ccm_id=ccm_id AND cm_type=:cm_type");
        Query query = session.createSQLQuery("SELECT cm_id, ctm_title, ccm_title, cscm_title, cm_title, cm_desc, cm_user_ratings, cm_user_rated, cm_storage_type, cm_thumbnail_small, cm_thumbnail_mid, cm_thumbnail_large, cm_status, cm_remarks FROM content_master, content_category_master, content_type_master, content_sub_category_master WHERE cm_ctm_id=ctm_id AND  cm_ccm_id=ccm_id AND cm_cscm_id=cscm_id AND cm_status=:cm_status AND cm_us_id=:cm_us_id");
        query.setParameter("cm_status", contentMasterStatus);
        query.setParameter("cm_us_id", userId);
        return query.list();
    }

        @Override
    public Object[] readContentMaster(int contentMasterId) {
        Session session = sessionFactory.openSession();
//        Query query = session.createSQLQuery("SELECT cm_id, cm_title, cm_desc, cm_type, cm_thumbnail_small, cm_thumbnail_mid, cm_thumbnail_large, cm_storage_type, cm_ccm_id, ccm_title, ctm_title, cscm_title FROM content_master, content_category_master, content_type_master, content_sub_category_master WHERE cm_ctm_id=ctm_id AND cm_ccm_id=ccm_id AND cm_cscm_id=cscm_id AND cm_id=:cm_id");
//        Query query = session.createSQLQuery("SELECT cm_id, cm_title, cm_desc, cm_type, cm_thumbnail_small, cm_thumbnail_mid, cm_thumbnail_large, cm_storage_type, cm_ccm_id, ccm_title, ctm_title, cscm_title FROM content_master, content_category_master, content_type_master, content_master LEFT OUTER JOIN content_sub_category_master ON content_master.cm_cscm_id=content_sub_category_master.cscm_id WHERE cm_ctm_id=ctm_id AND cm_ccm_id=ccm_id AND cm_cscm_id=cscm_id AND cm_id=:cm_id");
Query query = session.createSQLQuery("SELECT cm_id, cm_title, cm_desc, cm_type, cm_thumbnail_small, cm_thumbnail_mid, cm_thumbnail_large, cm_storage_type, cm_ccm_id, ccm_title, ctm_title, cscm_title FROM content_category_master, content_type_master, content_master LEFT OUTER JOIN content_sub_category_master ON content_master.cm_cscm_id=content_sub_category_master.cscm_id WHERE cm_ctm_id=ctm_id AND cm_ccm_id=ccm_id AND cm_id=:cm_id");
        query.setParameter("cm_id", contentMasterId);
        Object[] objects = (Object[]) query.uniqueResult();
            
        return objects;
    }
    
    @Override
    public String update(int contentMasterId, String title, String desc, String smallRelativePath, String middleRelativePath, String largeRelativePath, String storeType) {
        try
        {
            Session session = sessionFactory.openSession();
//            session.beginTransaction();
            Query query = session.createSQLQuery("UPDATE content_master SET cm_title=:cm_title, cm_desc=:cm_desc, cm_thumbnail_small=:cm_thumbnail_small, cm_thumbnail_mid=:cm_thumbnail_mid, cm_thumbnail_large=:cm_thumbnail_large, cm_storage_type=:cm_storage_type, cm_status=:cm_status WHERE cm_id=:cm_id");
            query.setParameter("cm_title", title);
            query.setParameter("cm_desc", desc);
//            query.setParameter("cm_type", type);
            query.setParameter("cm_thumbnail_small", smallRelativePath);
            query.setParameter("cm_thumbnail_mid", middleRelativePath);
            query.setParameter("cm_thumbnail_large", largeRelativePath);
            query.setParameter("cm_storage_type", storeType);
//            query.setParameter("cm_ccm_id", contentCategoryMasterId);
            query.setParameter("cm_id", contentMasterId);
            query.setParameter("cm_status", 1);
            query.executeUpdate();
//            session.getTransaction().commit();
            return "success";
        }
        catch(Exception ex)
        {
            
        }
        
        return "failure";
    }
    
//    @Override
//    public Object[] readContentMaster(int contentMasterId) {
//        Session session = sessionFactory.openSession();
//        Query query = session.createSQLQuery("SELECT cm_id, cm_title, cm_ctm_title, cm_desc, cm_ccm_title, cm_cscm_title, cm_type, cm_thumbnail_small, cm_thumbnail_mid, cm_thumbnail_large, cm_storage_type,  FROM content_master WHERE cm_id=:cm_id");
//        query.setParameter("cm_id", contentMasterId);
//        Object[] objects = (Object[]) query.uniqueResult();
//        return objects;
//    }
    
//    @Override
//    public Object[] readContentMaster(int contentMasterId) {
//        Session session = sessionFactory.openSession();
//        Query query = session.createSQLQuery("SELECT cm_id, cm_title, cm_desc, cm_type, cm_thumbnail_small, cm_thumbnail_mid, cm_thumbnail_large, cm_storage_type, cm_ccm_id FROM content_master WHERE cm_id=:cm_id");
//        query.setParameter("cm_id", contentMasterId);
//        Object[] objects = (Object[]) query.uniqueResult();
//        return objects;
//    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //New Update Ends...
    //Checked...
    @Override
    public String updateStatus(int contentMasterId, int status) {
        try
        {
            Session session = sessionFactory.openSession();
//            session.beginTransaction();
            Query query = session.createSQLQuery("UPDATE content_master SET cm_status=:cm_status WHERE cm_id=:cm_id");
            query.setParameter("cm_status", status);
            query.setParameter("cm_id", contentMasterId);
            query.executeUpdate();
//            session.getTransaction().commit();
            return "success";
        }
        catch(Exception ex)
        {
            
        }
        
        return "failure";
    }

    //Checked...
//    @Override
//    public String create(int userId, String title, String desc, String type, String smallDirectory, String middleDirectory, String largeDirectory, String storeType, int contentCategoryMasterId) {
//        try
//        {
//            Session session = sessionFactory.openSession();
//            Query query = session.createSQLQuery("INSERT INTO content_master (cm_title, cm_desc, cm_type, cm_thumbnail_small, cm_thumbnail_mid, cm_thumbnail_large, cm_storage_type, cm_ccm_id, cm_um_id) VALUES(:cm_title, :cm_desc, :cm_type, :cm_thumbnail_small, :cm_thumbnail_mid, :cm_thumbnail_large, :cm_storage_type, :cm_ccm_id, :cm_um_id)");
//            query.setParameter("cm_title", title);
//            query.setParameter("cm_desc", desc);
//            query.setParameter("cm_type", type);
//            
//            query.setParameter("cm_thumbnail_small", smallDirectory);
//            query.setParameter("cm_thumbnail_mid", middleDirectory);
//            query.setParameter("cm_thumbnail_large", largeDirectory);
//            query.setParameter("cm_storage_type", storeType);
//            query.setParameter("cm_ccm_id", contentCategoryMasterId);
//            query.setParameter("cm_um_id", userId);
//            
//            query.executeUpdate();
//            return "success";
//        }
//        catch(Exception ex)
//        {
//            System.out.println("Error from catch");
//        }
//        return "failure";
//    }
    
    //New
    @Override
    public String create(int userId, String title, String desc, int type, String smallDirectory, String middleDirectory, String largeDirectory, String storeType, int contentCategoryMasterId, int contentSubCategoryMasterId) {
        try
        {
            Session session = sessionFactory.openSession();
//            Query query = session.createSQLQuery("INSERT INTO content_master (cm_title, cm_desc, cm_type, cm_thumbnail_small, cm_thumbnail_mid, cm_thumbnail_large, cm_storage_type, cm_ccm_id, cm_um_id) VALUES(:cm_title, :cm_desc, :cm_type, :cm_thumbnail_small, :cm_thumbnail_mid, :cm_thumbnail_large, :cm_storage_type, :cm_ccm_id, :cm_um_id)");
            Query query = session.createSQLQuery("INSERT INTO content_master (cm_title, cm_desc, cm_ctm_id, cm_thumbnail_small, cm_thumbnail_mid, cm_thumbnail_large, cm_storage_type, cm_ccm_id, cm_cscm_id, cm_um_id) VALUES(:cm_title, :cm_desc, :cm_ctm_id, :cm_thumbnail_small, :cm_thumbnail_mid, :cm_thumbnail_large, :cm_storage_type, :cm_ccm_id, :cm_cscm_id, :cm_um_id)");
            query.setParameter("cm_title", title);
            query.setParameter("cm_desc", desc);
            query.setParameter("cm_ctm_id", type);
            
            query.setParameter("cm_thumbnail_small", smallDirectory);
            query.setParameter("cm_thumbnail_mid", middleDirectory);
            query.setParameter("cm_thumbnail_large", largeDirectory);
            query.setParameter("cm_storage_type", storeType);
            query.setParameter("cm_ccm_id", contentCategoryMasterId);
            query.setParameter("cm_cscm_id", contentSubCategoryMasterId);
            query.setParameter("cm_um_id", userId);
            
            query.executeUpdate();
            return "success";
        }
        catch(Exception ex)
        {
            System.out.println("Error from catch");
        }
        return "failure";
    }

    //Checked...
    

//    @Override
//    public String update(int contentMasterId, String title, String desc, String type, String smallRelativePath, String middleRelativePath, String largeRelativePath, String storeType, int contentCategoryMasterId) {
//        try
//        {
//            Session session = sessionFactory.openSession();
////            session.beginTransaction();
//            Query query = session.createSQLQuery("UPDATE content_master SET cm_title=:cm_title, cm_desc=:cm_desc, cm_type=:cm_type, cm_thumbnail_small=:cm_thumbnail_small, cm_thumbnail_mid=:cm_thumbnail_mid, cm_thumbnail_large=:cm_thumbnail_large, cm_storage_type=:cm_storage_type, cm_ccm_id=:cm_ccm_id, cm_status=:cm_status WHERE cm_id=:cm_id");
//            query.setParameter("cm_title", title);
//            query.setParameter("cm_desc", desc);
//            query.setParameter("cm_type", type);
//            query.setParameter("cm_thumbnail_small", smallRelativePath);
//            query.setParameter("cm_thumbnail_mid", middleRelativePath);
//            query.setParameter("cm_thumbnail_large", largeRelativePath);
//            query.setParameter("cm_storage_type", storeType);
//            query.setParameter("cm_ccm_id", contentCategoryMasterId);
//            query.setParameter("cm_id", contentMasterId);
//            query.setParameter("cm_status", 1);
//            query.executeUpdate();
////            session.getTransaction().commit();
//            return "success";
//        }
//        catch(Exception ex)
//        {
//            
//        }
//        
//        return "failure";
//    }

    
    

    

    @Override
    public String updateStatusByReviewer(int contentMasterId, int option, String remarks) {
        try
        {
            Session session = sessionFactory.openSession();
//            session.beginTransaction();
            Query query = session.createSQLQuery("UPDATE content_master SET cm_status=:cm_status, cm_remarks=:cm_remarks WHERE cm_id=:cm_id");
            query.setParameter("cm_status", option);
            query.setParameter("cm_remarks", remarks);
            query.setParameter("cm_id", contentMasterId);
            query.executeUpdate();
//            session.getTransaction().commit();
            return "success";
        }
        catch(Exception ex)
        {
            
        }
        
        return "failure";
    }

//    @Override
//    public List<Object[]> readByTitle(int contentMasterId, String title) {
//        Session session = sessionFactory.openSession();
//        Query query = session.createSQLQuery("SELECT cm_id, cm_title, cm_desc, cm_type, cm_user_ratings, cm_user_rated, cm_thumbnail_small, cm_thumbnail_mid, cm_thumbnail_large, cm_storage_type, cm_status, ccm_title FROM content_master, content_category_master WHERE cm_ccm_id=ccm_id AND cm_title=:cm_title");
//        query.setParameter("cm_title", title);
//        return query.list();
//    }
    
    @Override
    public List<Object[]> readByTitle(String title) {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT cm_id, cm_title, cm_desc, cm_type, cm_user_ratings, cm_user_rated, cm_thumbnail_small, cm_thumbnail_mid, cm_thumbnail_large, cm_storage_type, cm_status, ccm_title, cm_remarks FROM content_master, content_category_master WHERE cm_ccm_id=ccm_id AND cm_title LIKE '%"+title+"%'");
//        query.setParameter("cm_title", title);
        return query.list();
    }

    @Override
    public List<Object[]> readByStatus(String status) {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT cm_id, cm_title, cm_desc, cm_type, cm_user_ratings, cm_user_rated, cm_thumbnail_small, cm_thumbnail_mid, cm_thumbnail_large, cm_storage_type, cm_status, ccm_title, cm_remarks FROM content_master, content_category_master WHERE cm_ccm_id=ccm_id AND cm_status=:cm_status");
        query.setParameter("cm_status", status);
        return query.list();
    }

    

    @Override
    public List<Object[]> readByDescription(String desc) {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT cm_id, cm_title, cm_desc, cm_type, cm_user_ratings, cm_user_rated, cm_thumbnail_small, cm_thumbnail_mid, cm_thumbnail_large, cm_storage_type, cm_status, ccm_title, cm_remarks FROM content_master, content_category_master WHERE cm_ccm_id=ccm_id AND cm_desc LIKE '%"+desc+"%'");
//        query.setParameter("cm_desc", desc);
        return query.list();
    }

    //New
    @Override
    public Object[] readByOthers(int userId, String title, String desc, int type, String smallDirectory, String middleDirectory, String largeDirectory, String storeType, int contentCategoryMasterId) {
        Session session = sessionFactory.openSession();
//        Query query = session.createSQLQuery("SELECT cm_id, cm_title, cm_desc, cm_type, cm_thumbnail_small, cm_thumbnail_mid, cm_thumbnail_large, cm_storage_type, cm_ccm_id, cm_um_id, cm_ctm_id FROM content_master WHERE cm_ccm_id=:cm_ccm_id AND cm_title=:cm_title AND cm_desc=:cm_desc");
//        Query query = session.createSQLQuery("SELECT cm_id, cm_title, cm_desc, cm_type, cm_thumbnail_small, cm_thumbnail_mid, cm_thumbnail_large, cm_storage_type, cm_ccm_id, cm_um_id, cm_ctm_id FROM content_master WHERE cm_ccm_id=:cm_ccm_id AND cm_title=:cm_title AND cm_desc=:cm_desc");
        Query query = session.createSQLQuery("SELECT cm_id, cm_title, cm_desc, cm_type, cm_thumbnail_small, cm_thumbnail_mid, cm_thumbnail_large, cm_storage_type, cm_ccm_id, cm_um_id, cm_ctm_id FROM content_master WHERE cm_ccm_id=:cm_ccm_id AND cm_title=:cm_title AND cm_desc=:cm_desc AND cm_um_id=:cm_um_id ORDER BY cm_id DESC LIMIT 1");
        query.setParameter("cm_ccm_id", contentCategoryMasterId);
        query.setParameter("cm_desc", desc);
        query.setParameter("cm_title", title);
        query.setParameter("cm_um_id", userId);
        Object[] objects = (Object[]) query.uniqueResult();
        return objects;
    }

    @Override
    public List<Object[]> readAllModified() {
        Session session = sessionFactory.openSession();
//        Query query = session.createSQLQuery("SELECT cm_id, cm_title, cm_desc, cm_type, cm_user_ratings, cm_user_rated, cm_thumbnail_small, cm_thumbnail_mid, cm_thumbnail_large, cm_storage_type, cm_status, ccm_title FROM content_master, content_category_master WHERE cm_ccm_id=ccm_id");
//        Query query = session.createSQLQuery("SELECT cm_id, cm_title, cm_desc, cm_type, cm_user_ratings, cm_user_rated, cm_thumbnail_small, cm_thumbnail_mid, cm_thumbnail_large, cm_storage_type, cm_status, ccm_title, cm_remarks FROM content_master, content_category_master WHERE cm_ccm_id=ccm_id");
//        Query query = session.createSQLQuery("SELECT cm_id, ctm_title, ccm_title, cscm_title, cm_title, cf_file, cf_width_height, cf_os_name, cf_os_version, cm_status, ccm_local_title FROM content_master, content_category_master, content_sub_category_master, content_files, content_type_master WHERE cm_ccm_id=ccm_id AND cm_ctm_id=ctm_id AND cm_id=cf_cm_id AND cm_cscm_id=cscm_id");
        Query query = session.createSQLQuery("SELECT cm_id, ctm_title, ccm_title, cscm_title, cm_title, cf_file, cf_width_height, cf_os_name, cf_os_version, cm_status, ccm_local_title FROM content_master, content_category_master, content_sub_category_master, content_files, content_type_master WHERE cm_ccm_id=ccm_id AND cm_id=cf_cm_id AND cm_ctm_id=ctm_id AND cm_cscm_id=cscm_id");
        return query.list();
    }

    @Override
    public List<Object[]> readByIdModified(int userId) {
        Session session = sessionFactory.openSession();
//        Query query = session.createSQLQuery("SELECT cm_id, cm_title, cm_desc, cm_type, cm_user_ratings, cm_user_rated, cm_thumbnail_small, cm_thumbnail_mid, cm_thumbnail_large, cm_storage_type, cm_status, ccm_title FROM content_master, content_category_master WHERE cm_ccm_id=ccm_id");
//        Query query = session.createSQLQuery("SELECT cm_id, cm_title, cm_desc, cm_type, cm_user_ratings, cm_user_rated, cm_thumbnail_small, cm_thumbnail_mid, cm_thumbnail_large, cm_storage_type, cm_status, ccm_title, cm_remarks FROM content_master, content_category_master WHERE cm_ccm_id=ccm_id AND cm_um_id=:cm_um_id");
//        Query query = session.createSQLQuery("SELECT cm_id, ctm_title, ccm_title, cscm_title, cm_title, cf_file, cf_width_height, cf_os_name, cf_os_version, cm_status, ccm_local_title FROM content_master, content_category_master, content_sub_category_master, content_files, content_type_master WHERE cm_ccm_id=ccm_id AND cm_ctm_id=ctm_id AND cm_id=cf_cm_id AND cm_cscm_id=cscm_id AND cm_um_id=:cm_um_id");
        Query query = session.createSQLQuery("SELECT cm_id, ctm_title, ccm_title, cscm_title, cm_title, cf_file, cf_width_height, cf_os_name, cf_os_version, cm_status, ccm_local_title FROM content_master, content_category_master, content_sub_category_master, content_files, content_type_master WHERE cm_ccm_id=ccm_id AND cm_id=cf_cm_id AND cm_ctm_id=ctm_id AND cm_cscm_id=cscm_id AND cm_um_id=:cm_um_id");
        query.setParameter("cm_um_id", userId);
        return query.list();
    }

    @Override
    public List<Object[]> readAllForPortalMappingByUserTypeCategory(int userMaster, int contentTypeMaster, int contentCategoryMaster) {
        Session session = null;
        try
        {
            session = sessionFactory.openSession();

            Query query = session.createSQLQuery("SELECT cm_id, cm_title, um_user_name, ctm_title, ccm_title, cm_status FROM content_category_master, content_type_master, content_master, user_master WHERE cm_ctm_id=ctm_id AND  cm_ccm_id=ccm_id AND cm_um_id=um_id AND cm_um_id=:cm_um_id AND cm_ctm_id=:cm_ctm_id AND cm_ccm_id=:cm_ccm_id");
            query.setParameter("cm_um_id", userMaster);
            query.setParameter("cm_ctm_id", contentTypeMaster);
            query.setParameter("cm_ccm_id", contentCategoryMaster);
            List<Object[]> hh = query.list();

            return hh;
        }
        catch(Exception ex)
        {
            
        }
        finally
        {
            session.close();
        }
        return null;
    }

    @Override
    public List<Object[]> readAllForPortalMappingByUserType(int userMaster, int contentTypeMaster) {
        Session session = null;
        try
        {
            session = sessionFactory.openSession();

            Query query = session.createSQLQuery("SELECT cm_id, cm_title, um_user_name, ctm_title, ccm_title, cm_status FROM content_category_master, content_type_master, content_master, user_master WHERE cm_ctm_id=ctm_id AND  cm_ccm_id=ccm_id AND cm_um_id=um_id AND cm_um_id=:cm_um_id AND cm_ctm_id=:cm_ctm_id");
            query.setParameter("cm_um_id", userMaster);
            query.setParameter("cm_ctm_id", contentTypeMaster);
            List<Object[]> hh = query.list();

            return hh;
        }
        catch(Exception ex)
        {
            
        }
        finally
        {
            session.close();
        }
        return null;
    }

    @Override
    public List<Object[]> readAllForPortalMappingByUserCategory(int userMaster, int contentCategoryMaster) {
        Session session = null;
        try
        {
            session = sessionFactory.openSession();

            Query query = session.createSQLQuery("SELECT cm_id, cm_title, um_user_name, ctm_title, ccm_title, cm_status FROM content_category_master, content_type_master, content_master, user_master WHERE cm_ctm_id=ctm_id AND  cm_ccm_id=ccm_id AND cm_um_id=um_id AND cm_um_id=:cm_um_id AND cm_ccm_id=:cm_ccm_id");
            query.setParameter("cm_um_id", userMaster);
            query.setParameter("cm_ccm_id", contentCategoryMaster);
            List<Object[]> hh = query.list();

            return hh;
        }
        catch(Exception ex)
        {
            
        }
        finally
        {
            session.close();
        }
        return null;
    }

    @Override
    public List<Object[]> readAllForPortalMappingByUser(int userMaster) {
        Session session = null;
        try
        {
            session = sessionFactory.openSession();

            Query query = session.createSQLQuery("SELECT cm_id, cm_title, um_user_name, ctm_title, ccm_title, cm_status FROM content_category_master, content_type_master, content_master, user_master WHERE cm_ctm_id=ctm_id AND  cm_ccm_id=ccm_id AND cm_um_id=um_id AND cm_um_id=:cm_um_id");
            query.setParameter("cm_um_id", userMaster);
            List<Object[]> hh = query.list();

            return hh;
        }
        catch(Exception ex)
        {
            
        }
        finally
        {
            session.close();
        }
        return null;
    }

    @Override
    public List<Object[]> readAllForPortalMappingByTypeCategory(int contentTypeMaster, int contentCategoryMaster) {
        Session session = null;
        try
        {
            session = sessionFactory.openSession();

            Query query = session.createSQLQuery("SELECT cm_id, cm_title, um_user_name, ctm_title, ccm_title, cm_status FROM content_category_master, content_type_master, content_master, user_master WHERE cm_ctm_id=ctm_id AND  cm_ccm_id=ccm_id AND cm_um_id=um_id AND cm_ctm_id=:cm_ctm_id AND cm_ccm_id=:cm_ccm_id");
            query.setParameter("cm_ctm_id", contentTypeMaster);
            query.setParameter("cm_ccm_id", contentCategoryMaster);
            List<Object[]> hh = query.list();

            return hh;
        }
        catch(Exception ex)
        {
            
        }
        finally
        {
            session.close();
        }
        return null;
    }

    @Override
    public List<Object[]> readAllForPortalMappingByType(int contentTypeMaster) {
        Session session = null;
        try
        {
            session = sessionFactory.openSession();

            Query query = session.createSQLQuery("SELECT cm_id, cm_title, um_user_name, ctm_title, ccm_title, cm_status FROM content_category_master, content_type_master, content_master, user_master WHERE cm_ctm_id=ctm_id AND  cm_ccm_id=ccm_id AND cm_um_id=um_id AND cm_ctm_id=:cm_ctm_id");
            query.setParameter("cm_ctm_id", contentTypeMaster);
            List<Object[]> hh = query.list();

            return hh;
        }
        catch(Exception ex)
        {
            
        }
        finally
        {
            session.close();
        }
        return null;
    }

    @Override
    public List<Object[]> readAllForPortalMappingByCategory(int contentCategoryMaster) {
        Session session = null;
        try
        {
            session = sessionFactory.openSession();

            Query query = session.createSQLQuery("SELECT cm_id, cm_title, um_user_name, ctm_title, ccm_title, cm_status FROM content_category_master, content_type_master, content_master, user_master WHERE cm_ctm_id=ctm_id AND  cm_ccm_id=ccm_id AND cm_um_id=um_id AND cm_ccm_id=:cm_ccm_id");
            query.setParameter("cm_ccm_id", contentCategoryMaster);
            List<Object[]> hh = query.list();

            return hh;
        }
        catch(Exception ex)
        {
            
        }
        finally
        {
            session.close();
        }
        return null;
    }

    

    

    

    

    

    

    


    
    
}
