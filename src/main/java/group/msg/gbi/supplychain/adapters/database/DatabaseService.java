package group.msg.gbi.supplychain.adapters.database;

import group.msg.gbi.supplychain.entities.Order;
import group.msg.gbi.supplychain.entities.Payment;
import group.msg.gbi.supplychain.entities.ReceiptConfirmation;
import io.smallrye.mutiny.Uni;

public interface DatabaseService {
    Uni<Order> saveOrder(Order order);


    Uni<Payment> savePayment(ReceiptConfirmation receiptConfirmation);
}
