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
public class ContentFileDaoImp implements ContentFileDao{
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    

    @Override
    public List<Object[]> readAll() {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT cf_id, cm_title, cf_file, cf_width_height, cf_os_name, cf_os_version, cf_status FROM content_files, content_master WHERE cf_cm_id=cm_id");
        
        return query.list();
    }

    //Checked...
    @Override
    public String create(int contentMasterId, String fileName, String widthHeight, String osName, String osVersion) {
        try
        {
            Session session = sessionFactory.openSession();
            Query query = session.createSQLQuery("INSERT INTO content_files (cf_cm_id, cf_file, cf_width_height, cf_os_name, cf_os_version) VALUES(:cf_cm_id, :cf_file, :cf_width_height, :cf_os_name, :cf_os_version)");
            query.setParameter("cf_cm_id", contentMasterId);
            query.setParameter("cf_file", fileName);
            query.setParameter("cf_width_height", widthHeight);
            query.setParameter("cf_os_name", osName);
            query.setParameter("cf_os_version", osVersion);
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
    public String updateStatus(int contentFileId, int status) {
        try
        {
            Session session = sessionFactory.openSession();
//            session.beginTransaction();
            Query query = session.createSQLQuery("UPDATE content_files SET cf_status=:cf_status WHERE cf_id=:cf_id");
            query.setParameter("cf_status", status);
            query.setParameter("cf_id", contentFileId);
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
    public Object[] readContentFiles(int contentFileId) {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT cf_id, cm_title, cf_file, cf_width_height, cf_os_name, cf_os_version FROM content_files, content_master WHERE cf_id=:cf_id AND cf_cm_id=cm_id");
        query.setParameter("cf_id", contentFileId);
        Object[] objects = (Object[]) query.uniqueResult();
        return objects;
    }
    //Checked...
    @Override
    public String update(int contentFileId, int contentMasterId, String fullPath, String widthHeight, String osName, String osVersion) {
        try
        {
            Session session = sessionFactory.openSession();
//            session.beginTransaction();
            Query query = session.createSQLQuery("UPDATE content_files SET cf_cm_id=:cf_cm_id, cf_file=:cf_file, cf_width_height=:cf_width_height, cf_os_name=:cf_os_name, cf_os_version=:cf_os_version WHERE cf_id=:cf_id");
            query.setParameter("cf_cm_id", contentMasterId);
            query.setParameter("cf_file", fullPath);
            query.setParameter("cf_width_height", widthHeight);
            query.setParameter("cf_os_name", osName);
            query.setParameter("cf_os_version", osVersion);
            
            query.setParameter("cf_id", contentFileId);
            query.executeUpdate();
//            session.getTransaction().commit();
            return "success";
        }
        catch(Exception ex)
        {
            System.out.println("Error Occur");
            ex.printStackTrace();
        }
        
        return "failure";
    }

    //Checked...
    @Override
    public List<Object[]> readByContentMasterId(int contentMasterId) {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT cf_id, cm_title, cf_file, cf_width_height, cf_os_name, cf_os_version, cf_status FROM content_files, content_master WHERE cf_cm_id=:cf_cm_id AND cf_cm_id=cm_id AND cf_status=1");
        query.setParameter("cf_cm_id", contentMasterId);
//        query.setParameter("cf_status", 1);
        return query.list();
    }

    

    


    
    
}
