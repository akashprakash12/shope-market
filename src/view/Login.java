package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Login extends JFrame {
    private JTextField usernameField;
    private JTextField passwordField;
    protected Connection connection;

    public Login() {
        setTitle("Login Page");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        setVisible(true);

    }

    private void placeComponents(JPanel panel) {

        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel userLabel = new JLabel("Username:");

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(userLabel, gbc);
        usernameField = new JTextField(20);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(usernameField, gbc);
        JLabel passwordLabel = new JLabel("Password:");

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(passwordLabel, gbc);

        passwordField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(passwordField, gbc);

        JButton loginButton = new JButton("Login");
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(loginButton, gbc);
        

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = passwordField.getText();
                String passwordString = new String(password);

                // Replace this with your actual authentication logic
                if (authenticate(username, passwordString)) {
                    SwingUtilities.invokeLater( new Runnable() {
                        public void run() {
                            new Home();
                            dispose();
                        };
                    });
                    JOptionPane.showMessageDialog(loginButton, "login successfull", "login success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    System.out.println("login fail");
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            new Register();
                            dispose();

                        };
                    });

                }
            }
        });
    }

    private boolean authenticate(String username, String password) {

        boolean autheanticate = false;
        try {
            connection = Db.getConnection();
            String existeingPasswordString = "select password from register where name='" + username + "'";
            PreparedStatement preparedStatement = connection.prepareStatement(existeingPasswordString);

            ResultSet resultSet = preparedStatement.executeQuery(existeingPasswordString);
             String existeingPasswordString2 = "select password from admin where name=admin";
            PreparedStatement preparedStatement2 = connection.prepareStatement(existeingPasswordString2);
            
            ResultSet resultSet2 = preparedStatement2.executeQuery(existeingPasswordString2);

            while (resultSet2.next()) {
                 String existingPassword2 = resultSet.getString(1);
                if (existingPassword2.equals(password)) {
                    autheanticate = true;
                    break;
                    
                }else{
                    JOptionPane.showMessageDialog(rootPane, "password not exist ", "database error", JOptionPane.ERROR_MESSAGE);
                    break;
                }
                
            }
            while (resultSet.next()) {
                String existingPassword = resultSet.getString(1);
                if (existingPassword.equals(password)) {
                    autheanticate = true;
                    break;
                    
                }else{
                    JOptionPane.showMessageDialog(rootPane, "password not exist ", "database error", JOptionPane.ERROR_MESSAGE);
                    break;
                }

            }

        } catch (SQLException e) {

        }
        return autheanticate;

    }

}
