/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import java.awt.BorderLayout;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author TRUNG
 */
public class ManagerNYP{
    private JFrame frame;
    private JButton newBtn,delBtn,updateBtn, searchBtn;
    private JTextField searchBar, name, limit, time, cost;
    private JTable table;
    private JPanel searchPanel, mainPanel, contentPanel, infoPanel, btnPanel, utilPanel; 
    private JScrollPane scroll;
    private JComboBox filter;
    private final String[] category = new String[]{
        "< 500.000", "500.000 - 1.000.000"
    };
    private final String[] headerTable = new String[]{
      "Tên gói", "Mức giới hạn", "Thời gian giới hạn", "Đơn giá"  
    };
    private Object data = new Object [][]{};
    ManagerNYP(){
        JLabel header = new JLabel("Quản lý gói nhu yếu phẩm");
        header.setFont(new Font("Serif",Font.BOLD,35));
        header.setAlignmentX(CENTER_ALIGNMENT);
        
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
        mainPanel.add(header);
        mainPanel.add(searchPanel);
        mainPanel.add(contentPanel);
        
        frame = new JFrame();
        frame.setTitle("Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(950,600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.add(mainPanel);
    }
    /*public static void main(String[] args) {
        new ManagerNYP();
    }*/
}
