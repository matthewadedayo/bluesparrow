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
public class MenuMappingDaoImp implements MenuMappingDao{
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Object[]> readAllMenuMapping() {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT mp_id, mm_title, mc_title FROM menu_mapping, menu_master, menu_child");
        List<Object[]> menuList = query.list();
        return menuList;
    }

//    @Override
//    public List<Object[]> readUserMenuMapping(Object object) {
//        
//        System.out.println("pk_d: " + Integer.parseInt(object.toString()));
//        Session session = sessionFactory.openSession();
////        Query query = session.createSQLQuery("SELECT mp_id, mp_um_id, mp_mm_id, mp_mc_id, mp_status FROM menu_mapping WHERE mp_um_id =:mp_um_id AND mp_status=:mp_status");
////        Query query = session.createSQLQuery("SELECT mp_id, mm_title, mm_url, mc_title, mc_url FROM menu_mapping, menu_master, menu_child WHERE mp_um_id =:mp_um_id");
//        Query query = session.createSQLQuery("SELECT mp_id, mp_mm_id, mm_title, mp_mc_id, mc_title FROM menu_mapping, menu_master, menu_child WHERE mp_um_id =:mp_um_id AND mp_mm_id=mm_id AND mp_mc_id=mc_id");
//        query.setParameter("mp_um_id", Integer.parseInt(object.toString()));
////        query.setParameter("mp_status", 1);
//
//        
//        String menu="none";
//        String child="none";
//
//        List<Object[]> menuMappingList = query.list();
//        for(int i=0;i<menuMappingList.size(); ++i)
//        {
////            for(;;)
////            {
////                if(menuMappingList.get(i)[2].toString().equals(menu) && menuMappingList.get(i)[4].toString().equals(child))
////                    break;
//                System.out.println(menuMappingList.get(i)[2] + "  " + menuMappingList.get(i)[4]);
////                menu=menuMappingList.get(i)[2].toString();
////                child=menuMappingList.get(i)[4].toString();
//                
////            }
//        }
//        System.out.println("mapping size: "+menuMappingList.size());
//        return menuMappingList;
//        
//    }
//
    //Checked...
    @Override
    public List<Object[]> readAll() {
        Session session = sessionFactory.openSession();
//        Query query = session.createSQLQuery("SELECT mp_id, mp_um_id, mp_mm_id, mp_mc_id, mp_status FROM menu_mapping");
        Query query = session.createSQLQuery("SELECT mp_id, um_user_name, mm_title, mc_title, mp_status FROM menu_mapping, user_master, menu_master, menu_child WHERE mp_um_id=um_id AND mp_mm_id=mm_id AND mp_mc_id=mc_id");
        
        return query.list();
    }

    //Checked...
    @Override
    public String updateStatus(int menuMappingId, int status) {
        try
        {
            Session session = sessionFactory.openSession();
//            session.beginTransaction();
            Query query = session.createSQLQuery("UPDATE menu_mapping SET mp_status=:mp_status WHERE mp_id=:mp_id");
            query.setParameter("mp_status", status);
            query.setParameter("mp_id", menuMappingId);
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
    public List<Object[]> readUserMenuMapping(int userId, int menuId) {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT mp_mc_id FROM menu_mapping, menu_master, menu_child WHERE mp_um_id=:mp_um_id AND mp_mm_id=:mp_mm_id AND mp_mc_id=mc_id");
        query.setParameter("mp_um_id", userId);
        query.setParameter("mp_mm_id", menuId);
        List<Object[]> menuList = query.list();
        return menuList;
    }

    //Checked...
    @Override
    public String addPriviledge(int userId, int menuId, int menuItem) {
        try
        {
            Session session = sessionFactory.openSession();
//            session.beginTransaction();
            Query query = session.createSQLQuery("INSERT INTO menu_mapping (mp_um_id, mp_mm_id, mp_mc_id) VALUES(:mp_um_id, :mp_mm_id, :mp_mc_id)");
            query.setParameter("mp_um_id", userId);
            query.setParameter("mp_mm_id", menuId);
            query.setParameter("mp_mc_id", menuItem);
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
    public Object[] readByRoleMenu(int menuMasterId, String roleType) {
        Session session = sessionFactory.openSession();
//        Query query = session.createSQLQuery("SELECT mp_id, mp_um_id, mp_mm_id, mp_mc_id, mp_status FROM menu_mapping");
        Query query = session.createSQLQuery("SELECT mp_id, mp_role, mp_mm_id, mp_status FROM menu_mapping_v2 WHERE mp_mm_id=:mp_mm_id AND mp_role=:mp_role");
        query.setParameter("mp_mm_id", menuMasterId);
        query.setParameter("mp_role", roleType);
        
        return (Object[]) query.uniqueResult();
    }

    @Override
    public String addPriviledge(int menuId, String roleType, int menuMappingStatus) {
        String reply="";
//        contentPortalMappingExist
        if(!menuMappingExist(menuId, roleType))
            {
                reply=create(menuId, roleType);
            }
            else
            {
                reply=updateStatusByTypePortal(menuId, roleType, menuMappingStatus);
            }
        return reply;
    }

    private String updateStatusByTypePortal(int menuId, String roleType, int menuMappingStatus) {
        try
        {
            Session session = sessionFactory.openSession();
//            session.beginTransaction();
            Query query = session.createSQLQuery("UPDATE menu_mapping_v2 SET mp_status=:mp_status WHERE mp_mm_id=:mp_mm_id AND mp_role=:mp_role");
            query.setParameter("mp_status", menuMappingStatus);
            query.setParameter("mp_mm_id", menuId);
            query.setParameter("mp_role", roleType);
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
    public String create(int menuId, String roleType) 
    {
        try
        {
//            if(!contentPortalMappingExist(contentPortalMaster, contentTypeMaster))
//            {
                Session session = sessionFactory.openSession();
//                Query query = session.createSQLQuery("INSERT INTO content_portal_type_mapping (cptm_cpm_id, cptm_ctm_id) VALUES (:cptm_cpm_id, :cptm_ctm_id)");
                Query query = session.createSQLQuery("INSERT INTO menu_mapping_v2 (mp_mm_id, mp_role) VALUES (:mp_mm_id, :mp_role)");
                query.setParameter("mp_mm_id", menuId);
                query.setParameter("mp_role", roleType);
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
    
    private boolean menuMappingExist(int menuId, String roleType)
    {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT mp_id, mp_status FROM menu_mapping_v2 WHERE mp_mm_id=:mp_mm_id AND mp_role=:mp_role");
        query.setParameter("mp_mm_id", menuId);
        query.setParameter("mp_role", roleType);
//        query.setParameter("um_password", password);
        if(query.list().size()!=0)
        {
            return true;
        }
        return false;
        
    }
    
    private boolean menuChildMappingExist(int menuId, int menuChildId, String roleType)
    {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT mp_id, mp_status FROM menu_child_mapping WHERE mp_mm_id=:mp_mm_id AND mp_role=:mp_role AND mp_mc_id=:mp_mc_id");
        query.setParameter("mp_mm_id", menuId);
        query.setParameter("mp_mc_id", menuChildId);
        query.setParameter("mp_role", roleType);
//        query.setParameter("um_password", password);
        if(query.list().size()!=0)
        {
            System.out.println("1yes");
            return true;
        }
        System.out.println("1no");
        return false;
        
    }
//SELECT mp_id, um_user_name, mm_title, mc_title, mp_status FROM menu_mapping_v2 WHERE mp_role
    @Override
    public List<String> readByRole(String roleType) {
        Session session = sessionFactory.openSession();

//        Query query = session.createSQLQuery("SELECT mp_id, mp_role, mm_title, mp_status FROM menu_mapping_v2, menu_master_v2 WHERE mp_mm_id=mm_id AND mp_role=:mp_role AND mp_status=1");
        Query query = session.createSQLQuery("SELECT mm_title FROM menu_mapping_v2, menu_master_v2 WHERE mp_mm_id=mm_id AND mp_role=:mp_role AND mp_status=1");
        query.setParameter("mp_role", roleType);
        return query.list();
    }

    @Override
    public Object[] readByRoleMenuMenuChild(int menuChildId, String roleType, int menuId) {
        Session session = sessionFactory.openSession();
//        Query query = session.createSQLQuery("SELECT mp_id, mp_um_id, mp_mm_id, mp_mc_id, mp_status FROM menu_mapping");
        Query query = session.createSQLQuery("SELECT mp_id, mp_role, mp_mm_id, mp_mc_id, mp_status FROM menu_child_mapping WHERE mp_mm_id=:mp_mm_id AND mp_role=:mp_role AND mp_mc_id=:mp_mc_id");
        query.setParameter("mp_mm_id", menuId);
        query.setParameter("mp_role", roleType);
        query.setParameter("mp_mc_id", menuChildId);
        
        return (Object[]) query.uniqueResult();
    }

    @Override
    public String addPriviledgeChild(int menuId, int menuChildId, String roleType, int menuChildMappingStatus) {
        String reply="";
//        contentPortalMappingExist
        if(!menuChildMappingExist(menuId, menuChildId, roleType))
            {
                reply=createMenuChildMapping(menuId, menuChildId, roleType);
            }
            else
            {
                reply=updateMenuChildMappingStatusByTypePortal(menuId, menuChildId, roleType, menuChildMappingStatus);
            }
        return reply;
    }

    private String createMenuChildMapping(int menuId, int menuChildId, String roleType) {
        try
        {
//            if(!contentPortalMappingExist(contentPortalMaster, contentTypeMaster))
//            {
                Session session = sessionFactory.openSession();
//                Query query = session.createSQLQuery("INSERT INTO content_portal_type_mapping (cptm_cpm_id, cptm_ctm_id) VALUES (:cptm_cpm_id, :cptm_ctm_id)");
                Query query = session.createSQLQuery("INSERT INTO menu_child_mapping (mp_mm_id, mp_mc_id, mp_role) VALUES (:mp_mm_id, :mp_mc_id, :mp_role)");
                query.setParameter("mp_mm_id", menuId);
                query.setParameter("mp_mc_id", menuChildId);
                query.setParameter("mp_role", roleType);
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

    private String updateMenuChildMappingStatusByTypePortal(int menuId, int menuChildId, String roleType, int menuChildMappingStatus) {
        try
        {
            Session session = sessionFactory.openSession();
//            session.beginTransaction();
            Query query = session.createSQLQuery("UPDATE menu_child_mapping SET mp_status=:mp_status WHERE mp_mm_id=:mp_mm_id AND mp_mc_id=:mp_mc_id AND mp_role=:mp_role");
            query.setParameter("mp_status", menuChildMappingStatus);
            query.setParameter("mp_mm_id", menuId);
            query.setParameter("mp_mc_id", menuChildId);
            query.setParameter("mp_role", roleType);
            query.executeUpdate();
//            session.getTransaction().commit();
            return "success";
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        
        return "failure";
    }

    @Override
    public List<Object[]> readMenuChildByRole(String roleType, int menuMasterId) {
        
        Session session = sessionFactory.openSession();

//        Query query = session.createSQLQuery("SELECT mp_id, mp_role, mm_title, mp_status FROM menu_mapping_v2, menu_master_v2 WHERE mp_mm_id=mm_id AND mp_role=:mp_role AND mp_status=1");
//        Query query = session.createSQLQuery("SELECT mc_title FROM menu_child_ mapping, menu_child_v2 WHERE mp_mm_id=mm_id AND mp_role=:mp_role AND mp_status=1");
//        Query query = session.createSQLQuery("SELECT mc_title FROM menu_child_mapping, menu_child_v2 WHERE mp_mc_id=mc_id AND mp_role=:mp_role AND mp_status=1");
        Query query = session.createSQLQuery("SELECT mm_id,mm_title,mm_url,mm_order,mm_status, mc_id, mc_mm_id,mc_title, mc_url, mc_order, mc_status FROM bf_cms.menu_master_v2, bf_cms.menu_child_v2 WHERE mc_id IN  (SELECT mp_mc_id FROM bf_cms.menu_child_mapping WHERE mp_role=:mp_role AND mp_mm_id = :mp_mm_id AND mp_status=1) AND mm_id = mc_mm_id ORDER BY mm_order, mc_order");
        query.setParameter("mp_role", roleType);
        query.setParameter("mp_mm_id", menuMasterId);
        return query.list();
        
    }

    @Override
    public Object[] readByRoleMenuChild(int menuChildId, String roleType) {
        Session session = sessionFactory.openSession();
//        Query query = session.createSQLQuery("SELECT mp_id, mp_um_id, mp_mm_id, mp_mc_id, mp_status FROM menu_mapping");
        Query query = session.createSQLQuery("SELECT mp_id, mp_role, mp_mm_id, mp_mc_id, mp_status FROM menu_child_mapping WHERE mp_role=:mp_role AND mp_mc_id=:mp_mc_id");
        
        query.setParameter("mp_role", roleType);
        query.setParameter("mp_mc_id", menuChildId);
        
        return (Object[]) query.uniqueResult();
    }

    @Override
    public List<Object[]> readMenuByRole(String roleType) {
        Session session = sessionFactory.openSession();//SELECT mm_id,mm_title,mm_url,mm_order,mm_status FROM bf_cms.menu_master_v2 WHERE mm_id IN  (SELECT mp_mm_id FROM bf_cms.menu_child_mapping WHERE mp_role='ADMIN' AND mp_status=1) AND mm_status=1 ORDER BY mm_order DESC;
//        Query query = session.createSQLQuery("SELECT mp_id, mm_title, mc_title FROM menu_mapping, menu_master, menu_child");
        Query query = session.createSQLQuery("SELECT mm_id,mm_title,mm_url,mm_order,mm_status, mm_icon FROM bf_cms.menu_master_v2 WHERE mm_id IN  (SELECT mp_mm_id FROM bf_cms.menu_child_mapping WHERE mp_role=:mp_role AND mp_status=1) AND mm_status=1 ORDER BY mm_order ");
        query.setParameter("mp_role", roleType);
        List<Object[]> menuList = query.list();
        return menuList;
//return null;
    }

    @Override
    public List<Object[]> readAllMenuAccess() {
        Session session = sessionFactory.openSession();//SELECT mm_id,mm_title,mm_url,mm_order,mm_status FROM bf_cms.menu_master_v2 WHERE mm_id IN  (SELECT mp_mm_id FROM bf_cms.menu_child_mapping WHERE mp_role='ADMIN' AND mp_status=1) AND mm_status=1 ORDER BY mm_order DESC;
//        Query query = session.createSQLQuery("SELECT mp_id, mm_title, mc_title FROM menu_mapping, menu_master, menu_child");
        
        Query query = session.createSQLQuery("SELECT mm_id,mm_title,mm_url,mm_order,mm_status, mm_icon FROM bf_cms.menu_master_v2 WHERE mm_status=1 ORDER BY mm_order");
        
        List<Object[]> menuList = query.list();
        return menuList;
    }

    @Override
    public List<Object[]> readAllMenuChildAccess(int menuMasterId) {
        Session session = sessionFactory.openSession();

//        Query query = session.createSQLQuery("SELECT mp_id, mp_role, mm_title, mp_status FROM menu_mapping_v2, menu_master_v2 WHERE mp_mm_id=mm_id AND mp_role=:mp_role AND mp_status=1");
//        Query query = session.createSQLQuery("SELECT mc_title FROM menu_child_ mapping, menu_child_v2 WHERE mp_mm_id=mm_id AND mp_role=:mp_role AND mp_status=1");
//        Query query = session.createSQLQuery("SELECT mc_title FROM menu_child_mapping, menu_child_v2 WHERE mp_mc_id=mc_id AND mp_role=:mp_role AND mp_status=1");
        Query query = session.createSQLQuery("SELECT mm_id,mm_title,mm_url,mm_order,mm_status,  mc_id,mc_mm_id,mc_title, mc_url, mc_order, mc_status FROM bf_cms.menu_master_v2, bf_cms.menu_child_v2 WHERE  mm_id = mc_mm_id AND mc_mm_id = :mc_mm_id AND mc_status=1 ORDER BY mm_order, mc_order DESC");
        
        query.setParameter("mc_mm_id", menuMasterId);
        return query.list();
    }

  
}
