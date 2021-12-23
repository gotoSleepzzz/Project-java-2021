package main;

import javax.swing.UIManager;
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

public class mainTest {
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e1) {

        }
        new loginController();
        //new ViewManager();
        //new UserView();
        //adminView a = new adminView();
        //a.setVisible(true);
        //new ManagerController();
        //new ViewManager();

//        JFrame frame = new JFrame();
//        frame.add(new ViewDetailsUserCovid());
//        frame.setSize(1000,750);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setVisible(true);
    }
}
