package view;

import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedHashMap;


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

    ViewManagerUserCovid viewManagerUserCovid = new ViewManagerUserCovid();
    ViewRegisterUserCovid viewRegisterUserCovid = new ViewRegisterUserCovid();
    ManagerNYP viewManagerNYP = new ManagerNYP();
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
        getContentPane().add(header, BorderLayout.NORTH);
        getContentPane().add(body, BorderLayout.CENTER);
        setVisible(true);
        setLocationRelativeTo(null);
    }


    public ViewRegisterUserCovid getViewRegisterUserCovid() {
        return viewRegisterUserCovid;
    }

    public ManagerNYP getViewManagerNYP() {
        return viewManagerNYP;
    }

    // get this panel of viewManager
    public JPanel getPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(header,  BorderLayout.NORTH);
        panel.add(body, BorderLayout.CENTER);
        return panel;
    }

    public ViewManagerUserCovid getViewManagerUserCovid() {
        return viewManagerUserCovid;
    }

    public void addStatisticListener(ActionListener listener) {
        viewStatistic.addActionListener(listener);
    }

    public void addTransitionListener(ActionListener listener) {
        viewTransition.addActionListener(listener);
    }

    public void addComsumeListener(ActionListener listener) {
        viewComsume.addActionListener(listener);
    }

    public void addDebtListener(ActionListener listener) {
        viewDebt.addActionListener(listener);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == manageListButton) {
            getContentPane().removeAll();
            getContentPane().repaint();
            getContentPane().add(viewManagerUserCovid);
            setSize(1000, 800);
            getContentPane().revalidate();
        }
        if (e.getSource() == register) {
            getContentPane().removeAll();
            getContentPane().repaint();
            getContentPane().add(viewRegisterUserCovid);
            setSize(1000, 800);
            getContentPane().revalidate();
        }
        if (e.getSource() == manageNecessary) {
            getContentPane().removeAll();
            getContentPane().repaint();
            getContentPane().add(viewManagerNYP);
            setSize(1050, 700);
            getContentPane().revalidate();
        }

    }
}
