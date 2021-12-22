/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import view.admin.qlndt_Admin;
import utils.dbUtil;
import model.Hospital;
/**
 *
 * @author TRUNG
 */
public class QLNoiDieuTriController {
    qlndt_Admin view;
    dbUtil db;
    String response;
    ArrayList<Hospital> list;
    public QLNoiDieuTriController(qlndt_Admin view){
        response = "";
        db = dbUtil.getDbUtil();
        this.view = view;
        list = findAll();
        this.view.setTableModel(convertToArray2D(list));
        this.view.handlerAddButton(new AddEvent());
        this.view.handlerDelButton(new DelEvent());
        this.view.handlerUpdateButton(new UpdateEvent());
        this.view.setVisible(true);
    }
    class AddEvent implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.getCurField().equals("") || view.getNameField().equals("") || view.getTotalField().equals("")){
                response = "Vui lòng không để trống các ô";
                JOptionPane.showMessageDialog(view, response, "Cảnh báo",JOptionPane.ERROR_MESSAGE);
            }
            else{
                String ten = view.getNameField();
                Integer sucChua = Integer.parseInt(view.getTotalField());
                Integer SoLuongTiepNhan = Integer.parseInt(view.getCurField());
                
                Hospital hospital = findByName(ten);
                if (hospital != null){
                    response = "Đã tồn tại nơi điều trị có tên là '" + ten + "'";
                    JOptionPane.showMessageDialog(view, response, "Cảnh báo",JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    hospital = new Hospital(ten,sucChua,SoLuongTiepNhan);
                    insert(hospital);
                    hospital = findByName(ten);
                    list.add(hospital);
                    view.getTableModel().addRow(new Object[]{ten,sucChua,SoLuongTiepNhan});
                }
            }
        }   
    }
    class DelEvent implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            //System.out.println(view.getRow());
            int index = view.getSelectedRow();
            if (index == -1){
                response = "Vui lòng chọn nơi điều trị mà bạn muốn xóa";
                JOptionPane.showMessageDialog(view, response, "Thông báo",JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                Hospital hospital = list.get(index);
                if (existsUser(hospital.getId())){
                    response = "Vẫn còn tồn tại người điều trị tại nơi này";
                    JOptionPane.showMessageDialog(view, response, "Thông báo",JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    delete(hospital.getId());
                    list.remove(index);
                    view.getTableModel().removeRow(index);
                }
            }
        }
    }
    class UpdateEvent implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = view.getSelectedRow();
            if (index == -1){
                response = "Vui lòng chọn nơi điều trị mà bạn muốn cập nhật";
                JOptionPane.showMessageDialog(view, response, "Notification",JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                if (view.getCurField().equals("") || view.getNameField().equals("") || view.getTotalField().equals("")){
                    response = "Vui lòng không để trống các ô";
                    JOptionPane.showMessageDialog(view, response, "Warning",JOptionPane.ERROR_MESSAGE);
                }
                else{
                    String ten = view.getNameField();
                    Integer sucChua = Integer.parseInt(view.getTotalField());
                    Integer SoLuongTiepNhan = Integer.parseInt(view.getCurField());
                    
                    Hospital hospital = list.get(index);
                    hospital.setTen(ten);
                    hospital.setSucChua(sucChua);
                    hospital.setSLHienTai(SoLuongTiepNhan);
                    update(hospital);
                    
                    list.set(index, hospital);
                    view.getTableModel().setValueAt(ten, index, 0);
                    view.getTableModel().setValueAt(sucChua, index, 1);
                    view.getTableModel().setValueAt(SoLuongTiepNhan, index, 2);
                }
            }
        }
    }
    public Hospital findByName(String ten){
        Hospital result = null;
        ResultSet rs = db.executeQuery("Select * from `NOI_QUAN_LY` WHERE ten = '" + ten + "'");
        try {
            if (rs.next()){
                result = new Hospital(rs.getInt("id"),rs.getString("ten"),rs.getInt("succhua"),rs.getInt("soluongtiep"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(QLNoiDieuTriController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public ArrayList<Hospital> findAll(){
        ArrayList<Hospital> hospitals = new ArrayList<>();
        try {
            ResultSet rs = db.executeQuery("select * from `NOI_QUAN_LY`");
            while(rs.next()){
                int id = rs.getInt("id");
                String ten = rs.getString("ten");
                int sucChua = rs.getInt("succhua");
                int SL = rs.getInt("soluongtiep");
                Hospital hos = new Hospital(id, ten, sucChua, SL);
                hospitals.add(hos);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QLNoiDieuTriController.class.getName()).log(Level.SEVERE, null, ex);
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
    public String[][] convertToArray2D(List<Hospital> list){
        String data[][] = new String[list.size()][3];
        // convert list to data object 
        for (int i = 0; i < list.size(); i++) {
            Hospital hospital = list.get(i);
            data[i][0] = hospital.getTen();
            data[i][1] = hospital.getSucChua().toString();
            data[i][2] = hospital.getSLHienTai().toString();
        }
        return data;
    }
    public boolean existsUser(int id){
        try {
            ResultSet rs = db.executeQuery("select * from `NGUOI_LIEN_QUAN` where idnoiquanly = ?", new Object[]{id});
            if (rs.next()){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(QLNoiDieuTriController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
