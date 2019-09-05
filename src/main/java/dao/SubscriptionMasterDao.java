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
public interface SubscriptionMasterDao {

    public List<Object[]> readAll();

    public Object[] readByContent(String fQuestion, String fAnswer, String createDate);

    public Object[] read(int id);

    public List<Object[]> readAllByPortalId(int portalId);

    public String create(int portalId, String subName, String subPrice, String subPackage, String subCurrency);

    public Object[] readByAll(int portalId, String subName, String subPrice, String subPackage);

    public String update(int subId, int portalId, String subName, String subPrice, String subPackage, String subCurrency);

    


    
}
