/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;

/**
 *
 * @author OLAWALE
 */
public interface CouponMasterService {
    
    
    public List<Object[]> readAll();
    
    public List<Object[]> readAllCountry();
    
    public List<Object[]> readByCountry(String country);
    
    public List<Object[]> readAllData(String amount, String startDate, String expiryDate);

    public String create(String cTitle, String cCode, String discount, String desc, String country, 
            String currency, String amount, String minAmt, String maxAmt, String startDate, String expiryDate);
    
    public String update(int couponId, String cTitle);

    public String removeByCouponId(int couponId);
    
    public Object[] read(int id);
    
    //public Object[] readByAll(int couponId);
}
