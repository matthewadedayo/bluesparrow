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
public class ContentPortalMappingDaoImp implements ContentPortalMappingDao{
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    
    

    @Override
    public List<Object[]> readAll() {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT pcm_id, um_user_name, cpm_title, ctm_title, ccm_title, pcm_status FROM portal_content_mapping, content_portal_master, user_master, content_type_master, content_category_master WHERE pcm_cpm_id=cpm_id AND pcm_um_id=um_id AND pcm_ctm_id=ctm_id AND pcm_ccm_id=ccm_id");
        List<Object[]> objects = query.list();
        
        return objects;
    }

    //Checked...
    @Override
    public String updateStatus(int portalContentMappingId, int status) {
        try
        {
            Session session = sessionFactory.openSession();
//            session.beginTransaction();
            Query query = session.createSQLQuery("UPDATE portal_content_mapping SET pcm_status=:pcm_status WHERE pcm_id=:pcm_id");
            query.setParameter("pcm_status", status);
            query.setParameter("pcm_id", portalContentMappingId);
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
    public String create(int contentPortalMasterId,  int contentMasterId) {
        try
        {
//            if(!contentPortalMappingExist(contentPortalMasterId, contentMasterId))
//            {
                Session session = sessionFactory.openSession();
                Query query = session.createSQLQuery("INSERT INTO content_portal_mapping (cpm_cpm_id, cpm_cm_id) VALUES (:cpm_cpm_id, :cpm_cm_id)");
                query.setParameter("cpm_cpm_id", contentPortalMasterId);
                query.setParameter("cpm_cm_id", contentMasterId);
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

    
    private boolean contentPortalMappingExist(int contentPortalMasterId, int userMasterId, int contentTypeMasterId, int contentCategoryMasterId)
    {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT pcm_id, pcm_status FROM portal_content_mapping WHERE pcm_cpm_id=:pcm_cpm_id AND pcm_um_id=:pcm_um_id AND pcm_ctm_id=:pcm_ctm_id AND pcm_ccm_id=:pcm_ccm_id");
        query.setParameter("pcm_cpm_id", contentPortalMasterId);
        query.setParameter("pcm_um_id", userMasterId);
        query.setParameter("pcm_ctm_id", contentTypeMasterId);
        query.setParameter("pcm_ccm_id", contentCategoryMasterId);
//        query.setParameter("um_password", password);
        if(query.list().size()!=0)
        {
            return true;
        }
        return false;
        
    }

    @Override
    public Object[] readByContentPortal(int contentMasterId, int contentPortalMasterId) {
        Session session = null;
        try
        {
            session = sessionFactory.openSession();
            Query query = session.createSQLQuery("SELECT cpm_id, cpm_cpm_id, cpm_cm_id, cpm_status FROM content_portal_mapping WHERE cpm_cpm_id=:cpm_cpm_id AND cpm_cm_id=:cpm_cm_id");
            query.setParameter("cpm_cpm_id", contentPortalMasterId);
            query.setParameter("cpm_cm_id", contentMasterId);
    //        query.setParameter("um_password", password);
            return (Object[]) query.uniqueResult();
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
    public String createUpdate(int contentMasterId, int contentPortalId, int contentPortalMapStatus) {
        String reply="";
        if(!contentPortalMappingExist(contentPortalId, contentMasterId))
            {
                reply = create(contentPortalId, contentMasterId);
                System.out.println("CRE");
            }
            else
            {
                reply = updateStatusByTypePortal(contentPortalId, contentMasterId, contentPortalMapStatus);
                System.out.println("UPD");
            }
        return reply;
    }
    
    private boolean contentPortalMappingExist(int contentPortalMaster, int contentMasterMaster)
    {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT cpm_id, cpm_status FROM content_portal_mapping WHERE cpm_cpm_id=:cpm_cpm_id AND cpm_cm_id=:cpm_cm_id");
        query.setParameter("cpm_cpm_id", contentPortalMaster);
        query.setParameter("cpm_cm_id", contentMasterMaster);
//        query.setParameter("um_password", password);
        if(query.list().size()!=0)
        {
            return true;
        }
        return false;
        
    }

    private String updateStatusByTypePortal(int contentPortalId, int contentMasterId, int contentPortalMapStatus) {
        try
        {
            Session session = sessionFactory.openSession();
//            session.beginTransaction();
            Query query = session.createSQLQuery("UPDATE content_portal_mapping SET cpm_status=:cpm_status WHERE cpm_cm_id=:cpm_cm_id AND cpm_cpm_id=:cpm_cpm_id");
            query.setParameter("cpm_status", contentPortalMapStatus);
            query.setParameter("cpm_cm_id", contentMasterId);
            query.setParameter("cpm_cpm_id", contentPortalId);
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
