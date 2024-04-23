package view;

import business.*;
import core.Helper;
import entity.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.event.*;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class EmployeeGUI extends Layout {
    private JPanel wrapper;
    private JLabel lbl_welcome;
    private JButton btn_exit;
    private JTabbedPane tabs;
    private JPanel tab_Hotel;
    private JPanel tab_reservation;
    private JTable tbl_hotel;
    private JTable tbl_room;

    public JTable getTbl_room() {
        return tbl_room;
    }

    public void setTbl_room(JTable tbl_room) {
        this.tbl_room = tbl_room;
    }

    private JTable tbl_reservation;
    private JScrollPane w_left;
    private JScrollPane w_right;
    private JButton btn_search_room;
    private JButton btn_room_reset;
    private JButton btn_room_add;
    private JTextField tf_name_room;
    private JTextField tf_city_room;
    private JTextField tf_numb_adult_room;
    private JTextField tf_numb_children_room;
    private JTable tbl_pension;
    private JTable tbl_season;
    private JFormattedTextField tf_date_entry_room;
    private JFormattedTextField tf_date_release_room;
    private JScrollPane scrl_room;
    private JTable tbl_res_room;
    private JButton btn_pension_add;
    private Hotel hotel;
    private JPopupMenu hotel_menu;
    private HotelManager hotelManager = new HotelManager();
    private Pension pension = new Pension();
    private PensionManager pensionManager = new PensionManager();
    private SeasonManager seasonManager = new SeasonManager();
    private RoomManager roomManager = new RoomManager();
    private UserManager userManager = new UserManager();
    private JPopupMenu pension_menu;
    private JPopupMenu season_menu;
    private JPopupMenu room_menu;
    private JPopupMenu room_res_menu;
    private JPopupMenu reservation_menu;
    private ReservationManager reservationManager;
    private Room room;
    private int selectedHotelID;
    private int selectedRoomID;

    private Object[] col_room;

    private Object[] col_res_room;

    // Sütunlar ve tablolar için varsayılan tablo modelleri
    DefaultTableModel tmdl_hotel = new DefaultTableModel();
    DefaultTableModel tmdl_pension = new DefaultTableModel();
    DefaultTableModel tmdl_season = new DefaultTableModel();
    DefaultTableModel tmdl_room = new DefaultTableModel();
    DefaultTableModel tmdl_res = new DefaultTableModel();
    DefaultTableModel tmdl_res_room = new DefaultTableModel();

    public EmployeeGUI() {

    }

    //Kurucu metod - Kullanıcı bilgisi ile
    public EmployeeGUI(User user) {
        this.room = new Room();
        this.col_room = col_room;
        this.col_res_room = col_res_room;
        this.hotelManager = new HotelManager();
        this.hotel = new Hotel();
        this.add(wrapper);
        this.guiInitilaze(1400, 850);
        this.pension_menu = new JPopupMenu();
        this.season_menu = new JPopupMenu();
        this.room_menu = new JPopupMenu();
        this.room_res_menu = new JPopupMenu();
        this.reservation_menu = new JPopupMenu();
        this.lbl_welcome.setText("Username : " + user.getUsername());
        this.reservationManager = new ReservationManager();

        loadHotelTable();
        loadPensionTable();
        loadRoomTable(null);
        loadSeasonTable();
        LoadRoomTableComponent();
        loadReservationTable(null);
        LoadReservationTableComponent();
        loadReservationRoomTable(null);
        LoadReservationRoomComponent();

        tableRowSelect(tbl_hotel);
        tableRowSelect(tbl_pension);
        tableRowSelect(tbl_room);
        loadHotelTableComponent();
        LoadPensionTableComponent();
        loadSeasonTableComponent();

        // Bu butona basıldığında oda için filtrelemeyi sıfırlıyoruz
        btn_room_reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadReservationRoomTable(null);
            }
        });

        // Bu butona basınca oda içinde filtreleme yapılıyor
        btn_search_room.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField[] roomJTextField = new JTextField[]{tf_numb_adult_room, tf_numb_children_room};
                if (Helper.isFieldListEmpty(roomJTextField)) {// Filtreleme aşamasında çocuk veya yetişkin sayısını girmezse hata mesajı ver
                    Helper.showMsg("Please enter the number of children and adults");
                }else {
                    int selectedAdult = Integer.parseInt(tf_numb_adult_room.getText());
                    int selectedChild = Integer.parseInt(tf_numb_children_room.getText());

                    if (selectedAdult < 0 || selectedChild < 0) {
                        Helper.showMsg("Please enter the number of children and adults greater than 0");
                    }
                    ArrayList<Room> roomList = roomManager.searchForTable(
                            tf_name_room.getText(),
                            tf_city_room.getText(),
                            tf_date_entry_room.getText(),
                            tf_date_release_room.getText(),
                            tf_numb_adult_room.getText(),
                            tf_numb_children_room.getText()
                    );

                    ArrayList<Object[]> searchResult = roomManager.getForTable(col_res_room.length, roomList);
                    loadReservationRoomTable(searchResult);

                }
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

        tbl_hotel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = tbl_hotel.getSelectedRow();
                if (selectedRow != -1) {

                    selectedHotelID = (int) tmdl_hotel.getValueAt(selectedRow, 0);
                    loadSeasonTable();
                    loadPensionTable();
                    loadRoomTable(null);
                }
            }
        });

    }

    //oluşturulan tabloların ekranda görünmesini sağlayan metodlar

    public void loadHotelTable() {
        Object[] col_hotel = {"ID", "Name", "Adress", "City", "Mail", "Telefon", "Star", "Car Park", "Wifi", "Pool", "Fitness", "Convention", "Spa", "Room Services"};
        ArrayList<Object[]> hotelList = this.hotelManager.getForTable(col_hotel.length, this.hotelManager.findAll());
        createTable(this.tmdl_hotel, tbl_hotel, col_hotel, hotelList);
    }

    public void loadReservationTable(Reservation reservation) {
        Object[] col_res = {"ID", "Room ID", "Entry Date", "Finish Date","Total Amount", "Guest Number", "Guest Name ", "Guest TC Id Number", "Mail", "Phone"};
        ArrayList<Object[]> resList = this.reservationManager.getForTable(col_res.length, this.reservationManager.findAll());
        createTable(this.tmdl_res, tbl_reservation, col_res, resList);
    }


    public void loadPensionTable() {
        Object[] col_pension = {"ID", "Hotel ID", "Pension Type"};
        ArrayList<Object[]> pensionList = this.pensionManager.getForTable(col_pension.length, this.pensionManager.getPensionByOtelId(selectedHotelID));
        createTable(this.tmdl_pension, tbl_pension, col_pension, pensionList);
    }

    public void loadSeasonTable() {
        Object[] col_season = {"ID", "Hotel ID", "Start Date", "Finish Date", "Price Parameter"};
        ArrayList<Object[]> seasonList = this.seasonManager.getForTable(col_season.length, this.seasonManager.getSeasonsByOtelId(selectedHotelID));
        createTable(this.tmdl_season, tbl_season, col_season, seasonList);

    }

    public void loadRoomTable(ArrayList<Object[]> roomList) {
        col_room = new Object[]{"ID", "Hotel ID", "Pension ID", "Season ID", "Type", "Stock", "Adult Price", "Child Price", "Bed Capacity", "Square Meter", "Television", "Minibar", "Game Console", "Cash BOX", "Projection"};
        if (roomList == null) {
            roomList = roomManager.getForTable(col_room.length, this.roomManager.getRoomByOtelId(selectedHotelID));
        }
        createTable(this.tmdl_room, tbl_room, col_room, roomList);
    }

    public void loadReservationRoomTable(ArrayList<Object[]> roomList) {
        col_res_room = new Object[]{"ID", "Hotel ID", "Pension ID", "Season ID", "Type", "Stock", "Adult Price", "Child Price", "Bed Capacity", "Square Meter", "Television", "Minibar", "Game Console", "Cash BOX", "Projection"};
        if (roomList == null) {
            roomList = roomManager.getForTable(col_res_room.length, this.roomManager.getRoomByOtelId(selectedHotelID));
        }
        createTable(this.tmdl_res_room, tbl_res_room, col_res_room, roomList);
    }

    @Override
    public void tableRowSelect(JTable table) {
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int selected_row = table.rowAtPoint(e.getPoint());
                table.setRowSelectionInterval(selected_row, selected_row);
            }
        });
    }

    //Oda tablosundaki sağ tık menüsü içeriğinin ayarlanması
    public void LoadRoomTableComponent() {
        tableRowSelect(tbl_room);
        this.room_menu = new JPopupMenu();

        this.room_menu.add("Update Room").addActionListener(e -> {
            int selectedRow = tbl_room.getSelectedRow();
            if (selectedRow != -1)
            {
                int selectedRoomID = (int) tmdl_room.getValueAt(selectedRow, 0);
                loadRoomTable(null);
                UpdateRoomGUI updateRoomGUI = new UpdateRoomGUI(selectedRoomID);
                updateRoomGUI.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        loadRoomTable(null);
                    }
                });
            }
        });

        this.room_menu.addSeparator();

        this.room_menu.add("Delete Room").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectBrandId = this.getTableSelectedRow(tbl_room, 0);
                if (this.roomManager.delete(selectBrandId))
                {
                    Helper.showMsg("done");
                    loadRoomTable(null);
                    loadHotelTable();
                    loadPensionTable();
                }
                else
                {
                    Helper.showMsg("error");
                }
            }
        });
        this.tbl_room.setComponentPopupMenu(room_menu);
    }

    //Rezervasyon araması tablosundaki sağ tık menüsü içeriğinin ayarlanması
    public void LoadReservationRoomComponent() {
        tableRowSelect(tbl_res_room);
        this.room_res_menu = new JPopupMenu();

        this.room_res_menu.add("Add Reservation").addActionListener(e -> {
            int selectId = this.getTableSelectedRow(tbl_res_room, 0);
            JTextField[] roomJTextField = new JTextField[]{tf_date_entry_room, tf_date_release_room, tf_numb_adult_room, tf_numb_children_room};
            if (Helper.isFieldListEmpty(roomJTextField))
            {
                Helper.showMsg("fill");
            }
            else
            {
                int adult_numb = Integer.parseInt(this.tf_numb_adult_room.getText());
                int child_numb = Integer.parseInt(this.tf_numb_children_room.getText());
                AddReservationGUI reservationGUI = new AddReservationGUI(this.roomManager.getById(selectId), this.tf_date_entry_room.getText(), this.tf_date_release_room.getText(), adult_numb, child_numb, null);
                reservationGUI.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        loadRoomTable(null);
                        loadReservationTable(null);
                    }
                });
            }
        });

        this.tbl_res_room.setComponentPopupMenu(room_res_menu);
    }


    //Rezervasyon tablosundaki sağ tık menüsü içeriğinin ayarlanması

    public void LoadReservationTableComponent() {
        tableRowSelect(tbl_reservation);
        this.reservation_menu = new JPopupMenu();

        this.reservation_menu.add("Update Reservation").addActionListener(e -> {
            int selectId = this.getTableSelectedRow(tbl_reservation, 0);
            Reservation selectReservation = this.reservationManager.getById(selectId);
            int selectRoomId = selectReservation.getRoom_id();
            Room selectRoom = this.roomManager.getById(selectRoomId);
            UpdateReservationGUI reservationGUI = new UpdateReservationGUI(selectRoom,selectReservation.check_in_date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),selectReservation.check_out_date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),selectReservation.getAdult_count(), selectReservation.getChild_count(), selectReservation);
            reservationGUI.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadReservationTable(null);

                }
            });
        });

        this.reservation_menu.addSeparator();

        this.reservation_menu.add("Delete Reservation").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectResId = this.getTableSelectedRow(tbl_reservation, 0);
                int selectRoomId = this.reservationManager.getById(selectResId).getRoom_id();
                Room selectedRoom = this.roomManager.getById(selectRoomId);
                selectedRoom.setStock(selectedRoom.getStock()+1);
                this.roomManager.updateStock(selectedRoom);
                if (this.reservationManager.delete(selectResId)) {
                    Helper.showMsg("done");
                    loadRoomTable(null);
                    loadReservationTable(null);
                    loadHotelTable();
                    loadPensionTable();
                } else {
                    Helper.showMsg("error");
                }
            }
        });

        this.tbl_reservation.setComponentPopupMenu(reservation_menu);
    }

    //Pansiyon tablosundaki sağ tık menüsü içeriğinin ayarlanması

    public void LoadPensionTableComponent() {
        tableRowSelect(tbl_pension);
        this.pension_menu = new JPopupMenu();

        //pansiyon güncelleme kısmı eklenecektir.

        /*this.pension_menu.add("Update Pension").addActionListener(e ->{
            int selectedRow = tbl_pension.getSelectedRow();
            if (selectedRow != -1) {
                int selectedPensionId = (int) tmdl_pension.getValueAt(selectedRow, 0);
                loadPensionTable();
                //PensionUpdateGUI pensionUpdateGUI = new PensionUpdateGUI(selectedPensionId);
                HotelUpdateGUI hotelUpdateGUI = new HotelUpdateGUI(selectedHotelID);
                hotelUpdateGUI.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        loadHotelTable();
                    }
                });
            }
        });

        this.pension_menu.addSeparator();*/

        this.pension_menu.add("Delete Pension").addActionListener(e -> {
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

    //Sezon tablosundaki sağ tık menüsü içeriğinin ayarlanması

    public void loadSeasonTableComponent() {
        tableRowSelect(tbl_season);
        this.season_menu = new JPopupMenu();

        //otele bağlı olan sezon güncelleme kısmı eklenecektir.

        /*this.season_menu.add("Update Season").addActionListener(e -> {


        });

        this.season_menu.addSeparator();*/

        this.season_menu.add("Delete Season").addActionListener(e -> {
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

    //Otel tablosundaki sağ tık menüsü içeriğinin ayarlanması

    public void loadHotelTableComponent() {
        tableRowSelect(tbl_hotel);
        tableRowSelect(tbl_pension);
        tableRowSelect(tbl_room);
        tableRowSelect(tbl_season);

        this.hotel_menu = new JPopupMenu();

        this.hotel_menu.add("Add Hotel").addActionListener(e -> {
            HotelAddGUI hotelAddGUI = new HotelAddGUI();
            hotelAddGUI.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelTable();
                }
            });
        });

        this.hotel_menu.add("Add Season").addActionListener(e -> {
            SeasonGUI seasonGUI = new SeasonGUI();
            seasonGUI.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadSeasonTable();
                }
            });
        });

        this.hotel_menu.add("Add Room").addActionListener(e -> {
            ADDRoomGUI addRoomGUI = new ADDRoomGUI();
            addRoomGUI.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadRoomTable(null);
                }
            });
        });

        this.hotel_menu.add("Add Pension").addActionListener(e -> {
            int selectId = this.getTableSelectedRow(tbl_hotel, 0);
            PensionGUI pensionGUI = new PensionGUI(selectId);
            pensionGUI.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadPensionTable();
                }
            });
        });

        this.hotel_menu.addSeparator();

        this.hotel_menu.add("Update Hotel").addActionListener(e -> {
            int selectedRow = tbl_hotel.getSelectedRow();
            if (selectedRow != -1) {
                int selectedHotelId = (int) tmdl_hotel.getValueAt(selectedRow, 0);
                loadHotelTable();
                HotelUpdateGUI hotelUpdateGUI = new HotelUpdateGUI(selectedHotelID);
                hotelUpdateGUI.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        loadHotelTable();
                    }
                });
            }
        });

        this.hotel_menu.addSeparator();

        this.hotel_menu.add("Delete Hotel").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectBrandId = this.getTableSelectedRow(tbl_hotel, 0);
                if (this.hotelManager.delete(selectBrandId)) {
                    Helper.showMsg("done");
                    loadHotelTable();
                    loadPensionTable();
                    loadRoomTable(null);
                    loadSeasonTable();
                } else {
                    Helper.showMsg("error");
                }
            }
        });

        this.tbl_hotel.setComponentPopupMenu(hotel_menu);
    }

    //Formatlı olarak ayarlanmış tarih hücrelerinin ayarlanması

    private void createUIComponents() throws ParseException {
        this.tf_date_entry_room = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.tf_date_entry_room.setText("01/01/2024");
        this.tf_date_release_room = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.tf_date_release_room.setText("01/05/2024");
    }

}