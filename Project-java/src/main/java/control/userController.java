/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.swing.JOptionPane;
import model.Account;
import utils.dbUtil;
import view.UserView;
import model.UserCovid;
import model.ConsumeHistory;
import model.ManagerHistory;
import model.PaymentHistory;
import org.mindrot.jbcrypt.BCrypt;
import service.AccountService;
import org.apache.logging.log4j.LogManager;
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

    static org.apache.logging.log4j.Logger logger = LogManager.getLogger(userController.class);
    dbUtil db;
    UserView view;
    UserCovid user;
    Account acc;
    ArrayList<ConsumeHistory> listConsumeHistory;
    ArrayList<ManagerHistory> listManagerHistory;
    ArrayList<PaymentHistory> listPaymentHistory;
    ManagerService managerService;
    AccountService accountService;
    ConsumeHistoryService consumeHistory;
    ManagedHistoryService managerHistory;
    PaymentHistoryService paymentHistory;
    HospitalService hospitalService;
    SSLSocket sslSocket;
    String username;

    public userController(String username) {
        this.username = username;
        db = dbUtil.getDbUtil();
        managerService = new ManagerService();
        consumeHistory = new ConsumeHistoryService();
        managerHistory = new ManagedHistoryService();
        paymentHistory = new PaymentHistoryService();
        hospitalService = new HospitalService();

        user = managerService.findOneUserCovid(username);

        view = new UserView(username);
        

        // Set th??ng tin c?? nh??n
        view.setNameField(user.getName());
        view.setAddressField(user.getAddress());
        view.setDobField(user.getDob().toString());
        view.setIdField(user.getId());

        // Set d?? n???
        view.setDebtField(String.valueOf(user.getDebt()));

        //B???t s??? ki???n c??c n??t
        view.AddEventShowConsumeHistory(new ShowConsumeHistoryEvent());
        view.AddEventShowManageHistory(new ShowManagedHistoryEvent());
        view.AddEventShowPaymentHistory(new ShowPaymentHistoryEvent());
        view.AddEventChangePassword(new ShowChangePasswordEvent()); // show change password form
        //view.AddEventClickChangePassword(new ClickChangePasswordEvent()); // click change password
        view.AddEventBuy(new BuyEvent());
        view.AddEventPay(new PayEvent());

        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }

    class ShowConsumeHistoryEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            listConsumeHistory = consumeHistory.findbyUserId(user.getId());
            view.setDataConsumeHistoryTable(convertConsumeListToArray2D(listConsumeHistory));
            view.ConsumeHistory(view);
        }
    }

    class ShowManagedHistoryEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            listManagerHistory = managerHistory.findbyUserId(user.getId());
            view.setDataManageHistoryTable(convertManagedListToArray2D(listManagerHistory));
            view.ManageHistory(view);
        }
    }

    class ShowPaymentHistoryEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            listPaymentHistory = paymentHistory.findAll(user.getId());
            view.setDataPaymentHistoryTable(convertPaymentListToArray2D(listPaymentHistory));
            view.PaymentHistory(view);
        }
    }

    class BuyEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            view.Buy();
        }
    }

    class PayEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.setProperty("javax.net.ssl.trustStore", "myTrustStore.jts");
            System.setProperty("javax.net.ssl.trustStorePassword", "abc123");

            SSLSocketFactory sslsFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            try {
                sslSocket = (SSLSocket) sslsFactory.createSocket("Localhost", 5050);
                PrintWriter pw = new PrintWriter(sslSocket.getOutputStream(), true);
                BufferedReader br = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));
                pw.println(username);
                String recv = br.readLine();

                int sodu = Integer.parseInt(recv);
                ResultSet rs1 = dbUtil.getDbUtil().executeQuery("select ghino from nguoi_lien_quan where cmnd = '" + username + "'");
                rs1.next();
                float ghino = rs1.getFloat(1);
                float hanmuc = (float) (ghino * 0.5);
                view.Pay(view, sodu, ghino, hanmuc, new PayAction());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Kh??ng th??? k???t n???i server", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
                logger.error(ex);
            }

        }
    }

    class ShowChangePasswordEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            view.ChangePassword(view, new ClickChangePasswordEvent());
        }
    }

    class ClickChangePasswordEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            acc = AccountService.getInstance().findOne(username);
            String oldPass = view.getOldPass();
            String newPass = view.getNewPass();
            String reEnterPass = view.getReEnterPass();

            if (oldPass == null || newPass == null || reEnterPass == null) {
                view.setPasswordWarning("Vui l??ng nh???p th??ng tin ?????y ?????");
            } else {
                if ((newPass.length() < 6) || !newPass.matches(".*[a-zA-Z].*") || !newPass.matches(".*[0-9].*")) {
                    view.setPasswordWarning("Vui l??ng nh???p m???t kh???u ??t nh???t 6 k?? t??? bao g???m c??? ch??? v?? s???, kh??ng bao g???m kho???ng tr???ng");
                } else {
                    if (!newPass.equals(reEnterPass)) {
                        view.setPasswordWarning("M???t kh???u m???i kh??ng tr??ng kh???p");
                    } else if (BCrypt.checkpw(oldPass, acc.getPass())) {
                        if (AccountService.getInstance().updateOne(username, newPass, "user") != -1) {
                            view.setPasswordWarning("?????i m???t kh???u th??nh c??ng");
                        }
                    } else {
                        view.setPasswordWarning("M???t kh???u hi???n t???i kh??ng ch??nh x??c");
                    }
                }
            }
        }
    }

    class PayAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                float sotien = view.getSoTienThanhToan();
                float hanmuc = view.getHanMuc();
                float ghino = view.getGhiNo();
                System.out.println(sotien + ", " + hanmuc + ", " + ghino);
                if (ghino < 0.00001) {
                    JOptionPane.showMessageDialog(view, "Kh??ng c?? ghi n???", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (sotien < 0 || hanmuc < 0 || ghino < 0 || sotien < hanmuc || sotien > ghino) {
                    JOptionPane.showMessageDialog(view, "Vui l??ng nh???p ????ng th??ng tin", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                PrintWriter pw = new PrintWriter(sslSocket.getOutputStream(), true);
                BufferedReader br = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));

                pw.println(sotien);
                String rep = br.readLine();
                if (rep.equalsIgnoreCase("Thanh to??n th??nh c??ng!")) {
                    JOptionPane.showMessageDialog(view, "Thanh to??n th??nh c??ng!", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
                    dbUtil.getDbUtil().executeUpdate("call proc_ThanhToanGhiNo (?,?)", new Object[]{username, sotien});
                    view.setDebtField(String.valueOf(ManagerService.getInstance().findOneUserCovid(user.getId()).getDebt()));
                } else {
                    JOptionPane.showMessageDialog(view, "Thanh to??n kh??ng th??nh c??ng", "Th??ng b??o", JOptionPane.INFORMATION_MESSAGE);
                }
                sslSocket.close();
                view.closePayment();
            } catch (IOException ex) {
                logger.error(ex);
            }

        }

    }

    public String[][] convertConsumeListToArray2D(ArrayList<ConsumeHistory> list) {
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

    public String[][] convertManagedListToArray2D(ArrayList<ManagerHistory> list) {
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

    public String[][] convertPaymentListToArray2D(ArrayList<PaymentHistory> list) {
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
