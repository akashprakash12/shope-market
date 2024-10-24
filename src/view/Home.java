package view;

import java.awt.Dimension;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Admin.Add;

public class Home extends JFrame {

    Product product = new Product();

    public Home() {
        setTitle("Home");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();

        setLayout(new GridLayout(2, 2));

        createContentPanel(panel1, panel2);

        add(panel2);

        setVisible(true);
    }

    public void createContentPanel(JPanel jPanel, JPanel panel2) {
        try {
            Connection connection = Db.getConnection();
            String username = "SELECT * FROM product";
            PreparedStatement preparedStatement = connection.prepareStatement(username);
            ResultSet resultSet = preparedStatement.executeQuery();

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            String[] columnNames = new String[columnCount];
            for (int column = 1; column <= columnCount; column++) {
                columnNames[column - 1] = metaData.getColumnName(column);
            }
            List<Object[]> dataRows = new ArrayList<>();

            while (resultSet.next()) {
                Object[] rowData = new Object[columnCount];
                for (int column = 1; column <= columnCount; column++) {
                    rowData[column - 1] = resultSet.getObject(column);
                }
                dataRows.add(rowData);
            }
            // resultSet.beforeFirst();

            Object[][] data = new Object[dataRows.size()][columnCount];
            for (int i = 0; i < dataRows.size(); i++) {
                data[i] = dataRows.get(i);
            }
            DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
            int row = 0;
            while (resultSet.next() && row < 100) {
                Object[] rowData = new Object[columnCount];
                for (int column = 1; column <= columnCount; column++) {
                    rowData[column - 1] = resultSet.getObject(column);
                }
                tableModel.addRow(rowData);
                row++;
            }
            JTable table = new JTable(tableModel);
            table.setPreferredScrollableViewportSize(new Dimension(450, 200));
            JScrollPane scrollPane = new JScrollPane(table);

            panel2.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            Insets componentInsets = new Insets(5, 5, 5, 5);

          
            JButton Delete = new JButton("delete");
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = componentInsets;

            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.gridwidth = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = componentInsets;

            JTextField SearchTextField = new JTextField(20);
            JButton SearchButton = new JButton("Search");
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = componentInsets;
            panel2.add(SearchTextField, gbc);

            gbc.gridx = 1;
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = componentInsets;
            panel2.add(SearchButton, gbc);

            JButton Logout = new JButton("Logout");
            gbc.gridx = 5;
            gbc.gridy = 3;

            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = componentInsets;
            panel2.add(Logout, gbc);

            JButton Bill = new JButton("Paiment");
            gbc.gridx = 4;
            gbc.gridy = 3;

            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = componentInsets;

            JButton Add = new JButton("Add");
            gbc.gridx = 5;
            gbc.gridy = 4;

            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = componentInsets;
            panel2.add(Add, gbc);

            jPanel.add(scrollPane);
            add(jPanel);

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

                        JFrame search = new JFrame("search");
                        search.setSize(500, 500);
                        search.setLocationRelativeTo(null);
                        search.setVisible(true);

                        JPanel jPanel3 = new JPanel();
                        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

                        while (resultSet.next()) {
                            Object[] row = new Object[columnNames.length];
                            for (int i = 0; i < columnNames.length; i++) {
                                row[i] = resultSet.getObject(columnNames[i]);
                            }
                            tableModel.addRow(row);
                        }

                        if (tableModel.getRowCount() == 0) {

                            JOptionPane.showMessageDialog(SearchTextField, "Product not found", "product not found",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }

                        JTable table2 = new JTable(tableModel);
                        table2.setPreferredScrollableViewportSize(new Dimension(450, 200));
                        JScrollPane scrollPane = new JScrollPane(table2);

                        JTextField deletTextField = new JTextField(20);
                        JButton Delete2 = new JButton("delete");
                        gbc.gridx = 0;
                        gbc.gridy = 0;
                        gbc.gridwidth = 1;
                        gbc.fill = GridBagConstraints.HORIZONTAL;
                        gbc.insets = componentInsets;
                        jPanel3.add(deletTextField, gbc);

                        gbc.gridx = 1;
                        gbc.gridy = 0;
                        gbc.gridwidth = 1;
                        gbc.fill = GridBagConstraints.HORIZONTAL;
                        gbc.insets = componentInsets;
                        jPanel3.add(Delete2, gbc);
                        Delete2.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String Deleteid = deletTextField.getText();
                                int id = Integer.parseInt(Deleteid);
                                try {
                                    Connection connection = Db.getConnection();

                                    String Productid = "Delete FROM product where id=" + id;
                                    PreparedStatement preparedStatement = connection.prepareStatement(Productid);
                                    int resultSet = preparedStatement.executeUpdate();
                                    if (resultSet == 0) {
                                        System.out.println("not found product");

                                    } else {
                                        JOptionPane.showMessageDialog(Delete, "Deleted", "product is deleted",
                                                JOptionPane.INFORMATION_MESSAGE);
                                        new Home();
                                        dispose();

                                    }

                                } catch (SQLException err) {
                                    System.out.println(err);
                                }

                            }
                        });

                        jPanel3.add(scrollPane);

                        search.add(jPanel3);

                    } catch (Exception err) {

                    }

                }
            });
            Add.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new Add();

                }
            });

          
            Logout.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new Login();
                    dispose();

                }
            });
        } catch (Exception e) {
           System.out.println(e);
        }

        // Create table

    }
    


     public void addProduct() {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Product");
            System.out.println("2. Display Products");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter product name: ");
                    String name = scanner.next();
                    System.out.print("Enter product price: ");
                    double price = scanner.nextDouble();
                    System.out.print("Enter product price: ");
                    double quantity = scanner.nextDouble();
                    product.addProduct(name, price ,quantity);
                    break;
                case 2:
                    product.displayProducts();
                    break;
                case 3:
                    System.out.print("Enter product ID to update: ");
                    int updateId = scanner.nextInt();
                    System.out.print("Enter new product name: ");
                    String updateName = scanner.next();
                    System.out.print("Enter new product price: ");
                    double updatePrice = scanner.nextDouble();
                    product.updateProduct(updateId, updateName, updatePrice);
                    break;
                case 4:
                    System.out.print("Enter product ID to delete: ");
                    int deleteId = scanner.nextInt();
                    product.deleteProduct(deleteId);
                    break;
                case 5:
                    System.out.println("Exiting Shope Market");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

}
