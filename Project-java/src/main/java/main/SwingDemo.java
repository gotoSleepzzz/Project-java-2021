package main;
import service.ManagerService;
import view.admin.adminView;

public class SwingDemo {

    public static void main(String[] args) {
        ManagerService.getInstance().setNameManager("Manager");
        new adminView().setVisible(true);
    }
}