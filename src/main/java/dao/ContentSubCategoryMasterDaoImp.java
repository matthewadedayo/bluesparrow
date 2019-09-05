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
import org.hibernate.transform.Transformers;

/**
 *
 * @author OLAWALE
 */
public class ContentSubCategoryMasterDaoImp implements ContentSubCategoryMasterDao{
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public String create(String title, String localTitle, int contentCategoryId) {
        try
        {
            if(!contentCategoryExist(title, contentCategoryId))
            {
                Session session = sessionFactory.openSession();
                Query query = session.createSQLQuery("INSERT INTO content_sub_category_master (cscm_title, cscm_local_title, cscm_ccm_id) VALUES (:cscm_title, :cscm_local_title, :ccm_ccm_id)");
                query.setParameter("cscm_title", title);
                query.setParameter("cscm_local_title", localTitle);
                query.setParameter("ccm_ccm_id", contentCategoryId);
                if(query.executeUpdate()!=0)
                {
                    return "success";
                }
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
    
    private boolean contentCategoryExist(String title, int contentCategoryId)
    {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT * FROM content_sub_category_master WHERE cscm_title=:cscm_title AND cscm_ccm_id=:ccm_id");
        query.setParameter("cscm_title", title);
        query.setParameter("ccm_id", contentCategoryId);
//        query.setParameter("um_password", password);
        if(query.list().size()!=0)
        {
            return true;
        }
        return false;
        
    }

    //Checked...
    @Override
    public List<Object[]> readAll() {
        Session session = sessionFactory.openSession();
//        Query query = session.createSQLQuery("SELECT cscm_id, cscm_ccm_id, cscm_title, cscm_local_title, cscm_status FROM content_sub_category_master");
        Query query = session.createSQLQuery("SELECT cscm_id, ctm_title, ccm_title, ccm_local_title, cscm_title, cscm_local_title, cscm_status FROM content_sub_category_master, content_category_master, content_type_master WHERE cscm_ccm_id=ccm_id AND ccm_ctm_id=ctm_id");
        System.out.println(query.list().size());
        return query.list();
    }

    @Override
    public String updateStatus(int subCategoryMasterId, int status) {
        try
        {
            Session session = sessionFactory.openSession();
//            session.beginTransaction();
            Query query = session.createSQLQuery("UPDATE content_sub_category_master SET cscm_status=:cscm_status WHERE cscm_id=:cscm_id");
            query.setParameter("cscm_status", status);
            query.setParameter("cscm_id", subCategoryMasterId);
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
    public Object[] readByContentSubCategoryMaster(int contentSubCategoryMasterId) {
        Session session = sessionFactory.openSession();
//        Query query = session.createSQLQuery("SELECT ccm_id, ccm_title, ccm_local_title, ccm_ctm_id FROM content_category_master WHERE ccm_id=:ccm_id");
        Query query = session.createSQLQuery("SELECT cscm_id, cscm_title, cscm_local_title, cscm_status FROM content_sub_category_master WHERE cscm_id=:cscm_id");
        query.setParameter("cscm_id", contentSubCategoryMasterId);
        Object[] objects = (Object[]) query.uniqueResult();
        return objects;
    }
    
    
    //Checked...
    @Override
    public Object[] readContentSubCategoryMaster(int contentSubCategoryMasterId) {
        Session session = sessionFactory.openSession();
//        Query query = session.createSQLQuery("SELECT ccm_id, ccm_title, ccm_local_title, ccm_ctm_id FROM content_category_master WHERE ccm_id=:ccm_id");
        Query query = session.createSQLQuery("SELECT cscm_id, ctm_title, ccm_title, ccm_local_title, cscm_title, cscm_local_title, cscm_status FROM content_sub_category_master, content_category_master, content_type_master WHERE cscm_id=:cscm_id AND cscm_ccm_id=ccm_id AND ccm_ctm_id=ctm_id");
        query.setParameter("cscm_id", contentSubCategoryMasterId);
        Object[] objects = (Object[]) query.uniqueResult();
        return objects;
    }

    //Checked...
    @Override
    public String update(int contentSubCategoryMasterId, String title, String localTitle) {
        try
        {
            Session session = sessionFactory.openSession();
//            session.beginTransaction();
            Query query = session.createSQLQuery("UPDATE content_sub_category_master SET cscm_title=:cscm_title, cscm_local_title=:cscm_local_title WHERE cscm_id=:cscm_id");
            query.setParameter("cscm_title", title);
            query.setParameter("cscm_local_title", localTitle);
            query.setParameter("cscm_id", contentSubCategoryMasterId);
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
    public List<Object[]> readByStatus() {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT ccm_id, ccm_title, ccm_local_title, ccm_status FROM content_category_master WHERE ccm_status=1");
        return query.list();
    }


    @Override
    public List<Object[]> readByContentCatAndStatus(int contentCatId) {
        Session session = sessionFactory.openSession();
//        Query query = session.createSQLQuery("SELECT ccm_id, ccm_title, ccm_local_title, ccm_ctm_id FROM content_category_master WHERE ccm_id=:ccm_id");
        Query query = session.createSQLQuery("SELECT cscm_id, cscm_title FROM content_sub_category_master WHERE cscm_ccm_id=:cscm_ccm_id AND cscm_status=1");
//        Query query = session.createQuery("SELECT ccm_id, ccm_title FROM content_category_master WHERE ccm_ctm_id=:ccm_ctm_id").setResultTransformer(Transformers.aliasToBean(Object.class));
        query.setParameter("cscm_ccm_id", contentCatId);
        List<Object[]> objects = (List<Object[]>) query.list();
        return objects;
    }

    @Override
    public List<Object[]> readByContentCat(int contentCatId) {
        Session session = sessionFactory.openSession();
//        Query query = session.createSQLQuery("SELECT ccm_id, ccm_title, ccm_local_title, ccm_ctm_id FROM content_category_master WHERE ccm_id=:ccm_id");
        Query query = session.createSQLQuery("SELECT cscm_id, cscm_title FROM content_sub_category_master WHERE cscm_ccm_id=:cscm_ccm_id");
//        Query query = session.createQuery("SELECT ccm_id, ccm_title FROM content_category_master WHERE ccm_ctm_id=:ccm_ctm_id").setResultTransformer(Transformers.aliasToBean(Object.class));
        query.setParameter("cscm_ccm_id", contentCatId);
        List<Object[]> objects = (List<Object[]>) query.list();
        return objects;
    }
    
    @Override
    public List<Object[]> readByContentCat2(int contentCatId) {
        Session session = sessionFactory.openSession();
//        Query query = session.createSQLQuery("SELECT cscm_id, cscm_ccm_id, cscm_title, cscm_local_title, cscm_status FROM content_sub_category_master");
        Query query = session.createSQLQuery("SELECT cscm_id, ctm_title, ccm_title, ccm_local_title, cscm_title, cscm_local_title, cscm_status FROM content_sub_category_master, content_category_master, content_type_master WHERE cscm_ccm_id=ccm_id AND ccm_ctm_id=ctm_id AND ccm_id=:ccm_id");
        query.setParameter("ccm_id", contentCatId);
        System.out.println(query.list().size());
        return query.list();
    }

    @Override
    public List<Object[]> readByContentType(int contentTypeId) {
        //Laster
        Session session = sessionFactory.openSession();
//        Query query = session.createSQLQuery("SELECT cscm_id, cscm_ccm_id, cscm_title, cscm_local_title, cscm_status FROM content_sub_category_master");
        Query query = session.createSQLQuery("SELECT cscm_id, ctm_title, ccm_title, ccm_local_title, cscm_title, cscm_local_title, cscm_status FROM content_sub_category_master, content_category_master, content_type_master WHERE cscm_ccm_id=ccm_id AND ccm_ctm_id=ctm_id AND ctm_id=:ctm_id");
        query.setParameter("ctm_id", contentTypeId);
        System.out.println(query.list().size());
        return query.list();
    }

    @Override
    public List<Object[]> readByTypeAndContentCategoryMaster(int contentTypeId, int contentCategoryMasterId) {
        //Later
        Session session = sessionFactory.openSession();
//        Query query = session.createSQLQuery("SELECT cscm_id, cscm_ccm_id, cscm_title, cscm_local_title, cscm_status FROM content_sub_category_master");
        Query query = session.createSQLQuery("SELECT cscm_id, ctm_title, ccm_title, ccm_local_title, cscm_title, cscm_local_title, cscm_status FROM content_sub_category_master, content_category_master, content_type_master WHERE cscm_ccm_id=ccm_id AND ccm_ctm_id=ctm_id AND ctm_id=:ctm_id AND ccm_id=:ccm_id");
        query.setParameter("ctm_id", contentTypeId);
        query.setParameter("ccm_id", contentCategoryMasterId);
        System.out.println(query.list().size());
        return query.list();
    }
    
    

    

    


    
    
}
