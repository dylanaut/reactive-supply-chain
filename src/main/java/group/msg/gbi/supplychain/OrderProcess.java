package group.msg.gbi.supplychain;

import group.msg.gbi.supplychain.adapters.database.DatabaseService;
import group.msg.gbi.supplychain.adapters.database.DatabaseServiceImp;
import group.msg.gbi.supplychain.dtos.DeliveryNote;
import group.msg.gbi.supplychain.entities.Invoice;
import group.msg.gbi.supplychain.entities.Order;
import group.msg.gbi.supplychain.entities.OrderItem;
import group.msg.gbi.supplychain.entities.Payment;
import group.msg.gbi.supplychain.entities.ReceiptConfirmation;
import group.msg.gbi.supplychain.entities.Shipment;
import group.msg.gbi.supplychain.ports.erp.ERPService;
import group.msg.gbi.supplychain.ports.erp.ERPServiceImp;
import group.msg.gbi.supplychain.ports.mail.EmailService;
import group.msg.gbi.supplychain.ports.mail.EmailServiceImp;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import java.util.ArrayList;
import java.util.List;

public class OrderProcess {

    private final DatabaseService databaseService;

    private final ERPService erpService;

    private final EmailService emailService;


    public OrderProcess(DatabaseService databaseService, ERPService erpService, EmailService emailService) {
        this.databaseService = databaseService;
        this.erpService = erpService;
        this.emailService = emailService;
    }


    public Uni<Order> placeOrder(Order order) {
        return Uni.createFrom().item(order);
    }


    public Uni<Order> captureOrder(Order order) {
        return Uni.createFrom().item(order)
                .onItem().transformToUni(this::calculateDiscounts)
                .onItem().transformToUni(databaseService::saveOrder); // Speichern in der Datenbank
    }


    public Uni<Order> calculateDiscounts(Order order) {
        double orderTotal = order.getTotalValue();
        double itemDiscount = calculateItemDiscount(order.getOrderItems());
        double orderDiscount = calculateOrderDiscount(orderTotal);
        order.setItemDiscount(itemDiscount);
        order.setOrderDiscount(orderDiscount);
        order.setTotalValue(orderTotal - itemDiscount - orderDiscount);
        return Uni.createFrom().item(order);
    }


    private double calculateItemDiscount(List<OrderItem> orderItems) {
        double totalDiscount = 0;
        for (OrderItem item : orderItems) {
            double itemTotal = item.getPrice() * item.getQuantity();
            double discountRate = 0;
            if (itemTotal >= 100000) {
                discountRate = 0.04;
            } else if (itemTotal >= 10000) {
                discountRate = 0.03;
            } else if (itemTotal >= 1000) {
                discountRate = 0.02;
            }
            totalDiscount += itemTotal * discountRate;
        }
        return totalDiscount;
    }


    private double calculateOrderDiscount(double orderTotal) {
        double discountRate = 0;
        if (orderTotal >= 100000) {
            discountRate = 0.01;
        } else if (orderTotal >= 10000) {
            discountRate = 0.0075;
        } else if (orderTotal >= 1000) {
            discountRate = 0.005;
        }
        return orderTotal * discountRate;
    }


    public Uni<Invoice> createInvoice(Order order) {
        return Uni.createFrom().item(new Invoice(order));
    }


    public Uni<DeliveryNote> createDeliveryNote(Invoice invoice) {
        return Uni.createFrom().item(new DeliveryNote(invoice));
    }


    public Uni<List<Shipment>> prepareShipment(DeliveryNote deliveryNote) {
        // Integration mit dem ERP-System, um den Lagerbestand zu prüfen
        return erpService.checkStock(deliveryNote.getOrderItems())
                .onItem().transformToMulti(this::splitIntoPartialShipments) // Aufteilen in Teillieferungen, falls nötig
                .collect().asList(); // Sammeln der Teillieferungen in einer Liste
    }


    private Multi<Shipment> splitIntoPartialShipments(List<OrderItem> availableItems) {
        List<Shipment> shipments = new ArrayList<>();
        // Logik zur Aufteilung in Teillieferungen basierend auf dem Lagerbestand
        // Erstellt mehrere Shipment-Objekte
        return Multi.createFrom().iterable(shipments);
    }


    public Uni<Void> sendShipmentNotification(Shipment shipment) {
        // Senden einer E-Mail-Benachrichtigung mit dem Lieferschein
        return emailService.sendEmail(shipment.getCustomerEmail(), "Shipment Notification", shipment.getDeliveryNote().toString());
    }


    public Uni<Shipment> dispatchShipment(Shipment shipment) {
        // Asynchrone Operation für den Versand
        return Uni.createFrom().item(shipment);
    }


    public Uni<ReceiptConfirmation> confirmReceipt(Shipment shipment) {
        return Uni.createFrom().item(new ReceiptConfirmation(shipment));
    }


    public Uni<Payment> recordPayment(ReceiptConfirmation receiptConfirmation) {
        return Uni.createFrom().item(receiptConfirmation)
                .onItem().transformToUni(databaseService::savePayment); // Speichern der Zahlung in der Datenbank
    }


    public static void main(String[] args) {
        // Initialisierung der Services (DatabaseService, ERPService, EmailService)
        DatabaseService databaseService = new DatabaseServiceImp();
        ERPService erpService = new ERPServiceImp();
        EmailService emailService = new EmailServiceImp();

        OrderProcess orderProcess = new OrderProcess(databaseService, erpService, emailService);
        Order order = new Order();
        order.setCustomerEmail("customer@example.com");
        // Annahme: Gesamtwert der Bestellung beträgt 120.000 Euro
        order.setTotalValue(120000);
        List<OrderItem> orderItems = new ArrayList<>();
        order.setOrderItems(orderItems);
        OrderItem orderItem = new OrderItem();
        orderItem.setPrice(100);
        orderItem.setQuantity(50);
        orderItems.add(orderItem);

        orderProcess.placeOrder(order)
                .onItem().transformToUni(orderProcess::captureOrder)
                .onItem().transformToUni(orderProcess::createInvoice)
                .onItem().transformToUni(orderProcess::createDeliveryNote)
                .onItem().transformToMulti(orderProcess::prepareShipment)
                .onItem().transformToUni(orderProcess::sendShipmentNotification)
                .onItem().transformToUni(orderProcess::dispatchShipment)
                .onItem().transformToUni(orderProcess::confirmReceipt)
                .onItem().transformToUni(orderProcess::recordPayment)
                .subscribe().with(
                        payment -> System.out.println("Zahlung erfasst: " + payment),
                        failure -> System.err.println("Fehler im Bestellprozess: " + failure)
                );
    }
}

