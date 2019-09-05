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
public class ContentCategoryMappingDaoImp implements ContentCategoryMappingDao{
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    //Checked...
    @Override
    public String create(int contentMaster, int contentCategoryMaster) {
        Session session=null;
        try
        {
            if(!contentCategoryMappingExist(contentMaster, contentCategoryMaster))
            {
                session = sessionFactory.openSession();
                Query query = session.createSQLQuery("INSERT INTO content_category_mapping (ccmp_cm_id, ccmp_ccm_id) VALUES (:ccmp_cm_id, :ccmp_ccm_id)");
                query.setParameter("ccmp_cm_id", contentMaster);
                query.setParameter("ccmp_ccm_id", contentCategoryMaster);
                if(query.executeUpdate()!=0)
                {
                    return "success";
                }
            }
            else
            {
                return "exist";
            }
        }
        catch(Exception ex)
        {
            //
            ex.printStackTrace();
        }
        finally
        {
            session.close();
        }
        return "failure";
    }
    
    //cHECKED...
    @Override
    public List<Object[]> readAll() {
        Session session = null;
        try
        {
            session = sessionFactory.openSession();
    //        Query query = session.createSQLQuery("SELECT ccmp_id, cm_title, ccm_title, ccmp_status FROM content_category_mapping, content_master, content_category_master WHERE ccmp_cm_id=cm_id AND ccmp_ccm_id=ccm_id AND cm_ccm_id=ccm_id");
            Query query = session.createSQLQuery("SELECT ccmp_id, cm_title, ccm_title, ccmp_status FROM content_category_mapping, content_master, content_category_master WHERE ccmp_cm_id=cm_id AND ccmp_ccm_id=ccm_id");
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
    
    private boolean contentCategoryMappingExist(int contentMaster, int contentCategoryMaster)
    {
        Session session = null;
        try
        {
            session = sessionFactory.openSession();
            Query query = session.createSQLQuery("SELECT * FROM content_category_mapping WHERE ccmp_cm_id=:ccmp_cm_id AND ccmp_ccm_id=:ccmp_ccm_id");
            query.setParameter("ccmp_cm_id", contentMaster);
            query.setParameter("ccmp_ccm_id", contentCategoryMaster);
    //        query.setParameter("um_password", password);
        if(query.list().size()!=0)
        {
            return true;
        }
        
        }
        catch(Exception ex)
        {
            
        }
        finally
        {
            session.close();
        }
        return false;
        
    }


    @Override
    public String updateStatus(int contentCategoryMappingId, int status) {
        Session session = null;
        try
        {
            session = sessionFactory.openSession();
//            session.beginTransaction();
            Query query = session.createSQLQuery("UPDATE content_category_mapping SET ccmp_status=:ccmp_status WHERE ccmp_id=:ccmp_id");
            query.setParameter("ccmp_status", status);
            query.setParameter("ccmp_id", contentCategoryMappingId);
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

    @Override
    public List<Object[]> readByUserId(int userId) {
        Session session = null;
                try
                {
            session = sessionFactory.openSession();
    //        Query query = session.createSQLQuery("SELECT ccmp_id, cm_title, ccm_title, ccmp_status FROM content_category_mapping, content_master, content_category_master WHERE ccmp_cm_id=cm_id AND ccmp_ccm_id=ccm_id AND cm_ccm_id=ccm_id");
            Query query = session.createSQLQuery("SELECT ccmp_id, cm_title, ccm_title, ccmp_status FROM content_category_mapping, content_master, content_category_master WHERE ccmp_cm_id=cm_id AND ccmp_ccm_id=ccm_id AND cm_um_id=:cm_um_id");
            query.setParameter("cm_um_id", userId);
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
