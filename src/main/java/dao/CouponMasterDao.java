/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

/**
 *
 * @author OLAWALE
 */
public interface CouponMasterDao {

    public List<Object[]> readAll();
    
    public List<Object[]> readAllCountry();

    public List<Object[]> readAllData(String amount, String startDate, String expiryDate);

    public Object[] read(int id);
    
   // public Object[] read(String code);
    
    public List<Object[]> readByCountry(String country);

    public String create(String cTitle, String cCode, String discount, String desc, String country, 
            String currency,String amount, String minAmt, String maxAmt, String startDate, String expiryDate);

    public String update(int couponId, String cTitle);

    
    public String removeByCouponId(int couponId);
    
   //public Object[] readByAll(int couponId);


    
}
