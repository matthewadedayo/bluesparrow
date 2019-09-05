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
public class FeatureMasterDaoImp implements FeatureMasterDao{
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    
    

    @Override
    public List<Object[]> readAll() {
//        Session session = sessionFactory.openSession();
//        Query query = session.createSQLQuery("SELECT fm_id, fm_question, fm_answer, fm_visibility, fm_publish_date, fm_create_date, fm_status FROM faqs_master");
//        return query.list();
        return null;
    }

    @Override
    public String create(int portalId, String featureText) {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("INSERT INTO feature_master (fm_cpm_id, fm_text) VALUES (:fm_cpm_id, :fm_text)");
        query.setParameter("fm_cpm_id", portalId);
        query.setParameter("fm_text", featureText);
        if(query.executeUpdate()!=0)
        {
            return "success";
        }
        return "failure";
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
//        Session session = sessionFactory.openSession();
//        Query query = session.createSQLQuery("SELECT fm_id, fm_question, fm_answer, fm_visibility, fm_publish_date, fm_create_date, fm_status FROM faqs_master WHERE (fm_id = :fm_id)");
//        query.setParameter("fm_id", id);
//        return (Object [])query.uniqueResult();
        return null;
    }

    @Override
    public String update(int faqsMasterId, String question, String answer, String contVisibility, String createDate, int status) {
//        Session session = sessionFactory.openSession();
//        Query query = session.createSQLQuery("UPDATE faqs_master SET fm_question=:fm_question, fm_answer=:fm_answer, fm_visibility=:fm_visibility, fm_create_date=:fm_create_date, fm_status=:fm_status WHERE (fm_id=:fm_id)");
//        query.setParameter("fm_question", question);
//        query.setParameter("fm_answer", answer);
//        query.setParameter("fm_visibility", contVisibility);
//        query.setParameter("fm_create_date", createDate);
//        query.setParameter("fm_status", status);
//        query.setParameter("fm_id", faqsMasterId);
//        if(query.executeUpdate()!=0)
//        {
//            return "success";
//        }
        return "failure";
    }

    @Override
    public List<Object[]> readAllByPortalId(int portalId) {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT fm_id, fm_cpm_id, cpm_title, fm_text, fm_status FROM feature_master, content_portal_master WHERE fm_cpm_id=:fm_cpm_id AND fm_cpm_id=cpm_id");
        query.setParameter("fm_cpm_id", portalId);
        return query.list();
    }

 

    

    

    


    
    
}
