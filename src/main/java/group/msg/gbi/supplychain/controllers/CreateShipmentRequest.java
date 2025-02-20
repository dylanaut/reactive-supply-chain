package group.msg.gbi.supplychain.controllers;

// Request-DTO für das Erstellen eines Shipments
public class CreateShipmentRequest {
    private String orderId;
    private String deliveryNoteId;

    // Getter und Setter
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getDeliveryNoteId() {
        return deliveryNoteId;
    }

    public void setDeliveryNoteId(String deliveryNoteId) {
        this.deliveryNoteId = deliveryNoteId;
    }
}

