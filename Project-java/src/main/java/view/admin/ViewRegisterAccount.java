package view.admin;

import service.AccountService;
import view.CustomTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;

public class ViewRegisterAccount extends JFrame implements ActionListener {

    private final JButton register;
    private final JButton cancel;
    private final JComboBox<String> roleBox;
    private final CustomTextField username;
    private final CustomTextField password;

    private String placeholderId = "Nhập chứng minh nhân dân";
    private String placeholderPassword = "Bỏ trống nếu là người dùng";

    private ImageIcon icon;


    public ViewRegisterAccount() {

        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // set title
        setTitle("Đăng ký tài khoản");

        JLabel lbTitle = new JLabel("Đăng ký tài khoản", SwingUtilities.CENTER);
        lbTitle.setFont(new Font("Arial", Font.BOLD, 20));
        // padding
        lbTitle.setBorder(BorderFactory.createEmptyBorder(10, 10, 40, 10));


        // create a new JTextField for username
        username = new CustomTextField(30);
        username.setPlaceholder(placeholderId);
        // create a new JTextField for password
        password = new CustomTextField(30);
        password.setPlaceholder(placeholderPassword);
        // combo box for role
        String[] role = {"Admin", "Quản lý", "Người dùng"};

        roleBox = new JComboBox<>(role);
        // create a new JPanel


        JPanel panel = new JPanel(new FlowLayout());
        // add the username, password and role to the panel
        panel.add(new JLabel("Tài khoản:"));
        panel.add(username);

        JPanel panel1 = new JPanel(new FlowLayout());
        panel1.add(new JLabel("Mật khẩu:"));
        panel1.add(password);

        JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel2.add(new JLabel("          Vai trò:   "));
        panel2.add(roleBox);

        // get the username, password and role from the JTextField
        String user = username.getText();
        String pass = password.getText();

        String roleUser = roleBox.getSelectedItem().toString();
        // create a new ViewRegisterAccount
        // button for register

        JPanel body = new JPanel(new GridLayout(3, 1));
        body.add(panel);
        body.add(panel1);
        body.add(panel2);


        register = new JButton("Đăng ký");
        // button for cancel
        cancel = new JButton("Hủy");
        // create a new JPanel
        JPanel footer = new JPanel();
        footer.add(register);
        footer.add(cancel);


        add(lbTitle, BorderLayout.NORTH);
        add(body, BorderLayout.CENTER);
        add(footer, BorderLayout.SOUTH);
        setVisible(true);

        register.addActionListener(this);
        cancel.addActionListener(this);

    }

    public String mapRole(String role){
        HashMap<String, String> map = new HashMap<>();
        map.put("Admin", "admin");
        map.put("Quản lý", "manager");
        map.put("Người dùng", "user");
        return map.get(role);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == register) {
            // get username, password and role from the JTextField
            String user = username.getText();
            String pass = password.getText();
            String roleUser = mapRole(roleBox.getSelectedItem().toString());
            String regex = "[0-9]+";

            if (roleUser.equals("user") && !username.getText().equals(placeholderId) && (!username.getText().strip().matches(regex) || username.getText().length() != 9 && username.getText().length() != 12))
                showMessage("Chứng minh nhân dân của người liên quan phải đúng 9 ký tự số hoặc 12 ký tự số", "danger");
            else if (!user.equals("") && !user.equals(placeholderId)) {
                if (pass.equals(placeholderPassword)) {
                    AccountService.getInstance().findOne(user);
                    if (AccountService.getInstance().findOne(user) == null) {
                        AccountService.getInstance().addOne(user, "", roleUser);
                        showMessage("Đăng ký thành công", "success");
                    } else {
                        showMessage("Tài khoản đã tồn tại", "danger");
                    }
                } else {
                    AccountService.getInstance().findOne(user);
                    if (AccountService.getInstance().findOne(user) == null) {
                        AccountService.getInstance().addOne(user, pass, roleUser);
                        showMessage("Đăng ký thành công", "success");
                    } else {
                        showMessage("Tài khoản đã tồn tại", "danger");
                    }
                }
            } else {
                showMessage("Vui lòng nhập đầy đủ thông tin", "danger");
            }
        } else if (e.getSource() == cancel) {
            this.dispose();
        }
    }

    public void showMessage(String message, String state) {
        // get current

        String path = "." +  state.toLowerCase() + ".png";
        icon = new ImageIcon(path);
        icon.setImage(icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));

        if (state.equalsIgnoreCase("danger")) {
            JOptionPane.showMessageDialog(null, message, "Warning", JOptionPane.WARNING_MESSAGE, icon);
        } else if (state.equalsIgnoreCase("success")) {
            JOptionPane.showMessageDialog(null, message, "Success", JOptionPane.INFORMATION_MESSAGE, icon);
        }
    }


}
