package group.msg.gbi.supplychain.adapters.database;

import group.msg.gbi.supplychain.datatypes.OrderState;
import group.msg.gbi.supplychain.entities.DeliveryNote;
import group.msg.gbi.supplychain.entities.Invoice;
import group.msg.gbi.supplychain.entities.Order;
import group.msg.gbi.supplychain.entities.Shipment;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.unchecked.Unchecked;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.Tuple;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DatabaseServiceImpl implements DatabaseService {

    final PgPool client; // Der reaktive PostgreSQL-Client


    public DatabaseServiceImpl(PgPool client) {
        this.client = client;
    }


    @Override
    public Uni<Order> saveOrder(Order order) {
        String sql = "INSERT INTO orders (order_id, customer_email, total_value) VALUES ($1, $2, $3) RETURNING order_id";

        return client.preparedQuery(sql)
                .execute(Tuple.of(order.getOrderId(), order.getCustomerEmail(), order.getTotalValue()))
                .onItem().transform(Unchecked.function(rowSet -> {
                    if (rowSet.rowCount() > 0) {
                        return order; // Rückgabe des gespeicherten Objekts
                    } else {
                        throw new RuntimeException("Fehler beim Speichern der Bestellung");
                    }
                }));
    }


    @Override
    public Uni<Order> updateOrderState(String orderId, String state) {
        String sql = "UPDATE orders SET status = $1 WHERE order_id = $2 RETURNING order_id, state";

        return client.preparedQuery(sql)
                .execute(Tuple.of(state, orderId))
                .onItem().transform(Unchecked.function(rowSet -> {
                    if (rowSet.rowCount() > 0) {
                        Row row = rowSet.iterator().next();
                        Order updatedOrder = new Order();
                        updatedOrder.setOrderId(row.getString("order_id"));
                        updatedOrder.setState(OrderState.valueOf(row.getString("status")));
                        return updatedOrder;
                    } else {
                        throw new RuntimeException("Fehler beim Aktualisieren des Auftragsstatus");
                    }
                }));
    }


    @Override
    public Uni<Invoice> saveInvoice(Invoice invoice) {
        String sql = "INSERT INTO invoices (invoice_id, order_id) VALUES ($1, $2) RETURNING invoice_id";

        return client.preparedQuery(sql)
                .execute(Tuple.of(invoice.getInvoiceId(), invoice.getOrder().getOrderId()))
                .onItem().transform(Unchecked.function(rowSet -> {
                    if (rowSet.rowCount() > 0) {
                        return invoice; // Rückgabe des gespeicherten Objekts
                    } else {
                        throw new RuntimeException("Fehler beim Speichern der Rechnung");
                    }
                }));
    }


    @Override
    public Uni<DeliveryNote> saveDeliveryNote(DeliveryNote deliveryNote) {
        String sql = "INSERT INTO delivery_notes (delivery_note_id, invoice_id) VALUES ($1, $2) RETURNING delivery_note_id";

        return client.preparedQuery(sql)
                .execute(Tuple.of(deliveryNote.getDeliveryNoteId(), deliveryNote.getInvoice().getInvoiceId()))
                .onItem().transform(Unchecked.function(rowSet -> {
                    if (rowSet.rowCount() > 0) {
                        return deliveryNote; // Rückgabe des gespeicherten Objekts
                    } else {
                        throw new RuntimeException("Fehler beim Speichern des Lieferscheins");
                    }
                }));
    }


    @Override
    public Uni<Shipment> saveShipment(Shipment shipment) {
        String sql = "INSERT INTO shipments (shipment_id, delivery_note_id, order_id) VALUES ($1, $2, $3, $4) RETURNING shipment_id";

        return client.preparedQuery(sql)
                .execute(Tuple.of(shipment.getShipmentId(), shipment.getDeliveryNote().getDeliveryNoteId(),
                                  shipment.getOrder().getOrderId()))
                .onItem().transform(Unchecked.function(rowSet -> {
                    if (rowSet.rowCount() > 0) {
                        return shipment; // Rückgabe des gespeicherten Objekts
                    } else {
                        throw new RuntimeException("Fehler beim Speichern der Lieferung");
                    }
                }));
    }
}
