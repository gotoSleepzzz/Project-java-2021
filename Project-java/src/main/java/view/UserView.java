package view;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class UserView extends JFrame implements ActionListener {

    JTable jtbl;
    JDialog dialog;
    
    JTextField name,dob,address,id;
    
    public UserView() {
        this.setTitle("User");
        this.setLayout(new FlowLayout());
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Panel thông tin cá nhân
        name = new JTextField();
        dob = new JTextField();
        address = new JTextField();
        id = new JTextField();
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(4, 2, 5, 5));
        infoPanel.add(new JLabel("Họ tên"));
        infoPanel.add(name);
        infoPanel.add(new JLabel("Ngày tháng năm sinh"));
        infoPanel.add(dob);
        infoPanel.add(new JLabel("Địa chỉ nơi ở"));
        infoPanel.add(address);
        infoPanel.add(new JLabel("CMND/CCCD"));
        infoPanel.add(id);
        
        JPanel info = new JPanel();
        info.setBorder(new TitledBorder("Thông tin"));
        info.setLayout(new GridLayout(2, 1));
        info.add(infoPanel);
        
        // Panel dư nợ
        JTextField duhientai = new JTextField();
        JTextField duno = new JTextField();
        JPanel dunoPanel = new JPanel();
        dunoPanel.setLayout(new GridLayout(2, 2, 5, 5));
        dunoPanel.add(new JLabel("Số dư hiện tại"));
        dunoPanel.add(duhientai);
        dunoPanel.add(new JLabel("Dư nợ"));
        dunoPanel.add(duno);
        
        JPanel debt = new JPanel();
        debt.setBorder(new TitledBorder("Dư nợ"));
        debt.setLayout(new GridLayout(2, 1));
        debt.add(dunoPanel);
        
        // Panel thông tin
        JPanel jinfo = new JPanel(new BorderLayout());
        jinfo.add(info, BorderLayout.NORTH);
        jinfo.add(debt, BorderLayout.SOUTH);

        // Các nút chọn
        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(10, 1, 10, 10));
        //buttons.setBorder(new EmptyBorder(new Insets(150, 200, 150, 200)));
        JButton jbuy = new JButton("Mua gói nhu yếu phẩm");
        JButton jpay = new JButton("Thanh toán chi phí");
        JButton jchange = new JButton("Đổi mật khẩu");
        JButton jhistory = new JButton("Xem lịch sử");
        buttons.add(jbuy);
        buttons.add(jpay);
        buttons.add(jchange);
        buttons.add(jhistory);

        // Add action listener
        jbuy.addActionListener(this);
        jpay.addActionListener(this);
        jchange.addActionListener(this);
        jhistory.addActionListener(this);
        
        // Layout
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(buttons, BorderLayout.CENTER);
        panel.add(jinfo, BorderLayout.WEST);
        
        this.add(panel);
        this.pack();
    }
    public void setNameField(String t){
        name.setText(t);
    }
    public void setDobField(String t){
        dob.setText(t);
    }
    public void setAddressField(String t){
        address.setText(t);
    }
    public void setIdField(String t){
        id.setText(t);
    }
    //Handle buttons action events.
    @Override
    public void actionPerformed(ActionEvent ae) {
        String comStr = ae.getActionCommand();

        if (comStr.equals("Mua gói nhu yếu phẩm")) {
            Buy(this);
        }

        if (comStr.equals("Thanh toán chi phí")) {
            Pay(this);
        }

        if (comStr.equals("Đổi mật khẩu")) {
            ChangePassword(this);
        }

        if (comStr.equals("Xem lịch sử")) {
            ShowHistory(this);
        }
    }

    public void Buy(JFrame frame) {
        JTextField searchBar = new JTextField(50);
        JButton searchBtn = new JButton("Tìm kiếm");
        JButton buyBtn = new JButton("Chọn mua");

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        //topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.add(searchBar);
        topPanel.add(searchBtn);
        topPanel.add(buyBtn);

        JTable table = new JTable();
        JScrollPane scroll = new JScrollPane();
        Object data = new Object[][]{};
        String[] headerTable = new String[]{
            "Chọn mua", "Tên gói", "Số lượng", "Đơn giá"
        };
        table.setModel(new DefaultTableModel((Object[][]) data, headerTable));
        scroll.setViewportView(table);
        table.setAutoCreateRowSorter(true);

        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.CENTER);
        flowLayout.setHgap(30);
        flowLayout.setVgap(30);

        JTextField name = new JTextField();
        JTextField limit = new JTextField();
        JTextField time = new JTextField();
        JTextField cost = new JTextField();
        String[] category = new String[]{
            "< 500.000", "500.000 - 1.000.000"
        };
        JComboBox filter = new JComboBox(category);
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(5, 2, 20, 19));
        infoPanel.add(new JLabel("Lọc"));
        infoPanel.add(filter);
        infoPanel.add(new JLabel("Chọn mua"));
        infoPanel.add(name);
        infoPanel.add(new JLabel("Tên gói"));
        infoPanel.add(limit);
        infoPanel.add(new JLabel("Số lượng"));
        infoPanel.add(time);
        infoPanel.add(new JLabel("Đơn giá"));
        infoPanel.add(cost);

        JPanel utilPanel = new JPanel();
        utilPanel.setBorder(new TitledBorder("Thông tin"));
        utilPanel.setLayout(new GridLayout(2, 1));
        utilPanel.add(infoPanel);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new FlowLayout());
        contentPanel.add(scroll);
        contentPanel.add(utilPanel);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(topPanel);
        mainPanel.add(contentPanel);
        
        dialog = new JDialog(this, "Mua gói nhu yếu phẩm", false);
        dialog.setSize(950, 600);
        dialog.setResizable(false);
        dialog.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL); // Chặn parent cho tới khi hoàn thành mua gói nhu yếu phẩm hoặc thoát
        dialog.setLocationRelativeTo(null);
        dialog.add(mainPanel);
        dialog.setVisible(true);
    }

    public void Pay(JFrame frame) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
        label.add(new JLabel("Số dư hiện tại", SwingConstants.RIGHT));
        label.add(new JLabel("Dư nợ", SwingConstants.RIGHT));
        label.add(new JLabel("Hạn mức tối thiểu", SwingConstants.RIGHT));
        label.add(new JLabel("Số tiền muốn thanh toán:", SwingConstants.RIGHT));
        panel.add(label, BorderLayout.WEST);

        JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
        JTextField current = new JTextField();
        current.setHorizontalAlignment(JTextField.CENTER);
        controls.add(current);
        JTextField debt = new JTextField();
        debt.setHorizontalAlignment(JTextField.CENTER);
        controls.add(debt);
        JTextField minimum = new JTextField();
        minimum.setHorizontalAlignment(JTextField.CENTER);
        controls.add(minimum);
        JTextField payment = new JTextField();
        payment.setHorizontalAlignment(JTextField.CENTER);
        controls.add(payment);
        panel.add(controls, BorderLayout.CENTER);

        JPanel jpanel = new JPanel(new GridLayout(0, 1, 2, 2));
        JButton button = new JButton("Thanh toán dư nợ");
        jpanel.add(button);
        panel.add(jpanel, BorderLayout.SOUTH);

        button.addActionListener((ActionEvent ae) -> {
            if (true) {
                dialog.dispose();
                dialog.setVisible(false);
            } else {
                
            }
        });

        dialog = new JDialog(this, "Thanh toán dư nợ", false);
        dialog.setSize(400, 200);
        dialog.setResizable(false);
        dialog.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL); // Chặn parent cho tới khi hoàn thành
        dialog.setLocationRelativeTo(null);
        dialog.add(panel);
        dialog.setVisible(true);
    }

    public void ChangePassword(JFrame frame) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
        label.add(new JLabel("Mật khẩu cũ", SwingConstants.RIGHT));
        label.add(new JLabel("Mật khẩu mới", SwingConstants.RIGHT));
        label.add(new JLabel("Nhập lại mật khẩu mới", SwingConstants.RIGHT));
        panel.add(label, BorderLayout.WEST);

        JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
        JPasswordField oldPass = new JPasswordField();
        controls.add(oldPass);
        JPasswordField newPass = new JPasswordField();
        controls.add(newPass);
        JPasswordField reEnterPass = new JPasswordField();
        controls.add(reEnterPass);
        panel.add(controls, BorderLayout.CENTER);

        JPanel jpanel = new JPanel(new GridLayout(0, 1, 2, 2));
        JTextField warning = new JTextField();
        warning.setForeground(Color.red);
        warning.setBackground(null);
        warning.setBorder(null);
        warning.setHorizontalAlignment(JTextField.CENTER);
        JButton button = new JButton("Đổi mật khẩu");
        jpanel.add(warning);
        jpanel.add(button);
        panel.add(jpanel, BorderLayout.SOUTH);

        button.addActionListener((ActionEvent ae) -> {
            if (Arrays.equals(newPass.getPassword(), reEnterPass.getPassword())) {
                dialog.dispose();
                dialog.setVisible(false);
            } else {
                warning.setText("Mật khẩu mới không trùng khớp");
            }
        });

        dialog = new JDialog(this, "Đổi mật khẩu", false);
        dialog.setSize(400, 200);
        dialog.setResizable(false);
        dialog.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL); // Chặn parent cho tới khi hoàn thành đổi mật khẩu
        dialog.setLocationRelativeTo(null);
        dialog.add(panel);
        dialog.setVisible(true);
    }

    public void ShowHistory(JFrame frame) {
        // Panel Lịch sử tiêu thụ gói nhu yếu phẩm
        JTextField packageName = new JTextField();
        JTextField packageTime = new JTextField();
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(2, 2, 5, 5));
        infoPanel.add(new JLabel("Tên gói                      "));
        infoPanel.add(packageName);
        infoPanel.add(new JLabel("Thời gian"));
        infoPanel.add(packageTime);
        
        JPanel info = new JPanel();
        info.setBorder(new TitledBorder("Lịch sử tiêu thụ gói nhu yếu phẩm"));
        info.setLayout(new GridLayout(2, 3));
        info.add(infoPanel);
        
        // Panel Lịch sử được quản lý
        JTextField stateChange = new JTextField();
        JTextField locationChange = new JTextField();
        JTextField timeChange = new JTextField();
        JPanel dunoPanel = new JPanel();
        dunoPanel.setLayout(new GridLayout(3, 2, 5, 5));
        dunoPanel.add(new JLabel("Chuyển đổi trạng thái"));
        dunoPanel.add(stateChange);
        dunoPanel.add(new JLabel("Chuyển đổi nơi cách ly / điều trị"));
        dunoPanel.add(locationChange);
        dunoPanel.add(new JLabel("Thời gian thực hiện chuyển đổi"));
        dunoPanel.add(timeChange);
        
        JPanel debt = new JPanel();
        debt.setBorder(new TitledBorder("Lịch sử được quản lý"));
        debt.setLayout(new GridLayout(2, 1));
        debt.add(dunoPanel);
        
        // Panel Lịch sử thanh toán
        JTextField time = new JTextField();
        JTextField money = new JTextField();
        JPanel paymentinfo = new JPanel();
        paymentinfo.setLayout(new GridLayout(2, 2, 5, 5));
        paymentinfo.add(new JLabel("Thời gian"));
        paymentinfo.add(time);
        paymentinfo.add(new JLabel("Số tiền thanh toán"));
        paymentinfo.add(money);
        
        JPanel paymentHistory = new JPanel();
        paymentHistory.setBorder(new TitledBorder("Lịch sử thanh toán"));
        paymentHistory.setLayout(new GridLayout(2, 1));
        paymentHistory.add(paymentinfo);
        
        // Panel tổng
        JPanel jinfo = new JPanel();
        jinfo.add(info);
        jinfo.add(debt);
        jinfo.add(paymentHistory);
        
        dialog = new JDialog(this, "Lịch sử", false);
        dialog.setSize(1000, 200);
        dialog.setResizable(false);
        dialog.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL); // Chặn parent cho tới khi hoàn thành đổi mật khẩu
        dialog.setLocationRelativeTo(null);
        dialog.add(jinfo);
        dialog.pack();
        dialog.setVisible(true);
    }
}
