package main;

import javax.swing.UIManager;
import control.loginController;
import view.*;
import view.admin.adminView;

public class mainTest {
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e1) {

        }
        //new loginController();
        //new ViewManager();
        //new UserView();
        adminView a = new adminView();
        a.setVisible(true);
    }
}
