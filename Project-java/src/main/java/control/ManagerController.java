package control;

import org.jfree.ui.RefineryUtilities;
import service.ManagerService;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class ManagerController{


    private ManagerService managerService;
    private ViewManager viewManager;
    Container menu;

    public ManagerController() {
        viewManager = new ViewManager();
        setActionListener();
    }

    public void setActionListener() {
        viewManager.getViewManagerUserCovid().addSearchActionListener(new AddSearchEvent());
        viewManager.getViewManagerUserCovid().addBackButtonListener_ViewManagerUserCovid(new AddBackEventViewManager());
        viewManager.getViewManagerNYP().addBackListener(new AddBackEventViewManager());
        viewManager.getViewRegisterUserCovid().AddBackListener(new AddBackEventViewManager());
        viewManager.addStatisticListener(new AddStatisticsEvent());
        viewManager.addTransitionListener(new AddTransactionEvent());
        viewManager.addComsumeListener(new AddConsumeEvent());
        viewManager.addDebtListener(new AddViewDebtEvent());
    }


    class AddBackEventViewManager implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            viewManager.setSize(800, 800);
            viewManager.setLocationRelativeTo(null);
            viewManager.getContentPane().removeAll();
            viewManager.getContentPane().add(viewManager.getPanel());
            viewManager.getContentPane().validate();
        }
    }



    class AddSearchEvent implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // show message dialog
            JOptionPane.showMessageDialog(null, "Searching...");
        }
    }

    class AddStatisticsEvent implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // show message dialog
            LinkedHashMap<String, Integer> F1 = new LinkedHashMap();
            F1.put("1970", 15);
            F1.put("1980", 30);
            F1.put("1990", 60);
            F1.put("2000", 120);
            F1.put("2010", 240);
            F1.put("2014", 300);
            LinkedHashMap<String, Integer> F2 = new LinkedHashMap();
            F2.put("1970", 30);
            F2.put("1980", 45);
            F2.put("1990", 50);
            F2.put("2000", 100);
            F2.put("2010", 60);
            F2.put("2014", 150);
            HashMap<String, LinkedHashMap<String, Integer>> status = new HashMap();
            status.put("F1", F1);
            status.put("F2", F2);
            ChartStatus chart = new ChartStatus(status);
            chart.pack();
            RefineryUtilities.centerFrameOnScreen(chart);
            chart.setVisible(true);
        }
    }

    class AddTransactionEvent implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            LinkedHashMap<String, Integer> data = new LinkedHashMap();
            data.put("Chuyển trạng thái", 100);
            data.put("Khỏi bệnh", 300);
            ChartTransition chart = new ChartTransition(data);
            chart.pack();
            RefineryUtilities.centerFrameOnScreen(chart);
            chart.setVisible(true);
        }
    }

    class AddConsumeEvent implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            LinkedHashMap<String, Integer> data = new LinkedHashMap();
            data.put("Food 1", 100);
            data.put("Food 2", 300);
            data.put("Food 3", 900);
            ChartConsume chart = new ChartConsume(data);
            chart.pack();
            RefineryUtilities.centerFrameOnScreen( chart );
            chart.setVisible(true);
        }
    }

    class AddViewDebtEvent implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            LinkedHashMap<String, Double> data = new LinkedHashMap();
            data.put("<500", 60d);
            data.put("500-1000", 40d);
            ChartDebt chart = new ChartDebt(data);
            chart.pack();
            RefineryUtilities.centerFrameOnScreen(chart);
            chart.setVisible(true);
        }
    }





}
