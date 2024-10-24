package view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.util.logging.Logger;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserProfile extends JFrame {
    public static Logger logger = Logger.getLogger("ERROR");
    protected Connection connection;
    protected String name;
    protected String email;

    public UserProfile() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Profile");
        setSize(500, 500);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JPanel profilePicturePanel = new JPanel(null);
        profilePicturePanel.setPreferredSize(new Dimension(100, 100));
        ImageIcon profilephoto = new ImageIcon(
                "C:/Users/HP/OneDrive/Desktop/java shop market/Project/resourse/akImage.jpg");
        Image scaledimg = profilephoto.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        profilephoto = new ImageIcon(scaledimg);
        JLabel ProfileImage = new JLabel(profilephoto);
        ProfileImage.setBounds(0, 0, 100, 100);
        ProfileImage.setOpaque(true);
        profilePicturePanel.add(ProfileImage);

        // Create labels for username and email
        try {
            connection = Db.getConnection();
            String username = "SELECT NAME FROM USERS WHERE ID=3";
            PreparedStatement preparedStatement = connection.prepareStatement(username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                name = resultSet.getString("name");
               // email =resultSet.getString("email");

            }

        } catch (SQLException e) {
            logger.severe("ERROR CODE\t" + e.getErrorCode());
            logger.severe("SQL STATE\t" + e.getSQLState());
            if (e.getSQLState()=="S0022") {
                
                JOptionPane.showMessageDialog(null, e, "database table error", JOptionPane.ERROR_MESSAGE);
            }
           
        }

        JLabel usernameLabel = new JLabel("Username:" + name);
        JLabel emailLabel = new JLabel("Email:"+email);

        // Create a button for logging out
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              //new Login();
              //dispose();
                
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(profilePicturePanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(emailLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(logoutButton, gbc);

        // Add the panel to the JFrame
        setContentPane(panel);

        setVisible(true);
    }

}
