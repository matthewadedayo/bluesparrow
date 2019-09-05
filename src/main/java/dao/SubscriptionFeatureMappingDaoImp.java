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
public class SubscriptionFeatureMappingDaoImp implements SubscriptionFeatureMappingDao{
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    
    

    @Override
    public List<Object[]> readAll() {
//        Session session = sessionFactory.openSession();
//        Query query = session.createSQLQuery("SELECT sm_id, sm_title, cpm_title, sm_fm_id, sm_package, sm_price, sm_status FROM subscription_master, content_portal_master WHERE sm_cpm_id=cpm_id");
//        return query.list();
return null;
        
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
//        Query query = session.createSQLQuery("SELECT sm_id, sm_title, sm_cpm_id, sm_fm_id, sm_package, sm_price, sm_status FROM subscription_master WHERE (sm_id = :sm_id)");
//        query.setParameter("sm_id", id);
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
//        Session session = sessionFactory.openSession();
//        Query query = session.createSQLQuery("SELECT fm_id, fm_cpm_id, cpm_title, fm_text, fm_status FROM feature_master, content_portal_master WHERE fm_cpm_id=:fm_cpm_id AND fm_cpm_id=cpm_id");
//        query.setParameter("fm_cpm_id", portalId);
//        return query.list();
return null;
    }

    @Override
    public String create(int subscriptionId, int featureId) {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("INSERT INTO subscription_feature_mapping (sfm_sm_id, sfm_fm_id) VALUES (:sfm_sm_id, :sfm_fm_id)");
        query.setParameter("sfm_sm_id", subscriptionId);
        query.setParameter("sfm_fm_id", featureId);
        
        if(query.executeUpdate()!=0)
        {
            return "success";
        }
        return "failure";
    }

    @Override
    public List<Object[]> readAllBySubscriptionId(int subscriptionId) {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT sfm_id, sfm_sm_id, sfm_fm_id, fm_text, sfm_status FROM subscription_feature_mapping, feature_master WHERE sfm_sm_id=:sfm_sm_id AND sfm_fm_id=fm_id");
        query.setParameter("sfm_sm_id", subscriptionId);
        return query.list();
    }

    @Override
    public String removeAllBySubscriptionId(int subscriptionId) {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("DELETE FROM subscription_feature_mapping WHERE sfm_sm_id=:sfm_sm_id");
        query.setParameter("sfm_sm_id", subscriptionId);
        query.executeUpdate();
        return "success";
    }

 

    

    

    


    
    
}
