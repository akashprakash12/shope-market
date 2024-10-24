
import javax.swing.SwingUtilities;

import Admin.Add;
import view.Db;
import view.Home;
import view.Login;
import view.Uplode;
import view.UserProfile;

public class App {
  public static void main(String[] args) throws Exception {
    System.out.println("Hello, World!");

    Home home=new Home();
    home.addProduct();
    // Db db=new Db();
    // db.getConnection();

    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
      // new Login();
        //new Uplode();
     new Home();

        // new UserProfile();
       // new Add();
      }
    });

  }
}
