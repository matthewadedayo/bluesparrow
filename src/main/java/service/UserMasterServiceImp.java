/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.UserMasterDao;
import java.util.List;

/**
 *
 * @author OLAWALE
 */
public class UserMasterServiceImp implements UserMasterService{
    private UserMasterDao userMasterDao;

    @Override
    public Object[] login(String username, String password) {
        return userMasterDao.login(username, password);
    }

    public void setUserMasterDao(UserMasterDao userMasterDao) {
        this.userMasterDao = userMasterDao;
    }

    @Override
    public List<Object[]> readAll() {
        return userMasterDao.readAll();
    }

    @Override
    public String create(String username, String password, String firstName, String lastName, String type, String company, String  designation, String email, String phoneNumber) {
        return userMasterDao.create(username, password, firstName, lastName, type, company, designation, email, phoneNumber);
    }

    @Override
    public String updateStatus(int userId, int status) {
        return userMasterDao.updateStatus(userId, status);
    }

    @Override
    public Object[] readUser(int id) {
        return userMasterDao.readUser(id);
    }

    @Override
    public String update(int userId, String username, String password, String firstName, String lastName, String type, String company, String designation, String email, String phoneNumber) {
        return userMasterDao.update(userId, username, password, firstName, lastName, type, company, designation, email, phoneNumber);
    }

    @Override
    public List<Object[]> readByType(String userType) {
        return userMasterDao.readByType(userType);
    }

    @Override
    public List<Object[]> readAllFromContentMaster() {
        return userMasterDao.readAllFromContentMaster();
    }

}
