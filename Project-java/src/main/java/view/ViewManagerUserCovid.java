import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewManagerUserCovid extends JPanel implements ActionListener {

    private JPanel panelHeader;
    private JPanel panelBody;
    private JPanel panelFooter;
    private JLabel label;
    private JButton button;
    private JTable table;
    private JPanel MenuPanel;
    private JPanel TitlePanel;
    private JLabel titleLabel;
    private CustomTextField searchField;
    private Button searchButton;
    JButton refreshButton;
    private JPanel searchPanel;
    private JButton watchMore;

    public ViewManagerUserCovid() {
        setLayout(new BorderLayout());

        panelHeader = new JPanel();
        panelHeader.setLayout(new BorderLayout());


        MenuPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(this);


        searchField = new CustomTextField(30);
        searchField.setPlaceholder("Nhập tên cần tìm kiếm bằng tên, cmnd");

        searchButton = new Button("Tìm kiếm");
        searchPanel.add(refreshButton);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);


        label = new JLabel("Danh sách người liên quan Covid 19", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        panelHeader.add(MenuPanel, BorderLayout.NORTH);
        panelHeader.add(label, BorderLayout.SOUTH);
        panelHeader.add(searchPanel, BorderLayout.CENTER);


        String[] columns = new String[]{"Họ tên", "CMND", "Nơi điều trị", "Năm sinh",
                "Trạng thái hiện tại", "Xem chi tiết", "Chỉnh sửa"};
        String[][] data = new String[][]{
                {"Thomas", "1" , "Bệnh viện Dã Chiến", "1999", "F0"},
                {"Thomas", "2", "Bệnh viện Dã Chiến", "1999", "F0"},
                {"Thomas", "3", "Bệnh viện Dã Chiến", "1999", "F0"},
                {"Thomas", "4", "Bệnh viện Dã Chiến", "1999", "F0"},
                {"Thomas", "5", "Bệnh viện Dã Chiến", "1999", "F0"},
                {"Thomas", "6", "Bệnh viện Dã Chiến", "1999", "F0"},
                {"Thomas", "6", "Bệnh viện Dã Chiến", "1999", "F0"},
        };

        DefaultTableModel model = new DefaultTableModel(data, columns);
        table = new JTable();
        table.setModel(model);
        table.getColumn("Xem chi tiết").setCellRenderer(new ButtonRenderer("Xem chi tiết"));
        table.getColumn("Xem chi tiết").setCellEditor(new ButtonEditor(new JCheckBox(), "Xem chi tiết"));
        table.getColumn("Chỉnh sửa").setCellRenderer(new ButtonRenderer("Chỉnh sửa"));
        table.getColumn("Chỉnh sửa").setCellEditor(new ButtonEditor(new JCheckBox(), "Chỉnh sửa"));
        // add new row
        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 15));
        table.setRowHeight(30);
        table.setRowMargin(5);
        table.setShowGrid(true);
        table.setGridColor(Color.BLACK);
        table.setSelectionBackground(Color.LIGHT_GRAY);
        table.setSelectionForeground(Color.BLACK);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setBorder(new EtchedBorder());
        table.setFillsViewportHeight(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);



        button = new JButton("Xem chi tiết");
        button.addActionListener(
                event -> JOptionPane.showMessageDialog(null, "Do you want to modify this line?")
        );


        JScrollPane jScrollPane = new JScrollPane(table);
        panelBody = new JPanel();
        panelBody.setLayout(new BorderLayout());
        panelBody.add(jScrollPane, BorderLayout.CENTER);
        // create a nicer border
        panelBody.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        // add title to the panel
        add(panelHeader, BorderLayout.NORTH);
        add(panelBody, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == refreshButton) {
            searchField.setText("");
            searchField.setPlaceholder("Nhập tên cần tìm kiếm bằng tên, cmnd");

        }
    }


    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer(String text) {
            setText(text);
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
            return this;
        }
    }

    class ButtonEditor extends DefaultCellEditor {
        private String label;

        public ButtonEditor(JCheckBox checkBox, String label) {
            super(checkBox);
            this.label = label;
        }

        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {
            button.setText(label);
            return button;
        }

        public Object getCellEditorValue() {
            return label;
        }
    }
}
