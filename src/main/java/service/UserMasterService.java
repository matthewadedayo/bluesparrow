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
public interface UserMasterService {
    
    public Object[] login(String username, String password);

    public List<Object[]> readAll();

    public String create(String username, String password, String firstName, String lastName, String type, String company, String designation, String email, String phoneNumber);

    public String updateStatus(int userId, int status);

    public Object[] readUser(int id);

    public String update(int userId, String username, String password, String firstName, String lastName, String type, String company, String designation, String email, String phoneNumber);

    public List<Object[]> readByType(String userType);

    public List<Object[]> readAllFromContentMaster();

    
}
