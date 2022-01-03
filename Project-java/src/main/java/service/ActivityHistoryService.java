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
import utils.dbUtil;
import model.ActivityHistory;
import org.apache.logging.log4j.LogManager;
/**
 *
 * @author TRUNG
 */
public class ActivityHistoryService {
    dbUtil db;
    static org.apache.logging.log4j.Logger logger = LogManager.getLogger(ActivityHistoryService.class);
    public ActivityHistoryService(){
        db = dbUtil.getDbUtil();
    }
    public ArrayList<ActivityHistory> findById(String id){
        ArrayList<ActivityHistory> temp = new ArrayList<>();
        ResultSet rs = db.executeQuery("select * from `LICH_SU_HOAT_DONG` where username = ?", new Object[]{id});
        try {
            while(rs.next()){
               temp.add(new ActivityHistory(rs.getString("username"),rs.getString("hanhdong"),rs.getString("tb"),rs.getString("msg"),rs.getDate("thoigian"))); 
            }
        } catch (SQLException ex) {
            logger.error(ex);
        }
        return temp;
    }
}
