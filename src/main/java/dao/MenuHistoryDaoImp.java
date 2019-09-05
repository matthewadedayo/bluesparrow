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
public class MenuHistoryDaoImp implements MenuHistoryDao{
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    
    

    @Override
    public List<Object[]> readAll() {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT ccmp_id, cm_title, ccm_title, ccmp_status FROM content_category_mapping, content_master, content_category_master WHERE ccmp_cm_id=cm_id AND ccmp_ccm_id=ccm_id AND cm_ccm_id=ccm_id");
        return query.list();
    }

    @Override
    public String create(int userId, int menuMasterId, int MenuChildId) {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("INSERT INTO menu_history (mh_um_id, mh_mm_id, mh_mc_id) VALUES (:mh_um_id, :mh_mm_id, :mh_mc_id)");
        query.setParameter("mh_um_id", userId);
        query.setParameter("mh_mm_id", menuMasterId);
        query.setParameter("mh_mc_id", MenuChildId);
        if(query.executeUpdate()!=0)
        {
            return "success";
        }
        return "failure";
    }

    @Override
    public List<Object[]> readAllByUserMaster(int userMasterId) {
        Session session = sessionFactory.openSession();
//        SELECT mm_id,mm_title,mm_url,mm_order,mm_status, mc_id, mc_mm_id,mc_title, mc_url, mc_order, mc_status FROM bf_cms.menu_master_v2, bf_cms.menu_child_v2 WHERE mc_id IN  (SELECT mp_mc_id FROM bf_cms.menu_child_mapping WHERE mp_role=:mp_role AND mp_mm_id = :mp_mm_id AND mp_status=1) AND mm_id = mc_mm_id ORDER BY mm_order, mc_order
        Query query = session.createSQLQuery("SELECT mh_id, mh_um_id, mm_id, mm_title, mc_id, mc_title, mc_url, mh_status FROM menu_history, menu_master_v2, menu_child_v2 WHERE mh_mm_id=mm_id AND mh_mc_id=mc_id AND mh_um_id=:mh_um_id ORDER BY mh_id DESC LIMIT 5");
        query.setParameter("mh_um_id", userMasterId);
        return query.list();
    }

 

    

    

    


    
    
}
