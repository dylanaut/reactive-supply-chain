package group.msg.gbi.supplychain.controllers.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

/**
 * Contract for a generic dto to entity mapper.
 *
 * @param <RQ> - request type parameter.
 * @param <RP> - response type parameter.
 * @param <E> - Entity type parameter.
 */

public interface RequRespMapper<E, RQ, RP> {
    E toEntity(RQ request);


    List<E> toEntity(List<RQ> rqList);


    RP toResponse(E entity);


    List<RP> toResponse(List<E> entityList);


    @Named("partialUpdate")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget E entity, RQ request);
}
