/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import utils.dbUtil;
import view.UserView;
import model.UserCovid;
import model.ConsumeHistory;
import model.ManagerHistory;
import service.ManagerService;
import service.ConsumeHistoryService;
import service.ManagerHistoryService;
import service.HospitalService;
/**
 *
 * @author TRUNG
 */
public class userController {
    dbUtil db;
    UserView view;
    UserCovid user;
    ArrayList<ConsumeHistory> listConsumeHistory;
    ArrayList<ManagerHistory> listManagerHistory;
    ManagerService managerService;
    ConsumeHistoryService consumeHistory;
    ManagerHistoryService managerHistory;
    HospitalService hospitalService;
    public userController(String username){
        db = dbUtil.getDbUtil();
        managerService = new ManagerService();
        consumeHistory = new ConsumeHistoryService();
        managerHistory = new ManagerHistoryService();
        hospitalService = new HospitalService();
        
        user = managerService.findUserCovidByID(username);
        view = new UserView();
        
        // Set thông tin cá nhân
        view.setNameField(user.getName());
        view.setAddressField(user.getAddress());
        view.setDobField(user.getDob().toString());
        view.setIdField(user.getId());
        
        // Set dư nợ
        view.setDebtField(String.valueOf(user.getDebt()));
        
        //Bắt sự kiện các nút
        view.AddEventShowConsumeHistory(new ShowConsumeHistoryEvent());
        view.AddEventShowManageHistory(new ShowManagedHistoryEvent());
        
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }
    class ShowConsumeHistoryEvent implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            listConsumeHistory = consumeHistory.findAll(user.getId());
            view.setDataConsumeHistoryTable(convertConsumeListToArray2D(listConsumeHistory));
            view.ConsumeHistory(view);
        }  
    }
    class ShowManagedHistoryEvent implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            listManagerHistory = managerHistory.findAll(user.getId());
            view.setDataManageHistoryTable(convertManagedListToArray2D(listManagerHistory));
            view.ManageHistory(view);
        }  
    }
    public String[][] convertConsumeListToArray2D(ArrayList<ConsumeHistory> list){
        String data[][] = new String[list.size()][4];
        // convert list to data object 
        for (int i = 0; i < list.size(); i++) {
            ConsumeHistory history = list.get(i);
            data[i][0] = history.getNeccessaryId().toString();
            data[i][1] = history.getQuantity().toString();
            data[i][2] = history.getTotal().toString();
            data[i][3] = history.getTime().toString();
        }
        return data;
    }
    public String[][] convertManagedListToArray2D(ArrayList<ManagerHistory> list){
        String data[][] = new String[list.size()][5];
        // convert list to data object 
        for (int i = 0; i < list.size(); i++) {
            ManagerHistory history = list.get(i);
            data[i][0] = history.getOldStatus();
            data[i][1] = history.getNewStatus();
            data[i][2] = hospitalService.getNamebyId(history.getIdOldHospital());
            data[i][3] = hospitalService.getNamebyId(history.getIdNewHospital());
            data[i][4] = history.getTime().toString();
        }
        return data;
    }
}
