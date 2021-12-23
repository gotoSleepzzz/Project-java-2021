package control;

import utils.dbUtil;
import view.ViewManager;
import view.admin.adminView;
import view.loginView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class loginController {
    static dbUtil db;
    loginView login;

    public loginController() {
        db = dbUtil.getDbUtil();
        login = new loginView();
        login.setVisible(true);
        login.addLoginEvent(new loginEvent());
    }

    class loginEvent implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = login.getUsername();
            String password = login.getPass();
            if (!username.equals("") && !password.equals("")) {
                try {
                    ResultSet rs = db.executeQuery("Select * from `account` where `username` = '" + username + "'");
                    if (rs.next()) {
                        boolean status = rs.getBoolean("status");
                        if (status == true) {
                            String role = rs.getString(3);
                            if (role.equalsIgnoreCase("admin")) {
                                login.setVisible(false);
                                login.dispose();
                                new adminView().setVisible(true);
                            } else if (role.equalsIgnoreCase("manager")) {
                                login.dispose();
                                new ManagerController();
                            } else /*if(role.equalsIgnoreCase("user"))*/ {
                                // gắn abc...
                                login.dispose();
                                new userController(username);
                            }
                        } else {
                            String response = "Tài khoản của bạn đã bị khóa";
                            JOptionPane.showMessageDialog(login, response, "Notification", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(login, "Tài khoản hoặc mật khẩu không chính xác!");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(loginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
