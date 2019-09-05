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
public class ContentTypePortalMappingDaoImp implements ContentTypePortalMappingDao{
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    
    

    @Override
    public List<Object[]> readAll() {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT cptm_id, cpm_title, ctm_title, cptm_status FROM content_portal_type_mapping, content_portal_master, content_type_master WHERE cptm_cpm_id=cpm_id AND cptm_ctm_id=ctm_id");
        return query.list();
    }

    //Checked...
    @Override
    public String updateStatus(int contentPortalTypeMappingId, int status) {
        try
        {
            Session session = sessionFactory.openSession();
//            session.beginTransaction();
            Query query = session.createSQLQuery("UPDATE content_portal_type_mapping SET cptm_status=:cptm_status WHERE cptm_id=:cptm_id");
            query.setParameter("cptm_status", status);
            query.setParameter("cptm_id", contentPortalTypeMappingId);
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
    public String create(int contentPortalMaster, int contentTypeMaster) {
        try
        {
//            if(!contentPortalMappingExist(contentPortalMaster, contentTypeMaster))
//            {
                Session session = sessionFactory.openSession();
//                Query query = session.createSQLQuery("INSERT INTO content_portal_type_mapping (cptm_cpm_id, cptm_ctm_id) VALUES (:cptm_cpm_id, :cptm_ctm_id)");
                Query query = session.createSQLQuery("INSERT INTO content_type_portal_mapping (ctpm_cpm_id, ctpm_ctm_id) VALUES (:ctpm_cpm_id, :ctpm_ctm_id)");
                query.setParameter("ctpm_cpm_id", contentPortalMaster);
                query.setParameter("ctpm_ctm_id", contentTypeMaster);
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

    
    private boolean contentPortalMappingExist(int contentPortalMaster, int contentTypeMaster)
    {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT ctpm_id, ctpm_status FROM content_type_portal_mapping WHERE ctpm_cpm_id=:ctpm_cpm_id AND ctpm_ctm_id=:ctpm_ctm_id");
        query.setParameter("ctpm_cpm_id", contentPortalMaster);
        query.setParameter("ctpm_ctm_id", contentTypeMaster);
//        query.setParameter("um_password", password);
        if(query.list().size()!=0)
        {
            return true;
        }
        return false;
        
    }

    //New cms
    @Override
    public List<Object[]> readAllWithType() {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT ctpm_id, ctm_title, ctm_desc, ctpm_status FROM content_type_master LEFT JOIN content_type_portal_mapping ON ctpm_ctm_id=ctm_id");
        return query.list();
    }

//    @Override
//    public List<Object[]> readAllWithTypeByContentPortal(int contentPortalMaster) {
//        Session session = sessionFactory.openSession();
////        Query query = session.createSQLQuery("SELECT ctpm_id, ctm_title, ctm_desc, ctpm_status FROM content_type_master LEFT JOIN content_type_portal_mapping ON ctpm_ctm_id=ctm_id LEFT JOIN content_portal_master ON ctpm_cpm_id = cpm_id");
//
////        Query query = session.createSQLQuery("SELECT ctm_id, ctm_title, ctm_desc, ctpm_status FROM content_type_portal_mapping RIGHT JOIN content_type_master ON ctpm_ctm_id=ctm_id LEFT JOIN content_portal_master ON ctpm_cpm_id=cpm_id");
//        Query query = session.createSQLQuery("SELECT ctm_id, ctm_title, ctm_desc, ctm_status FROM  content_type_master"); 
//   
//        return query.list();
//    }

    @Override
    public String createUpdate(int typeId, int contentPortalId, int typePortalMapStatus) {
        String reply="";
        if(!contentPortalMappingExist(contentPortalId, typeId))
            {
                reply=create(contentPortalId, typeId);
            }
            else
            {
                reply=updateStatusByTypePortal(contentPortalId, typeId, typePortalMapStatus);
            }
        return reply;
    }

    private String updateStatusByTypePortal(int contentPortalId, int typeId, int status) {
        try
        {
            Session session = sessionFactory.openSession();
//            session.beginTransaction();
            Query query = session.createSQLQuery("UPDATE content_type_portal_mapping SET ctpm_status=:ctpm_status WHERE ctpm_ctm_id=:ctpm_ctm_id AND ctpm_cpm_id=:ctpm_cpm_id");
            query.setParameter("ctpm_status", status);
            query.setParameter("ctpm_ctm_id", typeId);
            query.setParameter("ctpm_cpm_id", contentPortalId);
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
    public Object[] readByTypePortal(int contentTypeMasterId, int contentPortalMasterId) {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT ctpm_id, ctpm_ctm_id, ctpm_cpm_id, ctpm_status FROM content_type_portal_mapping WHERE ctpm_cpm_id=:ctpm_cpm_id AND ctpm_ctm_id=:ctpm_ctm_id");
        query.setParameter("ctpm_cpm_id", contentPortalMasterId);
        query.setParameter("ctpm_ctm_id", contentTypeMasterId);
//        query.setParameter("um_password", password);
        return (Object[]) query.uniqueResult();
    }

    @Override
    public Object[] readByTypePortalStatus(int contentTypeMasterId, int contentPortalMasterId, int contentTypePortalMappingStatus) {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT ctpm_id, ctpm_ctm_id, ctpm_cpm_id, ctpm_status FROM content_type_portal_mapping WHERE ctpm_cpm_id=:ctpm_cpm_id AND ctpm_ctm_id=:ctpm_ctm_id AND ctpm_status=:ctpm_status");
        query.setParameter("ctpm_cpm_id", contentPortalMasterId);
        query.setParameter("ctpm_ctm_id", contentTypeMasterId);
        query.setParameter("ctpm_status", contentTypePortalMappingStatus);
//        query.setParameter("um_password", password);
        return (Object[]) query.uniqueResult();
    }
    

    


    
    
}
