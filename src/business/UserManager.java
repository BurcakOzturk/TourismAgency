package business;

import core.Helper;
import dao.UserDao;
import entity.User;

import java.util.ArrayList;

// Kullanıcı işlemlerini yöneten sınıf
public class UserManager {
    private final UserDao userDao;  // Kullanıcı veritabanı erişim nesnesi

    public UserManager() {
        this.userDao = new UserDao();
    }

    // Kullanıcı adı ve şifreye göre kullanıcıyı bulan metot
    public User findByLoging(String username, String password) {
        return this.userDao.findByLogin(username, password);

    }

    // Tablo için gerekli bilgileri sağlayan metot
    public ArrayList<Object[]> getForTable(int size, ArrayList<User> modelList) {
        ArrayList<Object[]> modelObjList = new ArrayList<>();
        for (User obj : modelList) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getUsername();
            rowObject[i++] = obj.getPassword();
            rowObject[i++] = obj.getRole();
            modelObjList.add(rowObject);
        }
        return modelObjList;

    }

    // Tüm kullanıcıları getiren metot
    public ArrayList<User> findAll() {
        return this.userDao.findAll();
    }

    // Kullanıcı kaydını veritabanına ekleyen metot
    public boolean save(User user) {
        if (this.getById(user.getId()) != null) {
            Helper.showMsg("error");
            return false;
        }
        return this.userDao.save(user);
    }

    // Kullanıcı bilgilerini güncelleyen metot
    public boolean update(User user) {
        if (this.getById(user.getId()) == null) {
            Helper.showMsg(user.getId() + "ID kayıtlı model bulunamadı");
            return false;
        }
        return this.userDao.update(user);
    }

    // Belirli bir ID'ye sahip kullanıcıyı getiren metot
    public User getById(int id) {
        return this.userDao.getByID(id);
    }


    //Silme işlemini yapan metod
    public boolean delete(int id) {
        if (this.getById(id) == null) {
            Helper.showMsg(id + " ID kayıtlı model bulunamadı");
            return false;
        }
        return this.userDao.delete(id);
    }

    // Rolüne göre kullanıcıları arayan metot
    public ArrayList<User> searchForTable(String role) {
        String select = "SELECT * FROM public.users";
        ArrayList<String> whereList = new ArrayList<>();
        if (role != null) {
            whereList.add("user_role='" + role + "'");
        }

        String whereStr = String.join(" AND ", whereList);
        String query = select;
        if (whereStr.length() > 0) {
            query += " WHERE " + whereStr;
        }
        System.out.println(whereList);
        return this.userDao.selectByQuery(query);
    }
}