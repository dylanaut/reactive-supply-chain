package group.msg.gbi.supplychain.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "invoices")
public class Invoice extends PanacheEntityBase {
    @Id
    private String invoiceId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id")
    private Order order;


    // Konstruktor
    public Invoice(Order order) {
        this.order = order;
        this.invoiceId = "INV-" + System.currentTimeMillis(); // Beispiel für eine einfache ID-Generierung
    }


    public Invoice() {
        // no args
    }


    // Getter und Setter
    public String getInvoiceId() {
        return invoiceId;
    }


    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }


    public Order getOrder() {
        return order;
    }


    public void setOrder(Order order) {
        this.order = order;
    }
}

