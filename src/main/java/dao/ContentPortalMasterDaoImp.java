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
public class ContentPortalMasterDaoImp implements ContentPortalMasterDao{
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    //Update Begins
    
    //Checked...
    @Override
    public String create(String title, String desc) {
        try
        {
            if(!contentPortalMasterExist(title))
            {
                Session session = sessionFactory.openSession();
                Query query = session.createSQLQuery("INSERT INTO content_portal_master (cpm_title, cpm_desc) VALUES (:cpm_title, :cpm_desc)");
                query.setParameter("cpm_title", title);
                query.setParameter("cpm_desc", desc);
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
        return "failure";
    }
    
    //Checked...
    @Override
    public List<Object[]> readAll() {
        Session session = null;
        try
        {
            session = sessionFactory.openSession();
            Query query = session.createSQLQuery("SELECT cpm_id, cpm_title, cpm_desc, cpm_status FROM content_portal_master");
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
    public List<Object[]> readByStatus(int contentPortalStatus) {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT cpm_id, cpm_title, cpm_status FROM content_portal_master WHERE cpm_status=:cpm_status");
        query.setParameter("cpm_status", contentPortalStatus);
        return query.list();
    }
    
    @Override
    public String updateStatus(int contentPortalMasterId, int status) {
        try
        {
            Session session = sessionFactory.openSession();
//            session.beginTransaction();
            Query query = session.createSQLQuery("UPDATE content_portal_master SET cpm_status=:cpm_status WHERE cpm_id=:cpm_id");
            query.setParameter("cpm_status", status);
            query.setParameter("cpm_id", contentPortalMasterId);
            query.executeUpdate();
//            session.getTransaction().commit();
            return "success";
        }
        catch(Exception ex)
        {
            
        }
        
        return "failure";
    }
    
    @Override
    public Object[] readContentPortalMaster(int contentPortalMasterId) {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT cpm_id, cpm_title, cpm_desc FROM content_portal_master WHERE cpm_id=:cpm_id");
        query.setParameter("cpm_id", contentPortalMasterId);
        Object[] objects = (Object[]) query.uniqueResult();
        return objects;
    }
    //Checked...
    @Override
    public String update(int contentPortalMasterId, String title, String desc) {
        try
        {
            Session session = sessionFactory.openSession();
//            session.beginTransaction();
            Query query = session.createSQLQuery("UPDATE content_portal_master SET cpm_title=:cpm_title, cpm_desc=:cpm_desc WHERE cpm_id=:cpm_id");
            query.setParameter("cpm_title", title);
            query.setParameter("cpm_desc", desc);
            query.setParameter("cpm_id", contentPortalMasterId);
            query.executeUpdate();
//            session.getTransaction().commit();
            return "success";
        }
        catch(Exception ex)
        {
            
        }
        
        return "failure";
    }

    
    private boolean contentPortalMasterExist(String title)
    {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT * FROM content_portal_master WHERE cpm_title=:cpm_title");
        query.setParameter("cpm_title", title);
//        query.setParameter("ccm_local_title", localTitle);
//        query.setParameter("um_password", password);
        if(query.list().size()!=0)
        {
            return true;
        }
        return false;
        
    }

    
    
    
    
    
    
    
    
    
    
    
    
    //Update Ends
    
    //Checked...
    
    //Checked...
    
    

    
    

    


    
    
}
