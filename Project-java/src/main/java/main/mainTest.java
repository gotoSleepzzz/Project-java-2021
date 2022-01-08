package main;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import view.UserView;

import control.*;
import view.*;
import view.admin.*;
import control.*;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

public class mainTest {
    
    static Logger logger = LogManager.getLogger(mainTest.class);
    public static void main(String[] args) {

        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }
        logger.info("Start program");
        new loginController();
//        adminView a = new adminView();
//        a.setVisible(true);
        //new ManagerController();
        //new ViewManager();

//        JFrame frame = new JFrame();
//        frame.add(new ViewDetailsUserCovid());
//        frame.setSize(1000,750);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setVisible(true);
    }
}
