package group.msg.gbi.supplychain.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class ReceiptConfirmation extends PanacheEntityBase {
    @Id
    private String confirmationId;

    @OneToOne
    private Shipment shipment;


    // Konstruktor
    public ReceiptConfirmation(Shipment shipment) {
        this.shipment = shipment;
        this.confirmationId = "CONF-" + System.currentTimeMillis();
    }


    public ReceiptConfirmation() {

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

