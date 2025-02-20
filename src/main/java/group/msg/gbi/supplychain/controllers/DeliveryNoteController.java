package group.msg.gbi.supplychain.controllers;

import group.msg.gbi.supplychain.entities.DeliveryNote;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("/api/delivery-notes")
@Produces("application/json")
@Consumes("application/json")
public class DeliveryNoteController {

    @POST
    public Response createDeliveryNote(DeliveryNote deliveryNote) {
        deliveryNote.persist(); // Panache persist
        return Response.status(Response.Status.CREATED).entity(deliveryNote).build();
    }

    @GET
    @Path("/{deliveryNoteId}")
    public DeliveryNote getDeliveryNoteById(@PathParam("deliveryNoteId") String deliveryNoteId) {
        return DeliveryNote.findById(deliveryNoteId); // Panache findByID
    }
}
