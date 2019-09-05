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
public class ContentTypeMasterDaoImp implements ContentTypeMasterDao{
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    

    @Override
    public List<Object[]> readAll() {
        Session session = null;
        try
        {
            session = sessionFactory.openSession();
    //        Query query = session.createSQLQuery("SELECT cm_id, cm_title, cm_desc, cm_type, cm_user_ratings, cm_user_rated, cm_thumbnail_small, cm_thumbnail_mid, cm_thumbnail_large, cm_storage_type, cm_status, ccm_title, cm_remarks FROM content_master, content_category_master WHERE cm_ccm_id=ccm_id");
            Query query = session.createSQLQuery("SELECT ctm_id, ctm_title, ctm_desc, ctm_status FROM content_type_master");
            return query.list();
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
    public Object[] readContentTypeMaster(int contentTypeMasterId) {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT ctm_id, ctm_title, ctm_desc FROM content_type_master WHERE ctm_id=:ctm_id");
        query.setParameter("ctm_id", contentTypeMasterId);
        Object[] objects = (Object[]) query.uniqueResult();
        return objects;
    }
    
    
    
    
    
    
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
    @Override
    public String create(int userId, String title, String desc, String type, String smallDirectory, String middleDirectory, String largeDirectory, String storeType, int contentCategoryMasterId) {
        try
        {
            Session session = sessionFactory.openSession();
            Query query = session.createSQLQuery("INSERT INTO content_master (cm_title, cm_desc, cm_type, cm_thumbnail_small, cm_thumbnail_mid, cm_thumbnail_large, cm_storage_type, cm_ccm_id, cm_um_id) VALUES(:cm_title, :cm_desc, :cm_type, :cm_thumbnail_small, :cm_thumbnail_mid, :cm_thumbnail_large, :cm_storage_type, :cm_ccm_id, :cm_um_id)");
            query.setParameter("cm_title", title);
            query.setParameter("cm_desc", desc);
            query.setParameter("cm_type", type);
            
            query.setParameter("cm_thumbnail_small", smallDirectory);
            query.setParameter("cm_thumbnail_mid", middleDirectory);
            query.setParameter("cm_thumbnail_large", largeDirectory);
            query.setParameter("cm_storage_type", storeType);
            query.setParameter("cm_ccm_id", contentCategoryMasterId);
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
    @Override
    public Object[] readContentMaster(int contentMasterId) {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT cm_id, cm_title, cm_desc, cm_type, cm_thumbnail_small, cm_thumbnail_mid, cm_thumbnail_large, cm_storage_type, cm_ccm_id FROM content_master WHERE cm_id=:cm_id");
        query.setParameter("cm_id", contentMasterId);
        Object[] objects = (Object[]) query.uniqueResult();
        return objects;
    }

    @Override
    public String update(int contentMasterId, String title, String desc, String type, String smallRelativePath, String middleRelativePath, String largeRelativePath, String storeType, int contentCategoryMasterId) {
        try
        {
            Session session = sessionFactory.openSession();
//            session.beginTransaction();
            Query query = session.createSQLQuery("UPDATE content_master SET cm_title=:cm_title, cm_desc=:cm_desc, cm_type=:cm_type, cm_thumbnail_small=:cm_thumbnail_small, cm_thumbnail_mid=:cm_thumbnail_mid, cm_thumbnail_large=:cm_thumbnail_large, cm_storage_type=:cm_storage_type, cm_ccm_id=:cm_ccm_id, cm_status=:cm_status WHERE cm_id=:cm_id");
            query.setParameter("cm_title", title);
            query.setParameter("cm_desc", desc);
            query.setParameter("cm_type", type);
            query.setParameter("cm_thumbnail_small", smallRelativePath);
            query.setParameter("cm_thumbnail_mid", middleRelativePath);
            query.setParameter("cm_thumbnail_large", largeRelativePath);
            query.setParameter("cm_storage_type", storeType);
            query.setParameter("cm_ccm_id", contentCategoryMasterId);
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

    //Checked...
    @Override
    public List<Object[]> readById(int userId) {
        Session session = sessionFactory.openSession();
//        Query query = session.createSQLQuery("SELECT cm_id, cm_title, cm_desc, cm_type, cm_user_ratings, cm_user_rated, cm_thumbnail_small, cm_thumbnail_mid, cm_thumbnail_large, cm_storage_type, cm_status, ccm_title FROM content_master, content_category_master WHERE cm_ccm_id=ccm_id");
        Query query = session.createSQLQuery("SELECT cm_id, cm_title, cm_desc, cm_type, cm_user_ratings, cm_user_rated, cm_thumbnail_small, cm_thumbnail_mid, cm_thumbnail_large, cm_storage_type, cm_status, ccm_title, cm_remarks FROM content_master, content_category_master WHERE cm_ccm_id=ccm_id AND cm_um_id=:cm_um_id");
        query.setParameter("cm_um_id", userId);
        return query.list();
    }

    

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
    public List<Object[]> readByType(String type) {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT cm_id, cm_title, cm_desc, cm_type, cm_user_ratings, cm_user_rated, cm_thumbnail_small, cm_thumbnail_mid, cm_thumbnail_large, cm_storage_type, cm_status, ccm_title, cm_remarks FROM content_master, content_category_master WHERE cm_ccm_id=ccm_id AND cm_type=:cm_type");
        query.setParameter("cm_type", type);
        return query.list();
    }

    @Override
    public List<Object[]> readByDescription(String desc) {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT cm_id, cm_title, cm_desc, cm_type, cm_user_ratings, cm_user_rated, cm_thumbnail_small, cm_thumbnail_mid, cm_thumbnail_large, cm_storage_type, cm_status, ccm_title, cm_remarks FROM content_master, content_category_master WHERE cm_ccm_id=ccm_id AND cm_desc LIKE '%"+desc+"%'");
//        query.setParameter("cm_desc", desc);
        return query.list();
    }

    @Override
    public List<Object[]> readByType(int contentTypeId) {
        Session session = null;
        try
        {
            session = sessionFactory.openSession();
    //        Query query = session.createSQLQuery("SELECT cm_id, cm_title, cm_desc, cm_type, cm_user_ratings, cm_user_rated, cm_thumbnail_small, cm_thumbnail_mid, cm_thumbnail_large, cm_storage_type, cm_status, ccm_title, cm_remarks FROM content_master, content_category_master WHERE cm_ccm_id=ccm_id");
            Query query = session.createSQLQuery("SELECT ctm_id, ctm_title, ctm_desc, ctm_status FROM content_type_master WHERE ctm_id=:ctm_id");
            query.setParameter("ctm_id", contentTypeId);
            return query.list();
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
    public List<Object[]> readByStatus(int contentTypeMasterStatus) {
        Session session = null;
        try
        {
            session = sessionFactory.openSession();
    //        Query query = session.createSQLQuery("SELECT cm_id, cm_title, cm_desc, cm_type, cm_user_ratings, cm_user_rated, cm_thumbnail_small, cm_thumbnail_mid, cm_thumbnail_large, cm_storage_type, cm_status, ccm_title, cm_remarks FROM content_master, content_category_master WHERE cm_ccm_id=ccm_id");
            Query query = session.createSQLQuery("SELECT ctm_id, ctm_title, ctm_desc, ctm_status FROM content_type_master WHERE ctm_status=:ctm_status");
            query.setParameter("ctm_status", contentTypeMasterStatus);
            return query.list();
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
    public List<Object[]> readByTypeStatus(int contentTypeId, int contentTypeMasterStatus) {
        Session session = null;
        try
        {
            session = sessionFactory.openSession();
    //        Query query = session.createSQLQuery("SELECT cm_id, cm_title, cm_desc, cm_type, cm_user_ratings, cm_user_rated, cm_thumbnail_small, cm_thumbnail_mid, cm_thumbnail_large, cm_storage_type, cm_status, ccm_title, cm_remarks FROM content_master, content_category_master WHERE cm_ccm_id=ccm_id");
            Query query = session.createSQLQuery("SELECT ctm_id, ctm_title, ctm_desc, ctm_status FROM content_type_master WHERE ctm_title=:ctm_title AND ctm_status=:ctm_status");
            query.setParameter("ctm_title", contentTypeId);
            query.setParameter("ctm_status", contentTypeMasterStatus);
            return query.list();
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
    public List<Object[]> readAllFromContentMaster() {
        Session session = null;
        try
        {
            session = sessionFactory.openSession();
    //        Query query = session.createSQLQuery("SELECT cm_id, cm_title, cm_desc, cm_type, cm_user_ratings, cm_user_rated, cm_thumbnail_small, cm_thumbnail_mid, cm_thumbnail_large, cm_storage_type, cm_status, ccm_title, cm_remarks FROM content_master, content_category_master WHERE cm_ccm_id=ccm_id");
            Query query = session.createSQLQuery("SELECT DISTINCT(ctm_title), ctm_id, ctm_desc, ctm_status FROM content_master, content_type_master WHERE cm_ctm_id=ctm_id");
            
            return query.list();
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
