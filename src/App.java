
import javax.swing.SwingUtilities;

import Admin.Add;
import view.Bill;
import view.Db;
import view.Home;
import view.Login;
import view.Uplode;
import view.UserProfile;

public class App {
  public static void main(String[] args) throws Exception {
    

    Home home=new Home();
    home.addProduct();
    // Db db=new Db();
    // db.getConnection();
    new Bill();


    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
      // new Login();
        //new Uplode();
   //  new Home();

        // new UserProfile();
       // new Add();
      }
    });

  }
}
