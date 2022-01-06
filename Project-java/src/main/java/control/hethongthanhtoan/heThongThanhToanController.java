package control.hethongthanhtoan;

import java.io.*;
import javax.net.ssl.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.logging.log4j.LogManager;
import utils.AppConstraints;
import utils.dbUtil;
import view.thanhtoan_server.ThanhtoanServer;

public class heThongThanhToanController {

    private ThanhtoanServer tts;
    private boolean isOn;
    private SSLServerSocket server = null;
    private List<Socket> clientList;
    static org.apache.logging.log4j.Logger logger = LogManager.getLogger(heThongThanhToanController.class);

    public heThongThanhToanController() {
        tts = new ThanhtoanServer();
        isOn = false;
        clientList = new ArrayList<Socket>();
        tts.addServerEvent(new powerServerEvent());
        tts.setVisible(true);
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
                System.setProperty("javax.net.ssl.keyStore", "za.store");
                System.setProperty("javax.net.ssl.keyStorePassword", "adminadmin");

                SSLServerSocketFactory sslsf = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
                server = (SSLServerSocket) sslsf.createServerSocket(port);
                tts.turnOn();
                isOn = true;
            }
        } catch (IOException ex) {
            logger.error(ex);
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
                    new HandleClient(server.accept()).start();
                } catch (IOException ex) {
                    logger.error(ex);
                }
            }
        }
    }

    public class HandleClient extends Thread {

        private Socket s;

        public HandleClient(Socket ss) {
            this.s = ss;
            clientList.add(ss);
        }

        @Override
        public void run() {
            try {
                PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
                BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                String recv = br.readLine();

                String temp[] = recv.split("-");
                String cmnd = temp[0];
                int sotien = Integer.parseInt(temp[1]);

                int res = dbUtil.getDbUtil().executeUpdate("call proc_ThanhToanGiaoDich (?,?,?);", new Object[]{"admin", cmnd, sotien});

                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                DecimalFormat df = new DecimalFormat("###,###,###");
                String log;
                if (res > 1) {
                    pw.println("Thanh toán thành công!");
                    log = String.format("* %20s  -  %12s  -  Thanh toan  -  %15s %3s",
                            dateFormat.format(date), cmnd, df.format(sotien), "vnđ");
                    tts.insertLog(log);
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
            }
        }
    }

}
