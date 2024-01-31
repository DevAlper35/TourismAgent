package view;

import business.UserManager;
import core.ComboItem;
import core.Helper;
import entity.User;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;

public class AdminGUI extends Layout {

    private JPanel wrapper;
    private JTabbedPane tabbedPane1;
    private JLabel lbl_welcome;
    private JButton LOGOUTButton;
    private JTable tbl_user;
    private JTextField tf_username;
    private JTextField tf_pass;
    private JComboBox<ComboItem> cmb_user_role;
    private JButton btn_add;
    private JTextField tf_id;
    private JButton DELETEButton;
    private JComboBox comboBox1;
    private JButton SEARCHButton;
    private JScrollPane scrl_user;
    private JPanel w_top;
    private JPanel w_bot;
    private User user;
    private UserManager userManager;
    private DefaultTableModel tmdl_user = new DefaultTableModel();
    private JPopupMenu userMenu;

    public AdminGUI(User user) {
        this.userManager = new UserManager();
        this.add(wrapper);
        this.guiInitilaze(1000, 500);
        this.user = user;
        if (user == null) {
            dispose();
        }


        this.lbl_welcome.setText("Hoşgeldiniz : " + this.user.getUsername());
        loadUserTable();
        tableRowSelect(tbl_user);
        loadUserComponent();
        LOGOUTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginGUI loginGUI = new LoginGUI();
                dispose();
            }
        });

        btn_add.addActionListener(new ActionListener() {
            User user = new User();

            @Override
            public void actionPerformed(ActionEvent e) {
                if (Helper.isFieldListEmpty(new JTextField[]{tf_username, tf_pass})) {
                    Helper.showMsg("fill");
                } else {
                    boolean result;

                    //       ComboItem selectedRole = (ComboItem) cmb_user_role.getSelectedItem();
                    this.user.setUsername(tf_username.getText());
                    this.user.setPassword(tf_pass.getText());
                    this.user.setRole((String) cmb_user_role.getSelectedItem());


                    result = userManager.save(this.user);

                    if (result) {
                        Helper.showMsg("done");
                        loadUserTable();
                    } else {
                        Helper.showMsg("error");

                    }
                }
            }
        });
        DELETEButton.addActionListener(new ActionListener() {    // BUTON İLE DELETE İŞLEMİ DENİYORUM.
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Helper.confirm("sure")){

                    int selectModelId = getTableSelectedRow(tbl_user,0);
                    if (userManager.delete(selectModelId)){
                        Helper.showMsg("done");
                        loadUserTable();
                    }else{
                        Helper.showMsg("error");
                    }
                }
            }
        });
    }

    public void loadUserTable() {
        Object[] col_user = {"ID", "Username", "Password", "Role"};
        ArrayList<Object[]> userList = this.userManager.getForTable(col_user.length, this.userManager.findAll());
        createTable(this.tmdl_user, tbl_user, col_user, userList);
    }
    public void tableRowSelect(JTable table){
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int selected_row = table.rowAtPoint(e.getPoint());
                table.setRowSelectionInterval(selected_row, selected_row);
            }
        });
    }
    private void loadUserComponent() {
        tableRowSelect(this.tbl_user);
        this.userMenu = new JPopupMenu();
        this.userMenu.add("Yeni").addActionListener(e -> {

            UserGUI usergui = new UserGUI();
            usergui.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadUserTable();
                }
            });


        });
        this.userMenu.add("Güncelle").addActionListener(e -> {

            int selectModelId = this.getTableSelectedRow(tbl_user, 0);
            UserGUI usergui = new UserGUI(this.UserManager.getById(selectModelId));
            UserGUI.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadUserTable();
                }
            });


        });
        this.userMenu.add("Sil").addActionListener(e -> {

            if (Helper.confirm("sure")) {
                int selectCarId = this.getTableSelectedRow(tbl_user, 0);
                if (this.UserManager.delete(selectCarId)) {
                    Helper.showMsg("done");
                    loadUserTable();
                } else {
                    Helper.showMsg("error");
                }
            }


        });
        this.tbl_user.setComponentPopupMenu(userMenu);
    }
}