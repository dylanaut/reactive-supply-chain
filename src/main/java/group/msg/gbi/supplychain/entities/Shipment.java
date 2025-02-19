package group.msg.gbi.supplychain.entities;

import group.msg.gbi.supplychain.dtos.DeliveryNote;

public class Shipment {
    private String shipmentId;         // ID der Lieferung
    private DeliveryNote deliveryNote; // Lieferschein der Lieferung
    private Order order;               // Referenz auf die Bestellung

    // Konstruktor
    public Shipment(DeliveryNote deliveryNote, Order order) {
        this.deliveryNote = deliveryNote;
        this.order = order;
        this.shipmentId = "SHIP-" + System.currentTimeMillis(); // Beispiel für eine einfache ID-Generierung
    }

    // Getter und Setter
    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
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
