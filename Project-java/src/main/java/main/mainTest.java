package main;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import view.UserView;

public class mainTest {

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
//        new loginController();
        //new ViewManager();
        new UserView();
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
