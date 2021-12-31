package main;

import control.ManagerController;
import java.sql.Timestamp;
import java.util.Date;
import model.UserCovid;
import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.traverse.BreadthFirstIterator;
import service.ManagerService;
import view.ViewChangeStateAndHospital;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SwingDemo {


    public static void main(String[] args) {

        new ManagerController();
        ManagerService.getInstance().setNameManager("Manager");
//        List<UserCovid> userCovids = ManagerService.getInstance().findAllUserCovid();
//        System.out.println(userCovids);
    }
}