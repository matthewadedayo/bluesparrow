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
public class ContentMetaMasterDaoImp implements ContentMetaMasterDao{

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
    
    @Override
    public List<Object[]> readAll() {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT cmm_id, cmm_title, cmm_status FROM content_meta_master");
        
        return query.list();
    }
    
}
