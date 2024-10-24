package view;

public class ProductBluprint {
    private int id;
    private String name;
    private double price;
    private double quantity;

    ProductBluprint() {

    }

    ProductBluprint(int id, String name, double price,double quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity=quantity;


    }
    

    public int getId() {
        return id;
    }
    
    public double getquantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public void setquantity(double quantity) {
        this.quantity = quantity;
    }


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price + '\'' +
                ", quantity=" + quantity + '\'' +
                '}';
    }
}
