package group.msg.gbi.supplychain.services;

import group.msg.gbi.supplychain.entities.Shipment;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class ShipmentService {
    OrderService orderService;

    public Shipment createShipment(Shipment shipment) {
        // Geschäftslogik (z. B. Validierungen)
        if (shipment.getOrder() == null || shipment.getDeliveryNote() == null) {
            throw new IllegalArgumentException("Order ID and Delivery Note ID must not be null");
        }

        // Persistieren der Entität mit Panache
        orderService.shipOrder(shipment.getOrder());
        shipment.persist();
        return shipment;
    }
}
