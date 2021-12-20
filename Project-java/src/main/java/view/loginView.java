package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class loginView extends JFrame{
    JLabel usrLabel;
    JLabel passLabel;
    JTextField usrInput;
    JPasswordField passInput;
    
    JCheckBox showPassBox;
    
    JButton loginBtn;
    JButton clearBtn;
    
    
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
        
        usrLabel = new JLabel("Username");
        passLabel = new JLabel("Password");
        
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
                        
        
        this.add(usrLabel);
        this.add(passLabel);
        this.add(usrInput);
        this.add(passInput);
        this.add(showPassBox);
        this.add(loginBtn);
        this.add(clearBtn);
        
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
    }
    
    public String getUsername(){
        return usrInput.getText();
    }
    
    public String getPass(){
        return passInput.getText();
    }
    
    public void addLoginEvent(ActionListener act){
        loginBtn.addActionListener(act);
    }
}
