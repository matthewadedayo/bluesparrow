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
public interface FaqsMasterService {
    
    
    public List<Object[]> readAll();
    
    public List<Object[]> readAllByUserMaster(int userMasterId);

    public String create(String question, String answer, String contVisibility, String createDate, int status);

    public Object[] readByContent(String fQuestion, String fAnswer, String createDate);

    public Object[] read(int id);

    public String update(int faqsMasterId, String fQuestion, String fAnswer, String fContVisibility, String createDate, int status);


    
}
