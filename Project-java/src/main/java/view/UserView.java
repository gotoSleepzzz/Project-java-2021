package view;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

public class UserView extends JFrame /*implements ActionListener*/ {

    JTable jtbl, hisTable;
    JDialog dialog;
    JButton jbuy, jpay, jchange, jconsumehistory, jmanagehistory, jpaymenthistory, changePassButton, searchBtn, buyBtn, payBtn;
    JTextField name, dob, address, id, debt, searchBar, productname, limit, time, cost, PassWarning;
    JTextField current, jdebt, minimum, payment;
    JPasswordField oldPass, newPass, reEnterPass;
    BuyProduct viewBuyProduct;

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

    private Object productData = new Object[][]{};
    String username;

    public UserView(String username) {
        this.username = username;
        this.setTitle("User");
        this.setLayout(new FlowLayout());
        //this.setResizable(false);
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
        debt = new JTextField();
        debt.setEditable(false);
        JPanel debtPanel = new JPanel();
        debtPanel.setLayout(new GridLayout(2, 2, 5, 5));
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

        // Layout
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(buttons, BorderLayout.CENTER);
        panel.add(jinfo, BorderLayout.WEST);

        hisTable = new JTable();
        hisTable.setRowHeight(50);

        this.add(panel);
        //this.pack();
        this.setVisible(true);
        this.setSize(1000, 550);
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

    public void Buy() {
        viewBuyProduct = new BuyProduct(username, this);
        viewBuyProduct.setVisible(true);
    }

    public void Pay(JFrame frame, int sodu, float ghino, float hanmuc, ActionListener act) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
        label.add(new JLabel("Số dư hiện tại (vnd)", SwingConstants.RIGHT));
        label.add(new JLabel("Dư nợ (vnd)", SwingConstants.RIGHT));
        label.add(new JLabel("Hạn mức tối thiểu (vnd)", SwingConstants.RIGHT));
        label.add(new JLabel("Số tiền muốn thanh toán (vnd):", SwingConstants.RIGHT));
        panel.add(label, BorderLayout.WEST);

        JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
        current = new JTextField();
        current.setHorizontalAlignment(JTextField.CENTER);
        controls.add(current);
        current.setText(sodu + "");
        current.setEditable(false);
        jdebt = new JTextField();
        jdebt.setHorizontalAlignment(JTextField.CENTER);
        jdebt.setText(debt.getText());
        jdebt.setText(ghino + "");
        jdebt.setEditable(false);
        controls.add(jdebt);
        minimum = new JTextField();
        minimum.setHorizontalAlignment(JTextField.CENTER);
        controls.add(minimum);
        minimum.setText(hanmuc + "");
        minimum.setEditable(false);
        payment = new JTextField();
        payment.setHorizontalAlignment(JTextField.CENTER);
        controls.add(payment);
        panel.add(controls, BorderLayout.CENTER);

        JPanel jpanel = new JPanel(new GridLayout(0, 1, 2, 2));
        payBtn = new JButton("Thanh toán dư nợ");
        jpanel.add(payBtn);
        payBtn.addActionListener(act);
        panel.add(jpanel, BorderLayout.SOUTH);

        dialog = new JDialog(this, "Thanh toán dư nợ", false);
        dialog.setSize(400, 200);
        dialog.setResizable(false);
        dialog.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL); // Chặn parent cho tới khi hoàn thành
        dialog.setLocationRelativeTo(null);
        dialog.add(panel);
        dialog.setVisible(true);
    }

    public float getHanMuc() {
        String hanmuc = minimum.getText().strip();
        if (hanmuc.length() > 0) {
            return Float.parseFloat(hanmuc);
        }
        return -1;
    }

    public int getSoTienThanhToan() {
        String sotien = payment.getText().strip();
        if (sotien.length() > 0) {
            return Integer.parseInt(sotien);
        }
        return -1;
    }

    public float getGhiNo() {
        String ghino = jdebt.getText().strip();
        if (ghino.length() > 0) {
            return Float.parseFloat(ghino);
        }
        return -1;
    }

    public void closePayment() {
        dialog.dispose();
        dialog.setVisible(false);
    }

    public void handdlePayEvent(ActionListener act) {
        payBtn.addActionListener(act);
    }

    public void ChangePassword(JFrame frame, ActionListener act) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
        label.add(new JLabel("Mật khẩu cũ", SwingConstants.RIGHT));
        label.add(new JLabel("Mật khẩu mới", SwingConstants.RIGHT));
        label.add(new JLabel("Nhập lại mật khẩu mới", SwingConstants.RIGHT));
        panel.add(label, BorderLayout.WEST);

        JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
        oldPass = new JPasswordField();
        controls.add(oldPass);
        newPass = new JPasswordField();
        controls.add(newPass);
        reEnterPass = new JPasswordField();
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

        changePassButton.addActionListener(act);

        dialog = new JDialog(this, "Đổi mật khẩu", false);
        dialog.setSize(800, 200);
        dialog.setResizable(false);
        dialog.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL); // Chặn parent cho tới khi hoàn thành đổi mật khẩu
        dialog.setLocationRelativeTo(null);
        dialog.add(panel);
        dialog.setVisible(true);
    }

    public void ConsumeHistory(JFrame frame) {
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
        //hisTable.setFillsViewportHeight(false);
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
        JScrollPane hisScroll = new JScrollPane();
        hisTable.setPreferredScrollableViewportSize(hisTable.getPreferredSize());
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

    public void AddEventShowConsumeHistory(ActionListener e) {
        jconsumehistory.addActionListener(e);
    }

    public void AddEventShowManageHistory(ActionListener e) {
        jmanagehistory.addActionListener(e);
    }

    public void AddEventShowPaymentHistory(ActionListener e) {
        jpaymenthistory.addActionListener(e);
    }

    public void AddEventBuy(ActionListener e) {
        jbuy.addActionListener(e);
    }

    public void AddEventPay(ActionListener e) {
        jpay.addActionListener(e);
    }

    public void AddEventChangePassword(ActionListener e) {
        jchange.addActionListener(e);
    }

    public void setDataPaymentHistoryTable(String[][] data) {
        hisTable.setModel(new DefaultTableModel(data, hisPaymentHeaders));
        hisTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumnAdjuster tca = new TableColumnAdjuster(hisTable);
        tca.adjustColumns();
    }

    public void setDataManageHistoryTable(String[][] data) {
        hisTable.setModel(new DefaultTableModel(data, hisManageHeaders));
        hisTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumnAdjuster tca = new TableColumnAdjuster(hisTable);
        tca.adjustColumns();
    }

    public void setDataConsumeHistoryTable(String[][] data) {
        hisTable.setModel(new DefaultTableModel(data, hisConsumeHeaders));
        hisTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumnAdjuster tca = new TableColumnAdjuster(hisTable);
        tca.adjustColumns();
    }

    public String getOldPass() {
        return oldPass.getText();
    }

    public String getNewPass() {
        return newPass.getText();
    }

    public String getReEnterPass() {
        return reEnterPass.getText();
    }

    public void setPasswordWarning(String data) {
        PassWarning.setText(data);
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
