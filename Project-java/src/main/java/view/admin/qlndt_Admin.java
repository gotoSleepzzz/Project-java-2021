package view.admin;

import view.CustomTextField;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class qlndt_Admin extends JPanel {
    private JLabel label;

    private CustomTextField searchField;
    private JButton searchBtn;

    private JTable table;
    private JScrollPane scroll;

    private JLabel nameLabel;
    private JTextField nameField;
    private JLabel totalLabel;
    private JTextField totalField;
    private JLabel curLabel;
    private JTextField curField;

    private JButton addBtn;
    private JButton delBtn;
    private JButton updateBtn;

    private final String[] headers = new String[]{
            "Tên", "Sức chứa", "Số lượng tiếp"
    };

    private String placeHolder = "Nhập tên cơ sở điều trị";

    private Object data = new Object[][]{};

    public qlndt_Admin() {
        init();
    }


    private void init() {


        label = new JLabel("QUẢN LÝ NƠI ĐIỀU TRỊ");
        label.setAlignmentX(CENTER_ALIGNMENT);
        label.setFont(new Font("Serif", Font.BOLD, 35));
        label.setBorder(new EmptyBorder(10, 10, 10, 10));

        searchField = new CustomTextField(70);
        searchField.setPlaceholder(placeHolder);
        searchBtn = new JButton("Tìm kiếm");
        searchBtn.setPreferredSize(new Dimension(100, 30));

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());
        searchPanel.add(searchField);
        searchPanel.add(searchBtn);


        table = new JTable();
        scroll = new JScrollPane();
        table.setModel(new DefaultTableModel((Object[][]) data, headers));
        scroll.setViewportView(table);
        scroll.setPreferredSize(new Dimension(600, 500));
        table.setFont(new Font("Serif", Font.PLAIN, 15));
        table.setAutoCreateRowSorter(true);


        addBtn = new JButton("Thêm");
        addBtn.setPreferredSize(new Dimension(100, 30));
        delBtn = new JButton("Xóa");
        delBtn.setPreferredSize(new Dimension(100, 30));
        updateBtn = new JButton("Cập nhật");
        updateBtn.setPreferredSize(new Dimension(100, 30));
        JPanel btnPanel = new JPanel();
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.CENTER);
        flowLayout.setHgap(50);
        flowLayout.setVgap(50);
        btnPanel.setLayout(flowLayout);
        btnPanel.add(addBtn);
        btnPanel.add(delBtn);
        btnPanel.add(updateBtn);


        nameLabel = new JLabel("Tên nơi điều trị");
        nameField = new JTextField();
        totalLabel = new JLabel("Sức chứa");
        totalField = new JTextField();
        curLabel = new JLabel("Số lương hiện tại");
        curField = new JTextField();

        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new GridLayout(3, 2, 20, 40));
        fieldPanel.add(nameLabel);
        fieldPanel.add(nameField);
        fieldPanel.add(totalLabel);
        fieldPanel.add(totalField);
        fieldPanel.add(curLabel);
        fieldPanel.add(curField);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(2, 1, 0, 65));
        infoPanel.setBorder(new TitledBorder("Thông tin"));
        infoPanel.add(fieldPanel);
        infoPanel.add(btnPanel);

        JPanel bigPanel = new JPanel();
        bigPanel.setLayout(new FlowLayout());
        bigPanel.add(scroll);
        bigPanel.add(infoPanel);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        mainPanel.add(label);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(searchPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(bigPanel);

        add(mainPanel);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();

                String name = table.getModel().getValueAt(row, 0).toString();
                String total = table.getModel().getValueAt(row, 1).toString();
                String cur = table.getModel().getValueAt(row, 2).toString();

                nameField.setText(name);
                totalField.setText(total);
                curField.setText(cur);



            }
        });
    }


    public String getNameField() {
        return nameField.getText();
    }

    public String getTotalField() {
        return totalField.getText();
    }

    public String getCurField() {
        return curField.getText();
    }

    public void handlerAddButton(ActionListener e) {
        addBtn.addActionListener(e);
    }

    public void handlerDelButton(ActionListener e) {
        delBtn.addActionListener(e);
    }

    public void handlerUpdateButton(ActionListener e) {
        updateBtn.addActionListener(e);
    }

    public DefaultTableModel getTableModel() {
        return (DefaultTableModel) table.getModel();
    }

    public int getSelectedRow() {
        return table.getSelectedRow();
    }

    public void setTableModel(String[][] list) {
        table.setModel(new DefaultTableModel(list, headers));
    }

    public String getSearchField() {
        return searchField.getText();
    }
    public void addSearchButtonListener(ActionListener e) {
        searchBtn.addActionListener(e);
    }
}
