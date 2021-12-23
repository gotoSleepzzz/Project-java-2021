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
import model.ManagerHistory;

/**
 *
 * @author TRUNG
 */
public class ManagerHistoryService {
    dbUtil db;
    public ManagerHistoryService(){
        db = dbUtil.getDbUtil();
    }
    public ArrayList<ManagerHistory> findAll(String userId){
        ArrayList<ManagerHistory> result = new ArrayList<>() ;
        ResultSet rs = db.executeQuery("select * from `LICH_SU_CHUYEN_TRANG_THAI` where doituong = ?", new Object[]{userId});
        try {
            while (rs.next()){
                ManagerHistory temp = new ManagerHistory();
                temp.setId(rs.getInt("id"));
                temp.setNguoiQL(rs.getString("nguoiquanly"));
                temp.setUserId(rs.getString("doituong"));
                temp.setOldStatus(rs.getString("trangthaicu"));
                temp.setNewStatus(rs.getString("trangthaimoi"));
                temp.setIdOldHospital(rs.getInt("noiquanlycu"));
                temp.setIdNewHospital(rs.getInt("noiquanlymoi"));
                temp.setTime(rs.getDate("thoigian"));
                result.add(temp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsumeHistoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
