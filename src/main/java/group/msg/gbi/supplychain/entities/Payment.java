package group.msg.gbi.supplychain.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "payments")
public class Payment extends PanacheEntityBase {
    @Id
    private String paymentId;
    @OneToOne
    private ReceiptConfirmation receiptConfirmation;

    // Konstruktor
    public Payment(ReceiptConfirmation receiptConfirmation) {
        this.receiptConfirmation = receiptConfirmation;
        this.paymentId = "PAY-" + System.currentTimeMillis();
    }


    public Payment() {
        // no args
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

