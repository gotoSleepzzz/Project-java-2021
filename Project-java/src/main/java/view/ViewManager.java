import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


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
//            new ViewRegisterUserCovid();
            dispose();
            JFrame test = new JFrame();
            test.setTitle("Manger");
            test.setSize(1000, 800);
            test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            test.add(new ViewRegisterUserCovid());
            test.setVisible(true);
        }
        if (e.getSource() == manageNecessary) {

        }
        if (e.getSource() == viewStatistic) {

        }
        if (e.getSource() == viewTransition) {

        }
        if (e.getSource() == viewComsume) {

        }
        if (e.getSource() == viewDebt) {

        }

    }
}
