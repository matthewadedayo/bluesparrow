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
public class HistoryDaoImp implements HistoryDao{
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    
    

    @Override
    public List<Object[]> readAll() {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT ccmp_id, cm_title, ccm_title, ccmp_status FROM content_category_mapping, content_master, content_category_master WHERE ccmp_cm_id=cm_id AND ccmp_ccm_id=ccm_id AND cm_ccm_id=ccm_id");
        return query.list();
    }

    @Override
    public String create(String userId, String remoteAddr, String loginTime) {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("INSERT INTO history (user_id, user_ip, login_time) VALUES (:user_id, :user_ip, :login_time)");
        query.setParameter("user_id", userId);
        query.setParameter("user_ip", remoteAddr);
        query.setParameter("login_time", loginTime);
        if(query.executeUpdate()!=0)
        {
            return "success";
        }
        return "failure";
    }

 

    

    

    


    
    
}
