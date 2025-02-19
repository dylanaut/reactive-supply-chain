package group.msg.gbi.supplychain.ports.erp;

import group.msg.gbi.supplychain.entities.OrderItem;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import java.util.List;

public interface ERPService {
    Uni<List<OrderItem>> checkStock(List<OrderItem> orderItems);


    Multi<Object> checkStock(List<OrderItem> orderItems);
}
