/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.CouponMasterDao;
import java.util.List;

/**
 *
 * @author OLAWALE
 */
public class CouponMasterServiceImp implements CouponMasterService{
    private CouponMasterDao couponMasterDao;

    
    
    @Override
    public List<Object[]> readAll() {
        return couponMasterDao.readAll();
    }
    
     @Override
    public List<Object[]> readAllCountry() {
        return couponMasterDao.readAllCountry();
    }

    public void setCouponMasterDao(CouponMasterDao couponMasterDao) {
        this.couponMasterDao = couponMasterDao;
    }

  

    @Override
    public String create(String cTitle, String cCode, String discount, String desc, String country, 
            String currency, String amount, String minAmt, String maxAmt, String startDate, String expiryDate) {
        return couponMasterDao.create(cTitle, cCode, discount, desc, country, currency, amount, minAmt, maxAmt, 
                startDate, expiryDate);
    }

    @Override
    public String update (int couponId, String cTitle){
        
        return couponMasterDao.update(couponId, cTitle);
    }
    
    
    @Override
    public List<Object[]> readByCountry(String country) {
        return couponMasterDao.readByCountry(country);
    } 

    @Override
    public String removeByCouponId(int couponId) {
        return couponMasterDao.removeByCouponId(couponId);
   }
   
  @Override
    public Object[] read(int id) {
        return couponMasterDao.read(id);
    }

  /**
     @Override
    public Object[] readByAll(int couponId){
      return couponMasterDao.readByAll(couponId);
  }
     **/
   

    
    @Override
    public List<Object[]> readAllData(String amount, String startDate, String expiryDate){
    return couponMasterDao.readAllData(amount, startDate, expiryDate);
    
  }
}

    

    

    

    

    

    


