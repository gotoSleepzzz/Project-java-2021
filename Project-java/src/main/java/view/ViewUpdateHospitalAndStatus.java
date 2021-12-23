/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author TRUNG
 */
public class ViewUpdateHospitalAndStatus extends JFrame{
    JLabel hosLabel, statusLabel;
    JLabel hosUser, statusUser;
    JComboBox hosBox, statusBox;
    public ViewUpdateHospitalAndStatus(){
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
        
        JPanel temp1 = new JPanel();
        JLabel title = new JLabel("Thay đổi");
        temp1.setLayout(new FlowLayout());
        temp1.add(title);
        mainPanel.add(temp1);
        
        JPanel temp2 = new JPanel();
        hosLabel = new JLabel("Nơi điều trị");
        hosLabel.setHorizontalAlignment(SwingConstants.LEFT);
        temp2.setLayout(new GridLayout(1,1));
        temp2.add(hosLabel);
        mainPanel.add(temp2);
        
        JPanel hosPanel = new JPanel();
        hosUser = new JLabel("F0");
        hosBox = new JComboBox();
        hosPanel.setLayout(new BoxLayout(hosPanel,BoxLayout.X_AXIS));
        hosPanel.add(Box.createRigidArea(new Dimension(15, 0)));
        hosPanel.add(hosUser);
        hosPanel.add(Box.createHorizontalGlue());
        hosPanel.add(hosBox);
        hosPanel.add(Box.createRigidArea(new Dimension(15, 0)));
        hosPanel.setMaximumSize(new Dimension(1000,20));
        mainPanel.add(hosPanel);
        
        JPanel temp3 = new JPanel();
        statusLabel = new JLabel("Trạng thái");
        statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
        temp3.setLayout(new GridLayout(1,1));
        temp3.add(statusLabel);
        mainPanel.add(temp3);
        
        JPanel statusPanel = new JPanel();
        statusUser = new JLabel("F0");
        statusBox = new JComboBox();
        statusPanel.setLayout(new BoxLayout(statusPanel,BoxLayout.X_AXIS));
        statusPanel.add(Box.createRigidArea(new Dimension(15, 0)));
        statusPanel.add(statusUser);
        statusPanel.add(Box.createHorizontalGlue());
        statusPanel.add(statusBox);
        statusPanel.add(Box.createRigidArea(new Dimension(15, 0)));
        statusPanel.setMaximumSize(new Dimension(1000,20));
        mainPanel.add(statusPanel);
        
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        
        add(mainPanel);
        
        setSize(1000,250);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
