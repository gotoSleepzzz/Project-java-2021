/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Hospital;
import org.apache.logging.log4j.LogManager;
import utils.dbUtil;

/**
 *
 * @author TRUNG
 */
public class HospitalService {
    dbUtil db;
    static org.apache.logging.log4j.Logger logger = LogManager.getLogger(HospitalService.class);
    public HospitalService(){
        db = dbUtil.getDbUtil();
    }
    public Hospital findByName(String ten){
        Hospital result = null;
        logger.info("Select from noi_quan_ly with ten = " + ten);
        ResultSet rs = db.executeQuery("Select * from `NOI_QUAN_LY` WHERE ten = '" + ten + "'");
        try {
            if (rs.next()){
                result = new Hospital(rs.getInt("id"),rs.getString("ten"),rs.getInt("succhua"),rs.getInt("soluongtiep"));
            }
        } catch (SQLException ex) {
            logger.error(ex);
        }
        return result;
    }
    public ArrayList<Hospital> findAll(){
        ArrayList<Hospital> hospitals = new ArrayList<>();
        logger.info("Select all from noi_quan_ly");
        ResultSet rs = db.executeQuery("select * from `NOI_QUAN_LY`");
        try {
            while(rs.next()){
                int id = rs.getInt("id");
                String ten = rs.getString("ten");
                int sucChua = rs.getInt("succhua");
                int SL = rs.getInt("soluongtiep");
                Hospital hos = new Hospital(id, ten, sucChua, SL);
                hospitals.add(hos);
            }
        } catch (SQLException ex) {
            logger.error(ex);
        }
        return hospitals;
    }
    public int delete(int id){
        logger.info("Delete from noi_quan_ly with id = " + id);
        return db.executeUpdate("delete from `NOI_QUAN_LY` where id = ?", new Object[]{id});
    }
    public void update(Hospital hos){
        Object[] temp = {hos.getTen(),hos.getSucChua(), hos.getSLHienTai(), hos.getId()};
        logger.info("Update from noi_quan_ly with id = " + hos.getId());
        db.executeUpdate("update `NOI_QUAN_LY` set ten = ?, succhua = ?, soluongtiep = ? where id = ?", temp);
    }
    public void insert(Hospital hos){
        Object[] temp = new Object[]{hos.getTen(),hos.getSucChua(),hos.getSLHienTai()};
        logger.info("Insert hospital");
        db.excuteProc("{CALL proc_ThemNoiQuanLy(?,?,?)}", temp);
    }
    public boolean existsUser(int id){
        logger.info("Select from noi_quan_ly where id = " + id + " and soluongtiep > 0");
        ResultSet rs = db.executeQuery("select * from `noi_quan_ly` where id = ? and soluongtiep > 0", new Object[]{id});
        try {
            if (rs.next()){
                return true;
            }       } catch (SQLException ex) {
            logger.error(ex);
        }
        return false;
    }
    public String getNamebyId(int id){
        logger.info("Select ten from noi_quan_ly with id = " + id);
        ResultSet rs = db.executeQuery("Select ten from `NOI_QUAN_LY` where id = ?",new Object[]{id});
        try {
            if (rs.next())
                return rs.getString(1);
        } catch (SQLException ex) {
            logger.error(ex);
        }
        return "";
    }
}
