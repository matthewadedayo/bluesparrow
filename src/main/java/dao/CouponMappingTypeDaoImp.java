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
public class CouponMappingTypeDaoImp implements CouponMappingTypeDao{
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    
    
     @Override
    public Object[] read(int id) {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT cm_cp_id, portal, map_type, map_id FROM coupon_mapping WHERE (cm_id = :cm_id)");
        query.setParameter("cm_id", id);
        return (Object [])query.uniqueResult();
        
    }
    
   @Override
    public String createSubscriptionMapping(int couponId, String portal,  String mapType, int mapId) {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("INSERT INTO coupon_mapping (cm_cp_id, portal, map_type, map_id) VALUES (:cm_cp_id, :portal, :map_type, :map_id)");
        query.setParameter("cm_cp_id", couponId);
        query.setParameter("portal", portal);
        query.setParameter("map_type", mapType);
        query.setParameter("map_id", mapId);
        
        if(query.executeUpdate()!=0)
        {
            return "success";
        }
        return "failure";
    }

    
     @Override
    public List<Object[]> readAllSubscription(int id) {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT sm_id, sm_title, sm_package FROM subscription_master");
        return query.list();
    }
    
    
  @Override
    public Object[] readBySubscriptionId(int id) {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT sm_id, sm_title, sm_package  FROM subscription_master WHERE (sm_id = :sm_id)");
        query.setParameter("sm_id", id);
        return (Object [])query.uniqueResult();
    }
    
   /** @Override
    public String removeAllProductByCouponId(int productId) {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("DELETE FROM coupon_product_mapping WHERE cpm_product_id=:cpm_product_id");
        query.setParameter("cpm_product_id", productId);
        query.executeUpdate();
        return "success";
    }*/
  
  /**@Override
    public List<Object[]> readAllByPortalId(int portalId) {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT fm_id, fm_cpm_id, cpm_title, fm_text, fm_status FROM feature_master, content_portal_master WHERE fm_cpm_id=:fm_cpm_id AND fm_cpm_id=cpm_id");
        query.setParameter("fm_cpm_id", portalId);
        return query.list();
    }**/
    
    
}
