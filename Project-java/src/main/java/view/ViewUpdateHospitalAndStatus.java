/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.UserCovid;
import service.ManagerService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * @author TRUNG
 */
public class ViewUpdateHospitalAndStatus extends JFrame implements ActionListener {
    private JLabel hosLabel, statusLabel;
    private JLabel hosUser, statusUser;
    private JComboBox hosBox, statusBox;
    private JButton save;
    String[] state = {"Không thay đổi", "F0"};

    public ViewUpdateHospitalAndStatus(UserCovid userCovid) {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel temp1 = new JPanel();
        JLabel title = new JLabel("Chỉnh sửa");
        title.setFont(title.getFont().deriveFont(20.0f));
        temp1.setLayout(new FlowLayout());
        temp1.add(title);
        mainPanel.add(temp1);

        JPanel temp2 = new JPanel();
        hosLabel = new JLabel("Nơi điều trị đang điều trị");
        hosLabel.setHorizontalAlignment(SwingConstants.LEFT);
        temp2.setLayout(new GridLayout(1, 1));
        temp2.add(hosLabel);
        mainPanel.add(temp2);

        JPanel hosPanel = new JPanel();
        hosUser = new JLabel(ManagerService.getInstance().getHealthCenterName(userCovid.getHealthCenter()));
        hosUser.setFont(hosUser.getFont().deriveFont(Font.BOLD));

        List<String> healthCenter = ManagerService.getInstance().getListHealtCenter();
        hosBox = new JComboBox();
        hosBox.addItem(state[0]);
        for (String s : healthCenter) {
            hosBox.addItem(s);
        }
        hosPanel.setLayout(new BoxLayout(hosPanel, BoxLayout.X_AXIS));
        hosPanel.add(Box.createRigidArea(new Dimension(15, 0)));
        hosPanel.add(hosUser);
        hosPanel.add(Box.createHorizontalGlue());
        hosPanel.add(hosBox);
        hosPanel.add(Box.createRigidArea(new Dimension(15, 0)));
        hosPanel.setMaximumSize(new Dimension(1000, 20));
        mainPanel.add(hosPanel);

        JPanel temp3 = new JPanel();
        statusLabel = new JLabel("Trạng thái hiện tại");
        statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
        temp3.setLayout(new GridLayout(1, 1));
        temp3.add(statusLabel);
        mainPanel.add(temp3);

        JPanel statusPanel = new JPanel();
        statusUser = new JLabel(userCovid.getState());
        // make font bold
        statusUser.setFont(statusUser.getFont().deriveFont(Font.BOLD));
        statusBox = new JComboBox(state);

        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
        statusPanel.add(Box.createRigidArea(new Dimension(15, 0)));
        statusPanel.add(statusUser);
        statusPanel.add(Box.createHorizontalGlue());
        statusPanel.add(statusBox);
        statusPanel.add(Box.createRigidArea(new Dimension(15, 0)));
        statusPanel.setMaximumSize(new Dimension(1000, 20));
        mainPanel.add(statusPanel);
        JPanel footer = new JPanel(new BorderLayout());
        JPanel tmp = new JPanel(new FlowLayout(FlowLayout.CENTER));
        save = new JButton("Lưu");
        tmp.add(save);
        save.addActionListener(this);
        footer.add(tmp, BorderLayout.CENTER);

        mainPanel.add(footer);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        add(mainPanel);
        setSize(700, 200);
        setLocation(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // pop up options confirm
        if (e.getSource() == save) {
            String currentState = statusBox.getSelectedItem().toString();
            String currentHealthCenter = hosBox.getSelectedItem().toString();
            if (currentState.equals(state[0]) && currentHealthCenter.equals(state[0])) {
                dispose();
            } else {
                Object[] options = {"Yes", "No"};
                JOptionPane.showOptionDialog(this, "Bạn có muốn lưu thay đổi không?",
                        "Lưu thay đổi",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null, options, options[1]);

            }

        }
    }
}