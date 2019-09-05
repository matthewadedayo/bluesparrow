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
public class MenuChildDaoImp implements MenuChildDao{
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    
    //Checked...
    @Override
    public List<Object[]> readAll() {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT mc_id, mm_title, mc_title, mc_url, mc_order, mc_status, mc_mm_id FROM menu_child_v2, menu_master_v2 WHERE mc_mm_id=mm_id");
        return query.list();
    }

    //Checked....
    @Override
    public List<Object[]> readUserMenuChild(int userMasterId, int menuMasterId) {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT mc_id, mc_title, mc_url FROM menu_child_v2, menu_mapping WHERE mp_mc_id=mc_id AND mp_um_id=:mp_um_id AND mc_mm_id=:mc_mm_id AND mp_status=:mp_status AND mc_status=:mc_status ORDER BY mc_order ASC");
        query.setParameter("mp_um_id", userMasterId);
        query.setParameter("mc_mm_id", menuMasterId);
        query.setParameter("mp_status", 1);
        query.setParameter("mc_status", 1);
        return query.list();
    }
    //Checked...
    @Override
    public String create(int menuId, String title, String url) {
        try
        {
            if(!menuChildExist(menuId, title))
            {
                Session session = sessionFactory.openSession();
                Query query = session.createSQLQuery("INSERT INTO menu_child_v2 (mc_mm_id, mc_title, mc_url) VALUES(:mc_mm_id, :mc_title, :mc_url)");
                query.setParameter("mc_mm_id", menuId);
                query.setParameter("mc_title", title);
                query.setParameter("mc_url", url);
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

    private boolean menuChildExist(int menuId, String title)
    {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT * FROM menu_child_v2 WHERE mc_mm_id=:mc_mm_id AND mc_title=:mc_title");
        query.setParameter("mc_mm_id", menuId);
        query.setParameter("mc_title", title);
//        query.setParameter("um_password", password);
        if(query.uniqueResult()!=null)
        {
            return true;
        }
        return false;
        
    }
    
    //Checked...
    @Override
    public String updateStatus(int menuChildId, int status) {
        try
        {
            Session session = sessionFactory.openSession();
//            session.beginTransaction();
            Query query = session.createSQLQuery("UPDATE menu_child_v2 SET mc_status=:mc_status WHERE mc_id=:mc_id");
            query.setParameter("mc_status", status);
            query.setParameter("mc_id", menuChildId);
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
    public List<Object[]> readMenuChildMenus(int menuId) {
        Session session = sessionFactory.openSession();
//        Query query = session.createSQLQuery("SELECT mc_id, mm_title, mc_title, mc_url FROM menu_child_v2, menu_master_v2 WHERE mc_mm_id=:mc_mm_id AND mc_mm_id=mm_id  ORDER BY mc_order ASC");
        Query query = session.createSQLQuery("SELECT mc_id, mm_title, mc_title, mc_url FROM menu_child_v2, menu_master_v2 WHERE mc_mm_id=:mc_mm_id AND mc_mm_id=mm_id ORDER BY mc_order ASC");
        query.setParameter("mc_mm_id", menuId);
        return query.list();
    }
    //Checked...
    @Override
    public Object[] readMenuChild(int menuChildId) {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT mc_id, mm_title, mc_title, mc_url FROM menu_child_v2, menu_master_v2 WHERE mc_id=:mc_id AND mc_mm_id=mm_id");
        query.setParameter("mc_id", menuChildId);
        Object[] objects = (Object[]) query.uniqueResult();
        return objects;
    }
    //Checked...
    @Override
    public String update(int menuChildId, String title, String url) {
        try
        {
            Session session = sessionFactory.openSession();
//            session.beginTransaction();
            Query query = session.createSQLQuery("UPDATE menu_child_v2 SET mc_title=:mc_title, mc_url=:mc_url WHERE mc_id=:mc_id");
            query.setParameter("mc_title", title);
            query.setParameter("mc_url", url);
            query.setParameter("mc_id", menuChildId);
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
    public List<Object[]> readByMenu(int menuId) {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT mc_id, mm_title, mc_title, mc_url, mc_order, mc_status FROM menu_child_v2, menu_master_v2 WHERE mc_mm_id=mm_id AND mc_mm_id=:mc_mm_id");
        query.setParameter("mc_mm_id", menuId);
        return query.list();
    }

    @Override
    public List<Object[]> readByStatus(int status) {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT mc_id, mm_title, mc_title, mc_url, mc_order, mc_status FROM menu_child_v2, menu_master_v2 WHERE mc_mm_id=mm_id AND mc_status=:mc_status");
        query.setParameter("mc_status", status);
        return query.list();
    }

    @Override
    public List<Object[]> readByMenuStatus(int menuId, int menuChildMasterStatus) {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT mc_id, mm_title, mc_title, mc_url, mc_order, mc_status FROM menu_child_v2, menu_master_v2 WHERE mc_mm_id=mm_id AND mc_mm_id=:mc_mm_id AND mc_status=:mc_status");
        query.setParameter("mc_mm_id", menuId);
        query.setParameter("mc_status", menuChildMasterStatus);
        return query.list();
    }

    

    


    
    
}
