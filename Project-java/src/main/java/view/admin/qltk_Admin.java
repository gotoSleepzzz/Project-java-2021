package view.admin;

import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
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


public class qltk_Admin extends JPanel{
    private JTextField searchField;
    private JButton searchBtn;

    private JLabel label;

    private JTable accTable;
    private JScrollPane accScroll;

    private JButton banBtn;
    private JButton showHisBtn;
    private JButton addAccBtn;

    private JTable hisTable;
    private JScrollPane hisScroll;
    
    private final String[] accHeaders = new String[] {
        "Username","Password","Role","Status"
    };
    
    private final String[] hisHeaders = new String[] {
        "Thời gian","Username","Lịch sử"
    };
    
    private Object accData = new Object[][]{};
    private Object hisData = new Object[][]{};

    public qltk_Admin() {
        init();
    }

    private void init() {
        searchField = new JTextField(30);
        searchBtn = new JButton("Tìm kiếm"); 
        searchBtn.setPreferredSize( new Dimension( 100, 30 ));
        
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        searchPanel.add(searchField);
        searchPanel.add(searchBtn);
        
        label = new JLabel("                    QUẢN LÝ TÀI KHOẢN            ");
        label.setAlignmentX(CENTER_ALIGNMENT);
        label.setFont(new Font("Serif",Font.BOLD,35));
        
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        
        topPanel.add(searchPanel);
        topPanel.add(label);
        
        accTable = new JTable();
        accScroll = new JScrollPane();
        accTable.setModel(new DefaultTableModel((Object[][]) accData, accHeaders));
        accTable.setAutoCreateRowSorter(true);
        accScroll.setViewportView(accTable);
        
        banBtn = new JButton("Khóa/Mở khóa");
        banBtn.setPreferredSize( new Dimension( 150, 50 ));
        showHisBtn = new JButton("Hiện lịch sử");
        showHisBtn.setPreferredSize( new Dimension( 150, 50 ));
        addAccBtn = new JButton("Thêm quản lý");
        addAccBtn.setPreferredSize( new Dimension( 150, 50 ));
        JPanel chucnang = new JPanel();
        chucnang.setLayout(new GridLayout(3,1,20,120));
        chucnang.setBorder(new CompoundBorder(new TitledBorder("Chức năng"), new EmptyBorder(4, 4, 4, 4)));
        chucnang.add(banBtn);
        chucnang.add(showHisBtn);
        chucnang.add(addAccBtn);
        
        hisTable = new JTable();
        hisScroll = new JScrollPane();
        hisTable.setModel(new DefaultTableModel((Object[][]) hisData, hisHeaders));
        hisTable.setAutoCreateRowSorter(true);
        hisScroll.setViewportView(hisTable);  
        
        JPanel centerPanel = new JPanel();
        FlowLayout flowLayout = new FlowLayout();
        centerPanel.setLayout(flowLayout);
        flowLayout.setHgap(10);
        centerPanel.add(accScroll);
        centerPanel.add(chucnang);
        centerPanel.add(hisScroll);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
        mainPanel.add(topPanel);
        mainPanel.add(centerPanel);
        
        add(mainPanel);
    }
    public DefaultTableModel getTableAccountModel(){
        return (DefaultTableModel) accTable.getModel();
    }
    public void setTableAccountModel(String[][]list){
        accTable.setModel(new DefaultTableModel(list,accHeaders));
    }
    public void handlerShowHisBtn(ActionListener e){
        showHisBtn.addActionListener(e);
    }
    public int getSelectedRowTableAcc(){
        return accTable.getSelectedRow();
    }
    public void setTableHistoryModel(String[][]list){
        hisTable.setModel(new DefaultTableModel(list,hisHeaders));
    }
    public void handlerBanBtn(ActionListener e){
        banBtn.addActionListener(e);
    }
}
