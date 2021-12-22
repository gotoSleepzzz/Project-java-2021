/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.dbUtil;
import model.History;
/**
 *
 * @author TRUNG
 */
public class ActivityHistoryService {
    dbUtil db;
    public ActivityHistoryService(){
        db = dbUtil.getDbUtil();
    }
    public ArrayList<History> findById(String id){
        ArrayList<History> temp = new ArrayList<>();
        ResultSet rs = db.executeQuery("select * from `LICH_SU_HOAT_DONG` where username = ?", new Object[]{id});
        try {
            while(rs.next()){
               temp.add(new History(rs.getString("username"),rs.getString("hanhdong"),rs.getString("tb"),rs.getString("msg"),rs.getDate("thoigian"))); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(ActivityHistoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return temp;
    }
}
