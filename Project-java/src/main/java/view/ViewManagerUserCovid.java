package view;

import model.UserCovid;
import service.UserService;

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
    private JButton button;
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
    List<UserCovid> list;
    private JButton backButton;


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
        searchField.setPlaceholder("Nhập tên cần tìm kiếm bằng tên, cmnd");

        searchButton = new Button("Tìm kiếm");
        searchPanel.add(refreshButton);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);


        label = new JLabel("Danh sách người liên quan Covid 19", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        panelHeader.add(MenuPanel, BorderLayout.NORTH);
        panelHeader.add(label, BorderLayout.SOUTH);
        panelHeader.add(searchPanel, BorderLayout.CENTER);


        String[] columns = new String[]{"Họ tên", "CMND", "Nơi điều trị", "Năm sinh",
                "Trạng thái hiện tại", "Xem chi tiết", "Chỉnh sửa"};

        // Convert list to String[][]
        list = UserService.getInstance().findAll();
        String[][] data = new String[list.size()][5];

        for (int i = 0; i < list.size(); i++) {
            data[i][0] = list.get(i).getName();
            data[i][1] = list.get(i).getId();
            data[i][2] = String.valueOf(list.get(i).getHealthCenter());
            data[i][3] = String.valueOf(list.get(i).getDob());
            data[i][4] = list.get(i).getState();
        }


        DefaultTableModel model = new DefaultTableModel(data, columns);
        table = new JTable();
        table.setModel(model);

        table.getColumn("Xem chi tiết").setCellRenderer(new ButtonRenderer("Xem chi tiết"));
        button = new JButton("Xem chi tiết");
        buttonModify = new JButton("Chỉnh sửa");
        table.getColumn("Xem chi tiết").setCellEditor(new ButtonEditor(new JCheckBox(), "Xem chi tiết", button));
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

        button.addActionListener(
                event -> {
                    JOptionPane.showMessageDialog(null, "Do you want to modify this line?");
                }
        );

        buttonModify.addActionListener(e -> {

            int option = JOptionPane.showConfirmDialog(null, "Bạn có muốn chỉnh sửa không?", "Chỉnh sửa", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                int selectedRow = table.getSelectedRow();
                String selectedRowData = (String) table.getValueAt(selectedRow, 1);
                JOptionPane.showMessageDialog(null, "You have selected: " + columns[1] + ": " + selectedRowData);
            }
        });


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

    public void addDetailsActionListener(ActionListener actionListener) {
        button.addActionListener(actionListener);
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
}
