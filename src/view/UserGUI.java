package view;

import business.UserManager;
import core.ComboItem;
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

    public UserGUI(User user){
        this.add(wrapper);

        this.guiInitilaze(400,450);
        this.user = user;
        this.userManager = new UserManager();
        if(this.user.getId() != 0){
            this.tf_username.setText(this.user.getUsername());
            this.tf_pass.setText(this.user.getPassword());
            this.cmb_user_role.setSelectedItem(this.user.getRole());
        }

        btn_add.addActionListener(e -> {

                if (Helper.isFieldListEmpty(new JTextField[]{tf_username, tf_pass})) {
                    Helper.showMsg("fill");
                } else {
                    boolean result;

                    this.user.setUsername(tf_username.getText());
                    this.user.setPassword(tf_pass.getText());
                    this.user.setRole((String) cmb_user_role.getSelectedItem());

                    if(this.user.getId() != 0){
                        result = userManager.update(this.user);

                    }
                    else{
                        result = userManager.save(this.user);
                    }


                    if (result) {
                        Helper.showMsg("done");
                        //loadUserTable();
                    } else {
                        Helper.showMsg("error");
                    }
                }

        });
    }
}
