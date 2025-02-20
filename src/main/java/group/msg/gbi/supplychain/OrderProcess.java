package group.msg.gbi.supplychain;

import group.msg.gbi.supplychain.adapters.database.DatabaseService;
import group.msg.gbi.supplychain.entities.DeliveryNote;
import group.msg.gbi.supplychain.entities.Invoice;
import group.msg.gbi.supplychain.entities.Order;
import group.msg.gbi.supplychain.entities.Shipment;
import group.msg.gbi.supplychain.ports.mail.EmailService;
import io.smallrye.mutiny.Uni;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OrderProcess {

    private final DatabaseService databaseService;
    private final EmailService emailService;

    public OrderProcess(DatabaseService databaseService, EmailService emailService) {
        this.databaseService = databaseService;
        this.emailService = emailService;
    }

    public Uni<Void> processOrder(Order order) {
        return placeOrder(order) // Schritt 1: Bestellung erstellen
                .onItem().transformToUni(this::captureOrder) // Schritt 2: Auftrag erfassen
                .onItem().transformToUni(this::createInvoice) // Schritt 3: Rechnung erstellen
                .onItem().transformToUni(this::createDeliveryNote) // Schritt 4: Lieferschein erstellen
                .onItem().transformToUni(this::prepareShipment) // Schritt 5: Versand vorbereiten
                .onItem().transformToUni(this::sendShipmentNotification); // Schritt 6: Versandbenachrichtigung senden
    }

    private Uni<Order> placeOrder(Order order) {
        return databaseService.saveOrder(order)
                .onItem().invoke(savedOrder -> System.out.println("Bestellung gespeichert: " + savedOrder.getOrderId()));
    }

    private Uni<Order> captureOrder(Order order) {
        return databaseService.updateOrderState(order.getOrderId(), "CAPTURED")
                .onItem().invoke(updatedOrder -> System.out.println("Auftrag erfasst: " + updatedOrder.getOrderId()));
    }

    private Uni<Invoice> createInvoice(Order order) {
        Invoice invoice = new Invoice(order);
        return databaseService.saveInvoice(invoice)
                .onItem().invoke(savedInvoice -> System.out.println("Rechnung erstellt: " + savedInvoice.getInvoiceId()));
    }

    private Uni<DeliveryNote> createDeliveryNote(Invoice invoice) {
        DeliveryNote deliveryNote = new DeliveryNote(invoice);
        return databaseService.saveDeliveryNote(deliveryNote)
                .onItem().invoke(savedDeliveryNote -> System.out.println("Lieferschein erstellt: " + savedDeliveryNote.getDeliveryNoteId()));
    }

    private Uni<Shipment> prepareShipment(DeliveryNote deliveryNote) {
        Shipment shipment = new Shipment(deliveryNote, deliveryNote.getInvoice().getOrder());
        return databaseService.saveShipment(shipment)
                .onItem().invoke(savedShipment -> System.out.println("Versand vorbereitet: " + savedShipment.getShipmentId()));
    }

    private Uni<Void> sendShipmentNotification(Shipment shipment) {
        return emailService.sendEmail(
                        shipment.getCustomerEmail(),
                        "Ihre Lieferung ist unterwegs!",
                        "Ihr Paket mit der ID " + shipment.getShipmentId() + " wurde versandt.")
                .onItem().invoke(() -> System.out.println("Versandbenachrichtigung gesendet an: " + shipment.getCustomerEmail()));
    }
}
