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
public class CouponMasterDaoImp implements CouponMasterDao{
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public List<Object[]> readAll() {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT cp_id, cp_title, cp_code, discount_type, cp_desc, country, currency, amount, min_amt, max_amt, start_date, expiry_date FROM coupon_master");
        return query.list();
        
    }

    @Override
    public Object[] read(int id) {
      Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT cp_id, cp_title, cp_code, discount_type, cp_desc, country, currency, amount, min_amt, max_amt, start_date, expiry_date FROM coupon_master WHERE (cp_id = :cp_id)");
        query.setParameter("cp_id", id);
        return (Object [])query.uniqueResult();
    }
   
    //selecting couppon using filter(country)
     @Override
     public List<Object[]> readByCountry(String country) {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT cp_id, cp_title, cp_code, discount_type, cp_desc, country, currency, amount, min_amt, max_amt, start_date, expiry_date FROM coupon_master WHERE country LIKE '%"+country+"%'");
        query.setParameter("country", country);
        return query.list();
      
    }
     
    /** @Override
    public Object[] readByAll(int couponId) {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT cp_id FROM coupon_master");
        //query.setParameter("cp_title", cTitle);
      //  query.setParameter("cp_code", cCode);
      //  query.setParameter("discount_type", discount);
       // query.setParameter("cp_desc", desc);
       // query.setParameter("country", country);
       // query.setParameter("amount", amount);
        List<Object[]> list =  query.list();
        if(list!=null)
        {
            return list.get(0);
        }
        return null;
    }*/

    @Override
    public String create(String cTitle, String cCode, String discount, String desc, String country, 
            String currency, String amount, String minAmt, String maxAmt, String startDate, String expiryDate) {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("INSERT INTO coupon_master (cp_title, cp_code, discount_type, cp_desc, country, currency, amount, min_amt, max_amt, start_date, expiry_date) VALUES (:cp_title, :cp_code, :discount_type, :cp_desc, :country, :currency, :amount, :min_amt, :max_amt, :start_date, :expiry_date )");
        query.setParameter("cp_title", cTitle);
        query.setParameter("cp_code", cCode);
        query.setParameter("discount_type", discount);
        query.setParameter("cp_desc", desc);
        query.setParameter("country", country);
        query.setParameter("currency", currency);
        query.setParameter("amount", amount);
        query.setParameter("min_amt", minAmt);
        query.setParameter("max_amt", maxAmt);
        query.setParameter("start_date", startDate);
        query.setParameter("expiry_date", expiryDate);
        
        
        if(query.executeUpdate()!=0)
        {
            return "success";
        }
        return "failure";
    }

    //@Override
    //public Object[] readByAll(int portalId, String subName, String subPrice, String subPackage) {
       // Session session = sessionFactory.openSession();
        //Query query = session.createSQLQuery("SELECT sm_id, sm_title, sm_cpm_id, sm_package, sm_price, sm_status FROM subscription_master, content_portal_master WHERE sm_title=:sm_title AND sm_cpm_id=:sm_cpm_id AND sm_package=:sm_package AND sm_price=:sm_price");
        //query.setParameter("sm_title", subName);
        //query.setParameter("sm_cpm_id", portalId);
        //query.setParameter("sm_package", subPackage);
        //query.setParameter("sm_price", subPrice);
        //List<Object[]> list =  query.list();
       // if(list!=null)
       // {
         //   return list.get(0);
       // }
       // return null;
    //}

    @Override
    public String update(int couponId, String cTitle) {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("UPDATE coupon_master SET cp_id=:cp_id cp_title=:cp_title WHERE cp_id=:cp_id");
        query.setParameter("cp_title", cTitle); 
        query.setParameter("cp_id", couponId);
        if(query.executeUpdate()!=0)
        {
            return "success";
        }
        
        return "failure";
        
    }
    
    

       @Override
        public String removeByCouponId(int couponId) {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("DELETE FROM coupon_master WHERE coupon_id=:coupon_id");
        query.setParameter("coupon_id", couponId);
        int row = query.executeUpdate();
        query.executeUpdate();
        return "success";
    }

    @Override
    public List<Object[]> readAllData(String amount, String startDate, String expiryDate) {
     Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT  amount, start_date, expiry_date FROM coupon_master WHERE (start_date >=:start_date AND expiry_date <= :expiry_date)");
        query.setParameter("amount", amount);
        query.setParameter("start_date", startDate);
        query.setParameter("expiry_date", expiryDate);
        return query.list();    
    }

  @Override
    public List<Object[]> readAllCountry() {
        Session session = sessionFactory.openSession();
        Query query = session.createSQLQuery("SELECT c_id, c_name amount FROM country_master");
        return query.list();
        
    }

    

    

    


    
    
}
