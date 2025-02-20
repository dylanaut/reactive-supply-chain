package group.msg.gbi.supplychain.controllers;

import group.msg.gbi.supplychain.entities.Invoice;

import java.util.List;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("/api/invoices")
@Produces("application/json")
@Consumes("application/json")
public class InvoiceController {

    @POST
    public Response createInvoice(Invoice invoice) {
        invoice.persist(); // Panache persist
        return Response.status(Response.Status.CREATED).entity(invoice).build();
    }

    @GET
    @Path("/{invoiceId}")
    public Invoice getInvoiceById(@PathParam("invoiceId") String invoiceId) {
        return Invoice.findById(invoiceId); // Panache findByID
    }

    @GET
    public List<Invoice> getAllInvoices() {
        return Invoice.listAll(); // Panache listAll
    }
}
