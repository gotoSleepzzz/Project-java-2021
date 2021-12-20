package main;

import javax.swing.UIManager;
import view.loginView;
import control.loginController;

public class mainTest {
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e1) {

        }
        new loginController();
    }
    
}
