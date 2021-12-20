package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedHashMap;
import org.jfree.ui.RefineryUtilities;


public class ViewManager extends JFrame implements ActionListener {

    JPanel header;
    JLabel title;
    JPanel body;
    JPanel header_body;
    JLabel titleMenu;
    JButton manageListButton;
    JButton register;
    JButton manageNecessary;
    JButton viewStatistic;
    JButton viewTransition;
    JButton viewComsume;
    JButton viewDebt;

    JPanel insideBody;

    public ViewManager() {

        setTitle("Manager");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        title = new JLabel("Welcome Manager");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        header = new JPanel(new FlowLayout());
        header.add(title, SwingUtilities.CENTER);

        header_body = new JPanel(new FlowLayout());
        titleMenu = new JLabel("Menu");
        titleMenu.setFont(new Font("Arial", Font.BOLD, 20));
        titleMenu.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        titleMenu.setHorizontalAlignment(JLabel.CENTER);
        header_body.add(titleMenu, SwingUtilities.CENTER);

        insideBody = new JPanel(new GridLayout(5, 2, 30, 50));
        // make hgap and vgap
        insideBody.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));


        body = new JPanel(new BorderLayout());
        body.add(header_body, BorderLayout.NORTH);

        manageListButton = new JButton("Quản lý danh sách");
        manageListButton.addActionListener(this);
        register = new JButton("Đăng ký người liên quan");
        register.addActionListener(this);
        manageNecessary = new JButton("Quản lý gói nhu yếu phẩm");
        manageNecessary.addActionListener(this);
        viewStatistic = new JButton("Thống kê trạng thái");
        viewStatistic.addActionListener(this);
        viewTransition = new JButton("Thống kê chuyển trạng thái");
        viewTransition.addActionListener(this);
        viewComsume = new JButton("Thông kê tiêu thụ");
        viewComsume.addActionListener(this);
        viewDebt = new JButton("Thống kê dư nợ");
        viewDebt.addActionListener(this);

        insideBody.add(viewStatistic);
        insideBody.add(manageListButton);

        insideBody.add(viewTransition);
        insideBody.add(register);

        insideBody.add(viewComsume);
        insideBody.add(manageNecessary);

        insideBody.add(viewDebt);

        body.add(insideBody, BorderLayout.CENTER);

        add(header, BorderLayout.NORTH);
        add(body, BorderLayout.CENTER);


        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == manageListButton) {
            dispose();
            JFrame test = new JFrame();
            test.setSize(1000, 800);
            test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            test.add(new ViewManagerUserCovid());
            test.setVisible(true);

        }
        if (e.getSource() == register) {
            dispose();
            JFrame test = new JFrame();
            test.setTitle("Manger");
            test.setSize(1000, 800);
            test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            test.add(new ViewRegisterUserCovid());
            test.setVisible(true);
        }
        if (e.getSource() == manageNecessary) {
            dispose();
            new ManagerNYP();
        }
        if (e.getSource() == viewStatistic) {
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
            HashMap<String,LinkedHashMap<String, Integer>> status = new HashMap();
            status.put("F1",F1);
            status.put("F2",F2);
            ChartStatus chart = new ChartStatus(status);
            chart.pack();
            RefineryUtilities.centerFrameOnScreen( chart );
            chart.setVisible( true );
            chart.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }
        if (e.getSource() == viewTransition) {
            LinkedHashMap<String, Integer> data = new LinkedHashMap();
            data.put("Chuyển trạng thái", 100);
            data.put("Khỏi bệnh", 300);
            ChartTransition chart = new ChartTransition(data);
            chart.pack();
            RefineryUtilities.centerFrameOnScreen( chart );
            chart.setVisible( true );
            chart.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }
        if (e.getSource() == viewComsume) {
            LinkedHashMap<String, Integer> data = new LinkedHashMap();
            data.put("Food 1", 100);
            data.put("Food 2", 300);
            data.put("Food 3", 900);
            ChartConsume chart = new ChartConsume(data);
            chart.pack();
            RefineryUtilities.centerFrameOnScreen( chart );
            chart.setVisible( true );
            chart.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }
        if (e.getSource() == viewDebt) {
            LinkedHashMap<String, Double> data = new LinkedHashMap();
            data.put("<500", 60d);
            data.put("500-1000", 40d);
            ChartDebt chart = new ChartDebt(data);
            chart.pack();
            RefineryUtilities.centerFrameOnScreen( chart );
            chart.setVisible( true );
            chart.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }

    }
}
