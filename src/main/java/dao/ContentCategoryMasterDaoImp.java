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
public class ContentCategoryMasterDaoImp implements ContentCategoryMasterDao{
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public String create(String title, String localTitle, int contentTypeId) {
        try
        {
            if(!contentCategoryExist(title, contentTypeId))
            {
                Session session = sessionFactory.openSession();
                Query query = session.createSQLQuery("INSERT INTO content_category_master (ccm_title, ccm_local_title, ccm_ctm_id) VALUES (:ccm_title, :ccm_local_title, :ccm_ctm_id)");
                query.setParameter("ccm_title", title);
                query.setParameter("ccm_local_title", localTitle);
                query.setParameter("ccm_ctm_id", contentTypeId);
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
    
    private boolean contentCategoryExist(String title, int contentTypeId)
    {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT * FROM content_category_master WHERE ccm_title=:ccm_title AND ccm_ctm_id=:ccm_ctm_id");
        query.setParameter("ccm_title", title);
        query.setParameter("ccm_ctm_id", contentTypeId);
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
        Session session = null;
        try
        {
            session = sessionFactory.openSession();
            Query query = session.createSQLQuery("SELECT ccm_id, ccm_title, ccm_local_title, ccm_status, ctm_title FROM content_category_master, content_type_master WHERE ccm_ctm_id=ctm_id");
            return query.list();
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
    public String updateStatus(int contentCategoryMasterId, int status) {
        try
        {
            Session session = sessionFactory.openSession();
//            session.beginTransaction();
            Query query = session.createSQLQuery("UPDATE content_category_master SET ccm_status=:ccm_status WHERE ccm_id=:ccm_id");
            query.setParameter("ccm_status", status);
            query.setParameter("ccm_id", contentCategoryMasterId);
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
    public Object[] readContentCategoryMaster(int contentCategoryMasterId) {
        Session session = sessionFactory.openSession();
//        Query query = session.createSQLQuery("SELECT ccm_id, ccm_title, ccm_local_title, ccm_ctm_id FROM content_category_master WHERE ccm_id=:ccm_id");
        Query query = session.createSQLQuery("SELECT ccm_id, ccm_title, ccm_local_title, ctm_title FROM content_category_master, content_type_master WHERE ccm_id=:ccm_id AND ccm_ctm_id=ctm_id");
        query.setParameter("ccm_id", contentCategoryMasterId);
        Object[] objects = (Object[]) query.uniqueResult();
        return objects;
    }

    //Checked...
    @Override
    public String update(int contentCategoryMasterId, String title, String localTitle) {
        try
        {
            Session session = sessionFactory.openSession();
//            session.beginTransaction();
            Query query = session.createSQLQuery("UPDATE content_category_master SET ccm_title=:ccm_title, ccm_local_title=:ccm_local_title WHERE ccm_id=:ccm_id");
            query.setParameter("ccm_title", title);
            query.setParameter("ccm_local_title", localTitle);
            query.setParameter("ccm_id", contentCategoryMasterId);
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
    public List<Object[]> readByContentType(int contentTypeId) {
        Session session = sessionFactory.openSession();
    Query query = session.createSQLQuery("SELECT ccm_id, ccm_title, ccm_local_title, ccm_status, ctm_title FROM content_category_master, content_type_master WHERE ccm_ctm_id=ctm_id AND ccm_ctm_id=:ccm_ctm_id");
//        Query query = session.createSQLQuery("SELECT ccm_id, ccm_title FROM content_category_master WHERE ccm_ctm_id=:ccm_ctm_id");
        query.setParameter("ccm_ctm_id", contentTypeId);
        
        List<Object[]> objects = (List<Object[]>) query.list();
        
        return objects;
    }

    @Override
    public List<Object[]> readAllFromContentMaster() {
        
        
        Session session = null;
        try
        {
            session = sessionFactory.openSession();
            Query query = session.createSQLQuery("SELECT DISTINCT(ccm_title), ccm_id, ccm_local_title, ccm_status FROM content_master, content_category_master WHERE cm_ccm_id=ccm_id");
            return query.list();
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
