/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import utils.dbUtil;
import view.UserView;
import model.User;
/**
 *
 * @author TRUNG
 */
public class userController {
    dbUtil db;
    UserView view;
    User user;
    public userController(String username){
        db = dbUtil.getDbUtil();
        user = getInfoUser(username);
        
        view = new UserView();
        
        view.setNameField(user.getName());
        view.setAddressField(user.getAddress());
        view.setDobField(user.getBornYear().toString());
        view.setIdField(user.getCmnd());
        view.setLocationRelativeTo(null);
        
        view.setVisible(true);
    }
    private User getInfoUser(String username){
        User temp = null;
        ResultSet rs = db.executeQuery("Select * from `NGUOI_LIEN_QUAN` where cmnd = '" + username + "'");
        try {
            if(rs.next()){
                temp = new User(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getFloat(7)); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(userController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return temp;
    }
}
