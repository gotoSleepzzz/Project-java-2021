package view;

import service.ManagerService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewChangeStateAndHospital extends JFrame implements ActionListener {


    private JTable table;
    private String[] columnNames = {"STT", "Bệnh viện trước đó", "Bệnh viện hiện tại", "Trạng thái trước đó", "Trạng thái hiện tại"};
    JButton exit;
    public ViewChangeStateAndHospital(String id) {
        setSize(850, 550);
        setTitle("Change State and Hospital");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());


        table = new JTable();
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


        setUpTable(ManagerService.getInstance().getHistoryChangeState(id));


        var x = ManagerService.getInstance().findOneUserCovid(id);

        JLabel title = new JLabel("Danh sách thay đổi trạng thái và bệnh viện của " + x.getName(), SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setBorder(new EmptyBorder(10,10,10,10));


        JScrollPane jScrollPane = new JScrollPane(table);
        JPanel panelBody = new JPanel();
        panelBody.setLayout(new BoxLayout(panelBody, BoxLayout.Y_AXIS));
        panelBody.add(jScrollPane);
        panelBody.setBorder(new EmptyBorder(10,10,10,10));
        add(title, BorderLayout.NORTH);
        add(panelBody, BorderLayout.CENTER);
        // make panelBody larger than the table
        panelBody.setPreferredSize(new Dimension(800, 550));
        // full screen of table
        exit = new JButton("Thoát");
        exit.addActionListener(this);
        JPanel panelExit = new JPanel(new FlowLayout(FlowLayout.CENTER));
        add(panelExit, BorderLayout.SOUTH);
        panelExit.add(exit);

        setVisible(true);
    }

    void setUpTable(Object[][] data) {
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        table.setModel(model);

        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        table.getColumnModel().getColumn(4).setPreferredWidth(100);

        table.setSize(800, 550);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exit) {
            this.dispose();
        }
    }
}
