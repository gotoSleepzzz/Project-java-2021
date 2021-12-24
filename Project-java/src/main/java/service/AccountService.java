/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import control.QLNoiDieuTriController;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import utils.dbUtil;

/**
 *
 * @author TRUNG
 */


public class AccountService{
    dbUtil db;
    public AccountService(){
        db = dbUtil.getDbUtil();
    }
    public ArrayList<Account> findAll(){
        ArrayList<Account> accounts = new ArrayList<>();
        try {
            ResultSet rs = db.executeQuery("select * from `account`");
            while(rs.next()){
                String username = rs.getString("username");
                String password = rs.getString("password");
                String role = rs.getString("role");
                boolean status = rs.getBoolean("status");
                Account acc = new Account(username,password,role,status);
                accounts.add(acc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QLNoiDieuTriController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return accounts;
    }
    public void LockAccount(Account account,boolean status){
        db.executeUpdate("update `account` set status = ? where username = ?",new Object[]{status,account.getUsername()});
    }
    
    
}
