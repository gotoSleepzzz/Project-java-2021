package main;

import control.hethongthanhtoan.heThongThanhToanController;
import javax.swing.UIManager;

public class serverMain {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e1) {

        }
        new heThongThanhToanController();
    }
}
