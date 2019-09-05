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
public class BannerMasterDaoImp implements BannerMasterDao{
    private SessionFactory sessionFactory;

    
    //Update Begins...
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    
    @Override
    public String create(int contentTypeMasterId, String title, String bannerImageDir, String size, String url, String displayTitle, String displayDescription, double displayAmount, double displayDiscountPercentage, String startDate, String endDate) {
        Session session=null;
        try
        {
            session = sessionFactory.openSession();
            Query query = session.createSQLQuery("INSERT INTO banner_master (bm_ctm_id, bm_title, bm_image, bm_size, bm_url, bm_display_title, bm_display_desc, bm_display_amount, bm_display_discount_percentage, start_on, expire_on) VALUES(:bm_ctm_id, :bm_title, :bm_image, :bm_size, :bm_url, :bm_display_title, :bm_display_desc, :bm_display_amount, :bm_display_discount_percentage, :start_on, :expire_on)");
            query.setParameter("bm_title", title);
            query.setParameter("bm_image", bannerImageDir);
            query.setParameter("bm_size", size);
            query.setParameter("bm_url", url);
            query.setParameter("bm_ctm_id", contentTypeMasterId);
            
            query.setParameter("bm_display_title", displayTitle);
            query.setParameter("bm_display_desc", displayDescription);
            query.setParameter("bm_display_amount", displayAmount);
            query.setParameter("bm_display_discount_percentage", displayDiscountPercentage);
            
            query.setParameter("start_on", startDate);
            query.setParameter("expire_on", endDate);
            
            
            
            
            
            query.executeUpdate();
            
            return "success";
        }
        catch(Exception ex)
        {
            System.out.println("Error from catch: " + ex.getMessage());
        }
        finally
        {
            session.close();
        }
        return "failure";
    }
    
    @Override
    public List<Object[]> readAll() {
        Session session=null;
        try
        {
            session = sessionFactory.openSession();
            Query query = session.createSQLQuery("SELECT bm_id, ctm_title, bm_title, bm_url, bm_size, bm_status, bm_image, CONCAT(start_on,'') start_on, CONCAT(expire_on,'') expire_on FROM banner_master, content_type_master WHERE bm_ctm_id=ctm_id");
            
            return query.list();
            
        }
        catch(Exception ex)
        {

        }
        finally
        {
//            sessionFactory.close();
            session.close();
            
        }
        return null;
    }
    
    @Override
    public Object[] readBanner(int id) {
        Session session=null;
        try
        {
            session = sessionFactory.openSession();
            Query query = session.createSQLQuery("SELECT bm_id, bm_ctm_id, bm_title, bm_image, bm_size, bm_url, bm_status, CONCAT(start_on,'') start_on, CONCAT(expire_on,'') expire_on, bm_display_title, bm_display_desc, bm_display_amount, bm_display_discount_percentage FROM banner_master WHERE bm_id=:bm_id");
            query.setParameter("bm_id", id);
            Object[] objects = (Object[]) query.uniqueResult();
            return objects;
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
    public String update(int bannerId, int contentTypeMasterId, String title, String bannerImageDir, String size, String url, String displayTitle, String displayDescription, double displayAmount, double displayDiscountPercentage, String startDate, String endDate) {
        Session session=null;
        try
        {
            session = sessionFactory.openSession();
            Query query = session.createSQLQuery("UPDATE banner_master SET bm_ctm_id=:bm_ctm_id, bm_title=:bm_title, bm_image=:bm_image, bm_size=:bm_size, bm_url=:bm_url, start_on=:bm_start_on, bm_display_title=:bm_display_title, bm_display_desc=:bm_display_desc, bm_display_amount=:bm_display_amount, bm_display_discount_percentage=:bm_display_discount_percentage, expire_on=:bm_expire_on WHERE(bm_id=:bm_id)");
            query.setParameter("bm_title", title);
            query.setParameter("bm_image", bannerImageDir);
            query.setParameter("bm_size", size);
            query.setParameter("bm_url", url);
            
            query.setParameter("bm_display_title", displayTitle);
            query.setParameter("bm_display_desc", displayDescription);
            query.setParameter("bm_display_amount", displayAmount);
            query.setParameter("bm_display_discount_percentage", displayDiscountPercentage);
            
            
            query.setParameter("bm_start_on", startDate);
            query.setParameter("bm_expire_on", endDate);
            
            
            
            
            query.setParameter("bm_id", bannerId);
            query.setParameter("bm_ctm_id", contentTypeMasterId);
            
            
            query.executeUpdate();
            
            return "success";
        }
        catch(Exception ex)
        {
            
        }
        finally
        {
            session.close();
        }
        
        return "failure";
    }
    
    @Override
    public List<Object[]> readByStatus(int status) {
        Session session=null;
        try
        {
            session = sessionFactory.openSession();
            Query query = session.createSQLQuery("SELECT bm_id, ctm_title, bm_title, bm_url, bm_size, bm_status, bm_image, CONCAT(start_on,'') start_on, CONCAT(expire_on,'') expire_on FROM banner_master, content_type_master WHERE bm_ctm_id=ctm_id AND bm_status=:bm_status");
            query.setParameter("bm_status", status);
            List<Object[]> objects = (List<Object[]>) query.list();
            return objects;
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
    public List<Object[]> readByType(int contentTypeId) {
        Session session=null;
        try
        {
        session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT bm_id, ctm_title, bm_title, bm_url, bm_size, bm_status, bm_image, CONCAT(start_on,'') start_on, CONCAT(expire_on,'') expire_on FROM banner_master, content_type_master WHERE bm_ctm_id=ctm_id AND bm_ctm_id=:bm_ctm_id");
        query.setParameter("bm_ctm_id", contentTypeId);
        List<Object[]> objects = (List<Object[]>) query.list();
        return objects;
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
    public List<Object[]> readByTypeStatus(int contentTypeId, int status) {
        Session session=null;
        try
        {
        session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT bm_id, ctm_title, bm_title, bm_url, bm_size, bm_status, bm_image, CONCAT(start_on,'') start_on, CONCAT(expire_on,'') expire_on FROM banner_master, content_type_master WHERE bm_ctm_id=ctm_id AND bm_ctm_id=:bm_ctm_id AND bm_status=:bm_status");
        query.setParameter("bm_ctm_id", contentTypeId);
        query.setParameter("bm_status", status);
        List<Object[]> objects = (List<Object[]>) query.list();
        return objects;
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
    
    
    
    
    //Update Ends...
    

    

    //Checked...
//    @Override
//    public String updateStatus(int contentMasterId, int status) {
//        try
//        {
//            Session session = sessionFactory.openSession();
////            session.beginTransaction();
//            Query query = session.createSQLQuery("UPDATE content_master SET cm_status=:cm_status WHERE cm_id=:cm_id");
//            query.setParameter("cm_status", status);
//            query.setParameter("cm_id", contentMasterId);
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

    //Checked...
    

    

    

    //Checked...
//    @Override
//    public List<Object[]> readById(int userId) {
//        Session session = sessionFactory.openSession();
////        Query query = session.createSQLQuery("SELECT cm_id, cm_title, cm_desc, cm_type, cm_user_ratings, cm_user_rated, cm_thumbnail_small, cm_thumbnail_mid, cm_thumbnail_large, cm_storage_type, cm_status, ccm_title FROM content_master, content_category_master WHERE cm_ccm_id=ccm_id");
//        Query query = session.createSQLQuery("SELECT cm_id, cm_title, cm_desc, cm_type, cm_user_ratings, cm_user_rated, cm_thumbnail_small, cm_thumbnail_mid, cm_thumbnail_large, cm_storage_type, cm_status, ccm_title, cm_remarks FROM content_master, content_category_master WHERE cm_ccm_id=ccm_id AND cm_um_id=:cm_um_id");
//        query.setParameter("cm_um_id", userId);
//        return query.list();
//    }

    


    
    @Override
    public List<Object[]> readByTitle(String title) {
//        Session session = sessionFactory.openSession();
//        Query query = session.createSQLQuery("SELECT cm_id, cm_title, cm_desc, cm_type, cm_user_ratings, cm_user_rated, cm_thumbnail_small, cm_thumbnail_mid, cm_thumbnail_large, cm_storage_type, cm_status, ccm_title, cm_remarks FROM content_master, content_category_master WHERE cm_ccm_id=ccm_id AND cm_title LIKE '%"+title+"%'");
////        query.setParameter("cm_title", title);
//        return query.list();
        return null;
    }

    

    @Override
    public String updateStatus(int bannerId, int status) {
        Session session=null;
        try
        {
            session = sessionFactory.openSession();
//            session.beginTransaction();
            Query query = session.createSQLQuery("UPDATE banner_master SET bm_status=:bm_status WHERE bm_id=:bm_id");
            query.setParameter("bm_status", status);
            query.setParameter("bm_id", bannerId);
            query.executeUpdate();
//            session.getTransaction().commit();
            return "success";
        }
        catch(Exception ex)
        {
            
        }
        finally
        {
            session.close();
        }
        
        return "failure";
    }

    

    

    

    

    


    
    
}
