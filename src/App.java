import core.Helper;
import view.EmployeeGUI;
import view.LoginGUI;

public class App {
    public static void main(String[] args) {
        Helper.setTheme();
        //Proje ilk çalışmada Kullanıcı adı ve şifrenin girildiği pencere açılıyor
        LoginGUI loginGUI = new LoginGUI();
    }
}