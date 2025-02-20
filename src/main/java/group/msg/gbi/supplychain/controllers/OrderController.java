package group.msg.gbi.supplychain.controllers;

import group.msg.gbi.supplychain.entities.Order;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/api/orders")
@Produces("application/json")
@Consumes("application/json")
public class OrderController {

    @GET
    public List<Order> getAllOrders(@QueryParam("status") String status) {
        // Optional: Filter nach Status
        return Order.listAll(); // Panache-Methode
    }

    @GET
    @Path("/{orderId}")
    public Order getOrderById(@PathParam("orderId") String orderId) {
        return Order.findById(orderId); // Panache-Methode
    }

    @POST
    public Response createOrder(Order order) {
        order.persist(); // Panache persist
        return Response.status(Response.Status.CREATED).entity(order).build();
    }

    @PUT
    @Path("/{orderId}/confirm")
    public Response confirmOrder(@PathParam("orderId") String orderId) {
        Order order = Order.findById(orderId);
        if (order == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        order.confirm();
        return Response.ok(order).build();
    }

    @PUT
    @Path("/{orderId}/cancel")
    public Response cancelOrder(@PathParam("orderId") String orderId) {
        Order order = Order.findById(orderId);
        if (order == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        order.cancel();
        return Response.ok(order).build();
    }
}
