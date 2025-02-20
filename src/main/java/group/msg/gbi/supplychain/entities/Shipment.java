package group.msg.gbi.supplychain.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "shipments")
public class Shipment extends PanacheEntityBase {
    @Id
    private String shipmentId;         // ID der Lieferung

    private Instant shipmentDate;

    @Transient
    private DeliveryNote deliveryNote; // Lieferschein der Lieferung

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;               // Referenz auf die Bestellung


    // Konstruktor
    public Shipment(DeliveryNote deliveryNote, Order order) {
        this.deliveryNote = deliveryNote;
        this.order = order;
        this.shipmentId = "SHIP-" + System.currentTimeMillis(); // Beispiel für eine einfache ID-Generierung
    }


    public Shipment() {
        // no args
    }


    // Getter und Setter
    public String getShipmentId() {
        return shipmentId;
    }


    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }


    public Instant getShipmentDate() {
        return shipmentDate;
    }


    public void setShipmentDate(Instant shipmentDate) {
        this.shipmentDate = shipmentDate;
    }


    public DeliveryNote getDeliveryNote() {
        return deliveryNote;
    }


    public void setDeliveryNote(DeliveryNote deliveryNote) {
        this.deliveryNote = deliveryNote;
    }


    public Order getOrder() {
        return order;
    }


    public void setOrder(Order order) {
        this.order = order;
    }


    // Hilfsmethode: Zugriff auf die E-Mail-Adresse des Kunden
    public String getCustomerEmail() {
        return order != null ? order.getCustomerEmail() : null;
    }


    @Override
    public String toString() {
        return "Shipment{" +
               "shipmentId='" + shipmentId + '\'' +
               ", deliveryNote=" + deliveryNote +
               ", customerEmail=" + getCustomerEmail() +
               '}';
    }
}
