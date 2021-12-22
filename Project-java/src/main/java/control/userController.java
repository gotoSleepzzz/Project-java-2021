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
import utils.dbUtil;
import view.UserView;
import model.UserCovid;
/**
 *
 * @author TRUNG
 */
public class userController {
    dbUtil db;
    UserView view;
    UserCovid user;
    public userController(String username){
        db = dbUtil.getDbUtil();
        user = getInfoUser(username);
        
        view = new UserView();
        
        view.setNameField(user.getName());
        view.setAddressField(user.getAddress());
        view.setDobField(user.getDob().toString());
        view.setIdField(user.getId());
        view.setLocationRelativeTo(null);
        
        view.setVisible(true);
    }
    private UserCovid getInfoUser(String username){
        UserCovid temp = null;
        ResultSet rs = db.executeQuery("Select * from `NGUOI_LIEN_QUAN` where cmnd = '" + username + "'");
        try {
            if(rs.next()){
                temp = new UserCovid(rs.getString("ten"),rs.getString("cmnd"),rs.getInt("namsinh"),rs.getString("diachi"),rs.getString("trangthai"),rs.getInt("idnoiquanly"),rs.getFloat("ghino")); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(userController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return temp;
    }
}
