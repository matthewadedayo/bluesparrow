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
public interface FeatureMasterDao {

    public List<Object[]> readAll();

    public String create(int portalId, String featureText);

    public Object[] readByContent(String fQuestion, String fAnswer, String createDate);

    public Object[] read(int id);

    public String update(int faqsMasterId, String question, String answer, String contVisibility, String createDate, int status);

    public List<Object[]> readAllByPortalId(int portalId);

    


    
}
