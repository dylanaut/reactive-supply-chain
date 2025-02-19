package group.msg.gbi.supplychain.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import jakarta.persistence.Id;

public class OrderItem  extends PanacheEntity {
    @Id
    private String productId;       // ID des Produkts
    private String productName;     // Name des Produkts
    private int quantity;           // Bestellte Menge
    private int quantityShipped;    // Bereits gelieferte Menge
    private double price;           // Preis pro Einheit

    // Konstruktoren
    public OrderItem() {
        this.quantityShipped = 0;   // Standardwert: Noch nichts geliefert
    }

    public OrderItem(String productId, String productName, int quantity, double price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.quantityShipped = 0;   // Standardwert: Noch nichts geliefert
    }

    // Getter und Setter
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantityShipped() {
        return quantityShipped;
    }

    public void setQuantityShipped(int quantityShipped) {
        if (quantityShipped > this.quantity) {
            throw new IllegalArgumentException("Die gelieferte Menge kann nicht größer als die bestellte Menge sein.");
        }
        this.quantityShipped = quantityShipped;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Hilfsmethode: Berechnung der verbleibenden Menge
    public int getRemainingQuantity() {
        return quantity - quantityShipped;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
               "productId='" + productId + '\'' +
               ", productName='" + productName + '\'' +
               ", quantity=" + quantity +
               ", quantityShipped=" + quantityShipped +
               ", remainingQuantity=" + getRemainingQuantity() +
               ", price=" + price +
               '}';
    }
}

