package Admin;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import view.Db;
import view.Home;

public class Add extends JFrame {
    protected Connection connection;

    public Add() {
        setSize(500, 500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

    }

    public void placeComponents(JPanel jPanel) {
        jPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel productLabel = new JLabel("Product name:");

        gbc.gridx = 0;
        gbc.gridy = 0;
        jPanel.add(productLabel, gbc);
        JTextField productTextField = new JTextField(20);

        gbc.gridx = 1;
        gbc.gridy = 0;
        jPanel.add(productTextField, gbc);

        JLabel pricLabel = new JLabel("Price:");

        gbc.gridx = 0;
        gbc.gridy = 1;
        jPanel.add(pricLabel, gbc);

        JFormattedTextField Pricetextfield = new JFormattedTextField();

        gbc.gridx = 1;
        gbc.gridy = 1;
        jPanel.add(Pricetextfield, gbc);
        JLabel Quantity = new JLabel("Quantity:");

        gbc.gridx = 0;
        gbc.gridy = 2;
        jPanel.add(Quantity, gbc);

        JFormattedTextField Quantitytextfield = new JFormattedTextField();

        gbc.gridx = 1;
        gbc.gridy = 2;
        jPanel.add(Quantitytextfield, gbc);

        JButton Add = new JButton("Add Product");
        gbc.gridx = 1;
        gbc.gridy = 3;
        jPanel.add(Add, gbc);

        Add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = productTextField.getText();
                String prObject = Pricetextfield.getText();
                String Quantity = Quantitytextfield.getText();
                int qu = Integer.parseInt(Quantity);
                int price = Integer.parseInt(prObject);

                try {

                    connection = Db.getConnection();
                    String INSERT = "insert into product(name,price,quantity) values('" + name + "','"
                            + price + "','" + qu + "')";
                    PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
                    int result = preparedStatement.executeUpdate();

                    JOptionPane.showMessageDialog(Add,
                            "product added name:" + name + "\n price:" + price + "\tQuantity:" + qu + "", "product ",
                            JOptionPane.INFORMATION_MESSAGE);
                    System.out.println(name);
                    System.out.println(price);
                    System.out.println(qu);
                    try (FileWriter writer = new FileWriter("op.txt", true)) {
                        writer.write("Name: " + name + "\tPrice: " + price + "\tQuantity: " + qu + "\n");
                    }

                    if (result == 1) {
                        new Home();
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(Add, "somethin happend");
                    }

                } catch (SQLException | IOException error) {
                    error.printStackTrace();
                }

            }
        });

    }

}
