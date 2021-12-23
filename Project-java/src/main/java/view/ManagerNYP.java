package view;
import java.awt.BorderLayout;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Utilities;

/**
 *
 * @author TRUNG
 */
public class ManagerNYP extends JPanel implements ActionListener {
    private JButton newBtn,delBtn,updateBtn, searchBtn;
    private JTextField searchBar, name, limit, time, cost;
    private JTable table;
    private JPanel searchPanel, mainPanel, contentPanel, infoPanel, btnPanel, utilPanel;
    private JScrollPane scroll;
    private JComboBox filter;
    private JButton back;
    private final String[] category = new String[]{
            "< 500.000", "500.000 - 1.000.000"
    };
    private final String[] headerTable = new String[]{
            "Tên gói", "Mức giới hạn", "Thời gian giới hạn", "Đơn giá"
    };
    private Object data = new Object [][]{};

    JPanel headerPanel;



    public ManagerNYP(){

        JLabel title = new JLabel("                              Quản lý gói nhu yếu phẩm");
        title.setFont(new Font("Serif",Font.BOLD,35));
        title.setAlignmentX(CENTER_ALIGNMENT);

        headerPanel = new JPanel(new BorderLayout());
        back = new JButton("Quay lại");

        JPanel temp = new JPanel(new FlowLayout(FlowLayout.LEFT));
        temp.add(back);
        headerPanel.add(temp, BorderLayout.NORTH);
        headerPanel.add(title, BorderLayout.CENTER);
        // make title at center
        title.setAlignmentX(CENTER_ALIGNMENT);



        searchBar = new JTextField(75);
        searchBtn = new JButton("Search");

        searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
        searchPanel.add(searchBar);
        searchPanel.add(searchBtn);

        table = new JTable();
        scroll = new JScrollPane();
        table.setModel(new DefaultTableModel((Object[][]) data, headerTable));
        scroll.setViewportView(table);
        table.setAutoCreateRowSorter(true);

        newBtn = new JButton("New");
        newBtn.setSize(90, 30);
        delBtn = new JButton("Del");
        delBtn.setSize(90, 30);
        updateBtn = new JButton("Update");
        updateBtn.setSize(90, 30);
        btnPanel = new JPanel();
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.CENTER);
        flowLayout.setHgap(30);
        flowLayout.setVgap(30);
        btnPanel.setLayout(flowLayout);
        btnPanel.add(newBtn);
        btnPanel.add(delBtn);
        btnPanel.add(updateBtn);

        name = new JTextField();
        limit = new JTextField();
        time = new JTextField();
        cost = new JTextField();
        filter = new JComboBox(category);
        infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(5,2,20,19));
        infoPanel.add(new JLabel("Lọc"));
        infoPanel.add(filter);
        infoPanel.add(new JLabel("Tên gói"));
        infoPanel.add(name);
        infoPanel.add(new JLabel("Mức giới hạn"));
        infoPanel.add(limit);
        infoPanel.add(new JLabel("Thời gian giới hạn"));
        infoPanel.add(time);
        infoPanel.add(new JLabel("Đơn giá"));
        infoPanel.add(cost);

        utilPanel = new JPanel();
        utilPanel.setBorder(new TitledBorder("Thông tin"));
        utilPanel.setLayout(new GridLayout(2,1));
        utilPanel.add(infoPanel);
        utilPanel.add(btnPanel);

        contentPanel = new JPanel();
        contentPanel.setLayout(new FlowLayout());
        contentPanel.add(scroll);
        contentPanel.add(utilPanel);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(headerPanel);
        mainPanel.add(searchPanel);
        mainPanel.add(contentPanel);
        add(mainPanel);
        setVisible(true);
    }

    public void addBackListener(ActionListener listener){
        back.addActionListener(listener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
