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
public interface FaqPortalMappingService {
    
    
    public List<Object[]> readAll();
    
    public List<Object[]> readAllByUserMaster(int userMasterId);

    public String create(int portalId, int faqsId);

    public List<Object[]> readByFaqsMasterId(int faqsMasterId);

    public List<Object> readPortalIdByFaqsMasterId(int faqsMasterId);

    

    public String remove(int faqsMasterId);


    
}
