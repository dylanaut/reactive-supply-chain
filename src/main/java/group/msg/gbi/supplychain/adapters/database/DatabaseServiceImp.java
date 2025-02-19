package group.msg.gbi.supplychain.adapters.database;

import group.msg.gbi.supplychain.entities.Order;
import group.msg.gbi.supplychain.entities.Payment;
import group.msg.gbi.supplychain.entities.ReceiptConfirmation;
import io.smallrye.mutiny.Uni;

public class DatabaseServiceImp implements DatabaseService {

    public Uni<Order> saveOrder(Order order) {
        System.out.println("DatenbankServiceImp: SaveOrder");
        Order.persist(order);
        return Uni.createFrom().item(order);
    }


    public Uni<Payment> savePayment(ReceiptConfirmation receiptConfirmation) {
        System.out.println("DatenbankServiceImp: savePayment");
        var payment = new Payment(receiptConfirmation);
        Payment.persist(payment);
        return Uni.createFrom().item(payment);
    }
}
