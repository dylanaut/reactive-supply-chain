package group.msg.gbi.supplychain.ports.erp;

import group.msg.gbi.supplychain.entities.OrderItem;
import io.smallrye.mutiny.Uni;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped

public class ERPServiceImp implements ERPService {

    public Uni<List<OrderItem>> checkStock(List<OrderItem> orderItems) {
        System.out.println("ERPServiceImp: CheckStock");
        return Uni.createFrom().item(orderItems);
    }
}
