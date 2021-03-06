/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import control.QLNoiDieuTriController;
import model.Account;
import org.mindrot.jbcrypt.BCrypt;
import utils.dbUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Account;
import org.apache.logging.log4j.LogManager;

/**
 * @author TRUNG
 */


public class AccountService {
    dbUtil db;
    // make AccountService singleton
    private static AccountService instance;



    static org.apache.logging.log4j.Logger logger = LogManager.getLogger(AccountService.class);
 public static AccountService getInstance() {
        if (instance == null) {
            instance = new AccountService();
        }
        return instance;
    }

    public AccountService() {
        db = dbUtil.getDbUtil();
    }

    private String queryUser = "SELECT * FROM account where username = ?";

    private String addUser = "INSERT INTO account(username, password, role) VALUES(?,?,?)";

    private String updateAccount = "UPDATE account SET password = ?,role = ? WHERE username = ?";


    public Account addOne(String username, String password, String role) {

        String hashPass = BCrypt.hashpw(password, BCrypt.gensalt(12));
        if (password.equals(""))
            hashPass = "";
        Object[] params = {username, hashPass, role};
        logger.info("insert account with role = " + role + " and username = " + username);
        db.executeUpdate(addUser, params);
        return findOne(username);
    }

    public int updateOne(String username, String password, String role) {
        String hashPass = BCrypt.hashpw(password, BCrypt.gensalt(12));
        if (password.equals(""))
            hashPass = "";
        Object[] params = {hashPass, role, username};
        logger.info("update account with username = " + username + " and role = " + role);
        return db.executeUpdate(updateAccount, params);
    }

    public Account findOne(String username) {
        Object[] params = {username};
        logger.info("Select an account with username = " + username);
        try (ResultSet rs = db.executeQuery(queryUser, params)) {
            try {
                if (rs.next()) {
                    String username1 = rs.getString("username");
                    String password = rs.getString("password");
                    String role = rs.getString("role");
                    boolean status = rs.getBoolean("status");
                    Account acc = new Account(username1, password, role, status);
                    return acc;
                }
            } catch (SQLException ex) {
                logger.error(ex);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


    public ArrayList<Account> findAll() {
        ArrayList<Account> accounts = new ArrayList<>();
        try {
            logger.info("Select all account");
            ResultSet rs = db.executeQuery("select * from `account`");
            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                String role = rs.getString("role");
                boolean status = rs.getBoolean("status");
                Account acc = new Account(username, password, role, status);
                accounts.add(acc);
            }
        } catch (SQLException ex) {
            logger.error(ex);
        }
        return accounts;
    }

    public void LockAccount(Account account, boolean status) {
        logger.info("Update status = " + status + " of account = " + account.getUsername());
        db.executeUpdate("update `account` set status = ? where username = ?", new Object[]{status, account.getUsername()});
    }


}
