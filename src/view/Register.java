package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.awt.Insets;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class Register extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JTextField emailField;
    private JTextArea addressTextArea;
    protected Connection connection;

    public Register() {
        setTitle("Registration Page");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(new GridBagLayout());
        setLocationRelativeTo(null);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Username
        JLabel usernameLabel = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(usernameLabel, gbc);

        usernameField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(usernameField, gbc);

        // Password
        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(passwordLabel, gbc);

        passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(passwordField, gbc);

        // Confirm Password
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(confirmPasswordLabel, gbc);

        confirmPasswordField = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(confirmPasswordField, gbc);

        // gmail
        JLabel gmail = new JLabel("email");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(gmail, gbc);
        emailField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(emailField, gbc);
        // address
        JLabel addressLabel = new JLabel("address");
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(addressLabel, gbc);

        addressTextArea = new JTextArea(10, 10);
        addressTextArea.setLineWrap(true);
        addressTextArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(addressTextArea);
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(scrollPane, gbc);

        // Register Button
        JButton registerButton = new JButton("Register");
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.NONE;
        add(registerButton, gbc);

        registerButton.addActionListener(e -> {
            String name = usernameField.getText();
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());
            String address=new String(addressTextArea.getText());
            try {
                
                connection = Db.getConnection();
                String INSERT = "insert into register(name,email,password,address) values('" + name + "','" + email + "','"
                        + password + "','"+address+"')";
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
                int result = preparedStatement.executeUpdate();
                
                
                if (result==1) {
                    new Login();
                    dispose();
                }
                else{
                    JOptionPane.showMessageDialog(registerButton, "somethin happend");
                }

            } catch (SQLException error) {
                error.printStackTrace();
            }

            System.out.println("Name: " + name);
            System.out.println("Email: " + email);
            System.out.println("Password: " + password);
            System.out.println("Confirm Password: " + confirmPassword);
        });

    }

}
