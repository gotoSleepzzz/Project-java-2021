package view;

import model.NYP;
import service.ManagerService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewUpdateNYP extends JFrame implements ActionListener {
    // HEADER
    private JLabel title;

    // BODY

    private JLabel nameLabel;
    private JLabel limitLabel;
    private JLabel dateLabel;
    private JLabel priceLabel;

    private JLabel nameCurrentLabel;
    private JLabel limitCurrentLabel;
    private JLabel dateCurrentLabel;
    private JLabel priceCurrentLabel;

    private JPanel body;

    private JTextField nameField;
    private JTextField limitField;
    private JTextField dateField;
    private JTextField priceField;

    private JComboBox typeDateCombobox;
    private JComboBox sortCombobox;


    // FOOTER
    private JButton saveButton;
    private JButton cancelButton;
    private JPanel footerPanel;

    NYP nyp;


    // VARIABLE
    String labelCombobox[] = {"Ngày", "Tuần", "Tháng"};

    public ViewUpdateNYP(NYP nyp) {
        this.nyp = nyp;
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        title = new JLabel("Cập nhật nhu yếu phẩm", SwingConstants.CENTER);
        title.setBorder(new EmptyBorder(10, 10, 10, 10));
        title.setFont(new Font("Arial", Font.BOLD, 20));
        saveButton = new JButton("Lưu");
        cancelButton = new JButton("Hủy");

        footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        nameLabel = new JLabel("Tên hiện tại");
        nameLabel = new JLabel("Tên nhu yếu phẩm");
        limitLabel = new JLabel("Giới hạn số lượng của mỗi người");
        dateLabel = new JLabel("Giời gian cho phép mua lại");
        priceLabel = new JLabel("Giá tiền");

        nameCurrentLabel = new JLabel(nyp.getName());
        nameCurrentLabel.setForeground(Color.BLUE);
        nameCurrentLabel.setFont(new Font("Arial", Font.BOLD, 15));
        limitCurrentLabel = new JLabel(String.valueOf(nyp.getLimit()));
        limitCurrentLabel.setForeground(Color.BLUE);
        limitCurrentLabel.setFont(new Font("Arial", Font.BOLD, 15));
        dateCurrentLabel = new JLabel(String.valueOf(nyp.getExpriredDate() + " ngày"));
        dateCurrentLabel.setForeground(Color.BLUE);
        dateCurrentLabel.setFont(new Font("Arial", Font.BOLD, 15));
        priceCurrentLabel = new JLabel(String.valueOf(nyp.getPrice()));
        priceCurrentLabel.setForeground(Color.BLUE);
        priceCurrentLabel.setFont(new Font("Arial", Font.BOLD, 15));

        nameField = new JTextField(nyp.getName());
        limitField = new JTextField(String.valueOf(nyp.getLimit()));
        dateField = new JTextField(String.valueOf(nyp.getExpriredDate()));
        priceField = new JTextField(String.valueOf(nyp.getPrice()));

        typeDateCombobox = new JComboBox(labelCombobox);


        body = new JPanel();
        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
        body.setBorder(new EmptyBorder(10, 10, 10, 10));
        // make body start from top
        body.setAlignmentX(Component.TOP_ALIGNMENT);
        body.setLayout(new GridLayout(4, 2));

        // ---------------------------------------------------
        JPanel namePanel = new JPanel(new GridLayout(2, 1));
        namePanel.add(nameLabel);
        namePanel.add(nameCurrentLabel);
        body.add(namePanel);

        JPanel nameTextPanel = new JPanel(new GridLayout(2, 1));
        JLabel nameTextLabel = new JLabel("Nhập tên mới");
        nameTextLabel.setFont(new Font("Tomaho", Font.BOLD, 15));
        nameTextPanel.add(nameTextLabel);

        nameTextPanel.add(nameField);
        body.add(nameTextPanel);

        // --------------------------------------------------
        JPanel limitPanel = new JPanel(new GridLayout(2, 1));
        limitPanel.add(limitLabel);
        limitPanel.add(limitCurrentLabel);
        body.add(limitPanel);

        JPanel limitTextPanel = new JPanel(new GridLayout(2, 1));
        JLabel limitTextLabel = new JLabel("Giới hạn lần mua mới");
        limitTextLabel.setFont(new Font("Tomaho", Font.BOLD, 15));
        limitField = new JTextField(nyp.getLimit() + "");
        limitTextPanel.add(limitTextLabel);
        limitTextPanel.add(limitField);

        body.add(limitTextPanel);
        // --------------------------------------------------

        JPanel datePanel = new JPanel(new GridLayout(2, 1));
        datePanel.add(dateLabel);
        datePanel.add(dateCurrentLabel);
        body.add(datePanel);

        JPanel dateTextPanel = new JPanel(new GridLayout(2, 1));
        JLabel dateTextLabel = new JLabel("Thời gian mua lại mới");
        dateTextLabel.setFont(new Font("Tomaho", Font.BOLD, 15));
        dateField = new JTextField(nyp.getExpriredDate() + "");
        dateTextPanel.add(dateTextLabel);
        dateTextPanel.add(dateField);
        body.add(dateTextPanel);

        // --------------------------------------------------
        JPanel pricePanel = new JPanel(new GridLayout(2, 1));
        pricePanel.add(priceLabel);
        pricePanel.add(priceCurrentLabel);
        body.add(pricePanel);


        JPanel priceTextPanel = new JPanel(new GridLayout(2, 1));
        body.add(priceField);
        JLabel priceTextLabel = new JLabel("Giá tiền mới");
        priceTextLabel.setFont(new Font("Tomaho", Font.BOLD, 15));
        priceTextPanel.add(priceTextLabel);
        priceTextPanel.add(priceField);
        body.add(priceTextPanel);

        // --------------------------------------------------
        cancelButton.addActionListener(this);
        //

        footerPanel.add(cancelButton);
        footerPanel.add(saveButton);
        add(title, BorderLayout.NORTH);
        add(footerPanel, BorderLayout.SOUTH);
        add(body, BorderLayout.CENTER);
        setLocationRelativeTo(null);
        setVisible(true);
    }


    public NYP getInfoNYP() {
        String nameNYP = nameField.getText();
        String limitNYP = limitField.getText();
        String typeDate = (String) typeDateCombobox.getSelectedItem();
        String unit = dateField.getText();
        String priceNYP = priceField.getText();

        if (nameNYP.equals("") || limitNYP.equals("") || priceNYP.equals("") || unit.equals(""))
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
        else if (Integer.parseInt(limitNYP) < 0 || Double.parseDouble(priceNYP) < 0)
            JOptionPane.showMessageDialog(null, "Giá và số lượng không được âm");
        else {
            int day = typeDate.equals(labelCombobox[0]) ? 1 : typeDate.equals(labelCombobox[1]) ? 7 : 30;
            return new NYP(nyp.getId(),nameNYP, Integer.parseInt(limitNYP), Integer.parseInt(unit) * day, Double.parseDouble(priceNYP));
        }
        return null;
    }

    public void addSaveButtonListener(ActionListener e) {
        saveButton.addActionListener(e);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancelButton) {
            this.dispose();
        }
    }

}
