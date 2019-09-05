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
public interface CouponMappingTypeDao {

   
    public Object[] read(int id);
    
    public String createSubscriptionMapping(int couponId, String portal,  String map_type, int map_id);

    public Object[] readBySubscriptionId(int id);
    
    public List<Object[]> readAllSubscription(int id);

   
    

    


    
}
