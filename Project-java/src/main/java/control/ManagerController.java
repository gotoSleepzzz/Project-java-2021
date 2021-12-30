package control;

import model.NYP;
import model.UserCovid;
import org.jfree.ui.RefineryUtilities;
import service.ManagerService;
import view.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class ManagerController {


    private ManagerService managerService;
    private ViewManager viewManager;
    private ViewDetailsUserCovid viewDetailsUserCovid;
    private ViewUpdateHospitalAndStatus viewUpdateHospitalAndStatus;
    private ViewUpdateNYP viewUpdateNYP;

    String[] sortBy = {"Họ tên tăng dần theo thứ tự từ điển", "Năm sinh tăng dần", "Trạng thái hiện tại tăng dần theo thứ tự từ điển", "Dư nợ tăng dần", "CMND tăng dần theo thứ tự từ điển",
            "Họ tên giảm dần theo thứ tự từ điển", "Năm sinh giảm dần", "Trạng thái hiện tại giảm dần theo thứ tự từ điển", "Dư nợ giảm dần", "CMND giảm dần theo thứ tự từ điển"};
    String[] sortNYP = {"Mức giới hạn tăng dần", "Thời gian giới hạn tăng dần", "Đơn giá tằng dần",
            "Mức giới hạn giảm dần", "Thời gian giới hạn giảm dần", "Đơn giá giảm dần"};

    public ManagerController() {
        viewManager = new ViewManager();
        setActionListener();
    }

    public void setActionListener() {
        viewManager.getViewManagerUserCovid().addSearchActionListener(new AddSearchEvent());
        viewManager.getViewManagerUserCovid().addBackButtonListener_ViewManagerUserCovid(new AddBackEventViewManager());
        viewManager.getViewManagerUserCovid().addButtonWatchDetailsListener(new AddButtonDetails_ViewManagerUserCovid());
        viewManager.getViewManagerUserCovid().addHistoryActionListener(new AddButtonHistory_ViewManagerUserCovid());
        viewManager.getViewManagerUserCovid().addButtonModifyListener(new AddButtonModify_ViewMangerUserCovid());
        viewManager.getViewManagerUserCovid().addDropdownListener(new AddComboboxSort());

        viewManager.getViewManagerNYP().addBackListener(new AddBackEventViewManager());
        viewManager.getViewManagerNYP().addModifyActionListener(new AddButtonModify_ViewManagerNYP());
        viewManager.getViewManagerNYP().addRemoveActionListener(new AddButtonRemove_ViewManagerNYP());
        viewManager.getViewManagerNYP().addNewActionListener(new AddButtonNew_ViewManagerNYP());
        viewManager.getViewManagerNYP().addSearchActionListener(new AddButtonSearch_ViewManagerNYP());
        viewManager.getViewManagerNYP().addSortActionListener(new AddSortCombobox_ViewManagerNYP());
        viewManager.getViewManagerNYP().addTypeComboboxActionListener(new AddTypeCombox_ViewMangerNYP());

        viewManager.getViewRegisterUserCovid().AddBackListener(new AddBackEventViewManager());
        viewManager.addStatisticListener(new AddStatisticsEvent());
        viewManager.addTransitionListener(new AddTransactionEvent());
        viewManager.addComsumeListener(new AddConsumeEvent());
        viewManager.addDebtListener(new AddViewDebtEvent());
    }


    class AddButtonSave_ViewUpdateNYP implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // show confirm dialog
            int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn lưu thông tin này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                NYP nyp = viewUpdateNYP.getInfoNYP();
                if (nyp != null) {
                    var ok = ManagerService.getInstance().updateNYP(nyp);
                    if (ok) {
                        JOptionPane.showMessageDialog(null, "Cập nhật thành công");
                        viewManager.getViewManagerNYP().renderTable(ManagerService.getInstance().findAllNYP());
                        viewManager.getViewManagerNYP().addModifyActionListener(new AddButtonModify_ViewManagerNYP());
                        viewManager.getViewManagerNYP().addRemoveActionListener(new AddButtonRemove_ViewManagerNYP());
                        viewUpdateNYP.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Cập nhật thất bại");
                    }
                }

            } else {
                viewUpdateNYP.dispose();
            }
        }
    }

    class AddButtonModify_ViewManagerNYP implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            NYP nyp = viewManager.getViewManagerNYP().getSelectedNYP();
            viewUpdateNYP = new ViewUpdateNYP(nyp);
            viewUpdateNYP.addSaveButtonListener(new AddButtonSave_ViewUpdateNYP());
        }
    }



    class AddButtonSearch_ViewManagerNYP implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String query = viewManager.getViewManagerNYP().getContentSearch();
            if (!query.equals(viewManager.getViewManagerNYP().getSearchPlacehoder())) {
                List<NYP> list = ManagerService.getInstance().findAllNYP();
                List<NYP> result = new ArrayList<>();
                // search list contains name is query with regex local vietnamese
                for (NYP nyp : list) {

                    String pattern = nyp.getName();
                    // convert vietnamese pattern string to english with non sign
                    pattern = pattern.replaceAll("[àáạảãâầấậẩẫăằắặẳẵ]", "a");
                    pattern = pattern.replaceAll("[èéẹẻẽêềếệểễ]", "e");
                    pattern = pattern.replaceAll("[ìíịỉĩ]", "i");
                    pattern = pattern.replaceAll("[òóọỏõôồốộổỗơờớợởỡ]", "o");
                    pattern = pattern.replaceAll("[ùúụủũưừứựửữ]", "u");
                    pattern = pattern.replaceAll("[ỳýỵỷỹ]", "y");
                    pattern = pattern.replaceAll("[đ]", "d");
                    pattern = pattern.replaceAll("[ÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴ]", "A");
                    pattern = pattern.replaceAll("[ÈÉẸẺẼÊỀẾỆỂỄ]", "E");
                    pattern = pattern.replaceAll("[ÌÍỊỈĨ]", "I");
                    pattern = pattern.replaceAll("[ÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠ]", "O");
                    pattern = pattern.replaceAll("[ÙÚỤỦŨƯỪỨỰỬỮ]", "U");
                    pattern = pattern.replaceAll("[ỲÝỴỶỸ]", "Y");
                    pattern = pattern.replaceAll("[Đ]", "D");
                    pattern = pattern.toLowerCase(Locale.ROOT);
                    pattern = pattern.replaceAll("\\s+", "");
                    pattern = pattern.replaceAll("\\W", "");

                    query = query.replaceAll("[àáạảãâầấậẩẫăằắặẳẵ]", "a");
                    query = query.replaceAll("[èéẹẻẽêềếệểễ]", "e");
                    query = query.replaceAll("[ìíịỉĩ]", "i");
                    query = query.replaceAll("[òóọỏõôồốộổỗơờớợởỡ]", "o");
                    query = query.replaceAll("[ùúụủũưừứựửữ]", "u");
                    query = query.replaceAll("[ỳýỵỷỹ]", "y");
                    query = query.replaceAll("[đ]", "d");
                    query = query.replaceAll("[ÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴ]", "A");
                    query = query.replaceAll("[ÈÉẸẺẼÊỀẾỆỂỄ]", "E");
                    query = query.replaceAll("[ÌÍỊỈĨ]", "I");
                    query = query.replaceAll("[ÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠ]", "O");
                    query = query.replaceAll("[ÙÚỤỦŨƯỪỨỰỬỮ]", "U");
                    query = query.replaceAll("[ỲÝỴỶỸ]", "Y");
                    query = query.replaceAll("[Đ]", "D");
                    query = query.toLowerCase(Locale.ROOT);
                    query = query.toLowerCase(Locale.ROOT);
                    query = query.replaceAll("\\s+", "");
                    query = query.replaceAll("\\W", "");

                    // if query is substring of pattern
                    if (pattern.contains(query)) {
                        result.add(nyp);
                    }
                }
                if (result.size() == 0) {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả");
                } else {
                    viewManager.getViewManagerNYP().renderTable(result);
                    viewManager.getViewManagerNYP().addModifyActionListener(new AddButtonModify_ViewManagerNYP());
                    viewManager.getViewManagerNYP().addRemoveActionListener(new AddButtonRemove_ViewManagerNYP());
                }

            } else if (query.equals("") || query.equals(viewManager.getViewManagerNYP().getSearchPlacehoder())) {
                viewManager.getViewManagerNYP().renderTable(ManagerService.getInstance().findAllNYP());
                viewManager.getViewManagerNYP().addModifyActionListener(new AddButtonModify_ViewManagerNYP());
                viewManager.getViewManagerNYP().addRemoveActionListener(new AddButtonRemove_ViewManagerNYP());
            }
        }
    }

    class AddSortCombobox_ViewManagerNYP implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //    String[] sortNYP = {"Mức giới hạn tăng dần", "Thời gian giới hạn tăng dần", "Đơn giá tằng dần",
            //            "Mức giới hạn giảm dần", "Thời gian giới hạn giảm dần", "Đơn giá giảm dần"};
            JComboBox comboBoxSort = (JComboBox) e.getSource();
            if (comboBoxSort.getSelectedItem().toString().equals(sortNYP[0]))
                viewManager.getViewManagerNYP().renderTable(ManagerService.getInstance().sortNYPByLimitIncrement());
            if (comboBoxSort.getSelectedItem().toString().equals(sortNYP[1])) {
                viewManager.getViewManagerNYP().renderTable(ManagerService.getInstance().sortNYPByDateIncrement());
            }
            if (comboBoxSort.getSelectedItem().toString().equals(sortNYP[2]))
                viewManager.getViewManagerNYP().renderTable(ManagerService.getInstance().sortNYPByPriceIncrement());
            if (comboBoxSort.getSelectedItem().toString().equals(sortNYP[3]))
                viewManager.getViewManagerNYP().renderTable(ManagerService.getInstance().sortNYPByLimitDecrement());
            if (comboBoxSort.getSelectedItem().toString().equals(sortNYP[4]))
                viewManager.getViewManagerNYP().renderTable(ManagerService.getInstance().sortNYPByDateDecrement());
            if (comboBoxSort.getSelectedItem().toString().equals(sortNYP[5]))
                viewManager.getViewManagerNYP().renderTable(ManagerService.getInstance().sortNYPByPriceDecrement());

            viewManager.getViewManagerNYP().addModifyActionListener(new AddButtonModify_ViewManagerNYP());
            viewManager.getViewManagerNYP().addRemoveActionListener(new AddButtonRemove_ViewManagerNYP());

        }
    }

    class AddButtonNew_ViewManagerNYP implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            NYP nyp = viewManager.getViewManagerNYP().getInfoNYP();
            if (nyp != null) {
                ManagerService.getInstance().addNYP(nyp);
                viewManager.getViewManagerNYP().renderTable(ManagerService.getInstance().findAllNYP());
                viewManager.getViewManagerNYP().addModifyActionListener(new AddButtonModify_ViewManagerNYP());
                viewManager.getViewManagerNYP().addRemoveActionListener(new AddButtonRemove_ViewManagerNYP());
            }
        }
    }




    class AddButtonRemove_ViewManagerNYP implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //             make confirm dialog
            int result = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                // remove user
                NYP nyp = viewManager.getViewManagerNYP().getSelectedNYP();
                System.out.println(nyp.getId());
                ManagerService.getInstance().removeNYP(nyp.getId());
                viewManager.getViewManagerNYP().renderTable(ManagerService.getInstance().findAllNYP());
                viewManager.getViewManagerNYP().addModifyActionListener(new AddButtonModify_ViewManagerNYP());
                viewManager.getViewManagerNYP().addRemoveActionListener(new AddButtonRemove_ViewManagerNYP());
            }
        }
    }


    class AddButtonHistory_ViewManagerUserCovid implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            var user = viewManager.getViewManagerUserCovid().getUserCovid();

            new ViewChangeStateAndHospital(user.getId());
        }
    }


    class AddButtonModify_ViewMangerUserCovid implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            var user = viewManager.getViewManagerUserCovid().getUserCovid();
            viewUpdateHospitalAndStatus = new ViewUpdateHospitalAndStatus(user);
            viewUpdateHospitalAndStatus.addSaveListener(new AddButtonSave_ViewUpdateHopitalAndStatus());
        }
    }

    public void renderActionListenerTable() {
        viewManager.getViewManagerUserCovid().addButtonWatchDetailsListener(new AddButtonDetails_ViewManagerUserCovid());
        viewManager.getViewManagerUserCovid().addButtonModifyListener(new AddButtonModify_ViewMangerUserCovid());
        viewManager.getViewManagerUserCovid().addHistoryActionListener(new AddButtonHistory_ViewManagerUserCovid());

    }

    class AddButtonSave_ViewUpdateHopitalAndStatus implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String[] state = {"Không thay đổi", "F0"};
            var id = viewUpdateHospitalAndStatus.getUserId();
            var user = ManagerService.getInstance().findOneUserCovid(id);
            String currentState = viewUpdateHospitalAndStatus.getSelectedStatus();
            String currentHealthCenter = viewUpdateHospitalAndStatus.getSelectedHospital();
            if (currentState.equals(state[0]) && currentHealthCenter.equals(state[0])) {
                viewUpdateHospitalAndStatus.dispose();
            } else {

                Object[] options = {"Yes", "No"};
                var option = JOptionPane.showOptionDialog(viewUpdateHospitalAndStatus, "Bạn có muốn lưu thay đổi không?",
                        "Lưu thay đổi",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null, options, options[1]);
                if (option == JOptionPane.YES_OPTION) {
                    if (!currentHealthCenter.equals(state[0])) {
                        int idHealthCenter = ManagerService.getInstance().mapHealthCenterToId(currentHealthCenter);
                        ManagerService.getInstance().updateUserCovidByHealthCenter(idHealthCenter, viewUpdateHospitalAndStatus.getUserId());
                    }
                    if (!currentState.equals(state[0])) {
                        ManagerService.getInstance().updateUserCovidByState(viewUpdateHospitalAndStatus.getUserId(), "F0", viewUpdateHospitalAndStatus.getUserState());
                    }

                    viewManager.getViewManagerUserCovid().renderTable(ManagerService.getInstance().findAllUserCovid());
                    viewManager.getViewManagerUserCovid().repaint();
                    renderActionListenerTable();


                }
                viewUpdateHospitalAndStatus.dispose();
            }
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
            viewManager.setSize(1150, 800);
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


    class AddTypeCombox_ViewMangerNYP implements  ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(viewManager.getViewManagerNYP().getFilter());
            if (viewManager.getViewManagerNYP().getFilter().equals("< 500.000")) {
                viewManager.getViewManagerNYP().renderTable( ManagerService.getInstance().filterNYP(0, 500000));
            } else if (viewManager.getViewManagerNYP().getFilter().equals("500.000 - 1.000.000")) {
                viewManager.getViewManagerNYP().renderTable(ManagerService.getInstance().filterNYP(500000, 1000000));
            }
            else {
                viewManager.getViewManagerNYP().renderTable(ManagerService.getInstance().filterNYP(2000000, 2000000000));
            }
            viewManager.getViewManagerNYP().addModifyActionListener(new AddButtonModify_ViewManagerNYP());
            viewManager.getViewManagerNYP().addRemoveActionListener(new AddButtonRemove_ViewManagerNYP());
        }
    }

    class AddComboboxSort implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            JComboBox comboBoxSort = (JComboBox) e.getSource();
            if (comboBoxSort.getSelectedItem().toString().equals(sortBy[0]))
                viewManager.getViewManagerUserCovid().renderTable(ManagerService.getInstance().sortByNameIncrement());
            if (comboBoxSort.getSelectedItem().toString().equals(sortBy[1]))
                viewManager.getViewManagerUserCovid().renderTable(ManagerService.getInstance().sortByDobIncrement());
            if (comboBoxSort.getSelectedItem().toString().equals(sortBy[2]))
                viewManager.getViewManagerUserCovid().renderTable(ManagerService.getInstance().sortByStatusIncrement());
            if (comboBoxSort.getSelectedItem().toString().equals(sortBy[3]))
                viewManager.getViewManagerUserCovid().renderTable(ManagerService.getInstance().sortByDebtIncrement());
            if (comboBoxSort.getSelectedItem().toString().equals(sortBy[4]))
                viewManager.getViewManagerUserCovid().renderTable(ManagerService.getInstance().sortByIdIncrement());
            if (comboBoxSort.getSelectedItem().toString().equals(sortBy[5]))
                viewManager.getViewManagerUserCovid().renderTable(ManagerService.getInstance().sortByNameDecrement());
            if (comboBoxSort.getSelectedItem().toString().equals(sortBy[6]))
                viewManager.getViewManagerUserCovid().renderTable(ManagerService.getInstance().sortByDobDecrement());
            if (comboBoxSort.getSelectedItem().toString().equals(sortBy[7]))
                viewManager.getViewManagerUserCovid().renderTable(ManagerService.getInstance().sortByStatusDecrement());
            if (comboBoxSort.getSelectedItem().toString().equals(sortBy[8]))
                viewManager.getViewManagerUserCovid().renderTable(ManagerService.getInstance().sortByDebtDecrement());
            if (comboBoxSort.getSelectedItem().toString().equals(sortBy[9]))
                viewManager.getViewManagerUserCovid().renderTable(ManagerService.getInstance().sortByIdDecrement());

            renderActionListenerTable();

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
                viewManager.getViewManagerUserCovid().addButtonWatchDetailsListener(new AddButtonDetails_ViewManagerUserCovid());
                viewManager.getViewManagerUserCovid().addButtonModifyListener(new AddButtonModify_ViewMangerUserCovid());
            } else {
                var user = ManagerService.getInstance().findOneUserCovid(textSearch);
                if (user != null) {
                    list.add(user);
                    viewManager.getViewManagerUserCovid().renderTable(list);
                    renderActionListenerTable();

                } else {
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
            RefineryUtilities.centerFrameOnScreen(chart);
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
