package view;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.EventObject;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class UserView extends JFrame /*implements ActionListener*/ {

    JTable jtbl, hisTable;
    JDialog dialog;
    JButton jbuy, jpay, jchange, jconsumehistory, jmanagehistory, jpaymenthistory, changePassButton, searchBtn, buyBtn, payBtn;
    JTextField name, dob, address, id, balance, debt, searchBar, productname, limit, time, cost, PassWarning;

    int editableCell = 3;

    private final String[] hisConsumeHeaders = new String[]{
        "ID gói nhu yếu phẩm", "Số lượng", "Thành tiền", "Thời gian"
    };

    private final String[] hisManageHeaders = new String[]{
        "Trạng thái cũ", "Trạng thái mới", "Nơi quản lý cũ", "Nơi quản lý mới", "Thời gian"
    };

    private final String[] hisPaymentHeaders = new String[]{
        "Số tiền", "Thời gian"
    };

    private Object hisConsumeData = new Object[][]{};
    private Object hisManageData = new Object[][]{};
    private Object hisPaymentData = new Object[][]{};
    private Object productData = new Object[][]{};

    public UserView() {
        this.setTitle("User");
        this.setLayout(new FlowLayout());
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel thông tin cá nhân
        name = new JTextField();
        name.setEditable(false);
        dob = new JTextField();
        dob.setEditable(false);
        address = new JTextField();
        address.setEditable(false);
        id = new JTextField();
        id.setEditable(false);
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
        balance = new JTextField();
        balance.setEditable(false);
        debt = new JTextField();
        debt.setEditable(false);
        JPanel debtPanel = new JPanel();
        debtPanel.setLayout(new GridLayout(2, 2, 5, 5));
        debtPanel.add(new JLabel("Số dư hiện tại"));
        debtPanel.add(balance);
        debtPanel.add(new JLabel("Dư nợ"));
        debtPanel.add(debt);

        JPanel jdebt = new JPanel();
        jdebt.setBorder(new TitledBorder("Dư nợ"));
        jdebt.setLayout(new GridLayout(2, 1));
        jdebt.add(debtPanel);

        // Panel thông tin
        JPanel jinfo = new JPanel(new BorderLayout());
        jinfo.add(info, BorderLayout.NORTH);
        jinfo.add(jdebt, BorderLayout.SOUTH);

        // Các nút chọn
        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(10, 1, 10, 10));
        //buttons.setBorder(new EmptyBorder(new Insets(150, 200, 150, 200)));
        jbuy = new JButton("Mua gói nhu yếu phẩm");
        jpay = new JButton("Thanh toán chi phí");
        jchange = new JButton("Đổi mật khẩu");
        jconsumehistory = new JButton("Lịch sử tiêu thụ gói nhu yếu phẩm");
        jmanagehistory = new JButton("Lịch sử được quản lý");
        jpaymenthistory = new JButton("Lịch sử thanh toán");

        buttons.add(jbuy);
        buttons.add(jpay);
        buttons.add(jchange);
        buttons.add(jconsumehistory);
        buttons.add(jmanagehistory);
        buttons.add(jpaymenthistory);

        // Add action listener
        /*jbuy.addActionListener(this);
        jpay.addActionListener(this);
        jchange.addActionListener(this);
        jconsumehistory.addActionListener(this);
        jmanagehistory.addActionListener(this);
        jpaymenthistory.addActionListener(this);*/

        // Layout
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(buttons, BorderLayout.CENTER);
        panel.add(jinfo, BorderLayout.WEST);
        
        hisTable = new JTable();
        
        this.add(panel);
        this.pack();
        this.setVisible(true);
        this.setSize(900, 400);
        this.setLocationRelativeTo(null);
    }

    public void setNameField(String t) {
        name.setText(t);
    }

    public void setDobField(String t) {
        dob.setText(t);
    }

    public void setAddressField(String t) {
        address.setText(t);
    }

    public void setIdField(String t) {
        id.setText(t);
    }

    public void setDebtField(String t) {
        debt.setText(t + "đ");
    }

    //Handle buttons action events.
    /*@Override
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

        if (comStr.equals("Lịch sử tiêu thụ gói nhu yếu phẩm")) {
            ConsumeHistory(this);
        }

        if (comStr.equals("Lịch sử được quản lý")) {
            ManageHistory(this);
        }

        if (comStr.equals("Lịch sử thanh toán")) {
            PaymentHistory(this);
        }
    }*/

    public void Buy(JFrame frame) {
        searchBar = new JTextField(50);
        searchBtn = new JButton("Tìm kiếm");
        buyBtn = new JButton("Chọn mua");

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        //topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.add(searchBar);
        topPanel.add(searchBtn);
        topPanel.add(buyBtn);
        
        // Chọn mua là kiểu boolean
        String[] headerTable = new String[]{
            "Chọn mua", "Tên gói", "Số lượng", "Đơn giá"
        };
        productData = new Object[][]{{false, "Gói số 1", 0, "200.000"}};
        DefaultTableModel model = new DefaultTableModel((Object[][]) productData, headerTable);
        
        JTable table = new JTable(model) {
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return Boolean.class;
                    case 1:
                        return String.class;
                    case 2:
                        return Integer.class;
                    case 3:
                        return String.class;
                    default:
                        return String.class;
                }
            }
        };
        table.setAutoCreateRowSorter(true);
        
        JScrollPane scroll = new JScrollPane(table);
        scroll.setViewportView(table);
        
        // Sử dụng spinner cho Số lượng
        TableColumn col = table.getColumnModel().getColumn(2);
        col.setCellEditor(new SpinnerEditor());

        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.CENTER);
        flowLayout.setHgap(30);
        flowLayout.setVgap(30);

        productname = new JTextField();
        limit = new JTextField();
        time = new JTextField();
        cost = new JTextField();
        String[] category = new String[]{
            "< 500.000", "500.000 - 1.000.000"
        };
        JComboBox filter = new JComboBox(category);
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(5, 2, 20, 19));
        infoPanel.add(new JLabel("Lọc"));
        infoPanel.add(filter);
        infoPanel.add(new JLabel("Chọn mua"));
        infoPanel.add(productname);
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
        JTextField jdebt = new JTextField();
        jdebt.setHorizontalAlignment(JTextField.CENTER);
        jdebt.setText(debt.getText());
        jdebt.setEditable(false);
        controls.add(jdebt);
        JTextField minimum = new JTextField();
        minimum.setHorizontalAlignment(JTextField.CENTER);
        controls.add(minimum);
        JTextField payment = new JTextField();
        payment.setHorizontalAlignment(JTextField.CENTER);
        controls.add(payment);
        panel.add(controls, BorderLayout.CENTER);

        JPanel jpanel = new JPanel(new GridLayout(0, 1, 2, 2));
        payBtn = new JButton("Thanh toán dư nợ");
        jpanel.add(payBtn);
        panel.add(jpanel, BorderLayout.SOUTH);

        payBtn.addActionListener((ActionEvent ae) -> {
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
        PassWarning = new JTextField();
        PassWarning.setForeground(Color.red);
        PassWarning.setBackground(null);
        PassWarning.setBorder(null);
        PassWarning.setHorizontalAlignment(JTextField.CENTER);
        changePassButton = new JButton("Đổi mật khẩu");
        jpanel.add(PassWarning);
        jpanel.add(changePassButton);
        panel.add(jpanel, BorderLayout.SOUTH);

        changePassButton.addActionListener((ActionEvent ae) -> {
            if (Arrays.equals(newPass.getPassword(), reEnterPass.getPassword())) {
                dialog.dispose();
                dialog.setVisible(false);
            } else {
                PassWarning.setText("Mật khẩu mới không trùng khớp");
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

    public void ConsumeHistory (JFrame frame) {
        JScrollPane hisScroll = new JScrollPane();
        hisTable.setPreferredScrollableViewportSize(hisTable.getPreferredSize());
        hisTable.setAutoCreateRowSorter(true);
        hisScroll.setViewportView(hisTable);

        JPanel centerPanel = new JPanel();
        FlowLayout flowLayout = new FlowLayout();
        centerPanel.setLayout(flowLayout);
        flowLayout.setHgap(10);
        //flowLayout.setVgap(10);
        centerPanel.add(hisScroll);
        

        dialog = new JDialog(this, "Lịch sử tiêu thụ gói nhu yếu phẩm", false);
        dialog.setResizable(false);
        dialog.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL); // Chặn parent
        dialog.setLocationRelativeTo(null);
        dialog.add(centerPanel);
        dialog.pack();
        dialog.setVisible(true);
    }

    public void ManageHistory(JFrame frame) {
        JScrollPane hisScroll = new JScrollPane();
        hisTable.setPreferredScrollableViewportSize(hisTable.getPreferredSize());
        hisTable.setAutoCreateRowSorter(true);
        hisScroll.setViewportView(hisTable);

        JPanel centerPanel = new JPanel();
        FlowLayout flowLayout = new FlowLayout();
        centerPanel.setLayout(flowLayout);
        flowLayout.setHgap(10);
        centerPanel.add(hisScroll);

        dialog = new JDialog(this, "Lịch sử được quản lý", false);
        dialog.setResizable(false);
        dialog.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL); // Chặn parent
        dialog.setLocationRelativeTo(null);
        dialog.add(centerPanel);
        dialog.pack();
        dialog.setVisible(true);
    }

    public void PaymentHistory(JFrame frame) {
        hisTable = new JTable();
        JScrollPane hisScroll = new JScrollPane();
        hisTable.setModel(new DefaultTableModel((Object[][]) hisPaymentData, hisPaymentHeaders));
        hisTable.setAutoCreateRowSorter(true);
        hisScroll.setViewportView(hisTable);

        JPanel centerPanel = new JPanel();
        FlowLayout flowLayout = new FlowLayout();
        centerPanel.setLayout(flowLayout);
        flowLayout.setHgap(10);
        centerPanel.add(hisScroll);

        dialog = new JDialog(this, "Lịch sử thanh toán", false);
        dialog.setSize(1000, 200);
        dialog.setResizable(false);
        dialog.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL); // Chặn parent
        dialog.setLocationRelativeTo(null);
        dialog.add(centerPanel);
        dialog.pack();
        dialog.setVisible(true);
    }
    public void AddEventShowConsumeHistory(ActionListener e){
        jconsumehistory.addActionListener(e);
    }
    public void AddEventShowManageHistory(ActionListener e){
        jmanagehistory.addActionListener(e);
    }
    public void AddEventShowPaymentHistory(ActionListener e){
        jpaymenthistory.addActionListener(e);
    }
    public void setDataPaymentHistoryTable(String[][] data){
        hisTable.setModel(new DefaultTableModel(data, hisPaymentHeaders));
        hisTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumnAdjuster tca = new TableColumnAdjuster(hisTable);
        tca.adjustColumns();
    }
    public void setDataManageHistoryTable(String[][] data){
        hisTable.setModel(new DefaultTableModel(data, hisManageHeaders));
        hisTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumnAdjuster tca = new TableColumnAdjuster(hisTable);
        tca.adjustColumns();
    }
    public void setDataConsumeHistoryTable(String[][] data){
        hisTable.setModel(new DefaultTableModel(data, hisConsumeHeaders));
        hisTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumnAdjuster tca = new TableColumnAdjuster(hisTable);
        tca.adjustColumns();
    }
}

class SpinnerEditor extends AbstractCellEditor implements TableCellEditor {
    final JSpinner spinner = new JSpinner();
    
    public SpinnerEditor() {
        spinner.setModel(new SpinnerNumberModel(0, 0, 10, 1));
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        spinner.setValue(value);
        return spinner;
    }

    public boolean isCellEditable(EventObject evt) {
        if (evt instanceof MouseEvent) {
            return ((MouseEvent) evt).getClickCount() >= 2;
        }
        return true;
    }

    public Object getCellEditorValue() {
        return spinner.getValue();
    }
}
