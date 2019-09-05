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
public interface FaqPortalMappingDao {

    public List<Object[]> readAll();

    public String create(int portalId, int faqsId);

    public List<Object[]> readByFaqsMasterId(int faqsMasterId);

    public List<Object> readPortalIdByFaqsMasterId(int faqsMasterId);

    public String remove(int faqsMasterId);

    


    
}
