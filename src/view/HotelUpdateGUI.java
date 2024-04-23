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
public class HotelUpdateGUI extends Layout {

    int hotelId;
    private JPanel wrapper;
    private JLabel lbl_otel_save;
    private JTextField tf_name;
    private JTextField tf_mail;
    private JTextField tf_phone;
    private JComboBox tf_star;
    private JTextField tf_adress;
    private JTextField tf_city;
    private JPanel pnl_left;
    private JRadioButton rbut_carpark;
    private JRadioButton rbut_wifi;
    private JRadioButton rbut_swim;
    private JRadioButton rbut_gym;
    private JRadioButton rbut_concierge;
    private JRadioButton rbut_spa;
    private JRadioButton rbut_roomservices;
    private JButton btn_save_add_menu;
    private ComboItem comboItem;
    private HotelManager hotelManager;
    private SeasonManager seasonManager;
    private PensionManager pensionManager;
    private RoomManager roomManager;
    private Hotel hotel;
    private Room room;
    private Season season;

    //Kurucu metod
    public HotelUpdateGUI(int hotelId) {
        this.add(wrapper);
        this.guiInitilaze(500, 500);
        this.comboItem = new ComboItem();
        this.hotel = new Hotel();
        this.room = new Room();
        this.season = new Season();
        this.hotelId = hotelId;
        this.pensionManager = new PensionManager();
        this.seasonManager = new SeasonManager();
        this.hotelManager = new HotelManager();
        this.roomManager = new RoomManager();
        Hotel currentHotel = hotelManager.getById(hotelId);

        //Açılan ekrandaki butonların, düzenlenen oteldeki verilerle eşleştirilmesi

        if(currentHotel.isCar_park()){rbut_carpark.setSelected(true);}
        if(currentHotel.isWifi()){rbut_wifi.setSelected(true);}
        if(currentHotel.isPool()){rbut_swim.setSelected(true);}
        if(currentHotel.isFitness()){rbut_gym.setSelected(true);}
        if(currentHotel.isConcierge()){rbut_concierge.setSelected(true);}
        if(currentHotel.isSpa()){rbut_spa.setSelected(true);}
        if(currentHotel.isRoom_service()){rbut_roomservices.setSelected(true);}

        tf_name.setText(currentHotel.getName());
        tf_adress.setText(currentHotel.getAddress());
        tf_city.setText(currentHotel.getCity());
        tf_mail.setText(currentHotel.getMail());
        tf_phone.setText(currentHotel.getPhone());
        tf_star.setSelectedItem(currentHotel.getStar());

        //Save butonu işlemleri

        btn_save_add_menu.addActionListener(e -> {

            JTextField[] selectedHotelList = new JTextField[]{tf_name, tf_city, tf_mail, tf_phone};


            if (Helper.isFieldListEmpty(selectedHotelList)){
                Helper.showMsg("fill");
            }
            else {}

            boolean result;

            hotel = hotelManager.getById(currentHotel.getId());

            //ComboItem selectedStar = (ComboItem) tf_star.getSelectedItem();

            this.hotel.setName(tf_name.getText());
            this.hotel.setAddress(tf_adress.getText());
            this.hotel.setCity(tf_city.getText());
            this.hotel.setMail(tf_mail.getText());
            this.hotel.setPhone(tf_phone.getText());
            this.hotel.setStar((String) tf_star.getSelectedItem());
            this.hotel.setCar_park(rbut_carpark.isSelected());
            this.hotel.setWifi(rbut_wifi.isSelected());
            this.hotel.setPool(rbut_swim.isSelected());
            this.hotel.setFitness(rbut_gym.isSelected());
            this.hotel.setConcierge(rbut_concierge.isSelected());
            this.hotel.setSpa(rbut_spa.isSelected());
            this.hotel.setRoom_service(rbut_roomservices.isSelected());

            result = hotelManager.update(hotel);

            if (result){
                Helper.showMsg("done");
                dispose();
            }
            else {
                Helper.showMsg("error");
            }
        });
    }
}
