package view;

import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

public class ViewRegisterUserCovid extends JPanel implements ActionListener {

    // declaration of variables

    Province province;

    {
        try {
            province = new Province();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    // JLabel
    private JLabel title;
    private JLabel name;
    private JLabel id;
    private JLabel address;
    private JLabel dob;
    private JLabel currentState;
    private JLabel healthCenter;
    private JLabel peopleReached;


    // JTextField
    private CustomTextField nameField;
    private CustomTextField idField;
    private CustomTextField addressField;
    private JComboBox<Integer> year;
    private JComboBox<String> currentStateComboBox;
    private JComboBox<String> districtComboBox;
    private JComboBox<String> provinceComboBox;
    private JComboBox<String> communeComboBox;
    private JPanel addressPanel;
    private JComboBox<String> healthCenterComboBox;
    private CustomTextField peopleReachedField;

    // Jbutton

    private String placeholderName = "Nhập họ tên:";
    private String placeholderId = "Nhập chứng minh nhân dân";
    private String placeholderAddress = "Nhập số nhà, tên đường";
    private String placeholderPeopleReached = "Những người liên quan, (cách nhau bởi dấu \",\" VD: Nguyễn Văn A, Nguyễn Văn B)";


    private JButton submit;
    private JButton back;
    // JPanel
    private JPanel row1;
    private JPanel row2;
    private JPanel row3;
    private JPanel row4;
    private JPanel row5;
    private JPanel row6;
    private JPanel row7;
    private JPanel panel;


    // JOptionPane
    private JOptionPane message;

    // Icon
    private ImageIcon icon;

    // isValidForm
    private boolean isValidForm;


    // JPanel

    public ViewRegisterUserCovid() {

        // initializing variables
        setLayout(new BorderLayout());

        // Initializing JLabel
        title = new JLabel("Đăng ký người liên quan Covid-19\n", SwingConstants.CENTER); // Using SwingConstants to set the text in the center of the label
        title.setFont(new Font("Arial", Font.BOLD, 20)); // Setting the font of the label
        // set padding top for title
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        name = new JLabel(         "Họ tên:                                 ");
        id = new JLabel(           "CMND:                                  ");
        address = new JLabel(      "Địa chỉ:                               ");
        dob = new JLabel(          "Năm sinh:                             ");
        currentState = new JLabel( "Trạng thái hiện tại:                ");
        healthCenter = new JLabel( "Nơi đang điều trị/cách ly:     ");
        peopleReached = new JLabel("Người liên quan Covid-19 :   ");
        currentStateComboBox = new JComboBox<>();
        currentStateComboBox.addItem("F0");
        currentStateComboBox.addItem("F1");
        currentStateComboBox.addItem("F2");
        currentStateComboBox.addItem("F3");

        // Initializing JTextField

        nameField = new CustomTextField(45);
        nameField.setPlaceholder("Họ tên");

        idField = new CustomTextField(45);
        idField.setPlaceholder("Nhập chứng minh nhân dân");


        provinceComboBox = new JComboBox<>();
        provinceComboBox.addItem("Tỉnh/Thành phố");
        List<String> provinceList = province.getListOfProvines();
        for (String province : provinceList) {
            provinceComboBox.addItem(province);
        }

        provinceComboBox.addActionListener(this);


        districtComboBox = new JComboBox<>();
        districtComboBox.addItem("Quận");
        districtComboBox.addActionListener(this);

        communeComboBox = new JComboBox<>();
        communeComboBox.addItem("Huyện");
        addressField = new CustomTextField(20);
        addressField.setPlaceholder("Nhập số nhà, tên đường");

        addressPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        addressPanel.add(provinceComboBox);
        addressPanel.add(districtComboBox);
        addressPanel.add(communeComboBox);
        addressPanel.add(addressField);


        year = new JComboBox<>();
        // get current year
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = currentYear; i >= 1900; i--) {
            year.addItem(i);
        }

        healthCenterComboBox = new JComboBox<>();
        healthCenterComboBox.addItem("Bệnh viện Dã Chiến");
        healthCenterComboBox.addActionListener(this);

        peopleReachedField = new CustomTextField(45);
        peopleReachedField.setPlaceholder("\"Những người liên quan, (cách nhau bởi dấu \\\",\\\" VD: Nguyễn Văn A, Nguyễn Văn B)\"");

        // Initializing Button
        submit = new JButton("Submit");
        submit.addActionListener(this);
        back = new JButton("Back");

        JLabel[] labels = {name, id, address, dob, currentState, healthCenter, peopleReached};
        JTextField[] textFields = {nameField, idField, addressField, new JTextField(), new JTextField(), new JTextField(), peopleReachedField};
        JPanel[] rows = {row1, row2, row3, row4, row5, row6, row7};

        // Create a new panel that will hold the contents of the frame and this panel would be center
        panel = new JPanel();
        panel.add(title);
        panel.add(name);
        panel.add(nameField);
        // Set layout for panel using GridLayout
        panel.setLayout(new GridLayout(7, 1, 20, 0));

        for (int i = 0; i < labels.length; i++) {

            rows[i] = new JPanel(new FlowLayout(FlowLayout.LEFT));
            rows[i].add(labels[i]);

            if (i == 4)
                rows[i].add(currentStateComboBox);
            if (i == 2)
                rows[i].add(addressPanel);
            if (i == 5)
                rows[i].add(healthCenterComboBox);
            if (i == 3)
                rows[i].add(year);

            if (i != 2 && i != 5  && i != 3  && i != 4)
                rows[i].add(textFields[i]);

            panel.add(rows[i]);

        }


        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(submit);


        panel.setBorder(BorderFactory.createEmptyBorder(10, 10 , 10, 10));
        // adding panel to the frame
        add(title, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }


    public void showMessage(String message, String state) {
        // get current
        String path = new File("").getAbsolutePath() + "/" + state.toLowerCase() + ".png";
        System.out.println(path);
        icon = new ImageIcon(path);
        icon.setImage(icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));

        if (state.equalsIgnoreCase("danger")) {
            JOptionPane.showMessageDialog(null, message, "Warning", JOptionPane.WARNING_MESSAGE, icon);
        } else if (state.equalsIgnoreCase("success")) {
            JOptionPane.showMessageDialog(null, message, "Success", JOptionPane.INFORMATION_MESSAGE, icon);
        }
    }

    public UserCovid getInfoUser() {
        String name = nameField.getText();
        String id = idField.getText();
        String address = addressField.getText();
        String dob = year.getSelectedItem().toString();
        String currentState = currentStateComboBox.getSelectedItem().toString();
        String healthCenter = healthCenterComboBox.getSelectedItem().toString();
        String peopleReached = peopleReachedField.getText();

        UserCovid userCovid = new UserCovid(name, id, Date.valueOf(dob), address, currentState, healthCenter, peopleReached);
        return userCovid;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {

            if (nameField.getText().equals(placeholderName) || idField.getText().equals(placeholderId) || addressField.getText().equals(placeholderAddress) || peopleReachedField.getText().equals(placeholderPeopleReached)) {
                if (provinceComboBox.getSelectedItem().toString().equals("Tỉnh/Thành phố") || districtComboBox.getSelectedItem().toString().equals("Quận") || communeComboBox.getSelectedItem().toString().equals("Huyện"))
                    showMessage("Vui lòng nhập đầy đủ thông tin", "danger");
            } else {
                isValidForm = true;
                showMessage("Đăng ký thông tin thành công", "success");
            }
        }

        if (e.getSource() == provinceComboBox) {

            String pv = provinceComboBox.getSelectedItem().toString();
            if (!pv.equals("Tỉnh/Thành phố")) {
                List<String> districts = province.getListOfDistricts(pv);
                districtComboBox.removeAllItems();
                districtComboBox.addItem("Quận");
                for (String district : districts) {
                    districtComboBox.addItem(district);
                }

            }
        }
        if (e.getSource() == districtComboBox) {

            var x = districtComboBox.getSelectedItem();
            if (x != null) {
                String dt = x.toString();
                if (!dt.equals("Quận")) {
                    List<String> communes = province.getListOfCommunes(dt);
                    communeComboBox.removeAllItems();
                    for (String ward : communes) {
                        communeComboBox.addItem(ward);
                    }
                }
            }

        }

    }

    public boolean isValidForm() {
        return isValidForm;
    }

}