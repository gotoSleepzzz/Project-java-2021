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
import model.PaymentHistory;
import service.ManagerService;
import service.ConsumeHistoryService;
import service.ManagedHistoryService;
import service.HospitalService;
import service.PaymentHistoryService;
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
    ArrayList<PaymentHistory> listPaymentHistory;
    ManagerService managerService;
    ConsumeHistoryService consumeHistory;
    ManagedHistoryService managerHistory;
    PaymentHistoryService paymentHistory;
    HospitalService hospitalService;
    public userController(String username){
        db = dbUtil.getDbUtil();
        managerService = new ManagerService();
        consumeHistory = new ConsumeHistoryService();
        managerHistory = new ManagedHistoryService();
        paymentHistory = new PaymentHistoryService();
        hospitalService = new HospitalService();
        
        user = managerService.findOneUserCovid(username);
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
        view.AddEventShowPaymentHistory(new ShowPaymentHistoryEvent());
        
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }
    class ShowConsumeHistoryEvent implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            listConsumeHistory = consumeHistory.findbyUserId(user.getId());
            view.setDataConsumeHistoryTable(convertConsumeListToArray2D(listConsumeHistory));
            view.ConsumeHistory(view);
        }  
    }
    class ShowManagedHistoryEvent implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            listManagerHistory = managerHistory.findbyUserId(user.getId());
            view.setDataManageHistoryTable(convertManagedListToArray2D(listManagerHistory));
            view.ManageHistory(view);
        }  
    }
    class ShowPaymentHistoryEvent implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            listPaymentHistory = paymentHistory.findAll(user.getId());
            view.setDataPaymentHistoryTable(convertPaymentListToArray2D(listPaymentHistory));
            view.PaymentHistory(view);
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
    
    public String[][] convertPaymentListToArray2D(ArrayList<PaymentHistory> list){
        String data[][] = new String[list.size()][5];
        // convert list to data object 
        for (int i = 0; i < list.size(); i++) {
            PaymentHistory history = list.get(i);
            data[i][0] = history.getMoney().toString();
            data[i][1] = history.getTime().toString();
        }
        return data;
    }
}
