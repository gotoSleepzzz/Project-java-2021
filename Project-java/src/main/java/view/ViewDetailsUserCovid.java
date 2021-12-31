package view;

import model.UserCovid;
import service.ManagerService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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

    private JLabel debtLabel;
    private JTextField debtTextField;

    private Button back;

    public ViewDetailsUserCovid(UserCovid userCovid) {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentX((float) 0.5);

        JPanel titlePanel = new JPanel(new BorderLayout());
        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        back = new Button("Quay lại");
        backPanel.add(back);
        title = new JLabel("Xem chi tiết người có liên quan", SwingUtilities.CENTER);
        // title center the screen
        title.setFont(new Font("Arial", Font.BOLD, 20));
        titlePanel.add(backPanel, BorderLayout.NORTH);
        titlePanel.add(title, BorderLayout.CENTER);
        titlePanel.setMaximumSize(new Dimension(1000, 70));

//        titlePanel.add(title);
        name = new JLabel("Họ tên  ");
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

        id = new JLabel("CMND:  ");
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

        address = new JLabel("Địa chỉ:  ");
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

        dob = new JLabel("Năm sinh:  ");
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

        currentState = new JLabel("Trạng thái hiện tại:  ");
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

        healthCenter = new JLabel("Nơi đang điều trị/cách ly:");
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

        debtLabel = new JLabel("Dư nợ:                            ");
        debtTextField = new JTextField(userCovid.getDebt() + "");
        JPanel debtPanel = new JPanel();
        debtPanel.setAlignmentX(Box.CENTER_ALIGNMENT);
        debtPanel.setLayout(new BoxLayout(debtPanel, BoxLayout.X_AXIS));
        debtPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        debtPanel.add(debtLabel);
        debtPanel.add(Box.createRigidArea(new Dimension(11, 0)));
        debtPanel.add(debtTextField);
        debtPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        debtPanel.setMaximumSize(new Dimension(1000, 30));


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
        add(debtPanel);
        add(Box.createRigidArea(new Dimension(0, 50)));

        JLabel idReachedLabel = new JLabel("Người liên đới");
        idReachedLabel.setFont(new Font("Arial", Font.BOLD, 20));
        idReachedLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        idReachedLabel.setAlignmentX(Box.CENTER_ALIGNMENT);
        add(idReachedLabel);

        // create a table
        JTable table = new JTable();

        List<UserCovid> list = ManagerService.getInstance().findAllRelative(userCovid.getId());
        if (userCovid.getIdReached() != null) {
            list.add(ManagerService.getInstance().findOneUserCovid(userCovid.getIdReached()));
        }

        String[][] data = new String[list.size()][6];
        for (int i = 0; i < list.size(); i++) {
            data[i][0] = list.get(i).getName();
            data[i][1] = list.get(i).getId();
            data[i][2] = list.get(i).getAddress();
            data[i][3] = String.valueOf(list.get(i).getDob());
            data[i][4] = list.get(i).getState();
            data[i][5] = String.valueOf(list.get(i).getHealthCenter());
        }


        String columnNames[] = {"Họ và tên", "Số CMND", "Địa chỉ", "Năm sinh", "Trạng thái", "Nơi đang điều trị"};

        table.setModel(new DefaultTableModel(data, columnNames));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);
        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 15));
        table.setForeground(Color.BLACK);
        table.setBackground(Color.WHITE);
        table.setSelectionBackground(Color.LIGHT_GRAY);
        table.setSelectionForeground(Color.BLACK);
        table.setShowGrid(true);
        table.setGridColor(Color.LIGHT_GRAY);
        table.setShowVerticalLines(true);
        table.setShowHorizontalLines(true);
        table.setRowSelectionAllowed(true);
        table.setColumnSelectionAllowed(false);
        table.setCellSelectionEnabled(false);
        table.setFocusable(false);
        table.setRowHeight(30);
        table.setRowMargin(5);

        JScrollPane scrollPanel = new JScrollPane(table);
        scrollPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        scrollPanel.setAlignmentX(Box.CENTER_ALIGNMENT);
        add(scrollPanel);
    }

    public void addBackButton(ActionListener listener) {
        back.addActionListener(listener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

