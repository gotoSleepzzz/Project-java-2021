package view;

import model.UserCovid;
import service.ManagerService;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class ViewManagerUserCovid extends JPanel implements ActionListener {

    private JPanel panelHeader;
    private JPanel panelBody;
    private JPanel panelFooter;
    private JLabel label;
    private JButton buttonWatchDetails;
    private JButton buttonModify;
    private JTable table;
    private JPanel MenuPanel;
    private JPanel TitlePanel;
    private JLabel titleLabel;
    private CustomTextField searchField;
    private Button searchButton;
    JButton refreshButton;
    private JPanel searchPanel;
    private JPanel footerPanel;
    private JButton watchMore;
    private JButton historyButton;
    List<UserCovid> list = ManagerService.getInstance().findAllUserCovid();
    String[][] data;
    private JButton backButton;
    String[] columns = new String[]{"Họ tên", "CMND", "Nơi điều trị", "Năm sinh",
            "Trạng thái hiện tại", "Dư nợ", "Xem chi tiết", "Chỉnh sửa", "Lịch sử"};
    private String placeHolderSearchTextField = "Nhập tên cần tìm kiếm bằng cmnd hoặc để trống để refresh lại bảng";
    private ViewDetailsUserCovid viewDetailsUserCovid;


    String[] sortBy = {"Họ tên tăng dần theo thứ tự từ điển", "Năm sinh tăng dần", "Trạng thái hiện tại tăng dần theo thứ tự từ điển", "Dư nợ tăng dần", "CMND tăng dần theo thứ tự từ điển",
            "Họ tên giảm dần theo thứ tự từ điển", "Năm sinh giảm dần", "Trạng thái hiện tại giảm dần theo thứ tự từ điển", "Dư nợ giảm dần", "CMND giảm dần theo thứ tự từ điển"};

    JComboBox<String> comboBoxSort;

    public ViewManagerUserCovid() {
        setLayout(new BorderLayout());
        panelHeader = new JPanel();
        panelHeader.setLayout(new BorderLayout());

        MenuPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backButton = new JButton("Quay lại");

        MenuPanel.add(backButton);
        searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        comboBoxSort = new JComboBox<>(sortBy);

        JLabel sortLabel = new JLabel("Sắp xếp theo: ");
        JPanel sortPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        sortPanel.add(sortLabel);
        sortPanel.add(comboBoxSort);

        panelHeader.add(sortPanel, BorderLayout.EAST);

        refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(this);
        searchField = new CustomTextField(40);
        searchField.setPlaceholder(placeHolderSearchTextField);

        searchButton = new Button("Tìm kiếm");
        searchPanel.add(refreshButton);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);


        label = new JLabel("Danh sách người liên quan Covid 19", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        panelHeader.add(MenuPanel, BorderLayout.NORTH);
        panelHeader.add(label, BorderLayout.SOUTH);
        panelHeader.add(searchPanel, BorderLayout.CENTER);

        table = new JTable();
        showTable();

        JScrollPane jScrollPane = new JScrollPane(table);
        panelBody = new JPanel();
        panelBody.setLayout(new BorderLayout());
        panelBody.add(jScrollPane, BorderLayout.CENTER);
        // create a nicer border
        panelBody.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), BorderFactory.createEmptyBorder(10, 10, 10, 10)));


        panelFooter = new JPanel(new FlowLayout(FlowLayout.CENTER));
        watchMore = new JButton("Xem thêm");
        panelFooter.add(watchMore);

        // add title to the panel
        add(panelHeader, BorderLayout.NORTH);
        add(panelBody, BorderLayout.CENTER);
        add(panelFooter, BorderLayout.SOUTH);
        setVisible(true);
    }

    public void setUpButtonTable() {
        table.getColumn("Xem chi tiết").setCellRenderer(new ButtonRenderer("Xem chi tiết"));
        buttonWatchDetails = new JButton("Xem chi tiết");
        buttonModify = new JButton("Chỉnh sửa");
        historyButton = new JButton("Lịch sử");
        table.getColumn("Xem chi tiết").setCellEditor(new ButtonEditor(new JCheckBox(), "Xem chi tiết", buttonWatchDetails));
        table.getColumn("Chỉnh sửa").setCellRenderer(new ButtonRenderer("Chỉnh sửa"));
        table.getColumn("Chỉnh sửa").setCellEditor(new ButtonEditor(new JCheckBox(), "Chỉnh sửa", buttonModify));
        table.getColumn("Lịch sử").setCellEditor(new ButtonEditor(new JCheckBox(), "Lịch sử", historyButton));
        table.getColumn("Lịch sử").setCellRenderer(new ButtonRenderer("Lịch sử"));

        // add new row
        // make table get the text fit the cell
        table.getColumnModel().getColumn(0).setPreferredWidth(80);
        table.getColumnModel().getColumn(1).setPreferredWidth(50);
        table.getColumnModel().getColumn(2).setPreferredWidth(120);
        table.getColumnModel().getColumn(3).setPreferredWidth(10);
        table.getColumnModel().getColumn(4).setPreferredWidth(10);
        table.getColumnModel().getColumn(5).setPreferredWidth(40);
        table.getColumnModel().getColumn(6).setPreferredWidth(100);
        table.getColumnModel().getColumn(7).setPreferredWidth(100);

        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 15));
        table.setRowHeight(30);
        table.setRowMargin(5);
        table.setShowGrid(true);
        table.setGridColor(Color.BLACK);
        table.setSelectionBackground(Color.LIGHT_GRAY);
        table.setSelectionForeground(Color.BLACK);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setBorder(new EtchedBorder());
        table.setFillsViewportHeight(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    }

    public UserCovid getUserCovid() {
        int row = table.getSelectedRow();
        String id = table.getValueAt(row, 1).toString();
        return ManagerService.getInstance().findOneUserCovid(id);
    }

    public void addButtonWatchDetailsListener(ActionListener actionListener) {
        buttonWatchDetails.addActionListener(actionListener);
    }

    public void addButtonModifyListener(ActionListener actionListener) {
        buttonModify.addActionListener(actionListener);
    }


    public void renderTable(List<UserCovid> userCovids) {
        String[][] dataUser = new String[userCovids.size()][6];
        for (int i = 0; i < userCovids.size(); ++i) {
            dataUser[i][0] = userCovids.get(i).getName();
            dataUser[i][1] = userCovids.get(i).getId();
            dataUser[i][2] = ManagerService.getInstance().getHealthCenterName(userCovids.get(i).getHealthCenter());
            dataUser[i][3] = String.valueOf(userCovids.get(i).getDob());

            var status = userCovids.get(i).getState();
            if (status != null) {
                if (status.equals("OK")) {
                    status = ManagerService.getInstance().mapHealthRecovery(status);
                }
            }
            dataUser[i][4] = status;
            dataUser[i][5] = String.valueOf(userCovids.get(i).getDebt());
        }
        DefaultTableModel model = new DefaultTableModel(dataUser, columns);
        table.setModel(model);
        setUpButtonTable();
    }


    public void showTable() {
        data = new String[list.size()][6];
        for (int i = 0; i < list.size(); i++) {
            data[i][0] = list.get(i).getName();
            data[i][1] = list.get(i).getId();
            data[i][2] = ManagerService.getInstance().getHealthCenterName(list.get(i).getHealthCenter());
            data[i][3] = String.valueOf(list.get(i).getDob());
            data[i][4] = list.get(i).getState();

            var status = list.get(i).getState();
            if (status != null) {
                if (status.equals("OK")) {
                    status = ManagerService.getInstance().mapHealthRecovery(status);
                }
            }
            data[i][4] = status;
            data[i][5] = String.valueOf(list.get(i).getDebt());
        }
        DefaultTableModel model = new DefaultTableModel(data, columns);
        table.setModel(model);
        setUpButtonTable();
    }

    public void addBackButtonListener_ViewManagerUserCovid(ActionListener actionListener) {
        backButton.addActionListener(actionListener);
    }

    public void addWatchMoreActionListener(ActionListener actionListener) {
        watchMore.addActionListener(actionListener);
    }

    public void addModifyActionListener(ActionListener actionListener) {
        buttonModify.addActionListener(actionListener);
    }

    public void addSearchActionListener(ActionListener actionListener) {
        searchButton.addActionListener(actionListener);
    }


    public String getContentSearch() {
        return searchField.getText();
    }

    public void addDetailsActionListener(ActionListener actionListener) {
        buttonWatchDetails.addActionListener(actionListener);
    }


    public void addHistoryActionListener(ActionListener actionListener) {
        historyButton.addActionListener(actionListener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == refreshButton) {
            searchField.setText("");
            searchField.setPlaceholder(placeHolderSearchTextField);

        }
    }


    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer(String text) {
            setText(text);
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
            return this;
        }
    }

    class ButtonEditor extends DefaultCellEditor {
        private String label;
        private JButton button;

        public ButtonEditor(JCheckBox checkBox, String label, JButton button) {
            super(checkBox);
            this.label = label;
            this.button = button;
        }

        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {
            this.button.setText(label);
            return this.button;
        }

        public Object getCellEditorValue() {
            return label;
        }

    }


    public String getPlaceHolderSearchTextField() {
        return placeHolderSearchTextField;
    }

    public void setViewDetailsUserCovid(ViewDetailsUserCovid viewDetailsUserCovid) {
        this.viewDetailsUserCovid = viewDetailsUserCovid;
    }

    public void addDropdownListener(ActionListener actionListener) {
        comboBoxSort.addActionListener(actionListener);
    }

}
