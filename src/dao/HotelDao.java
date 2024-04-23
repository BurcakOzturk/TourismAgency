package dao;

import core.Database;
import entity.Hotel;
import entity.User;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// Otel veritabanı işlemlerini yapan sınıf
public class HotelDao {
    private final Connection connection;

    // Yapılandırıcı metot
    public HotelDao() {

        this.connection = Database.getInstance();
    }


    // Belirli bir ID'ye sahip oteli getiren metot
    public Hotel getByID(int id) {
        Hotel obj = null;
        String query = "SELECT * FROM public.hotel WHERE id = ? ";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = this.match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    // ResultSet'ten Hotel nesnesine eşleme yapan yardımcı metot
    public Hotel match(ResultSet rs) throws SQLException {
        Hotel obj = new Hotel();
        obj.setId(rs.getInt("id"));
        obj.setName(rs.getString("name"));
        obj.setAddress(rs.getString("address"));
        obj.setCity(rs.getString("city"));
        obj.setMail(rs.getString("mail"));
        obj.setPhone(rs.getString("phone"));
        obj.setStar(rs.getString("star"));
        obj.setCar_park(rs.getBoolean("car_park"));
        obj.setWifi(rs.getBoolean("wifi"));
        obj.setPool(rs.getBoolean("pool"));
        obj.setFitness(rs.getBoolean("fitness"));
        obj.setConcierge(rs.getBoolean("concierge"));
        obj.setSpa(rs.getBoolean("spa"));
        obj.setRoom_service(rs.getBoolean("room_service"));

        return obj;
    }


    // Tüm otelleri getiren metot
    public ArrayList<Hotel> findAll() {
        ArrayList<Hotel> hotelList = new ArrayList<>();
        String sql = "SELECT * FROM public.hotel";
        try {
            ResultSet rs = this.connection.createStatement().executeQuery(sql);
            while (rs.next()) {

                hotelList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotelList;
    }


    // Otel ekleyen metot
    public boolean save(Hotel hotel) {
        String query = "INSERT INTO public.hotel " +
                "(" +
                "name," +
                "mail," +
                "phone," +
                "address," +
                "city," +
                "star,"+
                "car_park," +
                "wifi," +
                "pool," +
                "fitness," +
                "concierge," +
                "spa," +
                "room_service " +
                ")" +
                " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = connection.prepareStatement(query);
            pr.setString(1, hotel.getName());
            pr.setString(2, hotel.getMail());
            pr.setString(3, hotel.getPhone());
            pr.setString(4, hotel.getAddress());
            pr.setString(5, hotel.getCity());
            pr.setString(6, hotel.getStar());
            pr.setBoolean(7, hotel.isCar_park());
            pr.setBoolean(8, hotel.isWifi());
            pr.setBoolean(9, hotel.isPool());
            pr.setBoolean(10, hotel.isFitness());
            pr.setBoolean(11, hotel.isConcierge());
            pr.setBoolean(12, hotel.isSpa());
            pr.setBoolean(13, hotel.isRoom_service());
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    // Otel silen metot
    public boolean delete(int model_id){
        try{
            String query = "DELETE FROM public.hotel WHERE id = ?";
            PreparedStatement pr = connection.prepareStatement(query);
            pr.setInt(1,model_id);
            return pr.executeUpdate() != -1;
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return true;
    }

    // Otel güncelleyen metot
    public boolean update(Hotel hotel) {
        try {
            String query = "UPDATE public.hotel SET " +
                    "name = ?,"+
                    "mail = ?,"+
                    "phone = ?,"+
                    "address = ?,"+
                    "city = ?,"+
                    "star = ?,"+
                    "car_park = ?,"+
                    "wifi = ?,"+
                    "pool = ?,"+
                    "fitness = ?,"+
                    "concierge = ?,"+
                    "spa = ?,"+
                    "room_service = ? "+
                    "WHERE id = ?";
            PreparedStatement pr = connection.prepareStatement(query);
            pr.setString(1, hotel.getName());
            pr.setString(2, hotel.getMail());
            pr.setString(3, hotel.getPhone());
            pr.setString(4, hotel.getAddress());
            pr.setString(5, hotel.getCity());
            pr.setString(6, hotel.getStar());
            pr.setBoolean(7, hotel.isCar_park());
            pr.setBoolean(8, hotel.isWifi());
            pr.setBoolean(9, hotel.isPool());
            pr.setBoolean(10, hotel.isFitness());
            pr.setBoolean(11, hotel.isConcierge());
            pr.setBoolean(12, hotel.isSpa());
            pr.setBoolean(13, hotel.isRoom_service());
            pr.setInt(14, hotel.getId());

            return pr.executeUpdate() != -1;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
