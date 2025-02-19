package group.msg.gbi.supplychain.entities;

public class ReceiptConfirmation {
    private String confirmationId;
    private Shipment shipment;

    // Konstruktor
    public ReceiptConfirmation(Shipment shipment) {
        this.shipment = shipment;
        this.confirmationId = "CONF-" + System.currentTimeMillis();
    }

    // Getter und Setter
    public String getConfirmationId() {
        return confirmationId;
    }

    public void setConfirmationId(String confirmationId) {
        this.confirmationId = confirmationId;
    }

    public Shipment getShipment() {
        return shipment;
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }
}

