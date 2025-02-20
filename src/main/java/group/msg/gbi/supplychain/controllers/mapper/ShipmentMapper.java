package group.msg.gbi.supplychain.controllers.mapper;

import group.msg.gbi.supplychain.entities.Shipment;
import group.msg.gbi.supplychain.controllers.CreateShipmentRequest;
import group.msg.gbi.supplychain.controllers.CreateShipmentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "jakarta")
public interface ShipmentMapper extends RequRespMapper<Shipment, CreateShipmentRequest, CreateShipmentResponse> {

    @Mapping(target = "shipmentId", ignore = true)
        // ID wird vom System generiert
    Shipment toEntity(CreateShipmentRequest request);


    CreateShipmentResponse toResponse(Shipment shipment);
}
