package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Bill extends JFrame {
    public Bill() {
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

        gbc.fill = GridBagConstraints.HORIZONTAL;
        Insets componentInsets = new Insets(5, 5, 5, 5);

        JTextField SearchTextField = new JTextField(20);
        JButton SearchButton = new JButton("Search");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = componentInsets;
        jPanel.add(SearchTextField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = componentInsets;
        jPanel.add(SearchButton, gbc);
        JTextArea addressTextArea = new JTextArea(10, 10);
        addressTextArea.setLineWrap(true);
        addressTextArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(addressTextArea);
        gbc.gridx = 0;
        gbc.gridy = 2;
        jPanel.add(scrollPane, gbc);

        SearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String searchid = SearchTextField.getText();
                    int id = Integer.parseInt(searchid);
                    Connection connection = Db.getConnection();

                    String query = "SELECT * FROM product WHERE id =" + id;
                    PreparedStatement preparedStatement = connection.prepareStatement(query);

                    ResultSet resultSet = preparedStatement.executeQuery();
                    List<Object[]> dataRows = new ArrayList<>();

                    while (resultSet.next()) {
                        Object[] rowData = new Object[] {
                                resultSet.getObject("name"),
                                resultSet.getObject("price"),

                                // Add more columns as needed
                        };

                        dataRows.add(rowData);

                    }
                    int totalSum = 0;
                    for (Object[] row : dataRows) {
                        Object priceObject = row[1];
                        if (priceObject != null) {
                            // Convert the "price" to int
                            int pri = ((Number) priceObject).intValue();
                            System.out.println("Price: " + pri);

                            totalSum += pri;
                        } else {
                            System.out.println("Price is null");
                        }

                    }
                    System.out.println("total sum=" + totalSum);
                } catch (Exception err) {
                    // TODO: handle exception
                }

            }
        });

    }

}
