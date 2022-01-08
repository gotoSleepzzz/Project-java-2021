package main;

import control.hethongthanhtoan.heThongThanhToanController;
import javax.swing.UIManager;

public class serverMain {
    public static void main(String[] args) {
       try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }
        new heThongThanhToanController();
    }
}
