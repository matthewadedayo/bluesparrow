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
public class HandsetProfileMasterDaoImp implements HandsetProfileMasterDao{
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    
    
    //Checked...
    @Override
    public List<Object[]> readAll() {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT hpm_id, hpm_user_agent, hpm_width_height, hpm_make, hpm_model, hpm_os, hpm_os_version FROM handset_profile_master");
        return query.list();
    }

    @Override
    public String updateStatus(int contentCategoryMasterId, int status) {
        try
        {
            Session session = sessionFactory.openSession();
//            session.beginTransaction();
            Query query = session.createSQLQuery("UPDATE content_category_master SET ccm_status=:ccm_status WHERE ccm_id=:ccm_id");
            query.setParameter("ccm_status", status);
            query.setParameter("ccm_id", contentCategoryMasterId);
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
    public String create(String userAgent, String make, String handsetModel, String os, String osVersion, String widthHeight) {
        try
        {
            Session session = sessionFactory.openSession();
            Query query = session.createSQLQuery("INSERT INTO handset_profile_master (hpm_user_agent, hpm_width_height, hpm_make, hpm_model, hpm_os, hpm_os_version) VALUES(:hpm_user_agent, :hpm_width_height, :hpm_make, :hpm_model, :hpm_os, :hpm_os_version)");
            query.setParameter("hpm_user_agent", userAgent);
            query.setParameter("hpm_width_height", widthHeight);
            query.setParameter("hpm_make", make);
            query.setParameter("hpm_model", handsetModel);
            query.setParameter("hpm_os", os);
            query.setParameter("hpm_os_version", osVersion);
            query.executeUpdate();
            return "success";
        }
        catch(Exception ex)
        {
            //
        }
        return "failure";
    }

    //Checked...
    @Override
    public Object[] readHandsetProfileMaster(int handsetProfileManagerId) {
        
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT hpm_id, hpm_user_agent, hpm_width_height, hpm_make, hpm_model, hpm_os, hpm_os_version FROM handset_profile_master WHERE hpm_id=:hpm_id");
        query.setParameter("hpm_id", handsetProfileManagerId);
        Object[] objects = (Object[]) query.uniqueResult();
        return objects;
        
        
    }

    //Checked...
    @Override
    public String update(int handsetProfileMasterId, String userAgent, String make, String handsetModel, String os, String osVersion, String widthHeight) {
        try
        {
            Session session = sessionFactory.openSession();
//            session.beginTransaction();
            Query query = session.createSQLQuery("UPDATE handset_profile_master SET hpm_user_agent=:hpm_user_agent, hpm_width_height=:hpm_width_height, hpm_make=:hpm_make, hpm_model=:hpm_model, hpm_os=:hpm_os, hpm_os_version=:hpm_os_version WHERE hpm_id=:hpm_id");
            query.setParameter("hpm_user_agent", userAgent);
            query.setParameter("hpm_width_height", widthHeight);
            query.setParameter("hpm_make", make);
            query.setParameter("hpm_model", handsetModel);
            query.setParameter("hpm_os", os);
            query.setParameter("hpm_os_version", osVersion);
            query.setParameter("hpm_id", handsetProfileMasterId);
            query.executeUpdate();
//            session.getTransaction().commit();
            return "success";
        }
        catch(Exception ex)
        {
            
        }
        
        return "failure";
    }

    

    

    


    
    
}
