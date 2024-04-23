package view;

import business.HotelManager;
import business.PensionManager;
import business.RoomManager;
import business.SeasonManager;
import core.ComboItem;
import core.Helper;
import entity.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UpdateRoomGUI extends Layout {
    int roomId;
    private JComboBox cmb_season_add;
    private JComboBox cmb_room_type_add;
    private JTextField tf_adult_price;
    private JRadioButton rbut_projection;
    private JButton btn_save_add_room_menu;
    private JPanel wrapper;
    private JComboBox cmb_pension_add;
    private JTextField tf_child_price;
    private JTextField tf_bed_capacity;
    private JTextField tf_square_meter;
    private JRadioButton rbut_television;
    private JRadioButton rbut_minibar;
    private JRadioButton rbut_game_console;
    private JRadioButton rbut_cashbox;
    private JTextField tf_stock;
    private JTextField tf_hotel_name;
    private HotelManager hotelManager;
    private SeasonManager seasonManager;
    private PensionManager pensionManager;
    private ComboItem comboItem;
    private EmployeeGUI employeeGUI = new EmployeeGUI();
    private Hotel hotel;
    private Room room;
    private Season season;
    private RoomManager roomManager;

    //Kurucu metod
    public UpdateRoomGUI(int roomId) {
        this.add(wrapper);
        this.guiInitilaze(725, 425);
        this.comboItem = new ComboItem();
        this.hotel = new Hotel();
        this.room = new Room();
        this.season = new Season();
        this.roomId=roomId;
        this.pensionManager = new PensionManager();
        this.seasonManager = new SeasonManager();
        this.hotelManager = new HotelManager();
        this.roomManager = new RoomManager();
        Room currentRoom = roomManager.getById(roomId);

        //Açılan ekrandaki değerler, seçilen odanın değerleri ile eşitleme ayarları

        if (currentRoom.isCash_box()){rbut_cashbox.setSelected(true);}
        if (currentRoom.isGame_console()){rbut_game_console.setSelected(true);}
        if (currentRoom.isMinibar()){rbut_minibar.setSelected(true);}
        if (currentRoom.isProjection()){rbut_projection.setSelected(true);}
        if (currentRoom.isTelevision()){rbut_television.setSelected(true);}

        ArrayList<Pension> pensions = pensionManager.getPensionByOtelId(currentRoom.getHotel_id());
        for(Pension pension:pensions){
            cmb_pension_add.addItem(pension.getComboItem());
        }

        ArrayList<Season> seasons = seasonManager.getSeasonsByOtelId(currentRoom.getHotel_id());
        for (Season season : seasons) {
            cmb_season_add.addItem(season.getComboItem());
        }

        tf_hotel_name.setText(currentRoom.getHotel().getName());
        cmb_pension_add.setSelectedItem(pensionManager.getPensionByOtelId(currentRoom.getHotel_id()));
        cmb_season_add.setSelectedItem(seasonManager.getById(currentRoom.getSeason_id()));
        cmb_room_type_add.setSelectedItem(currentRoom.getType());
        tf_stock.setText(String.valueOf(currentRoom.getStock()));
        tf_adult_price.setText(String.valueOf(currentRoom.getAdult_price()));
        tf_child_price.setText(String.valueOf(currentRoom.getChild_price()));
        tf_bed_capacity.setText(String.valueOf(currentRoom.getBed_capacity()));
        tf_square_meter.setText(String.valueOf(currentRoom.getSquare_meter()));

        //Save butonu ayarları

        btn_save_add_room_menu.addActionListener(e -> {

            JTextField[] selectedRoomList = new JTextField[]{tf_adult_price, tf_child_price, tf_bed_capacity, tf_bed_capacity, tf_square_meter};

            if (Helper.isFieldListEmpty(selectedRoomList)) {
                Helper.showMsg("fill");
            } else {

            }
            boolean result;

            room = roomManager.getById(currentRoom.getId());

            ComboItem selectedPension = (ComboItem) cmb_pension_add.getSelectedItem();
            ComboItem selectedSeason = (ComboItem) cmb_season_add.getSelectedItem();

            this.room.setHotel_id(currentRoom.getHotel_id());
            this.room.setSeason_id(selectedSeason.getKey());
            this.room.setPension_id(selectedPension.getKey());
            this.room.setType((String) cmb_room_type_add.getSelectedItem());
            this.room.setStock(Integer.parseInt(tf_stock.getText()));
            this.room.setAdult_price(Double.parseDouble(tf_adult_price.getText()));
            this.room.setChild_price(Double.parseDouble(tf_child_price.getText()));
            this.room.setBed_capacity(Integer.parseInt(tf_bed_capacity.getText()));
            this.room.setSquare_meter(Integer.parseInt(tf_square_meter.getText()));
            this.room.setTelevision(rbut_television.isSelected());
            this.room.setMinibar(rbut_minibar.isSelected());
            this.room.setGame_console(rbut_game_console.isSelected());
            this.room.setCash_box(rbut_cashbox.isSelected());
            this.room.setProjection(rbut_projection.isSelected());

            result = roomManager.update(room);

            if (result) {
                Helper.showMsg("done");
                dispose();
            } else {
                Helper.showMsg("error");
            }

        });
    }
}
