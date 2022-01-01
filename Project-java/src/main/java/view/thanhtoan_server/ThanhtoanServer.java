package view.thanhtoan_server;

import java.awt.Color;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class ThanhtoanServer extends JFrame {
    private JTextField addressInput;
    private JTextField portInput;
    private JButton powerBtn;
    private JTextArea log;
    private JButton doanhthuBtn;
    private JLabel doanhthu;
    private JScrollPane scroll;
    private long total;
    
    public ThanhtoanServer() {
        init();
    }

    private void init() {
        JPanel mainPanel = new JPanel();
        JPanel inforPanel = new JPanel();
        inforPanel.setBorder(new TitledBorder("Thông tin"));
        JPanel logPanel = new JPanel();
        logPanel.setBorder(new TitledBorder("Lịch sử"));
        JPanel totalPanel = new JPanel();
        totalPanel.setBorder(new TitledBorder("Doanh thu"));

        JLabel label = new JLabel("Server thanh toán");
        label.setAlignmentX(CENTER_ALIGNMENT);
        label.setFont(new Font("Serif", Font.BOLD, 35));

        JLabel addressLabel = new JLabel("Địa chỉ:");
        addressInput = new JTextField(10);
        addressInput.setText("Localhost");
        addressInput.setEditable(false);
        JPanel addrPanel = new JPanel();
        addrPanel.add(addressLabel);
        addrPanel.add(addressInput);
        
        JLabel portLabel = new JLabel("Cổng:");
        portInput = new JTextField(10);
        portInput.setText("5050");
        JPanel portPanel = new JPanel();
        portPanel.add(portLabel);
        portPanel.add(portInput);
        
        powerBtn = new JButton("BẬT");
        powerBtn.setForeground(Color.white);
        powerBtn.setBackground(Color.red);
        log = new JTextArea(22,50);
        scroll = new JScrollPane(log);
        log.setEditable(false);
        log.setLineWrap(true);
        doanhthuBtn = new JButton("Hiện doanh thu");
        doanhthu = new JLabel("Tổng doanh thu: " + total + " vnđ");
        
        inforPanel.add(addrPanel);
        inforPanel.add(portPanel);
        inforPanel.add(powerBtn);
        
        logPanel.add(scroll);
        
        totalPanel.add(doanhthuBtn);
        totalPanel.add(doanhthu);
        
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
        mainPanel.add(label);
        mainPanel.add(inforPanel);
        mainPanel.add(logPanel);
        mainPanel.add(totalPanel);
        
        this.add(mainPanel);
        this.setTitle("Server thanh toán");
        this.setSize(550, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }
    
    public void addServerEvent(ActionListener act){
        powerBtn.addActionListener(act);
    }
    
    public synchronized void insertLog(String msg){
//        DecimalFormat df = new DecimalFormat("###,###,###");
//        String temp = String.format("* %20s  -  %12s  -  Thanh toan  -  %15s%4s\n",
//                        thoigian,cmnd,df.format(sotien),"VND");
        log.append(msg + "\n");
        log.setCaretPosition(log.getDocument().getLength());
    }
    
    public synchronized void updateTotal(int sotien) {
        DecimalFormat df = new DecimalFormat("###,###,###");
        total += sotien;
        doanhthu.setText("Tổng doanh thu: "+df.format(total)+"vnđ");
    }
    
    public int getPort(){
        String p = portInput.getText();
        if(p.length() > 0){
            return Integer.parseInt(p);
        }
        return -1;
    }

    public void turnOn() {
        portInput.setEditable(false);
        powerBtn.setText("TẮT");
        powerBtn.setBackground(Color.green);
    }
    
    public void turnOff() {
        portInput.setEditable(true);
        powerBtn.setText("BẬT");
        powerBtn.setBackground(Color.red);
    }

}
