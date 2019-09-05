/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author OLAWALE
 */
public class UserMasterDaoImp implements UserMasterDao{
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    

    //Checked....
    @Override
    public Object[] login(String username, String password) {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT um_id, um_first_name, um_last_name, um_password, um_status, um_type FROM user_master WHERE um_user_name=:username AND um_password=:password AND um_status=:um_status");
        query.setParameter("username", username);
        query.setParameter("password", password);
        query.setParameter("um_status", 1);
        Object[] user  =  (Object[]) query.uniqueResult();
//        if(user!=null)
//        {
//            return "success";
//        }
//         return "failure";
        return user;
    }

    //Checked....
    @Override
    public List<Object[]> readAll() {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT um_id, um_user_name, um_password, um_first_name, um_last_name, um_status, um_type, um_company, um_designation, um_email, um_phone_number FROM user_master");
        List<Object[]> objects = query.list();
        return objects;
        
    }

    //Checked...
    @Override
    public String create(String username, String password, String firstName, String lastName, String type, String company, String  designation, String email, String phoneNumber) {
        try
        {
            if(!userExist(username))
            {
                Session session = sessionFactory.openSession();
                Query query = session.createSQLQuery("INSERT INTO user_master (um_user_name, um_password, um_first_name, um_last_name, um_type, um_company, um_designation, um_email, um_phone_number) VALUES(:um_user_name, :um_password, :um_first_name, :um_last_name, :um_type, :um_company, :um_designation, :um_email, :um_phone_number)");
                query.setParameter("um_user_name", username);
                query.setParameter("um_password", password);
                query.setParameter("um_first_name", firstName);
                query.setParameter("um_last_name", lastName);
                query.setParameter("um_type", type);
                
                query.setParameter("um_company", company);
                query.setParameter("um_designation", designation);
                query.setParameter("um_email", email);
                query.setParameter("um_phone_number", phoneNumber);
                query.executeUpdate();
                return "success";
            }
            else
            {
                return "exist";
            }
        }
        catch(Exception ex)
        {
            //
        }
        return "failure";
    }

    private boolean userExist(String username)
    {
        Session session = null;
        try
        {
            session = sessionFactory.openSession();
            Query query = session.createSQLQuery("SELECT * FROM user_master WHERE um_user_name=:um_user_name");
            query.setParameter("um_user_name", username);
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
    
    //Checked...
    @Override
    public String updateStatus(int userId, int status) {
        try
        {
            Session session = sessionFactory.openSession();
//            session.beginTransaction();
            Query query = session.createSQLQuery("UPDATE user_master SET um_status=:um_status WHERE um_id=:um_id");
            query.setParameter("um_status", status);
            query.setParameter("um_id", userId);
            query.executeUpdate();
//            session.getTransaction().commit();
            return "success";
        }
        catch(Exception ex)
        {
            
        }
        
        return "failure";
        
    }

    //Checked....
    @Override
    public Object[] readUser(int id) {
        Session session = null;
        try
        {
            session = sessionFactory.openSession();
            Query query = session.createSQLQuery("SELECT um_id, um_user_name, um_first_name, um_last_name, um_password, um_type, um_company, um_designation, um_email, um_phone_number FROM user_master WHERE um_id=:um_id");
            query.setParameter("um_id", id);
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

    //Checked...
    @Override
    public String update(int userId, String username, String password, String firstName, String lastName, String type, String company, String designation, String email, String phoneNumber) {
        Session session = null;
        try
        {
            session = sessionFactory.openSession();
            System.out.println("id: " + userId);
//            session.beginTransaction();
            Query query = session.createSQLQuery("UPDATE user_master SET um_user_name=:um_user_name, um_password=:um_password, um_first_name=:um_first_name, um_last_name=:um_last_name, um_type=:um_type, um_company=:um_company, um_designation=:um_designation, um_email=:um_email, um_phone_number=:um_phone_number  WHERE um_id=:um_id");
            query.setParameter("um_user_name", username);
            query.setParameter("um_password", password);
            query.setParameter("um_first_name", firstName);
            query.setParameter("um_last_name", lastName);
            query.setParameter("um_type", type);
            query.setParameter("um_company", company);
            query.setParameter("um_designation", designation);
            query.setParameter("um_email", email);
            query.setParameter("um_phone_number", phoneNumber);
            query.setParameter("um_id", userId);
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
    public List<Object[]> readByType(String userType) {
        Session session = null;
        try
        {
            session = sessionFactory.openSession();
            Query query = session.createSQLQuery("SELECT um_id, um_user_name, um_first_name, um_last_name, um_password, um_type FROM user_master WHERE um_type=:um_type");
            query.setParameter("um_type", userType);
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
    public List<Object[]> readAllFromContentMaster() {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT DISTINCT(um_user_name), um_id, um_password, um_first_name, um_last_name, um_status, um_type, um_company, um_designation, um_email, um_phone_number FROM content_master, user_master WHERE cm_um_id=um_id");
        List<Object[]> objects = query.list();
        return objects;
    }


    
    
}
