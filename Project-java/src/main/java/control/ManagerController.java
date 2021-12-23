package control;

import model.UserCovid;
import org.jfree.ui.RefineryUtilities;
import service.ManagerService;
import view.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class ManagerController{


    private ManagerService managerService;
    private ViewManager viewManager;
    private ViewDetailsUserCovid viewDetailsUserCovid;

    public ManagerController() {
        viewManager = new ViewManager();
        setActionListener();
    }

    public void setActionListener() {
        viewManager.getViewManagerUserCovid().addSearchActionListener(new AddSearchEvent());
        viewManager.getViewManagerUserCovid().addBackButtonListener_ViewManagerUserCovid(new AddBackEventViewManager());
        viewManager.getViewManagerUserCovid().addButtonWatchDetailsListener(new AddButtonDetails_ViewManagerUserCovid());
        viewManager.getViewManagerUserCovid().addButtonModifyListener(new AddButtonModify_ViewMangerUserCovid());
        viewManager.getViewManagerNYP().addBackListener(new AddBackEventViewManager());
        viewManager.getViewRegisterUserCovid().AddBackListener(new AddBackEventViewManager());
        viewManager.addStatisticListener(new AddStatisticsEvent());
        viewManager.addTransitionListener(new AddTransactionEvent());
        viewManager.addComsumeListener(new AddConsumeEvent());
        viewManager.addDebtListener(new AddViewDebtEvent());
    }

    class AddButtonModify_ViewMangerUserCovid implements  ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            JOptionPane.showMessageDialog(viewManager.getViewManagerUserCovid(), "Ban thich modify u");
        }
    }

    class AddButtonDetails_ViewManagerUserCovid implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            var user = viewManager.getViewManagerUserCovid().getUserCovid();
            viewManager.setSize(1000, 750);
            viewManager.setLocationRelativeTo(null);
            viewManager.getContentPane().removeAll();

            viewDetailsUserCovid = new ViewDetailsUserCovid(user);
            viewDetailsUserCovid.addBackButton(new AddBack_ViewDetailsUserCovid());
            viewManager.getViewManagerUserCovid().setViewDetailsUserCovid(viewDetailsUserCovid);
            viewManager.getContentPane().add(viewDetailsUserCovid);
            viewManager.getContentPane().validate();
        }
    }

    class AddBack_ViewDetailsUserCovid implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            viewManager.setSize(1000, 750);
            viewManager.setLocationRelativeTo(null);
            viewManager.getContentPane().removeAll();
            viewManager.getContentPane().add(viewManager.getViewManagerUserCovid());
            viewManager.getContentPane().validate();
        }
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
            List<UserCovid> list = new ArrayList<>();
            String textSearch = viewManager.getViewManagerUserCovid().getContentSearch();
            if (textSearch.equals(viewManager.getViewManagerUserCovid().getPlaceHolderSearchTextField())) {
                viewManager.getViewManagerUserCovid().showTable();
            } else {
                var user = ManagerService.getInstance().findOneUserCovid(textSearch);
                if (user != null) {
                    list.add(user);
                    viewManager.getViewManagerUserCovid().renderTable(list);
                    viewManager.getViewManagerUserCovid().addButtonWatchDetailsListener(new AddButtonDetails_ViewManagerUserCovid());
                    viewManager.getViewManagerUserCovid().addButtonModifyListener(new AddButtonModify_ViewMangerUserCovid());
                }
                else {
                    // show message dialog Khong tim thay nguoi lien quan
                    JOptionPane.showMessageDialog(viewManager.getViewManagerUserCovid(), "Không tìm thấy người liên quan");
                }
            }
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
