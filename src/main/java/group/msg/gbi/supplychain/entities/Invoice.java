package group.msg.gbi.supplychain.entities;

public class Invoice {
    private String invoiceId;
    private Order order;

    // Konstruktor
    public Invoice(Order order) {
        this.order = order;
        this.invoiceId = "INV-" + System.currentTimeMillis(); // Beispiel für eine einfache ID-Generierung
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

