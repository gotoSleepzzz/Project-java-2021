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
        new ManagerController();
    }


    public String changeState(String status) {

        if (status.equals("F2")) {
            return "F1";
        }
        if (status.equals("F3")) {
            return "F2";
        }
        if (status.equals("F4")) {
            return "F3";
        }
        // never reach here
        return "F0";
    }


    void BuildGraph() {
        List<UserCovid> userCovidList = ManagerService.getInstance().findAllUserCovid();
        // Convert List to TreeMap
        Map<String, UserCovid> userCovidMap = new HashMap<>();

        for (UserCovid userCovid : userCovidList) {
            userCovidMap.put(userCovid.getId(), userCovid);
        }

        DirectedGraph<String, DefaultEdge> directedGraph = new DefaultDirectedGraph<>(DefaultEdge.class);

        for (UserCovid userCovid : userCovidList) {
            if (userCovid.getId().equals("2")) {
                userCovid.setIdReached("1");
            }
            if (userCovid.getId().equals("3")) {
                userCovid.setIdReached("2");
            }
            if (userCovid.getId().equals("4")) {
                userCovid.setIdReached("2");
            }
        }

        for (UserCovid userCovid : userCovidList) {
            String id = userCovid.getId();
            directedGraph.addVertex(id);
            String idReached = userCovid.getIdReached();
            if (idReached != null) {
                if (!directedGraph.containsVertex(idReached)) {
                    directedGraph.addVertex(idReached);
                }
                directedGraph.addEdge(idReached, id);
            }
        }

        var iterator = new BreadthFirstIterator<>(directedGraph, "1");
        var first = iterator.next();
        userCovidMap.get(first).setState("F0");
        // breath first iterator

        while (iterator.hasNext()) {
            String id = iterator.next();
            userCovidMap.get(id).setState(changeState(userCovidMap.get(id).getState()));
        }

        // loops through all map
        for (Map.Entry<String, UserCovid> entry : userCovidMap.entrySet()) {
            System.out.println(entry.getValue());
        }
    }

}