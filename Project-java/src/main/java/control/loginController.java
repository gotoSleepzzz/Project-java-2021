package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Account;
import utils.dbUtil;
import view.ViewManager;
import view.admin.adminView;
import view.loginView;

public class loginController {

    static dbUtil db;
    loginView login;
    String username;

    public loginController() {
        db = dbUtil.getDbUtil();
        login = new loginView();
        login.addLoginEvent(new loginEvent());
        login.addCreateAdminEvent(new createAdmin());
        login.addCreatNewPassEvent(new createNewPass());
        if (this.isFirstRun()) {
            login.showCreateAdminView();
        }
        login.setVisible(true);
    }

    private boolean isFirstRun() {

        ResultSet rs = db.executeQuery("Select * from `account` where `role` = 'admin'");
        try {
            return !(rs.next());
        } catch (SQLException ex) {
            Logger.getLogger(loginController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public class createNewPass implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String newPass = login.getNewPass();
            String confirmPass = login.getConfirmPass();
            System.out.println(newPass);
            System.out.println(confirmPass);
            if (newPass.equalsIgnoreCase(confirmPass)) {
                String query = "update `account` set `password` = '" + newPass + "' where `username`= '" + username + "'";
                db.executeUpdate(query);
                login.showLoginView();
            } else {
                JOptionPane.showMessageDialog(login, "Mật khẩu không trùng khớp", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    class createAdmin implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = login.getAdmiName();
            String password = login.getAdminPass();
            if (username != null && password != null) {
                if (username.length() < 1 || password.length() < 1) {
                    JOptionPane.showMessageDialog(login, "Vui lòng điền đầy đủ thông tin", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    String query = "Insert into `account` (`username`,`password`,`role`) values (?,?,?)";
                    db.executeUpdate(query, new Object[]{username, password, "admin"});
                    login.showLoginView();
                }
            }
        }
    }

    class loginEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            username = login.getUsername();
            String password = login.getPass();
            if (username != null && password != null) {
                if (username.length() < 1) {
                    JOptionPane.showMessageDialog(login, "Vui lòng điền đầy đủ thông tin", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    try {
                        ResultSet rs = db.executeQuery("Select * from `account` where `username` = '" + username + "'");
                        if (rs.next()) {
                            username = rs.getString("username");
                            String rol = rs.getString("role");
                            String pass = rs.getString("password");
                            boolean status = rs.getBoolean("status");

                            if (pass.length() < 1) {
                                login.showCreateNewPass();
                            } else if (password.equalsIgnoreCase(pass)) {
                                if (status == true) {
                                    String role = rs.getString(3);
                                    if (role.equalsIgnoreCase("admin")) {
                                        login.setVisible(false);
                                        login.dispose();
                                        new adminView().setVisible(true);
                                    } else if (role.equalsIgnoreCase("manager")) {
                                        new ViewManager();
                                    } else {
                                        login.setVisible(false);
                                        login.dispose();
                                        new userController(username);

                                    }
                                } else {
                                    JOptionPane.showMessageDialog(login, "Tài khoản đã bị khóa", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                                }
                            } else {
                                JOptionPane.showMessageDialog(login, "Tài khoản hoặc mật khẩu không chính xác!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(login, "Tài khoản hoặc mật khẩu không chính xác!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(loginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }

}
