package group.msg.gbi.supplychain.controllers;

import group.msg.gbi.supplychain.entities.Shipment;
import group.msg.gbi.supplychain.controllers.mapper.ShipmentMapper;
import group.msg.gbi.supplychain.services.ShipmentService;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("/api/shipments")
@Produces("application/json")
@Consumes("application/json")
public class ShipmentController {

    ShipmentService shipmentService;

    ShipmentMapper shipmentMapper;


    public ShipmentController(ShipmentService shipmentService, ShipmentMapper shipmentMapper) {
        this.shipmentService = shipmentService;
        this.shipmentMapper = shipmentMapper;
    }


    @POST
    public Response createShipment(CreateShipmentRequest request) {
        // Mapping von DTO zu Domain-Objekt
        Shipment shipment = shipmentMapper.toEntity(request);

        // Persistieren der Entität (Geschäftslogik)
        Shipment createdShipment = shipmentService.createShipment(shipment);

        // Mapping von Domain-Objekt zu Response-DTO
        CreateShipmentResponse response = shipmentMapper.toResponse(createdShipment);

        return Response.status(Response.Status.CREATED).entity(response).build();
    }
}
