package view;

import business.UserManager;
import core.Helper;
import entity.User;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class UserGUI extends Layout{
    private JPanel wrapper;
    private JPanel User;
    private JTextField tf_username;
    private JTextField tf_pass;
    private JComboBox cmb_user_role;
    private JButton btn_add;
    private User user;
    private UserManager userManager;

    public UserGUI(){
        this.add(wrapper);

        this.guiInitilaze(400,450);
        this.user = user;
        this.userManager = new UserManager();
        btn_add.addActionListener(e -> {
            entity.User user = new User();

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
    }
}
