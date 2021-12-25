package view;

import model.NYP;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import service.ManagerService;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author TRUNG
 */
public class ViewManagerNYP extends JPanel implements ActionListener {
    private JButton newBtn, searchBtn;
    private CustomTextField searchBar;
    private CustomTextField name;
    private CustomTextField limit;
    private CustomTextField time;
    private CustomTextField cost;
    private JComboBox sortCombobox;
    private JTable table;
    private JPanel searchPanel, mainPanel, contentPanel, infoPanel, btnPanel, utilPanel;
    private JScrollPane scroll;
    private JComboBox filter;
    private JButton back;
    private JButton modifyButton;
    private JButton removeButton;
    private JDatePickerImpl datePicker;
    List<NYP> list = ManagerService.getInstance().findAllNYP();
    String data[][] = new String[list.size()][4];
    private final String[] category = new String[]{
            "< 500.000", "500.000 - 1.000.000"
    };
    private final String[] headerTable = new String[]{
            "Tên gói", "Mức giới hạn", "Thời gian giới hạn", "Đơn giá", "Xoá", "Cập nhật"
    };

    JPanel headerPanel;

    private String searchPlacehoder = "Tìm kiếm bằng tên gói hoặc bỏ trống để refresh lại bảng";
    private String namePlacehoder = "Tên gói";
    private String limitPlacehoder = "Mức giới hạn";
    private String timePlacehoder = "Thời gian giới hạn";
    private String costPlacehoder = "Đơn giá";

    String[] sortBy = {"Mức giới hạn tăng dần", "Thời gian giới hạn tăng dần", "Đơn giá tằng dần",
            "Mức giới hạn giảm dần", "Thời gian giới hạn giảm dần", "Đơn giá giảm dần"};

    public ViewManagerNYP() {

        JLabel title = new JLabel("                               Quản lý gói nhu yếu phẩm");
        title.setFont(new Font("Serif", Font.BOLD, 35));
        title.setAlignmentX(CENTER_ALIGNMENT);

        headerPanel = new JPanel(new BorderLayout());
        back = new JButton("Quay lại");

        JPanel temp = new JPanel(new FlowLayout(FlowLayout.LEFT));
        temp.add(back);
        headerPanel.add(temp, BorderLayout.NORTH);
        headerPanel.add(title, BorderLayout.CENTER);
        // make title at center
        title.setAlignmentX(CENTER_ALIGNMENT);


        searchBar = new CustomTextField(65);
        searchBar.setPlaceholder(searchPlacehoder);
        searchBtn = new JButton("Search");
        searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        searchPanel.add(searchBar);
        searchPanel.add(searchBtn);

        table = new JTable();
        scroll = new JScrollPane();
        scroll.setViewportView(table);
        table.setAutoCreateRowSorter(true);
        table.setPreferredScrollableViewportSize(new Dimension(700, 500));
        // set width for column

        setUpTable();


        newBtn = new JButton("Thêm");
        newBtn.setSize(90, 30);
        btnPanel = new JPanel();
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.CENTER);
        flowLayout.setHgap(10);
        flowLayout.setVgap(10);
        btnPanel.setLayout(flowLayout);
        btnPanel.add(newBtn);

        name = new CustomTextField(10);
        name.setPlaceholder(namePlacehoder);
        limit = new CustomTextField(10);
        limit.setPlaceholder(limitPlacehoder);
        cost = new CustomTextField(10);
        cost.setPlaceholder(costPlacehoder);
        filter = new JComboBox(category);
        sortCombobox = new JComboBox(sortBy);
        infoPanel = new JPanel();
        GridLayout gridLayout = new GridLayout(6, 2, 20, 19);

        infoPanel.setLayout(gridLayout);

        infoPanel.add(new JLabel("Lọc"));
        infoPanel.add(filter);
        infoPanel.add(new JLabel("Sắp xếp"));
        infoPanel.add(sortCombobox);
        infoPanel.add(new JLabel("Tên gói"));
        infoPanel.add(name);
        infoPanel.add(new JLabel("Mức giới hạn"));
        infoPanel.add(limit);
        infoPanel.add(new JLabel("Thời gian giới hạn"));

        // create a button for  choose time picker nice jdatepicker
        datePicker = new JDatePickerImpl(new JDatePanelImpl(new UtilDateModel()));
        datePicker.setPreferredSize(new Dimension(150, 30));
        infoPanel.add(datePicker);


        infoPanel.add(new JLabel("Đơn giá"));
        infoPanel.add(cost);
        infoPanel.setPreferredSize(new Dimension(800, 500));

        utilPanel = new JPanel();
        utilPanel.setBorder(new TitledBorder("Thông tin"));
        utilPanel.setLayout(new GridLayout(2, 1));
        utilPanel.add(infoPanel);
        utilPanel.add(btnPanel);
        utilPanel.setPreferredSize(new Dimension(350, 500));

        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout(10, 10));
        contentPanel.add(scroll, BorderLayout.CENTER);
        contentPanel.add(utilPanel, BorderLayout.EAST);


        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(headerPanel);
        mainPanel.add(searchPanel);
        mainPanel.add(contentPanel);
        add(mainPanel);
    }

    public void addBackListener(ActionListener listener) {
        back.addActionListener(listener);
    }

    public NYP getSelectedNYP() {
        int row = table.getSelectedRow();
        if (row == -1) return null;
        // get selected row
        String nameNYP = String.valueOf(table.getValueAt(row, 0));
        return ManagerService.getInstance().getNYPByName(list, nameNYP);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }


    public void setUpTable() {

        for (int i = 0; i < list.size(); i++) {
            data[i][0] = list.get(i).getName();
            data[i][1] = String.valueOf(list.get(i).getLimit());
            data[i][2] = String.valueOf(list.get(i).getExpriredDate());
            data[i][3] = String.valueOf(list.get(i).getPrice());
        }

        DefaultTableModel model = new DefaultTableModel(data, headerTable);
        table.setModel(model);
        addListenerTable();
    }

    public void renderTable(List<NYP> nyps) {
        data = new String[nyps.size()][4];
        for (int i = 0; i < nyps.size(); i++) {
            data[i][0] = nyps.get(i).getName();
            data[i][1] = String.valueOf(nyps.get(i).getLimit());
            data[i][2] = String.valueOf(nyps.get(i).getExpriredDate());
            data[i][3] = String.valueOf(nyps.get(i).getPrice());
        }
        DefaultTableModel model = new DefaultTableModel(data, headerTable);
        table.setModel(model);
        addListenerTable();
    }

    public void addListenerTable() {
        table.getColumnModel().getColumn(1).setPreferredWidth(10);
        table.getColumnModel().getColumn(2).setPreferredWidth(10);
        table.getColumnModel().getColumn(3).setPreferredWidth(10);
        removeButton = new JButton("Xoá");
        modifyButton = new JButton("Chỉnh sửa");
        table.getColumn("Xoá").setCellEditor(new ButtonEditor(new JCheckBox(), "Xoá", removeButton));
        table.getColumn("Xoá").setCellRenderer(new ButtonRenderer("Xoá"));
        table.getColumn("Cập nhật").setCellEditor(new ButtonEditor(new JCheckBox(), "Cập nhật", modifyButton));
        table.getColumn("Cập nhật").setCellRenderer(new ButtonRenderer("Cập nhật"));


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
        private JButton button;

        public ButtonEditor(JCheckBox checkBox, String label, JButton button) {
            super(checkBox);
            this.label = label;
            this.button = button;
        }

        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {
            this.button.setText(label);
            return this.button;
        }

        public Object getCellEditorValue() {
            return label;
        }

    }

    public void addRemoveActionListener(ActionListener actionListener) {
        removeButton.addActionListener(actionListener);
    }

    public void addSortActionListener(ActionListener actionListener) {
        sortCombobox.addActionListener(actionListener);
    }


    public void addModifyActionListener(ActionListener actionListener) {
        modifyButton.addActionListener(actionListener);
    }

    public void addSearchActionListener(ActionListener actionListener) {
        searchBtn.addActionListener(actionListener);
    }

    public String getContentSearch() {
        return searchBar.getText();
    }


    public String getSearchPlacehoder() {
        return searchPlacehoder;
    }

    public void addNewActionListener(ActionListener actionListener) {
        newBtn.addActionListener(actionListener);
    }

    public NYP getInfoNYP() {
        String nameNYP = name.getText();
        String limitNYP = limit.getText();
        String datePattern = "yyyy-MM-dd";
        SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
        String expiredDate = dateFormat.format(datePicker.getModel().getValue());
        String priceNYP = cost.getText();
        if (nameNYP.equals("") || limitNYP.equals("") || expiredDate == null || expiredDate.equals("") || priceNYP.equals(""))
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
        else if (Integer.parseInt(limitNYP) < 0 || Integer.parseInt(priceNYP) < 0)
            JOptionPane.showMessageDialog(null, "Giá và số lượng không được âm");
        else if (nameNYP.equals(namePlacehoder) || limitNYP.equals(limitPlacehoder) || priceNYP.equals(costPlacehoder))
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
        else return new NYP(nameNYP, Integer.parseInt(limitNYP), Date.valueOf(expiredDate), Double.valueOf(priceNYP));
        return null;

    }

}
