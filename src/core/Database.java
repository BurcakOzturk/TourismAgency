package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Veritabanı bağlantısını yöneten sınıf
public class Database {
    private static Database instance = null;
    private Connection connection = null;
    private final String DB_URL = "jdbc:postgresql://localhost:5432/turism"; // Veritabanı URL'si
    private final String DB_USER = "postgres"; // Veritabanı kullanıcı adı
    private final String DB_PASS = "burcak"; // Veritabanı şifresi

    // Yapılandırıcı metot
    private Database () {
        try {
            // Veritabanı bağlantısını kur
            this.connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASS);
        } catch (SQLException e) {
            // Hata durumunda ekrana hata mesajını yazdır
            System.out.println(e.getMessage());
        }
    }

    // Bağlantıyı döndüren metot
    public Connection getConnection() {
        return connection;
    }

    // Singleton deseni ile veritabanı bağlantısını tek bir örneğe sınırlayan metot
    public static Connection getInstance(){
        try {
            // Eğer instance null ise veya bağlantı kapalı ise
            if (instance == null || instance.getConnection().isClosed()) {
                instance = new Database(); // Yeni bir Database nesnesi oluştur
            }
        } catch (SQLException e) {
            // Hata durumunda ekrana hata mesajını yazdır
            System.out.println(e.getMessage());
        }

        return instance.getConnection(); // Bağlantıyı döndür

    }
}

