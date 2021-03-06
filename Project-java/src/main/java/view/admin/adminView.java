package view.admin;


import control.QLNoiDieuTriController;
import control.QLTaiKhoanController;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.border.EmptyBorder;

public class adminView extends JFrame implements ActionListener {
    QLNoiDieuTriController qlNoiDieuTriController = null;
    QLTaiKhoanController qlTaiKhoanController = null;
    public adminView(){
        init();
    }
    
    private void init(){
        this.setTitle("ADMIN");
        this.setBounds(20, 20, 1300, 610);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        
        mainPanel = new JPanel();
        JPanel northPanel = new JPanel();
        JPanel centerPanel = new JPanel();
        
        label = new JLabel("WELCOME  ADMIN !!!");
        label.setFont(new Font("Serif",Font.BOLD,40));
        
        qltkBtn = new JButton("Quản lý tài khoản");
        qltkBtn.setPreferredSize(new Dimension(200, 200));
        qlndtBtn = new JButton("Quản lý nơi điều tri");
        qlndtBtn.setPreferredSize(new Dimension(200, 200));
        backBtn = new JButton("Quay lại");
        backBtn.setPreferredSize(new Dimension(40, 40));
        backBtn.setBounds(10, 10, 40, 40);
        
        
        qlndt = new qlndt_Admin();
        qltk = new qltk_Admin();
        
        northPanel.add(label);
        
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.CENTER);
        flowLayout.setHgap(200);
        flowLayout.setVgap(100);
        centerPanel.setLayout(flowLayout);
        centerPanel.add(qltkBtn);
        centerPanel.add(qlndtBtn);
        
        qltkBtn.addActionListener(this);
        qlndtBtn.addActionListener(this);
        backBtn.addActionListener(this);
        
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(new Insets(50, 50, 50, 50)));
        
        mainPanel.add(northPanel);
        mainPanel.add(centerPanel);
        
        
        backBtn.setBounds(0,0, 80, 40);
        mainPanel.setBounds(1, 1, this.getWidth(), this.getHeight());
        qltk.setBounds(1, 1, this.getWidth(), this.getHeight());
        qlndt.setBounds(1, 1, this.getWidth(), this.getHeight());
        
        
        backBtn.setVisible(false);
        qlndt.setVisible(false);
        qltk.setVisible(false);
        mainPanel.setVisible(true);
        
        add(backBtn);
        add(qlndt);
        add(qltk);
        add(mainPanel);
        setLocationRelativeTo(null);
    }
    
    private JPanel mainPanel;
    private JLabel label;
    private JButton qltkBtn;
    private JButton qlndtBtn;
    private JButton backBtn;
    private qlndt_Admin qlndt;
    private qltk_Admin qltk;

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == qltkBtn){
            mainPanel.setVisible(false);
            qltk.setBounds(1, 1, this.getWidth(), this.getHeight());
            if (qlTaiKhoanController == null)
                qlTaiKhoanController = new QLTaiKhoanController(qltk);
            else
                qltk.setVisible(true);
            backBtn.setVisible(true);                     
        }
        else if(e.getSource() == qlndtBtn){
            mainPanel.setVisible(false);
            setSize(new Dimension(1300, 700));
            qlndt.setBounds(1, 1, this.getWidth(), this.getHeight());
            qlndt.setSize(this.getWidth(), this.getHeight());

            if (qlNoiDieuTriController == null)
                qlNoiDieuTriController = new QLNoiDieuTriController(qlndt);
            else{
                qlndt.setVisible(true);
            }
            backBtn.setVisible(true);
        }
        else if (e.getSource() == backBtn){
            setSize(new Dimension(1300, 610));
            mainPanel.setVisible(true);
            qltk.setVisible(false);
            qlndt.setVisible(false);
            backBtn.setVisible(false);
            mainPanel.setBounds(0, 0, this.getWidth(), this.getHeight());
            mainPanel.setVisible(true);
        }
    }
}
