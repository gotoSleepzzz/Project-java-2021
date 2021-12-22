package view;

import control.loginController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class loginView extends JFrame{
    JTextField usrInput;
    JPasswordField passInput;
    
    JTextField adminInput;
    JPasswordField adminPassInput;
    
    JPasswordField newPass;
    JPasswordField confirmNewPass;
    
    JCheckBox showPassBox;
    JCheckBox showPassBox1;
    JCheckBox showPassBox2;
    
    JButton loginBtn;
    JButton clearBtn;
    JButton adminBtn;
    JButton createNewPassBtn;
    
    JPanel mainPanel;
    JPanel createAdminPanel;
    JPanel createNewPassPanel;
    
    
    public loginView(){
        init();
    }
    
    private void init(){
        this.setTitle("Covid App - Login");
        this.setBounds(10, 10, 600, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        mainPanel = new JPanel();
        mainPanel.setBounds(1,1,600,400);
        mainPanel.setLayout(null);
        
        createAdminPanel = new JPanel();
        createAdminPanel.setBounds(0,0,600,400);
        createAdminPanel.setLayout(null);
        
        createNewPassPanel = new JPanel();
        createNewPassPanel.setBounds(0,0,600,400);
        createNewPassPanel.setLayout(null);
        
        
        //=============================================
        
        JLabel usrLabel = new JLabel("Username");
        JLabel passLabel = new JLabel("Password");
        
        usrInput = new JTextField(20);
        passInput = new JPasswordField(20);
        showPassBox = new JCheckBox("Show Password");
        
        loginBtn = new JButton("LOGIN");
        clearBtn = new JButton("CLEAR");
        
        usrLabel.setBounds(160, 100, 70, 10);
        passLabel.setBounds(160, 150, 70, 10);
        
        usrInput.setBounds(270,95,170,25);
        passInput.setBounds(270,145,170,25);
        
        showPassBox.setBounds(270,175,150,20);
        
        clearBtn.setBounds(160,220,120,30);
        loginBtn.setBounds(320,220,120,30);
                        
        
        mainPanel.add(usrLabel);
        mainPanel.add(passLabel);
        mainPanel.add(usrInput);
        mainPanel.add(passInput);
        mainPanel.add(showPassBox);
        mainPanel.add(loginBtn);
        mainPanel.add(clearBtn);
        
        showPassBox.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(showPassBox.isSelected()){
                    passInput.setEchoChar((char)0);
                }else{
                    passInput.setEchoChar('*');
                }
            }
        });
        
        clearBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                usrInput.setText("");
                passInput.setText("");
            }
        });
        
        
        //====================================
        JLabel adminLabel = new JLabel("CREATE ADMIN");
        JLabel usrLabel1 = new JLabel("Admin name");
        JLabel passLabel1 = new JLabel("Password");
        
        adminInput = new JTextField(20);
        adminPassInput = new JPasswordField(20);
        showPassBox1 = new JCheckBox("Show Password");
        
        adminBtn = new JButton("CREATE ADMIN");
        
        usrLabel1.setBounds(160, 100, 70, 10);
        passLabel1.setBounds(160, 150, 70, 11);
        
        adminInput.setBounds(270,95,170,25);
        adminPassInput.setBounds(270,145,170,25);
        
        showPassBox1.setBounds(270,175,150,20);
        adminBtn.setBounds(270,220,170,30);
        
        createAdminPanel.add(usrLabel1);
        createAdminPanel.add(passLabel1);
        createAdminPanel.add(adminInput);
        createAdminPanel.add(adminPassInput);
        createAdminPanel.add(showPassBox1);
        createAdminPanel.add(adminBtn);
        
        showPassBox1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(showPassBox1.isSelected()){
                    adminPassInput.setEchoChar((char)0);
                }else{
                    adminPassInput.setEchoChar('*');
                }
            }
        });
        
        //=================================
        
        //====================================
        JLabel createPassLabel = new JLabel("CREATE NEW PASSWORD");
        JLabel newpassLabel2 = new JLabel("New password");
        JLabel confirmPassLabel2 = new JLabel("Confirm password");
        
        newPass = new JPasswordField(20);
        confirmNewPass = new JPasswordField(20);
        showPassBox2 = new JCheckBox("Show Password");
        
        createNewPassBtn = new JButton("CREATE");
        
        newpassLabel2.setBounds(120, 100, 120, 10);
        confirmPassLabel2.setBounds(120, 150, 120, 11);
        
        newPass.setBounds(290,95,170,25);
        confirmNewPass.setBounds(290,145,170,25);
        
        showPassBox2.setBounds(290,175,150,20);
        createNewPassBtn.setBounds(290,220,170,30);
        
        createNewPassPanel.add(newpassLabel2);
        createNewPassPanel.add(confirmPassLabel2);
        createNewPassPanel.add(newPass);
        createNewPassPanel.add(confirmNewPass);
        createNewPassPanel.add(showPassBox2);
        createNewPassPanel.add(createNewPassBtn);
        
        showPassBox2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(showPassBox2.isSelected()){
                    newPass.setEchoChar((char)0);
                    confirmNewPass.setEchoChar((char)0);
                }else{
                    newPass.setEchoChar('*');
                    confirmNewPass.setEchoChar('*');
                }
            }
        });
        
        //=================================
        
        this.add(mainPanel);
        this.add(createAdminPanel);
        this.add(createNewPassPanel);
        createAdminPanel.setVisible(false);
        createNewPassPanel.setVisible(false);
        mainPanel.setVisible(true);
        
    }
    
    public String getUsername(){
        return usrInput.getText();
    }
    
    public String getPass(){
        return passInput.getText();
    }
    
    public String getAdmiName(){
        return adminInput.getText();
    }
    
    public String getAdminPass(){
        return adminPassInput.getText();
    }
    
    public String getNewPass() {
        return newPass.getText();
    }

    public String getConfirmPass() {
        return confirmNewPass.getText();
    }
    
    public void addLoginEvent(ActionListener act){
        loginBtn.addActionListener(act);
    }
    
    public void showLoginView() {
        mainPanel.setVisible(true);
        createNewPassPanel.setVisible(false);
        createAdminPanel.setVisible(false);
    }

    public void showCreateAdminView() {
        mainPanel.setVisible(false);
        createNewPassPanel.setVisible(false);
        createAdminPanel.setVisible(true);
    }
    
    public void showCreateNewPass(){
        mainPanel.setVisible(false);
        createAdminPanel.setVisible(false);
        createNewPassPanel.setVisible(true);
    }

    public void addCreateAdminEvent(ActionListener createAdmin) {
        adminBtn.addActionListener(createAdmin);
    }
    
    public void addCreatNewPassEvent(ActionListener act){
        createNewPassBtn.addActionListener(act);
    }

    
}
