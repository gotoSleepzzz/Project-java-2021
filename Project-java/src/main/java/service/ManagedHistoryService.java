/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import utils.dbUtil;
import model.ManagerHistory;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author TRUNG
 */
public class ManagedHistoryService {
    dbUtil db;
    private static ManagedHistoryService single_instance;
    static org.apache.logging.log4j.Logger logger = LogManager.getLogger(ManagedHistoryService.class);
    public ManagedHistoryService(){
        db = dbUtil.getDbUtil();
    }
    public static ManagedHistoryService getInstance() {
        if (single_instance == null)
            single_instance = new ManagedHistoryService();
        return single_instance;
    }
    public ArrayList<ManagerHistory> findbyUserId(String userId){
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
            logger.error(ex);
        }
        return result;
    }
    public ArrayList<ManagerHistory> findAll(){
        ArrayList<ManagerHistory> result = new ArrayList<>() ;
        ResultSet rs = db.executeQuery("select * from `LICH_SU_CHUYEN_TRANG_THAI`");
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
            logger.error(ex);
        }
        return result;
    }
}
