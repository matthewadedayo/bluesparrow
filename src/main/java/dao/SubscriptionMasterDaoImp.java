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
public class SubscriptionMasterDaoImp implements SubscriptionMasterDao{
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    
    

    @Override
    public List<Object[]> readAll() {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT sm_id, sm_title, cpm_title, sm_package, sm_price, sm_currency, sm_status FROM subscription_master, content_portal_master WHERE sm_cpm_id=cpm_id");
        return query.list();
        
    }

    
    

    @Override
    public Object[] readByContent(String fQuestion, String fAnswer, String createDate) {
//        Session session = sessionFactory.openSession();
//        Query query = session.createSQLQuery("SELECT fm_id, fm_question, fm_answer FROM faqs_master WHERE (fm_question = :fm_question AND fm_answer = :fm_answer AND fm_create_date=:fm_create_date)");
//        query.setParameter("fm_question", fQuestion);
//        query.setParameter("fm_answer", fAnswer);
//        query.setParameter("fm_create_date", createDate);
//        return (Object [])query.uniqueResult();
        return null;
        
    }

    @Override
    public Object[] read(int id) {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT sm_id, sm_title, sm_cpm_id, sm_package, sm_price, cpm_title, sm_currency, sm_status FROM subscription_master, content_portal_master WHERE (sm_id = :sm_id AND sm_cpm_id=cpm_id)");
        query.setParameter("sm_id", id);
        return (Object [])query.uniqueResult();
        
    }

    

    @Override
    public List<Object[]> readAllByPortalId(int portalId) {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT fm_id, fm_cpm_id, cpm_title, fm_text, fm_status FROM feature_master, content_portal_master WHERE fm_cpm_id=:fm_cpm_id AND fm_cpm_id=cpm_id");
        query.setParameter("fm_cpm_id", portalId);
        return query.list();
    }

    @Override
    public String create(int portalId, String subName, String subPrice, String subPackage, String subCurrency) {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("INSERT INTO subscription_master (sm_title, sm_cpm_id, sm_package, sm_price, sm_currency) VALUES (:sm_title, :sm_cpm_id, :sm_package, :sm_price, :sm_currency)");
        query.setParameter("sm_title", subName);
        query.setParameter("sm_cpm_id", portalId);
        query.setParameter("sm_package", subPackage);
        query.setParameter("sm_price", subPrice);
        query.setParameter("sm_currency", subCurrency);
        
        if(query.executeUpdate()!=0)
        {
            return "success";
        }
        return "failure";
    }

    @Override
    public Object[] readByAll(int portalId, String subName, String subPrice, String subPackage) {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT sm_id, sm_title, sm_cpm_id, sm_package, sm_price, sm_status FROM subscription_master, content_portal_master WHERE sm_title=:sm_title AND sm_cpm_id=:sm_cpm_id AND sm_package=:sm_package AND sm_price=:sm_price");
        query.setParameter("sm_title", subName);
        query.setParameter("sm_cpm_id", portalId);
        query.setParameter("sm_package", subPackage);
        query.setParameter("sm_price", subPrice);
        List<Object[]> list =  query.list();
        if(list!=null)
        {
            return list.get(0);
        }
        return null;
    }

    @Override
    public String update(int subId, int portalId, String subName, String subPrice, String subPackage, String subCurrency) {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("UPDATE subscription_master SET sm_title=:sm_title, sm_cpm_id=:sm_cpm_id, sm_package=:sm_package, sm_price=:sm_price, sm_currency=:sm_currency WHERE sm_id=:sm_id");
        query.setParameter("sm_title", subName);
        query.setParameter("sm_cpm_id", portalId);
        query.setParameter("sm_package", subPackage);
        query.setParameter("sm_price", subPrice);
        query.setParameter("sm_currency", subCurrency);
        query.setParameter("sm_id", subId);
        if(query.executeUpdate()!=0)
        {
            return "success";
        }
        
        return "failure";
    }

 

    

    

    


    
    
}
