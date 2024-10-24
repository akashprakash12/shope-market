package view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Product {
    protected Connection connection;

    private List<ProductBluprint> products;
    private int nextProductId;

    public Product() {
        products = new ArrayList<>();
        nextProductId = 1;
    }

    public void addProduct(String name, double price) {
        ProductBluprint product = new ProductBluprint();
        product.setId(nextProductId++);
        product.setName(name);
        product.setPrice(price);
        products.add(product);
        try {
            connection=Db.getConnection();
            int id=product.getId();
            String ProductName=product.getName();
            double ProductPrice=product.getPrice();
            String addProduct="insert into Product (id,name,price) values('"+id+"','"+ProductName+"','"+ProductPrice+"')";
            PreparedStatement preparedStatement =connection.prepareStatement(addProduct);
            int result =preparedStatement.executeUpdate();
            System.out.println(result);
        
            
        } catch (SQLException e) {
            
            
        }
        System.out.println("Product added successfully!");
    }

    public void displayProducts() {
        if (products.isEmpty()) {
            System.out.println("No products available.");
        } else {
            for (ProductBluprint product : products) {
                System.out.println(product);
            }
        }
    }

    // Update operation
    public void updateProduct(int productId, String newName, double newPrice) {
        for (ProductBluprint product : products) {
            if (product.getId() == productId) {
                product.setName(newName);
                product.setPrice(newPrice);
                System.out.println("Product updated successfully!");
                return;
            }
        }
        System.out.println("Product not found.");
    }

    // Delete operation
    public void deleteProduct(int productId) {
        products.removeIf(product -> product.getId() == productId);
        System.out.println("Product deleted successfully!");

    }
}
