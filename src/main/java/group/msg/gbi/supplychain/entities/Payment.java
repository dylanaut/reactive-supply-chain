package group.msg.gbi.supplychain.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import jakarta.persistence.Id;

public class Payment extends PanacheEntity {
    @Id
    private String paymentId;
    private ReceiptConfirmation receiptConfirmation;

    // Konstruktor
    public Payment(ReceiptConfirmation receiptConfirmation) {
        this.receiptConfirmation = receiptConfirmation;
        this.paymentId = "PAY-" + System.currentTimeMillis();
    }

    // Getter und Setter
    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public ReceiptConfirmation getReceiptConfirmation() {
        return receiptConfirmation;
    }

    public void setReceiptConfirmation(ReceiptConfirmation receiptConfirmation) {
        this.receiptConfirmation = receiptConfirmation;
    }
}

