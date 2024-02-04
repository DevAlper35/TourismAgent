package view;

import business.HotelManager;
import business.SeasonManager;
import core.ComboItem;
import core.Helper;
import entity.Hotel;
import entity.Season;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SeasonGUI extends Layout {
    private JPanel wrap;
    private JPanel wrap_season;
    private JComboBox cmb_hotel;
    private JButton btn_save_season;
    private JFormattedTextField tf_season_start;
    private JFormattedTextField tf_season_finish;
    private JLabel lbl_hotel_id;
    private JComboBox cmb_season;

    private SeasonManager seasonManager;
    private Season season;
    private HotelManager hotelManager;
    private Hotel hotel;

    public SeasonGUI(int hotel_id){
        this.hotel = new Hotel();
        this.hotelManager = new HotelManager();
        this.seasonManager = new SeasonManager();
        this.season = new Season();
        this.add(wrap);
        this.guiInitilaze(375,300);

        this.cmb_season.getSelectedItem();
        //LocalDate strDate = LocalDate.parse(tf_season_start.getText());
        //LocalDate fnshDate = LocalDate.parse(tf_season_finish.getText());
        for (Hotel hotel : this.hotelManager.findAll()) {
            this.cmb_season.addItem(hotel.getComboItem());

        }

        this.btn_save_season.addActionListener(e->{
            boolean result = false;
            ComboItem selectedSeason = (ComboItem) cmb_season.getSelectedItem();
            this.season.setHotel_id(selectedSeason.getKey());
            this.season.setSeason_type(cmb_season.getSelectedItem().toString());
            season.setStart_date(LocalDate.parse(tf_season_start.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            season.setFinish_date(LocalDate.parse(tf_season_finish.getText(),DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            if (this.season.getId() != 0) {
                //result = this.seasonManager.update(this.season);

            } else {
                result = this.seasonManager.save(this.season);
            }
            if (result) {
                Helper.showMsg("done");
                dispose();
            } else {
                Helper.showMsg("error");
            }

        });
    }


    private void createUIComponents() throws ParseException {
        this.tf_season_start = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.tf_season_start.setText("01/06/2024");
        this.tf_season_finish = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.tf_season_finish.setText("01/12/2024");
    }
}

