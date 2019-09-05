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
public class ContentPortalCategoryMappingDaoImp implements ContentPortalCategoryMappingDao{
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    
    

    @Override
    public List<Object[]> readAll() {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT cpcm_id, cpm_title, ccm_title, cpcm_status FROM content_portal_category_mapping, content_portal_master, content_category_master WHERE cpcm_cpm_id=cpm_id AND cpcm_ccm_id=ccm_id");
        return query.list();
    }

    //Checked...
    @Override
    public String updateStatus(int contentPortalCategoryMappingId, int status) {
        try
        {
            Session session = sessionFactory.openSession();
//            session.beginTransaction();
            Query query = session.createSQLQuery("UPDATE content_portal_category_mapping SET cpcm_status=:cpcm_status WHERE cpcm_id=:cpcm_id");
            query.setParameter("cpcm_status", status);
            query.setParameter("cpcm_id", contentPortalCategoryMappingId);
            query.executeUpdate();
//            session.getTransaction().commit();
            return "success";
        }
        catch(Exception ex)
        {
            
        }
        
        return "failure";
    }

    //nEW
    @Override
    public String create(int contentPortalMaster, int contentCategoryMaster) {
        try
        {
//            if(!contentPortalMappingExist(contentPortalMaster, contentCategoryMaster))
//            {
                Session session = sessionFactory.openSession();
                Query query = session.createSQLQuery("INSERT INTO content_portal_category_mapping (cpcm_cpm_id, cpcm_ccm_id) VALUES (:cpcm_cpm_id, :cpcm_ccm_id)");
                query.setParameter("cpcm_cpm_id", contentPortalMaster);
                query.setParameter("cpcm_ccm_id", contentCategoryMaster);
                if(query.executeUpdate()!=0)
                {
                    return "success";
                }
//            }
//            else
//            {
//                return "exist";
//            }
        }
        catch(Exception ex)
        {
            //
            ex.printStackTrace();
        }
        return "failure";
    }

    
    private boolean contentPortalMappingExist(int contentPortalMaster, int contentCategoryMaster)
    {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT cpcm_id, cpcm_status FROM content_portal_category_mapping WHERE cpcm_cpm_id=:cpcm_cpm_id AND cpcm_ccm_id=:cpcm_ccm_id");
        query.setParameter("cpcm_cpm_id", contentPortalMaster);
        query.setParameter("cpcm_ccm_id", contentCategoryMaster);
//        query.setParameter("um_password", password);
        if(query.list().size()!=0)
        {
            return true;
        }
        return false;
        
    }

    @Override
    public Object[] readByCategoryPortal(int contentCategoryMasterId, int contentPortalMasterId) {
        
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT cpcm_id, cpcm_ccm_id, cpcm_cpm_id, cpcm_status FROM content_portal_category_mapping WHERE cpcm_cpm_id=:cpcm_cpm_id AND cpcm_ccm_id=:cpcm_ccm_id");
        query.setParameter("cpcm_cpm_id", contentPortalMasterId);
        query.setParameter("cpcm_ccm_id", contentCategoryMasterId);
//        query.setParameter("um_password", password);

        return (Object[]) query.uniqueResult();
    }

    @Override
    public String createUpdate(int categoryId, int contentPortalId, int catPortalMapStatus) {
        String reply="";
        if(!contentPortalMappingExist(contentPortalId, categoryId))
            {
                reply=create(contentPortalId, categoryId);
            }
            else
            {
                reply=updateStatusByCategoryPortalPortal(contentPortalId, categoryId, catPortalMapStatus);
            }
        return reply;
    }

    private String updateStatusByCategoryPortalPortal(int contentPortalId, int categoryId, int catPortalMapStatus) {
        try
        {
            Session session = sessionFactory.openSession();
//            session.beginTransaction();
            Query query = session.createSQLQuery("UPDATE content_portal_category_mapping SET cpcm_status=:cpcm_status WHERE cpcm_ccm_id=:cpcm_ccm_id AND cpcm_cpm_id=:cpcm_cpm_id");
            query.setParameter("cpcm_status", catPortalMapStatus);
            query.setParameter("cpcm_ccm_id", categoryId);
            query.setParameter("cpcm_cpm_id", contentPortalId);
            query.executeUpdate();
//            session.getTransaction().commit();
            return "success";
        }
        catch(Exception ex)
        {
            
        }
        
        return "failure";
    }
    

    


    
    
}
