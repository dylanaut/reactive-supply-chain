package group.msg.gbi.supplychain.entities;

import group.msg.gbi.supplychain.datatypes.OrderState;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order extends PanacheEntityBase {
    @Id
    private String orderId;

    private String customerEmail;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order")
    private List<OrderItem> orderItems;

    private double totalValue;

    private double itemDiscount;

    private double orderDiscount;

    private OrderState state;


    public Order() {
        this.state = OrderState.CREATED;
        this.orderId = "ORD-" + System.currentTimeMillis(); // Beispiel für eine einfache ID-Generierung
    }


    // Getter und Setter
    public String getOrderId() {
        return orderId;
    }


    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }


    public String getCustomerEmail() {
        return customerEmail;
    }


    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }


    public List<OrderItem> getOrderItems() {
        return orderItems;
    }


    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }


    public double getTotalValue() {
        return totalValue;
    }


    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }


    public double getItemDiscount() {
        return itemDiscount;
    }


    public void setItemDiscount(double itemDiscount) {
        this.itemDiscount = itemDiscount;
    }


    public double getOrderDiscount() {
        return orderDiscount;
    }


    public void setOrderDiscount(double orderDiscount) {
        this.orderDiscount = orderDiscount;
    }


    public void setState(OrderState state) {
        this.state = state;
    }


    public OrderState getState() {
        return state;
    }

    // ----------------------------------------------------------------------------------
    // business state transitions
    // ----------------------------------------------------------------------------------


    public void confirm() {
        if (state == OrderState.CREATED) {
            state = OrderState.CONFIRMED;
        } else {
            throw new IllegalStateException("Order cannot be confirmed from state: " + state);
        }
    }


    public void process() {
        if (state == OrderState.CONFIRMED) {
            state = OrderState.PROCESSING;
        } else {
            throw new IllegalStateException("Order cannot be processed from state: " + state);
        }
    }


    public void readyForShipment() {
        if (state == OrderState.PROCESSING) {
            state = OrderState.READY_FOR_SHIPMENT;
        } else {
            throw new IllegalStateException("Order cannot be marked ready for shipment from state: " + state);
        }
    }


    public void ship() {
        if (state == OrderState.READY_FOR_SHIPMENT) {
            state = OrderState.SHIPPED;
        } else {
            throw new IllegalStateException("Order cannot be shipped from state: " + state);
        }
    }


    public void complete() {
        if (state == OrderState.SHIPPED) {
            state = OrderState.COMPLETED;
        } else {
            throw new IllegalStateException("Order cannot be completed from state: " + state);
        }
    }


    public void cancel() {
        if (state != OrderState.COMPLETED && state != OrderState.CANCELLED) {
            state = OrderState.CANCELLED;
        } else {
            throw new IllegalStateException("Order cannot be cancelled from state: " + state);
        }
    }


    public void markAsProblematic() {
        if (state != OrderState.COMPLETED && state != OrderState.CANCELLED) {
            state = OrderState.PROBLEMATIC;
        } else {
            throw new IllegalStateException("Order cannot be marked as problematic from state: " + state);
        }
    }
}

