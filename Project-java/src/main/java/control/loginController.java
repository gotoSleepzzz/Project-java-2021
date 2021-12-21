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

public class loginController{
    static dbUtil db;
    loginView login;
    public loginController() {
        db = dbUtil.getDbUtil();
        login = new loginView();
        login.setVisible(true);
        login.addLoginEvent(new loginEvent());
    }
    
    class loginEvent implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
                String username = login.getUsername();
                String password = login.getPass();
                if (username != null && password != null){
                    try {
                        ResultSet rs = db.executeQuery("Select * from `account` where `username` = '" + username + "'");
                        if (rs.next()){
                            String role = rs.getString(3);
                            if(role.equalsIgnoreCase("admin")){
                                login.setVisible(false);
                                login.dispose();
                                new adminView().setVisible(true);
                            }else if(role.equalsIgnoreCase("manager")){
                                new ViewManager();
                            }else /*if(role.equalsIgnoreCase("user"))*/{
                                // gắn abc...
                                login.dispose();
                                new userController(username);
                            }
                        }
                        else{
                            JOptionPane.showMessageDialog(login, "Tài khoản hoặc mật khẩu không chính xác!");
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(loginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        }
    }
    
}
