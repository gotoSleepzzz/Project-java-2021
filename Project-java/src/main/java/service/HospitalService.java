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
import model.Hospital;
import utils.dbUtil;

/**
 *
 * @author TRUNG
 */
public class HospitalService {
    dbUtil db;
    public HospitalService(){
        db = dbUtil.getDbUtil();
    }
    public Hospital findByName(String ten){
        Hospital result = null;
        ResultSet rs = db.executeQuery("Select * from `NOI_QUAN_LY` WHERE ten = '" + ten + "'");
        try {
            if (rs.next()){
                result = new Hospital(rs.getInt("id"),rs.getString("ten"),rs.getInt("succhua"),rs.getInt("soluongtiep"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HospitalService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public ArrayList<Hospital> findAll(){
        ArrayList<Hospital> hospitals = new ArrayList<>();
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
            Logger.getLogger(HospitalService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hospitals;
    }
    public void delete(int id){
        db.executeUpdate("delete from `NOI_QUAN_LY` where id = ?", new Object[]{id});
    }
    public void update(Hospital hos){
        Object[] temp = {hos.getTen(),hos.getSucChua(), hos.getSLHienTai(), hos.getId()};
        db.executeUpdate("update `NOI_QUAN_LY` set ten = ?, succhua = ?, soluongtiep = ? where id = ?", temp);
    }
    public void insert(Hospital hos){
        Object[] temp = new Object[]{hos.getTen(),hos.getSucChua(),hos.getSLHienTai()};
        db.excuteProc("{CALL proc_ThemNoiQuanLy(?,?,?)}", temp);
    }
    public boolean existsUser(int id){
        ResultSet rs = db.executeQuery("select * from `NGUOI_LIEN_QUAN` where idnoiquanly = ?", new Object[]{id});
        try {
            if (rs.next()){
                return true;
            }       } catch (SQLException ex) {
            Logger.getLogger(HospitalService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public String getNamebyId(int id){
        ResultSet rs = db.executeQuery("Select ten from `NOI_QUAN_LY` where id = ?",new Object[]{id});
        try {
            if (rs.next())
                return rs.getString(1);
        } catch (SQLException ex) {
            Logger.getLogger(HospitalService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
}
