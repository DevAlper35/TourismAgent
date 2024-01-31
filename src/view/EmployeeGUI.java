package view;

import entity.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeGUI extends Layout{
    private JPanel wrapper;
    private JLabel lbl_welcome;
    private JButton btn_exit;
    private JScrollPane w_bot;
    private JPanel w_top;
    private JTabbedPane tab_rezervation;
    private JPanel tab_Hotel;
    private JPanel tab_room;
    private JPanel tab_reservation;
    private JTable tbl_hotel;
    private JTable tbl_room;
    private JTable tbl_reservation;
    private JScrollPane w_left;
    private JScrollPane w_right;
    private JButton btn_hotel_add;
    private JPanel pnl_room;
    private JLabel lbl_room_name;
    private JButton btn_search_room;
    private JButton btn_room_reset;
    private JButton btn_room_add;
    private JButton button4;
    private JLabel lbl_city;
    private JLabel lbl_date_entry;
    private JLabel lbl_date_release;
    private JLabel lbl_number_adult;
    private JLabel lbl_number_child;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTable tbl_pension;
    private JTable tbl_season;

    public EmployeeGUI(User user) {
        this.add(wrapper);
        this.guiInitilaze(1000,500);



        btn_exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginGUI loginGUI = new LoginGUI();
                dispose();
            }
        });
    }
}

