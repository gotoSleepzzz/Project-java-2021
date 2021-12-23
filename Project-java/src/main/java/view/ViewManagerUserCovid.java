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
    List<UserCovid> list = ManagerService.getInstance().findAllUserCovid();
    String[][] data = new String[list.size()][5];
    private JButton backButton;
    String[] columns = new String[]{"Họ tên", "CMND", "Nơi điều trị", "Năm sinh",
            "Trạng thái hiện tại", "Xem chi tiết", "Chỉnh sửa"};
    private String placeHolderSearchTextField = "Nhập tên cần tìm kiếm bằng tên, cmnd";




    private ViewDetailsUserCovid viewDetailsUserCovid;



    public ViewManagerUserCovid() {
        setLayout(new BorderLayout());
        panelHeader = new JPanel();
        panelHeader.setLayout(new BorderLayout());

        MenuPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backButton = new JButton("Quay lại");

        MenuPanel.add(backButton);
        searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(this);
        searchField = new CustomTextField(30);
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
        table.getColumn("Xem chi tiết").setCellEditor(new ButtonEditor(new JCheckBox(), "Xem chi tiết", buttonWatchDetails));
        table.getColumn("Chỉnh sửa").setCellRenderer(new ButtonRenderer("Chỉnh sửa"));
        table.getColumn("Chỉnh sửa").setCellEditor(new ButtonEditor(new JCheckBox(), "Chỉnh sửa", buttonModify));
        // add new row
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


//    public void addButtonListener() {
//
//        buttonWatchDetails.addActionListener(
//                event -> {
//                    int row = table.getSelectedRow();
//                    String id = table.getValueAt(row, 1).toString();
//                }
//        );
//
//        buttonModify.addActionListener(e -> {
//
//            int option = JOptionPane.showConfirmDialog(null, "Bạn có muốn chỉnh sửa không?", "Chỉnh sửa", JOptionPane.YES_NO_OPTION);
//            if (option == JOptionPane.YES_OPTION) {
//                int selectedRow = table.getSelectedRow();
//                String selectedRowData = (String) table.getValueAt(selectedRow, 1);
//                JOptionPane.showMessageDialog(null, "You have selected: " + columns[1] + ": " + selectedRowData);
//            }
//        });
//
//    }


    public void renderTable(List<UserCovid> userCovids) {
        System.out.println(" ++" + userCovids.size());
        String[][] dataUser = new String[userCovids.size()][5];

        for (int i = 0; i < userCovids.size(); ++i) {
            dataUser[i][0] = userCovids.get(i).getName();
            dataUser[i][1] = userCovids.get(i).getId();
            dataUser[i][2] = ManagerService.getInstance().getHealthCenterName(userCovids.get(i).getHealthCenter());
            dataUser[i][3] = String.valueOf(userCovids.get(i).getDob());
            dataUser[i][4] = userCovids.get(i).getState();
        }
        DefaultTableModel model = new DefaultTableModel(dataUser, columns);
        table.setModel(model);
        setUpButtonTable();
    }


    public void showTable() {

        for (int i = 0; i < list.size(); i++) {
            data[i][0] = list.get(i).getName();
            data[i][1] = list.get(i).getId();
            data[i][2] = ManagerService.getInstance().getHealthCenterName(list.get(i).getHealthCenter());
            data[i][3] = String.valueOf(list.get(i).getDob());
            data[i][4] = list.get(i).getState();

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

    // get button listener
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


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == refreshButton) {
            searchField.setText("");
            searchField.setPlaceholder("Nhập tên cần tìm kiếm bằng tên, cmnd");

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

    public ViewDetailsUserCovid getViewDetailsUserCovid() {
        return viewDetailsUserCovid;
    }

}
