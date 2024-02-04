package view;

import business.*;
import core.Helper;
import entity.Hotel;
import entity.Pension;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.event.*;
import java.text.ParseException;
import java.util.ArrayList;

public class EmployeeGUI extends Layout {
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
    private JLabel lbl_city;
    private JLabel lbl_date_entry;
    private JLabel lbl_date_release;
    private JLabel lbl_number_adult;
    private JLabel lbl_number_child;
    private JTextField tf_name_room;
    private JTextField tf_city_room;
    private JTextField tf_numb_adult_room;
    private JTextField tf_numb_children_room;
    private JTable tbl_pension;
    private JTable tbl_season;
    private JScrollPane scrl_room;
    private JFormattedTextField tf_season_strt;
    private JFormattedTextField tf_season_fnsh;
    private JButton btn_pension_add;
    private Hotel hotel;
    private JPopupMenu hotel_menu;
    private JPopupMenu pension_menu;
    public HotelManager hotelManager;
    private JPopupMenu season_menu;
    private JPopupMenu room_menu;

    private Pension pension = new Pension();
    private PensionManager pensionManager = new PensionManager();
    private SeasonManager seasonManager = new SeasonManager();
    private RoomManager roomManager = new RoomManager();
    private UserManager userManager = new UserManager();

    DefaultTableModel tmdl_hotel = new DefaultTableModel();
    DefaultTableModel tmdl_pension = new DefaultTableModel();
    DefaultTableModel tmdl_season = new DefaultTableModel();
    DefaultTableModel tmdl_room = new DefaultTableModel();



    /*public EmployeeGUI() {
        btn_room_reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }*/

    public JTable getTbl_room() {
        return tbl_room;
    }

    public void setTbl_room(JTable tbl_room) {
        this.tbl_room = tbl_room;
    }
    public EmployeeGUI(User user) {
        this.hotelManager = new HotelManager();
        this.hotel = new Hotel();
        this.add(wrapper);
        this.guiInitilaze(1250, 800);

        this.pension_menu = new JPopupMenu();
        this.season_menu = new JPopupMenu();
        this.room_menu = new JPopupMenu();
        this.lbl_welcome.setText("Welcome : " + user.getUsername());

        loadHotelTable();
        loadPensionTable();
        loadRoomTable();
        loadSeasonTable();

        tableRowSelect(tbl_hotel);
        tableRowSelect(tbl_pension);
        tableRowSelect(tbl_room);
        loadHotelTableComponent();

        btn_room_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ADDRoomGUI addRoomGUI = new ADDRoomGUI();
                addRoomGUI.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        loadRoomTable();
                    }
                });

            }
        });
        btn_exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginGUI loginGUI = new LoginGUI();

                loadHotelTable();
                dispose();
            }
        });

        btn_hotel_add.addActionListener(e -> {
            HotelAddGUI hotelAddGUI = new HotelAddGUI(new Hotel());
            hotelAddGUI.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelTable();
                }
            });
            tableRowSelect(tbl_hotel);

        });


    }

    public void loadHotelTable() {
        Object[] col_hotel = {"ID", "Name", "Adress", "Mail", "Telefon", "Star", "Car Park", "Wifi", "Pool", "Fitness", "Concierge", "Spa", "Room Services"};
        ArrayList<Object[]> hotelList = this.hotelManager.getForTable(col_hotel.length, this.hotelManager.findAll());
        createTable(this.tmdl_hotel, tbl_hotel, col_hotel, hotelList);

    }

    public void loadPensionTable() {
        Object[] col_pension = {"ID", "Hotel ID", "Pension Type"};
        ArrayList<Object[]> pensionList = this.pensionManager.getForTable(col_pension.length,this.pensionManager.findAll());
        createTable(this.tmdl_pension, tbl_pension, col_pension, pensionList);

    }
    public void loadSeasonTable() {
        Object[] col_season = {"ID", "Hotel ID", "Start Date","Finish Date"};
        ArrayList<Object[]> seasonList = this.seasonManager.getForTable(col_season.length,this.seasonManager.findAll());
        createTable(this.tmdl_season, tbl_season, col_season, seasonList);

    }
    public void loadRoomTable() {
        Object[] col_room = {"ID", "Hotel ID", "Pension ID","Season ID","Type","Stock","Adult Price","Child Price","Bed Capacity","Square Meter","Television","Minibar","Game Console","Cash BOX","Projection"};
        ArrayList<Object[]> roomList = this.roomManager.getForTable(col_room.length, this.roomManager.findAll());
        createTable(this.tmdl_room, tbl_room, col_room,roomList );

    }

    @Override
    public void tableRowSelect(JTable table){
        table.addMouseListener(new MouseAdapter() {


            @Override
            public void mouseReleased(MouseEvent e) {
                int selected_row = table.rowAtPoint(e.getPoint());
                table.setRowSelectionInterval(selected_row,selected_row);
            }
        });

    }

    public void LoadPensionTableComponent() {

        tableRowSelect(tbl_pension);


        pension_menu.add("Delete").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectBrandId = this.getTableSelectedRow(tbl_pension, 0);
                if (this.pensionManager.delete(selectBrandId)) {
                    Helper.showMsg("done");
                    loadPensionTable();


                } else {
                    Helper.showMsg("error");
                }
            }
        });
        this.tbl_pension.setComponentPopupMenu(pension_menu);
    }

    public void loadSeasonTableComponent() {
        tableRowSelect(tbl_season);

        season_menu.add("Delete").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectBrandId = this.getTableSelectedRow(tbl_season, 0);
                if (this.seasonManager.delete(selectBrandId)) {
                    Helper.showMsg("done");
                    loadSeasonTable();

                } else {
                    Helper.showMsg("error");
                }
            }
        });
        this.tbl_season.setComponentPopupMenu(season_menu);
    }


    public void loadHotelTableComponent(){
        tableRowSelect(tbl_hotel);
        tableRowSelect(tbl_pension);
        tableRowSelect(tbl_room);
        tableRowSelect(tbl_season);


        this.hotel_menu = new JPopupMenu();
        this.hotel_menu.add("New").addActionListener(e -> {
            HotelAddGUI hotelAddGUI = new HotelAddGUI(new Hotel());
            hotelAddGUI.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelTable();
                }
            });
            loadHotelTable();
        });

        this.hotel_menu.add("Season Add").addActionListener(e -> {
            int selectId=this.getTableSelectedRow(tbl_hotel,0);
            SeasonGUI seasonGUI = new SeasonGUI(selectId);
            seasonGUI.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelTable();
                    loadSeasonTable();
                }
            });
        });

        this.hotel_menu.add("Pension Add").addActionListener(e -> {
            int selectId=this.getTableSelectedRow(tbl_hotel,0);
            PensionGUI pensionGUI = new PensionGUI(selectId);
            pensionGUI.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelTable();
                    loadPensionTable();
                }
            });
        });

       this.hotel_menu.add("Update").addActionListener(e -> {

           int selectModelId = this.getTableSelectedRow(tbl_hotel, 0);
           HotelAddGUI hotelGUI = new HotelAddGUI(this.hotelManager.getById(selectModelId));
           hotelGUI.addWindowListener(new WindowAdapter() {
               @Override
               public void windowClosed(WindowEvent e) {
                   loadHotelTable();
               }
           });

       });

        this.hotel_menu.add("Delete").addActionListener(e -> {
            if(Helper.confirm("sure")){
                int selectBrandId = this.getTableSelectedRow(tbl_hotel,0);
                if (this.hotelManager.delete(selectBrandId)){
                    Helper.showMsg("done");
                    loadHotelTable();
                }else{
                    Helper.showMsg("error");
                }
            }



        });

        this.tbl_hotel.setComponentPopupMenu(hotel_menu);
    }

    private void createUIComponents() throws ParseException {
        this.tf_season_strt = new JFormattedTextField(new MaskFormatter("####-##-##"));
        this.tf_season_strt.setText("2024-06-01");
        this.tf_season_fnsh = new JFormattedTextField(new MaskFormatter("####-##-##"));
        this.tf_season_fnsh.setText("2024-12-01");
    }
}

