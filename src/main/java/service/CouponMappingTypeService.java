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
public interface CouponMappingTypeService {
    
    public Object[] read(int id);
    
    public String createSubscriptionMapping(int couponId, String portal,  String mapType, int mapId);

    public List<Object[]> readAllSubscription(int id);

   // public String removeAllSubscriptionByCouponId(int couponId);

    public Object[] readBySubscriptionId(int id);

    
}
