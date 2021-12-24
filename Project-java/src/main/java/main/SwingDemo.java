package main;

import control.ManagerController;
import model.UserCovid;
import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.traverse.BreadthFirstIterator;
import service.ManagerService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SwingDemo {


    public static void main(String[] args) {

        var x = new ManagerController();
        ManagerService.getInstance().setNameManager("Manager");
//        List<UserCovid> userCovids = ManagerService.getInstance().findAllUserCovid();
//        System.out.println(userCovids);
    }






}