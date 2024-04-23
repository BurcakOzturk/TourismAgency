package dao;

import core.Database;
import entity.Pension;
import entity.Season;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

// Sezon veritabanı işlemlerini yapan sınıf
public class SeasonDao {
    private final Connection connection;

    // Yapılandırıcı metot
    public SeasonDao() {
        this.connection = Database.getInstance();
    }

    // Belirli bir otelin sezonlarını getiren metot
    public ArrayList<Season> getSeasonsByOtelId(int otelId) {
        ArrayList<Season> seasons = new ArrayList<>();
        String query = "SELECT * FROM public.hotel_season WHERE hotel_id = ?";

        try (PreparedStatement pr = connection.prepareStatement(query)) {
            pr.setInt(1, otelId);
            ResultSet rs = pr.executeQuery();

            while (rs.next()) {
                Season season = match(rs);
                seasons.add(season);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return seasons;
    }

    // Belirli bir ID'ye sahip sezonu getiren metot
    public Season getByID(int id) {
        Season obj = null;
        String query = "SELECT * FROM public.hotel_season WHERE id = ? ";
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

    // ResultSet'ten Season nesnesine eşleme yapan yardımcı metot
    public Season match(ResultSet rs) throws SQLException {
        Season obj = new Season();
        obj.setId(rs.getInt("id"));
        obj.setHotel_id(rs.getInt("hotel_id"));
        obj.setStart_date(LocalDate.parse(rs.getString("start_date")));
        obj.setFinish_date(LocalDate.parse(rs.getString("finish_date")));
        obj.setPrice_parameter(rs.getDouble("price_parameter"));

        return obj;
    }

    // Tüm sezonları getiren metot
    public ArrayList<Season> findAll() {
        ArrayList<Season> seasonList = new ArrayList<>();
        String sql = "SELECT * FROM public.hotel_season";
        try {
            ResultSet rs = this.connection.createStatement().executeQuery(sql);
            while (rs.next()) {

                seasonList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seasonList;
    }

    // Sezon ekleyen / kaydeden metot
    public boolean save(Season season){
        String query = "INSERT INTO public.hotel_season"+
                "("+
                "hotel_id,"+
                "start_date," +
                "finish_date,"+
                "price_parameter"+
                ")"+
                "VALUES (?,?,?,?)";
        try {
            PreparedStatement pr = connection.prepareStatement(query);
            pr.setInt(1,season.getHotel_id());
            pr.setDate(2, Date.valueOf(season.getStart_date()));
            pr.setDate(3, Date.valueOf(season.getFinish_date()));
            pr.setDouble(4, season.getPrice_parameter());
            return  pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    //Id numarasına göre fiyat katsayısı döndüren metod
    public double returnPriceParameter(int id){

        double priceParameter = 0.0;
        String query = "SELECT price_parameter FROM public.hotel_season WHERE id=?";

        try {
            PreparedStatement pr = connection.prepareStatement(query);
            pr.setInt(1,id);

            ResultSet rs = pr.executeQuery();

            if(rs.next()){
                priceParameter = rs.getDouble("price_parameter");
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return priceParameter;
    }

    //otele eklenmiş olan sezonu güncelleyen metod da sonradan eklenecektir.

    // Sezon silen metot
    public boolean delete(int hotel_id){
        try{
            String query = "DELETE FROM public.hotel_season WHERE id = ?";
            PreparedStatement pr = connection.prepareStatement(query);
            pr.setInt(1,hotel_id);
            return pr.executeUpdate() != -1;
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return true;
    }
}


