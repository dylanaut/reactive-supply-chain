package group.msg.gbi.supplychain.adapters.database;

import group.msg.gbi.supplychain.entities.DeliveryNote;
import group.msg.gbi.supplychain.entities.Invoice;
import group.msg.gbi.supplychain.entities.Order;
import group.msg.gbi.supplychain.entities.Shipment;
import io.smallrye.mutiny.Uni;

public interface DatabaseService {

    Uni<Order> saveOrder(Order order);


    Uni<Order> updateOrderState(String orderId, String status);


    Uni<Invoice> saveInvoice(Invoice invoice);


    Uni<DeliveryNote> saveDeliveryNote(DeliveryNote deliveryNote);


    Uni<Shipment> saveShipment(Shipment shipment);
}
