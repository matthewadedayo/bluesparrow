/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.CouponMappingTypeDao;
import java.util.List;

/**
 *
 * @author OLAWALE
 */
public class CouponMappingTypeServiceImp implements CouponMappingTypeService{
    
    private CouponMappingTypeDao couponMappingTypeDao;

    public void setCouponMappingTypeDao(CouponMappingTypeDao couponMappingTypeDao) {
        this.couponMappingTypeDao = couponMappingTypeDao;
    }

    
    
    
    @Override
    public Object[] read(int id) {
        return couponMappingTypeDao.read(id);
    }
    
    @Override
    public String  createSubscriptionMapping(int couponId, String portal,  String mapType, int mapId) {
       return couponMappingTypeDao.createSubscriptionMapping (couponId, portal, mapType, mapId);
    }

 
    @Override
    public List<Object[]> readAllSubscription(int id) {
        return couponMappingTypeDao.readAllSubscription(id);
    }
    
     @Override
    public Object[] readBySubscriptionId(int id) {
        return couponMappingTypeDao.readBySubscriptionId(id);
    }

}
    

    


