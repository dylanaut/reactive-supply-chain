package group.msg.gbi.supplychain.controllers;

import java.time.Instant;

// Response-DTO für das Erstellen eines Shipments
public class CreateShipmentResponse {
    private String shipmentId;
    private Instant shipmentDate;


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
}
