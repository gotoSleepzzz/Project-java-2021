package view;

import javax.swing.*;
import java.awt.*;
import model.UserCovid;

public class ViewDetailsUserCovid extends JPanel {
    private UserCovid userCovid;

    private JLabel title;
    private JLabel name;
    private JLabel id;
    private JLabel address;
    private JLabel dob;
    private JLabel currentState;
    private JLabel healthCenter;
    private JLabel peopleReached;
    private JLabel history;

    // JPanel
    public ViewDetailsUserCovid(UserCovid userCovid) {
        this.userCovid = userCovid;
        setLayout(new BorderLayout());
        title = new JLabel("Xem chi tiết người có liên quan");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setHorizontalAlignment(JLabel.CENTER);

        name = new JLabel("Họ tên: " + userCovid.getName());
        id = new JLabel("CMND: " + userCovid.getId());
        address = new JLabel("Địa chỉ: " + userCovid.getAddress());
        dob = new JLabel("Năm sinh: " + userCovid.getDob());
        currentState = new JLabel("Trạng thái hiện tại :" + userCovid.getState());
        healthCenter = new JLabel("Nơi đang điều trị / cách ly: " + userCovid.getHealthCenter());
        //peopleReached = new JLabel("Số người đã điều trị: " + userCovid.getPeopleReached());
        //history = new JLabel("Lịch sử quá trình được quản lý: " + userCovid.getHistory());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 1));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(name);
        panel.add(id);
        panel.add(address);
        panel.add(dob);
        panel.add(currentState);
        panel.add(healthCenter);
        panel.add(peopleReached);
        panel.add(history);

        add(title, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);

    }

}

