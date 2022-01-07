/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import model.Account;
import model.ActivityHistory;
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
    AccountService accountService = AccountService.getInstance();
    ActivityHistoryService historyService;

    public QLTaiKhoanController(qltk_Admin view) {
        response = "";
        historyService = new ActivityHistoryService();
        accounts = accountService.findAll();
        db = dbUtil.getDbUtil();
        this.view = view;
        this.view.handlerShowHisBtn(new ShowHistoryEvent());
        this.view.handlerBanBtn(new BanEvent());
        this.view.setTableAccountModel(convertAccountToArray2D(accounts));
        this.view.addSearchBtnListener(new AddSearchButtonListener());
        this.view.setVisible(true);
    }

    public String[][] convertAccountToArray2D(ArrayList<Account> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        String data[][] = new String[list.size()][3];
        // convert list to data object 
        for (int i = 0; i < list.size(); i++) {
            Account acc = list.get(i);
            data[i][0] = acc.getUsername();
            data[i][1] = acc.getRole();
            if (acc.getStatus())
                data[i][2] = "Không khoá";
            else
                data[i][2] = "Bị khoá";
        }
        return data;
    }

    public String[][] convertHistoryToArray2D(ArrayList<ActivityHistory> list) {
        String data[][] = new String[list.size()][4];
        // convert list to data object 
        for (int i = 0; i < list.size(); i++) {
            ActivityHistory history = list.get(i);
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
                response = "Vui lòng chọn tài khoản mà bạn muốn xem lịch sử";
                JOptionPane.showMessageDialog(view, response, "Notification", JOptionPane.INFORMATION_MESSAGE);
            } else {
                Account account = accounts.get(index);
                String username = account.getUsername();
                ArrayList<ActivityHistory> temp = historyService.findById(username);
                view.setTableHistoryModel(convertHistoryToArray2D(temp));
            }
        }
    }

    class BanEvent implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = view.getSelectedRowTableAcc();
            if (index == -1) {
                response = "Vui lòng chọn tài khoản mà bạn muốn khóa/mở khóa";
                JOptionPane.showMessageDialog(view, response, "Notification", JOptionPane.INFORMATION_MESSAGE);
            } else {
                boolean status;
                Account account = accounts.get(index);
                status = !account.getStatus();
                account.setStatus(status);
                accountService.LockAccount(account, status);
                if (status)
                    view.getTableAccountModel().setValueAt("Không khoá", index, 2);
                else
                    view.getTableAccountModel().setValueAt("Bị khoá", index, 2);

            }
        }
    }

    class AddSearchButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String search = view.getContentSearch();
            // show message if search is empty
            if (search.equals("") || search.equals("Nhập tên tài khoản")) {
                // find all account
                accounts = accountService.findAll();
                view.setTableAccountModel(convertAccountToArray2D(accounts));
            } else {
                ArrayList<Account> list = new ArrayList<>();
                var account = AccountService.getInstance().findOne(search);
                if (account == null) {
                    response = "Không tìm thấy tài khoản";
                    JOptionPane.showMessageDialog(view, response, "Notification", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    list.add(account);
                    view.setTableAccountModel(convertAccountToArray2D(list));
                }
            }
        }
    }
}
