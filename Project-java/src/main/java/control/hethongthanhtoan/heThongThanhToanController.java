package control.hethongthanhtoan;

import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocket;
import javax.swing.JOptionPane;
import org.apache.logging.log4j.LogManager;
import utils.dbUtil;
import view.thanhtoan_server.ThanhtoanServer;

public class heThongThanhToanController {

    private ThanhtoanServer tts;
    private boolean isOn;
    private SSLServerSocket server = null;
    private List<SSLSocket> clientList;
    static org.apache.logging.log4j.Logger logger = LogManager.getLogger(heThongThanhToanController.class);

    public heThongThanhToanController() {
        tts = new ThanhtoanServer();
        isOn = false;
        clientList = new ArrayList<SSLSocket>();
        tts.addServerEvent(new powerServerEvent());
        tts.setVisible(true);
        checkAdminAccount();
    }

    public void checkAdminAccount() {
        try {
            ResultSet rs = dbUtil.getDbUtil().executeQuery("Select * from tai_khoan_giao_dich where tk = 'admin'");
            if (rs.next()) {
                tts.setTotal(rs.getLong(2));
                return;
            }
            dbUtil.getDbUtil().excuteProc("insert into tai_khoan_giao_dich (tk,sodu) value (?, ?)", new Object[]{"admin",0});
            tts.setTotal(0);
        } catch (SQLException ex) {
            Logger.getLogger(heThongThanhToanController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public class powerServerEvent implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (isOn == false) {
                On();
            } else {
                Off();
            }
        }
    }

    private void On() {
        try {
            int port = tts.getPort();
            if (port > 0) {
                
                System.setProperty("javax.net.ssl.keyStore", "myKeyStore.jks");
                System.setProperty("javax.net.ssl.keyStorePassword", "abc123");
                SSLServerSocketFactory sslsf = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
                server = (SSLServerSocket) sslsf.createServerSocket(port);
                tts.turnOn();
                isOn = true;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Thread thrd = new StartServer();
        thrd.start();
    }

    private void Off() {
        if (clientList.size() > 0) {
            JOptionPane.showMessageDialog(tts, "Vẫn còn giao dịch đang diễn ra", "Warning", JOptionPane.INFORMATION_MESSAGE);
        } else {
            try {
                isOn = false;
                server.close();
                tts.turnOff();
            } catch (IOException ex) {
                logger.error(ex);
            }
        }
    }

    public class StartServer extends Thread {

        @Override
        public void run() {
            while (isOn) {
                try {
                    new HandleClient((SSLSocket)server.accept()).start();
                } catch (IOException ex) {
                    logger.error(ex);
                }
            }
        }
    }

    public class HandleClient extends Thread {

        private SSLSocket s;

        public HandleClient(SSLSocket ss) {
            this.s = ss;
            clientList.add(ss);
        }

        @Override
        public void run() {
            try {
                PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
                BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                String cmnd = br.readLine();
                
                ResultSet rs1 = dbUtil.getDbUtil().executeQuery("select sodu from tai_khoan_giao_dich where tk = '" + cmnd +"'");
                long duno=0;
                if(rs1.next()){
                duno = rs1.getLong(1);
                }
                pw.println(duno);
                
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date1 = new Date();
                DecimalFormat df = new DecimalFormat("###,###,###");
                
                String recv = br.readLine();
                
                if (recv.equalsIgnoreCase("bye")){
                    s.close();
                    return;
                }

                float sotien = Float.parseFloat(recv);

                ResultSet rs2 = dbUtil.getDbUtil().executeQuery("call proc_ThanhToanGiaoDich (?,?,?);", new Object[]{"admin", cmnd, sotien});
                rs2.next();
                int res = rs2.getInt(1);
                
                Date date = new Date();
                String log;
                if (res == 1) {
                    pw.println("Thanh toán thành công!");
                    log = String.format("* %20s  -  %12s  -  Thanh toan thanh cong -  %15s %3s",
                            dateFormat.format(date), cmnd, df.format(sotien), "vnđ");
                    tts.updateTotal(sotien);
                } else {
                    pw.println("Tài khoản không đủ tiền, thanh toán thất bại!");
                    log = String.format("* %20s  -  %12s  -  Thanh toan that bai!!!",
                            dateFormat.format(date), cmnd);
                }
                tts.insertLog(log);
                clientList.remove(s);
                s.close();
            } catch (IOException ex) {
                logger.error(ex);
            } catch (SQLException ex) {
                Logger.getLogger(heThongThanhToanController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
