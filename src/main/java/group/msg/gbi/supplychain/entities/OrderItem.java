package group.msg.gbi.supplychain.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orderitems")
public class OrderItem extends PanacheEntityBase {
    @Id
    private String orderItemId;     // ID des Items

    private String productId;       // ID/ref des Produkts

    private String productName;     // Name des Produkts

    private int quantity;           // Bestellte Menge

    private int quantityShipped;    // Bereits gelieferte Menge

    private double price;           // Preis pro Einheit

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="delivery_note__id")
    private DeliveryNote deliveryNote;

    // Konstruktoren
    public OrderItem() {
        this.quantityShipped = 0;   // Standardwert: Noch nichts geliefert
    }


    public OrderItem(String productId, String productName, int quantity, double price) {
        this.orderItemId = "OID-" + System.currentTimeMillis();
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
               "orderItemId='" + orderItemId + '\'' +
               ", productId='" + productId + '\'' +
               ", productName='" + productName + '\'' +
               ", quantity=" + quantity +
               ", quantityShipped=" + quantityShipped +
               ", remainingQuantity=" + getRemainingQuantity() +
               ", price=" + price +
               '}';
    }
}

