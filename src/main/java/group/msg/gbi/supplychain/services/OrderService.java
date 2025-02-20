package group.msg.gbi.supplychain.services;

import group.msg.gbi.supplychain.entities.Order;

import java.util.Objects;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class OrderService {
    public void confirmOrder(Order order) {
        Objects.requireNonNull(order);

        order.confirm();
        order.persist();
    }


    public void processOrder(Order order) {
        Objects.requireNonNull(order);

        order.process();
        order.persist();
    }


    public void readyForShipmentOrder(Order order) {
        Objects.requireNonNull(order);

        order.readyForShipment();
        order.persist();
    }


    public void shipOrder(Order order) {
        Objects.requireNonNull(order);

        order.ship();
        order.persist();
    }


    public void completeOrder(Order order) {
        Objects.requireNonNull(order);

        order.complete();
        order.persist();
    }


    public void cancelOrder(Order order) {
        Objects.requireNonNull(order);

        order.cancel();
        order.persist();
    }


    public void markAsProblematicOrder(Order order) {
        Objects.requireNonNull(order);

        order.markAsProblematic();
        order.persist();
    }
}
