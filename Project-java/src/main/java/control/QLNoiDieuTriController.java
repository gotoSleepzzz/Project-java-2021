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
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public QLNoiDieuTriController(qlndt_Admin view){
        response = "";
        db = dbUtil.getDbUtil();
        this.view = view;
        this.view.handlerAddButton(new AddEvent());
        this.view.setVisible(true);
    }
    public class AddEvent implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String ten = view.getNameField();
                Integer sucChua = Integer.parseInt(view.getTotalField());
                Integer SoLuongTiepNhan;
                
                if (view.getCurField().equals(""))
                    SoLuongTiepNhan = 0;
                else SoLuongTiepNhan = Integer.parseInt(view.getCurField());
                
                ResultSet rs = db.executeQuery("Select * from `NOI_QUAN_LY` WHERE ten = '" + ten + "'");
                if (rs.next()){
                    response = "Đã tồn tại nơi điều trị " + '"' + ten + '"'; 
                }
                else{
                    db.excuteProc("{CALL proc_ThemNoiQuanLy(?,?,?)}", new Object[]{ten,sucChua,SoLuongTiepNhan});
                }
            } catch (SQLException ex) {
                Logger.getLogger(QLNoiDieuTriController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
}
