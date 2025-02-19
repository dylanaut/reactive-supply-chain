package group.msg.gbi.supplychain.dtos;

import group.msg.gbi.supplychain.entities.Invoice;
import group.msg.gbi.supplychain.entities.OrderItem;

import java.util.List;

public class DeliveryNote {
    private String deliveryNoteId;
    private Invoice invoice;

    private List<OrderItem> orderItems;


    // Konstruktor
    public DeliveryNote(Invoice invoice) {
        this.invoice = invoice;
        this.deliveryNoteId = "DN-" + System.currentTimeMillis(); // Beispiel für eine einfache ID-Generierung
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

