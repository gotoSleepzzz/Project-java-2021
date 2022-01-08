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
import model.ConsumeHistory;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author TRUNG
 */
public class ConsumeHistoryService {
    dbUtil db;
    private static ConsumeHistoryService single_instance;
    static org.apache.logging.log4j.Logger logger = LogManager.getLogger(ConsumeHistoryService.class);
    public ConsumeHistoryService(){
        db = dbUtil.getDbUtil();
    }
    public static ConsumeHistoryService getInstance() {
        if (single_instance == null)
            single_instance = new ConsumeHistoryService();
        return single_instance;
    }
    public ArrayList<ConsumeHistory> findbyUserId(String userId){
        ArrayList<ConsumeHistory> result = new ArrayList<>() ;
        logger.info("Select from lich_su_mua with nguoimua = " + userId);
        ResultSet rs = db.executeQuery("select * from `LICH_SU_MUA` where nguoimua = ?", new Object[]{userId});
        try {
            while (rs.next()){
                ConsumeHistory temp = new ConsumeHistory();
                temp.setId(rs.getInt("id"));
                temp.setUserId(rs.getString("nguoimua"));
                temp.setNeccessaryId(rs.getInt("idgoinhupham"));
                temp.setQuantity(rs.getInt("soluong"));
                temp.setTotal(rs.getFloat("thanhtien"));
                temp.setTime(rs.getDate("thoigian"));
                result.add(temp);
            }
        } catch (SQLException ex) {
            logger.error(ex);
        }
        return result;
    }
    public int getConsumeTotal(int ProductId){
        int total = 0;
        ResultSet rs = db.executeQuery("select * from `LICH_SU_MUA` where idgoinhupham = ?", new Object[]{ProductId});
        try {
            while(rs.next()){
                total += rs.getInt("soluong");
            }
        } catch (SQLException ex) {
            logger.error(ex);
        }
        return total;
    }
}
