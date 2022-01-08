/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.PaymentHistory;
import org.apache.logging.log4j.LogManager;
import utils.dbUtil;
/**
 *
 * @author TRUNG
 */
public class PaymentHistoryService {
    dbUtil db;
    static org.apache.logging.log4j.Logger logger = LogManager.getLogger(PaymentHistoryService.class);
    public PaymentHistoryService(){
        db = dbUtil.getDbUtil();
    }
    public ArrayList<PaymentHistory> findAll(String userId){
        ArrayList<PaymentHistory> result = new ArrayList<>() ;
        logger.info("Select from lich_su_thanh_toan where cmnd = " + userId);
        ResultSet rs = db.executeQuery("select * from `LICH_SU_THANH_TOAN` where cmnd = ?", new Object[]{userId});
        try {
            while (rs.next()){
                PaymentHistory temp = new PaymentHistory();
                temp.setUserId(userId);
                temp.setMoney(rs.getFloat("sotien"));
                temp.setTime(rs.getDate("thoigian"));
                result.add(temp);
            }
        } catch (SQLException ex) {
            logger.error(ex);
        }
        return result;
    }
}
