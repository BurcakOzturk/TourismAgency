package view;

import business.HotelManager;
import core.Helper;
import entity.Hotel;

import javax.swing.*;

public class HotelAddGUI extends Layout {
    private JPanel wrap;
    private JLabel lbl_otel_save;
    private JTextField tf_name;
    private JTextField tf_mail;
    private JTextField tf_phone;
    private JTextField tf_adress;
    private JPanel pnl_left;
    private JRadioButton rbut_carpark;
    private JRadioButton rbut_wifi;
    private JRadioButton rbut_swim;
    private JRadioButton rbut_gym;
    private JRadioButton rbut_concierge;
    private JRadioButton rbut_spa;
    private JRadioButton rbut_roomservices;
    private JButton btn_save_add_menu;
    private JComboBox tf_star;
    private JTextField tf_city;
    private Hotel hotel;
    private HotelManager hotelManager;


    //Kurucu metod
    public HotelAddGUI() {
        this.hotelManager = new HotelManager();
        this.hotel = new Hotel();
        this.add(wrap);
        this.guiInitilaze(500, 500);
        if(this.hotel.getId() != 0) {
            this.tf_name.setText(this.hotel.getName());
            this.tf_mail.setText(this.hotel.getMail());
            this.tf_city.setText(this.hotel.getCity());
            this.tf_phone.setText(this.hotel.getPhone());
            this.tf_adress.setText(this.hotel.getAddress());
            this.tf_star.setSelectedItem(this.hotel.getStar());
        }
        btn_save_add_menu.addActionListener(e -> {

            JTextField[] checkFieldList = {this.tf_name, this.tf_mail, this.tf_phone, this.tf_adress, this.tf_city};

            if (Helper.isFieldListEmpty(checkFieldList)) {
                Helper.showMsg("fill");

            } else {
                boolean result = true;
                this.hotel.setName(this.tf_name.getText());
                this.hotel.setMail(this.tf_mail.getText());
                this.hotel.setCity(this.tf_city.getText());
                this.hotel.setPhone(this.tf_phone.getText());
                this.hotel.setAddress(this.tf_adress.getText());
                this.hotel.setStar((String)this.tf_star.getSelectedItem());
                this.hotel.setCar_park(this.rbut_carpark.isSelected());
                this.hotel.setWifi(this.rbut_wifi.isSelected());
                this.hotel.setPool(this.rbut_swim.isSelected());
                this.hotel.setFitness(this.rbut_gym.isSelected());
                this.hotel.setConcierge(this.rbut_concierge.isSelected());
                this.hotel.setSpa(this.rbut_spa.isSelected());
                this.hotel.setRoom_service(this.rbut_roomservices.isSelected());
                result = this.hotelManager.save(hotel);
                if (result) {
                    Helper.showMsg("done");

                    dispose();

                } else {
                    Helper.showMsg("error");
                }
            }
        });
    }
}




