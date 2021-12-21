package view.admin;

import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;


public class qlndt_Admin extends JPanel {
    private JLabel label;
    
    private JTextField searchField;
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
    
    private final String[] headers = new String[] {
        "Tên","Sức chứa","Số lượng tiếp"
    };
    
    private Object data = new Object[][]{};

    public qlndt_Admin() {
        init();
    }

    private void init() {
        label = new JLabel("QUẢN LÝ NƠI ĐIỀU TRỊ");
        label.setAlignmentX(CENTER_ALIGNMENT);
        label.setFont(new Font("Serif",Font.BOLD,35));
        
        
        searchField = new JTextField(78);
        searchBtn = new JButton("Tìm kiếm"); 
        searchBtn.setPreferredSize( new Dimension( 100, 30 ));
        
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());
        searchPanel.add(searchField);
        searchPanel.add(searchBtn);
        
        
        
        table = new JTable();
        scroll = new JScrollPane();
        table.setModel(new DefaultTableModel((Object[][]) data, headers));
        scroll.setViewportView(table);
        table.setAutoCreateRowSorter(true);
        
        
        addBtn = new JButton("Thêm");
        addBtn.setPreferredSize( new Dimension( 100, 30 ));
        delBtn = new JButton("Xóa");
        delBtn.setPreferredSize( new Dimension( 100, 30 ));
        updateBtn = new JButton("Cập nhật");
        updateBtn.setPreferredSize( new Dimension( 100, 30 ));
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
        fieldPanel.setLayout(new GridLayout(3,2,20,40));
        fieldPanel.add(nameLabel);
        fieldPanel.add(nameField);
        fieldPanel.add(totalLabel);
        fieldPanel.add(totalField);
        fieldPanel.add(curLabel);
        fieldPanel.add(curField);
        
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(2,1,0,65));
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
        mainPanel.add(Box.createRigidArea(new Dimension(0,10)));
        mainPanel.add(searchPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0,10)));
        mainPanel.add(bigPanel);
        
        add(mainPanel);
        
    }
    public String getNameField(){
        return nameField.getText();
    }
    public String getTotalField(){
        return totalField.getText();
    }
    public String getCurField(){
        return curField.getText();
    }
    public void handlerAddButton(ActionListener e){
        addBtn.addActionListener(e);
    }
}
