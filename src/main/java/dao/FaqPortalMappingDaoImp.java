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
public class FaqPortalMappingDaoImp implements FaqPortalMappingDao{
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    
    

    @Override
    public List<Object[]> readAll() {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT fm_id, fm_question, fm_answer, fm_visibility, fm_publish_date, fm_create_date, fm_status FROM faqs_master");
        return query.list();
    }

    

    @Override
    public String create(int portalId, int faqsId) {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("INSERT INTO faq_portal_mapping (fpm_cpm_id, fpm_fm_id) VALUES (:fpm_cpm_id, :fpm_fm_id)");
        query.setParameter("fpm_cpm_id", portalId);
        query.setParameter("fpm_fm_id", faqsId);
        if(query.executeUpdate()!=0)
        {
            return "success";
        }
        return "failure";
    }

    @Override
    public List<Object[]> readByFaqsMasterId(int faqsMasterId) {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT fpm_id, fpm_cpm_id, fpm_fm_id, fpm_status FROM faq_portal_mapping WHERE fpm_fm_id=:fpm_fm_id");
        query.setParameter("fpm_fm_id", faqsMasterId);
        return query.list();
    }

    @Override
    public List<Object> readPortalIdByFaqsMasterId(int faqsMasterId) {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT fpm_cpm_id FROM faq_portal_mapping WHERE fpm_fm_id=:fpm_fm_id");
        query.setParameter("fpm_fm_id", faqsMasterId);
        return query.list();
    }

    @Override
    public String remove(int faqsMasterId) {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("DELETE FROM faq_portal_mapping WHERE fpm_fm_id=:fpm_fm_id");
        query.setParameter("fpm_fm_id", faqsMasterId);
        int row = query.executeUpdate();
        System.out.println("Removed");
        return "success";
    }

 

    

    

    


    
    
}
