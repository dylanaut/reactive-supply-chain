package group.msg.gbi.supplychain.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "delivery_notes")
public class DeliveryNote extends PanacheEntityBase {
    @Id
    private String deliveryNoteId;

    @OneToOne
    private Invoice invoice;

    @OneToMany
    private List<OrderItem> orderItems;


    // Konstruktor
    public DeliveryNote(Invoice invoice) {
        this.invoice = invoice;
        this.deliveryNoteId = "DN-" + System.currentTimeMillis(); // Beispiel für eine einfache ID-Generierung
    }


    public DeliveryNote() {
        // do nothing
    }


    // Getter und Setter
    public String getDeliveryNoteId() {
        return deliveryNoteId;
    }


    public void setDeliveryNoteId(String deliveryNoteId) {
        this.deliveryNoteId = deliveryNoteId;
    }


    public Invoice getInvoice() {
        return invoice;
    }


    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }


    public List<OrderItem> getOrderItems() {
        return orderItems;
    }


    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}

