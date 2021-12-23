package view;

import model.UserCovid;
import service.ManagerService;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewDetailsUserCovid extends JPanel implements ActionListener {
    private JLabel title;
    private JLabel name;
    private JLabel id;
    private JLabel address;
    private JLabel dob;
    private JLabel currentState;
    private JLabel healthCenter;

    private JTextField nameField, nameNLQField;
    private JTextField idField, idNLQField;
    private JTextField addressField, addressNLQField;
    private JTextField dobField, dobNLQField;
    private JTextField curField, curNLQField;
    private JTextField healthCenterField, healthCenterNLQField;

    private JPanel namePanel, idPanel, addressPanel, dobPanel, curPanel, heathCenterPanel, NQLpanel;
    private JPanel namePanel1, idPanel1, addressPanel1, dobPanel1, curPanel1, heathCenterPanel1;

    private Button back;
    public ViewDetailsUserCovid(UserCovid userCovid) {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentX((float) 0.5);

        JPanel titlePanel = new JPanel(new BorderLayout());
        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        back = new Button("Quay lại");
        backPanel.add(back);

        title = new JLabel("Xem chi tiết người có liên quan", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setAlignmentX(Box.CENTER_ALIGNMENT);

        titlePanel.add(backPanel, BorderLayout.NORTH);
        titlePanel.add(title, BorderLayout.CENTER);


        name = new JLabel("Họ tên ");
        nameField = new JTextField(userCovid.getName());
        namePanel = new JPanel();
        namePanel.setAlignmentX(Box.CENTER_ALIGNMENT);
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));
        namePanel.add(Box.createRigidArea(new Dimension(20, 0)));
        namePanel.add(name);
        namePanel.add(Box.createRigidArea(new Dimension(120, 0)));
        namePanel.add(nameField);
        namePanel.add(Box.createRigidArea(new Dimension(20, 0)));
        namePanel.setMaximumSize(new Dimension(1000, 30));

        id = new JLabel("CMND: ");
        idField = new JTextField(userCovid.getId());
        idPanel = new JPanel();
        idPanel.setAlignmentX(Box.CENTER_ALIGNMENT);
        idPanel.setLayout(new BoxLayout(idPanel, BoxLayout.X_AXIS));
        idPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        idPanel.add(id);
        idPanel.add(Box.createRigidArea(new Dimension(119, 0)));
        idPanel.add(idField);
        idPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        idPanel.setMaximumSize(new Dimension(1000, 30));

        address = new JLabel("Địa chỉ: ");
        addressField = new JTextField(userCovid.getAddress());
        addressPanel = new JPanel();
        addressPanel.setAlignmentX(Box.CENTER_ALIGNMENT);
        addressPanel.setLayout(new BoxLayout(addressPanel, BoxLayout.X_AXIS));
        addressPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        addressPanel.add(address);
        addressPanel.add(Box.createRigidArea(new Dimension(116, 0)));
        addressPanel.add(addressField);
        addressPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        addressPanel.setMaximumSize(new Dimension(1000, 30));

        dob = new JLabel("Năm sinh: ");
        dobField = new JTextField(userCovid.getDob().toString());
        dobPanel = new JPanel();
        dobPanel.setAlignmentX(Box.CENTER_ALIGNMENT);
        dobPanel.setLayout(new BoxLayout(dobPanel, BoxLayout.X_AXIS));
        dobPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        dobPanel.add(dob);
        dobPanel.add(Box.createRigidArea(new Dimension(101, 0)));
        dobPanel.add(dobField);
        dobPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        dobPanel.setMaximumSize(new Dimension(1000, 30));

        currentState = new JLabel("Trạng thái hiện tại: ");
        curField = new JTextField(userCovid.getState());
        curPanel = new JPanel();
        curPanel.setAlignmentX(Box.CENTER_ALIGNMENT);
        curPanel.setLayout(new BoxLayout(curPanel, BoxLayout.X_AXIS));
        curPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        curPanel.add(currentState);
        curPanel.add(Box.createRigidArea(new Dimension(48, 0)));
        curPanel.add(curField);
        curPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        curPanel.setMaximumSize(new Dimension(1000, 30));

        healthCenter = new JLabel("Nơi đang điều trị/cách ly: ");
        healthCenterField = new JTextField(ManagerService.getInstance().getHealthCenterName(userCovid.getHealthCenter()));
        heathCenterPanel = new JPanel();
        heathCenterPanel.setAlignmentX(Box.CENTER_ALIGNMENT);
        heathCenterPanel.setLayout(new BoxLayout(heathCenterPanel, BoxLayout.X_AXIS));
        heathCenterPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        heathCenterPanel.add(healthCenter);
        heathCenterPanel.add(Box.createRigidArea(new Dimension(11, 0)));
        heathCenterPanel.add(healthCenterField);
        heathCenterPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        heathCenterPanel.setMaximumSize(new Dimension(1000, 30));

        NQLpanel = new JPanel();
        Border titleBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Người liên quan",
                TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION);
        NQLpanel.setBorder(titleBorder);
        NQLpanel.setLayout(new BoxLayout(NQLpanel, BoxLayout.Y_AXIS));
        NQLpanel.setAlignmentX((float) 0.5);

        nameNLQField = new JTextField();
        namePanel1 = new JPanel();
        namePanel1.setAlignmentX(Box.CENTER_ALIGNMENT);
        namePanel1.setLayout(new BoxLayout(namePanel1, BoxLayout.X_AXIS));
        namePanel1.add(Box.createRigidArea(new Dimension(20, 0)));
        namePanel1.add(new JLabel("Họ tên "));
        namePanel1.add(Box.createRigidArea(new Dimension(120, 0)));
        namePanel1.add(nameNLQField);
        namePanel1.add(Box.createRigidArea(new Dimension(20, 0)));
        namePanel1.setMaximumSize(new Dimension(1000, 30));

        idNLQField = new JTextField();
        idPanel1 = new JPanel();
        idPanel1.setAlignmentX(Box.CENTER_ALIGNMENT);
        idPanel1.setLayout(new BoxLayout(idPanel1, BoxLayout.X_AXIS));
        idPanel1.add(Box.createRigidArea(new Dimension(20, 0)));
        idPanel1.add(new JLabel("CMND: "));
        idPanel1.add(Box.createRigidArea(new Dimension(119, 0)));
        idPanel1.add(idNLQField);
        idPanel1.add(Box.createRigidArea(new Dimension(20, 0)));
        idPanel1.setMaximumSize(new Dimension(1000, 30));

        addressNLQField = new JTextField();
        addressPanel1 = new JPanel();
        addressPanel1.setAlignmentX(Box.CENTER_ALIGNMENT);
        addressPanel1.setLayout(new BoxLayout(addressPanel1, BoxLayout.X_AXIS));
        addressPanel1.add(Box.createRigidArea(new Dimension(20, 0)));
        addressPanel1.add(new JLabel("Địa chỉ: "));
        addressPanel1.add(Box.createRigidArea(new Dimension(116, 0)));
        addressPanel1.add(addressNLQField);
        addressPanel1.add(Box.createRigidArea(new Dimension(20, 0)));
        addressPanel1.setMaximumSize(new Dimension(1000, 30));

        dobNLQField = new JTextField();
        dobPanel1 = new JPanel();
        dobPanel1.setAlignmentX(Box.CENTER_ALIGNMENT);
        dobPanel1.setLayout(new BoxLayout(dobPanel1, BoxLayout.X_AXIS));
        dobPanel1.add(Box.createRigidArea(new Dimension(20, 0)));
        dobPanel1.add(new JLabel("Năm sinh: "));
        dobPanel1.add(Box.createRigidArea(new Dimension(101, 0)));
        dobPanel1.add(dobNLQField);
        dobPanel1.add(Box.createRigidArea(new Dimension(20, 0)));
        dobPanel1.setMaximumSize(new Dimension(1000, 30));

        curNLQField = new JTextField();
        curPanel1 = new JPanel();
        curPanel1.setAlignmentX(Box.CENTER_ALIGNMENT);
        curPanel1.setLayout(new BoxLayout(curPanel1, BoxLayout.X_AXIS));
        curPanel1.add(Box.createRigidArea(new Dimension(20, 0)));
        curPanel1.add(new JLabel("Trạng thái hiện tại: "));
        curPanel1.add(Box.createRigidArea(new Dimension(48, 0)));
        curPanel1.add(curNLQField);
        curPanel1.add(Box.createRigidArea(new Dimension(20, 0)));
        curPanel1.setMaximumSize(new Dimension(1000, 30));

        healthCenterNLQField = new JTextField();
        heathCenterPanel1 = new JPanel();
        heathCenterPanel1.setAlignmentX(Box.CENTER_ALIGNMENT);
        heathCenterPanel1.setLayout(new BoxLayout(heathCenterPanel1, BoxLayout.X_AXIS));
        heathCenterPanel1.add(Box.createRigidArea(new Dimension(20, 0)));
        heathCenterPanel1.add(new JLabel("Nơi đang điều trị/cách ly: "));
        heathCenterPanel1.add(Box.createRigidArea(new Dimension(11, 0)));
        heathCenterPanel1.add(healthCenterNLQField);
        heathCenterPanel1.add(Box.createRigidArea(new Dimension(20, 0)));
        heathCenterPanel1.setMaximumSize(new Dimension(1000, 30));

        NQLpanel.add(Box.createRigidArea(new Dimension(0, 20)));
        NQLpanel.add(namePanel1);
        NQLpanel.add(Box.createRigidArea(new Dimension(0, 20)));
        NQLpanel.add(idPanel1);
        NQLpanel.add(Box.createRigidArea(new Dimension(0, 20)));
        NQLpanel.add(addressPanel1);
        NQLpanel.add(Box.createRigidArea(new Dimension(0, 20)));
        NQLpanel.add(dobPanel1);
        NQLpanel.add(Box.createRigidArea(new Dimension(0, 20)));
        NQLpanel.add(curPanel1);
        NQLpanel.add(Box.createRigidArea(new Dimension(0, 20)));
        NQLpanel.add(heathCenterPanel1);
        NQLpanel.add(Box.createRigidArea(new Dimension(0, 20)));

        add(titlePanel);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(namePanel);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(idPanel);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(addressPanel);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(dobPanel);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(curPanel);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(heathCenterPanel);
        add(Box.createRigidArea(new Dimension(0, 50)));
        add(NQLpanel);
    }

    public void addBackButton(ActionListener listener) {
        back.addActionListener(listener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

