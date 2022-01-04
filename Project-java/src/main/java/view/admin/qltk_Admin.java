package view.admin;

import view.CustomTextField;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class qltk_Admin extends JPanel implements ActionListener{
    private CustomTextField searchField;
    private JButton searchBtn;

    private JLabel label;

    private JTable accTable;
    private JScrollPane accScroll;

    private JButton banBtn;
    private JButton showHisBtn;
    private JButton addAccBtn;

    private JTable hisTable;
    private JScrollPane hisScroll;

    private final String[] accHeaders = new String[]{
            "Tên tài khoản", "Vai trò", "Trạng thái"
    };

    private final String[] hisHeaders = new String[]{
            "Thời gian", "Tên tài khoản", "Lịch sử"
    };


    public qltk_Admin() {
        init();
    }

    private void init() {
        searchField = new CustomTextField(30);
        searchField.setPlaceholder("Nhập tên tài khoản");
        searchBtn = new JButton("Tìm kiếm");
        searchBtn.setPreferredSize(new Dimension(100, 30));

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        searchPanel.add(searchField);
        searchPanel.add(searchBtn);

        label = new JLabel("                    QUẢN LÝ TÀI KHOẢN            ");
        label.setAlignmentX(CENTER_ALIGNMENT);
        label.setFont(new Font("Serif", Font.BOLD, 35));
        label.setBorder(new EmptyBorder(10, 0, 0, 0));

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());

        topPanel.add(searchPanel);
        topPanel.add(label);

        accTable = new JTable();
        accScroll = new JScrollPane(accTable);

        accScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        accScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);


        banBtn = new JButton("Khóa/Mở khóa");
        banBtn.setPreferredSize(new Dimension(150, 50));
        showHisBtn = new JButton("Hiện lịch sử");
        showHisBtn.setPreferredSize(new Dimension(150, 50));
        addAccBtn = new JButton("Tạo tài khoản");
        addAccBtn.setPreferredSize(new Dimension(150, 50));
        addAccBtn.addActionListener(this);
        JPanel chucnang = new JPanel();
        chucnang.setLayout(new GridLayout(3, 1, 20, 120));
        chucnang.setBorder(new CompoundBorder(new TitledBorder("Chức năng"), new EmptyBorder(4, 4, 4, 4)));
        chucnang.add(banBtn);
        chucnang.add(showHisBtn);
        chucnang.add(addAccBtn);
        // create a scrollable table in vertical scroll mode
        hisTable = new JTable();
        hisScroll = new JScrollPane(hisTable);
        hisScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        hisScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        hisScroll.setPreferredSize(new Dimension(630, 500));

        JPanel centerPanel = new JPanel();
        FlowLayout flowLayout = new FlowLayout();
        centerPanel.setLayout(flowLayout);
        flowLayout.setHgap(10);
        centerPanel.add(accScroll);
        centerPanel.add(chucnang);
        centerPanel.add(hisScroll);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(topPanel);
        mainPanel.add(centerPanel);

        add(mainPanel);
        // set location relative to parent
        setLocation(0, 0);


    }

    public DefaultTableModel getTableAccountModel() {
        return (DefaultTableModel) accTable.getModel();
    }

    public void setTableAccountModel(String[][] list) {
        DefaultTableModel model = new DefaultTableModel(list, accHeaders);
        accTable.setModel(model);
        // make column width fit content
        for (int i = 0; i < accTable.getColumnCount(); i++) {
            TableColumn column = accTable.getColumnModel().getColumn(i);
            int preferredWidth = accTable.getCellRenderer(0, i).getTableCellRendererComponent(accTable, list[0][i], false, false, 0, i).getPreferredSize().width;
            int width = Math.max(preferredWidth, column.getMinWidth());
            column.setPreferredWidth(width);
        }
        accTable.setFont(new Font("Serif", Font.PLAIN, 16));

    }

    public void addSearchBtnListener(ActionListener actionListener) {
        searchBtn.addActionListener(actionListener);
    }

    public String getContentSearch() {
        return searchField.getText();
    }

    public void handlerShowHisBtn(ActionListener e) {
        showHisBtn.addActionListener(e);
    }

    public int getSelectedRowTableAcc() {
        return accTable.getSelectedRow();
    }

    public void setTableHistoryModel(String[][] list) {
        DefaultTableModel model = new DefaultTableModel(list, hisHeaders);
        hisTable.setModel(model);
        // set column 3 bigger
        hisTable.getColumnModel().getColumn(0).setPreferredWidth(32);
        hisTable.getColumnModel().getColumn(1).setPreferredWidth(40);
        hisTable.getColumnModel().getColumn(2).setPreferredWidth(350);

        // make table nice
        hisTable.setRowHeight(30);
        hisTable.setFont(new Font("Serif", Font.PLAIN, 16));
        hisTable.setFillsViewportHeight(true);
        hisTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        hisTable.setAutoCreateRowSorter(true);

        hisTable.setPreferredSize(null);

    }

    public void handlerBanBtn(ActionListener e) {
        banBtn.addActionListener(e);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addAccBtn) {
            new ViewRegisterAccount();
        }
    }
}
