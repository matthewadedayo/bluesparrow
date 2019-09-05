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
public class MenuMasterDaoImp implements MenuMasterDao{
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    

    @Override
    public String login(String username, String password) {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT * FROM user_master WHERE um_user_name=:username AND um_password=:password");
        query.setParameter("username", username);
        query.setParameter("password", password);
        Object user =  query.uniqueResult();
        
        if(user!=null)
        {
            return "success";
        }
        return "failure";
    }
    //Checked...
    @Override
    public List<Object[]> readAll() {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT mm_id, mm_title, mm_url, mm_order, mm_status FROM menu_master_v2");
        return query.list();
    }

    //Checked.....
    @Override
    public List<Object[]> readUserMenuMaster(int userMasterId) {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT DISTINCT(mm_id), mm_title, mm_url FROM menu_master_v2, menu_mapping WHERE mp_mm_id=mm_id AND mp_um_id=:mp_um_id AND mp_status=:mp_status AND mm_status=:mm_status ORDER BY mm_order ASC");
        query.setParameter("mp_um_id", userMasterId);
        query.setParameter("mp_status", 1);
        query.setParameter("mm_status", 1);
        return query.list();
    }
    //Checked...
    @Override
    public String create(String title, String url) {
        try
        {
            if(!menuExist(title))
            {
            Session session = sessionFactory.openSession();
            Query query = session.createSQLQuery("INSERT INTO menu_master_v2 (mm_title, mm_url) VALUES(:mm_title, :mm_url)");
            query.setParameter("mm_title", title);
            query.setParameter("mm_url", url);
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

    private boolean menuExist(String title)
    {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT * FROM menu_master_v2 WHERE mm_title=:mm_title");
        query.setParameter("mm_title", title);
//        query.setParameter("um_password", password);
        if(query.uniqueResult()!=null)
        {
            return true;
        }
        return false;
        
    }
    
    //Checked...
    @Override
    public String updateStatus(int menuId, int status) {
        try
        {
            Session session = sessionFactory.openSession();
//            session.beginTransaction();
            Query query = session.createSQLQuery("UPDATE menu_master_v2 SET mm_status=:mm_status WHERE mm_id=:mm_id");
            query.setParameter("mm_status", status);
            query.setParameter("mm_id", menuId);
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
    public Object[] readMenu(int menuMasterId) {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT mm_id, mm_title, mm_url FROM menu_master_v2 WHERE mm_id=:mm_id");
        query.setParameter("mm_id", menuMasterId);
        Object[] objects = (Object[]) query.uniqueResult();
        return objects;
    }
    //Checked...
    @Override
    public String update(int menuMasterId, String title, String url) {
        try
        {
            Session session = sessionFactory.openSession();
//            session.beginTransaction();
            Query query = session.createSQLQuery("UPDATE menu_master_v2 SET mm_title=:mm_title, mm_url=:mm_url WHERE mm_id=:mm_id");
            query.setParameter("mm_title", title);
            query.setParameter("mm_url", url);
            query.setParameter("mm_id", menuMasterId);
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
    public List<Object[]> readByStatus(int menuMasterStatus) {
        
        Session session=null;
        try
        {
            session = sessionFactory.openSession();
            Query query = session.createSQLQuery("SELECT mm_id, mm_title, mm_url, mm_order, mm_status FROM menu_master_v2 WHERE mm_status=:mm_status");
            query.setParameter("mm_status", menuMasterStatus);
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


    
    
}
