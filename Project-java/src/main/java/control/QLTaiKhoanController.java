/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import model.Account;
import model.History;
import service.AccountService;
import service.ActivityHistoryService;
import utils.dbUtil;
import view.admin.qltk_Admin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * @author TRUNG
 */
public class QLTaiKhoanController {
    qltk_Admin view;
    dbUtil db;
    String response;
    ArrayList<Account> accounts;
    AccountService accountService;
    ActivityHistoryService historyService;

    public QLTaiKhoanController(qltk_Admin view) {
        response = "";
        accountService = new AccountService();
        historyService = new ActivityHistoryService();
        accounts = accountService.findAll();
        db = dbUtil.getDbUtil();
        this.view = view;
        this.view.handlerShowHisBtn(new ShowHistoryEvent());
        this.view.handlerBanBtn(new BanEvent());
        this.view.setTableAccountModel(convertAccountToArray2D(accounts));
        this.view.setVisible(true);
    }

    public String[][] convertAccountToArray2D(ArrayList<Account> list) {
        String data[][] = new String[list.size()][4];
        // convert list to data object 
        for (int i = 0; i < list.size(); i++) {
            Account acc = list.get(i);
            data[i][0] = acc.getUsername();
            data[i][1] = acc.getPass();
            data[i][2] = acc.getRole();
            if (acc.getStatus())
                data[i][3] = "Unlocked";
            else
                data[i][3] = "Locked";
        }
        return data;
    }

    public String[][] convertHistoryToArray2D(ArrayList<History> list) {
        String data[][] = new String[list.size()][4];
        // convert list to data object 
        for (int i = 0; i < list.size(); i++) {
            History history = list.get(i);
            data[i][0] = history.getTime().toString();
            data[i][1] = history.getUser();
            data[i][2] = history.getMsg();
        }
        return data;
    }

    class ShowHistoryEvent implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = view.getSelectedRowTableAcc();
            if (index == -1) {
                response = "Please select a row on the table that you want to show";
                JOptionPane.showMessageDialog(view, response, "Notification", JOptionPane.INFORMATION_MESSAGE);
            } else {
                Account account = accounts.get(index);
                String username = account.getUsername();
                ArrayList<History> temp = historyService.findById(username);
                view.setTableHistoryModel(convertHistoryToArray2D(temp));
            }
        }
    }

    class BanEvent implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = view.getSelectedRowTableAcc();
            if (index == -1) {
                response = "Please select a row on the table that you want to lock/unlock";
                JOptionPane.showMessageDialog(view, response, "Notification", JOptionPane.INFORMATION_MESSAGE);
            } else {
                boolean status;
                Account account = accounts.get(index);
                status = !account.getStatus();
                account.setStatus(status);
                accountService.LockAccount(account, status);
                if (status)
                    view.getTableAccountModel().setValueAt("Unlocked", index, 3);
                else
                    view.getTableAccountModel().setValueAt("Locked", index, 3);

            }
        }
    }
}
