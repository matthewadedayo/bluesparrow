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
public class ContentMetaDetailsDaoImp implements ContentMetaDetailsDao{
    
    private SessionFactory sessionFactory;

    //Update Begins...
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public String updateStatus(int contentMetaDetailsId, int status) {
        try
        {
            Session session = sessionFactory.openSession();
//            session.beginTransaction();
            Query query = session.createSQLQuery("UPDATE content_meta_details SET cmd_status=:cmd_status WHERE cmd_id=:cmd_id");
            query.setParameter("cmd_status", status);
            query.setParameter("cmd_id", contentMetaDetailsId);
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
    public List<Object[]> readByContentMaster(int contentMasterId) {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT cmd_id, cm_title, cmd_cmm_title, cmd_value, cmd_status FROM content_meta_details, content_master WHERE cmd_cm_id=:cmd_cm_id AND cmd_cm_id=cm_id AND cmd_status=1");
        query.setParameter("cmd_cm_id", contentMasterId);
        List<Object[]> objects = (List<Object[]>) query.list();
        return objects;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    //Update Ends...
    @Override
    public List<Object[]> readAll() {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT cmd_id, cm_title, cmd_cmm_title, cmd_value, cmd_status FROM content_meta_details, content_master WHERE cmd_cm_id=cm_id");
//        query.setParameter("cmd_cm_id", contentMasterId);
        List<Object[]> objects = (List<Object[]>) query.list();
        return objects;
    }

    @Override
    public String create(int contentMasterId, String title, String value) {
        try
        {
            Session session = sessionFactory.openSession();
            if(!contentMetaDetailExist(contentMasterId, title))
            {
                
                Query query = session.createSQLQuery("INSERT INTO content_meta_details (cmd_cm_id, cmd_cmm_title, cmd_value) VALUES (:cmd_cm_id, :cmd_cmm_title, :cmd_value)");
                query.setParameter("cmd_cm_id", contentMasterId);
                query.setParameter("cmd_cmm_title", title);
                query.setParameter("cmd_value", value);
                if(query.executeUpdate()!=0)
                {
                    return "success";
                }
            }
            else
            {
//                Query query = session.createSQLQuery("UPDATE content_meta_details SET cmd_value=:cmd_value WHERE (cmd_cmm_title=:cmd_cmm_title AND cmd_cm_id=:cmd_cm_id)");
//                query.setParameter("cmd_cm_id", contentMasterId);
//                query.setParameter("cmd_cmm_title", title);
//                query.setParameter("cmd_value", value);
//                if(query.executeUpdate()!=0)
//                {
//                    return "change";
//                }
                return "exist";
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return "failure";
    }

    
    
    private boolean contentMetaDetailExist(int contentMasterId, String title)
    {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT * FROM content_meta_details WHERE cmd_cmm_title=:cmd_cmm_title AND cmd_cm_id=:cmd_cm_id");
        query.setParameter("cmd_cmm_title", title);
        query.setParameter("cmd_cm_id", contentMasterId);
//        query.setParameter("um_password", password);
        if(query.list().size()!=0)
        {
            return true;
        }
        return false;
        
    }

    @Override
    public String update(int contentMetaDetailId, String contentMetaMasterTitle, String contentMstDetailValue) {
        try
        {
            Session session = sessionFactory.openSession();
//            session.beginTransaction();
//            Query query = session.createSQLQuery("UPDATE content_meta_details SET cmd_cmm_title=:cmd_cmm_title, cmd_value=:cmd_value WHERE cmd_id=:cmd_id");
            Query query = session.createSQLQuery("UPDATE content_meta_details SET cmd_value=:cmd_value WHERE cmd_id=:cmd_id");
//            query.setParameter("cmd_cmm_title", contentMetaMasterTitle);
            query.setParameter("cmd_value", contentMstDetailValue);
            query.setParameter("cmd_id", contentMetaDetailId);
            query.executeUpdate();
//            session.getTransaction().commit();
            return "success";
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        
        return "failure";
    }

    @Override
    public Object[] readByContentMetaDetail(int contentMetaDetailId, int contentMasterId) {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT cmd_id, cmd_cmm_title, cmd_value, cmd_status FROM content_meta_details WHERE cmd_id=:cmd_id AND cmd_cm_id=:cmd_cm_id");
        query.setParameter("cmd_id", contentMetaDetailId);
        query.setParameter("cmd_cm_id", contentMasterId);
        Object[] objects = (Object[]) query.uniqueResult();
        return objects;
    }

    
    
}
