package cz.cleverfarm.mapper;

import cz.cleverfarm.dto.FieldDtoIn;
import cz.cleverfarm.dto.FieldDtoOut;
import cz.cleverfarm.entity.Field;
import cz.cleverfarm.service.FarmService;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import static java.util.stream.Collectors.toUnmodifiableList;


@Mapper(componentModel = "spring")
public abstract class FieldMapper {

    @Autowired
    FarmService farmService;

    @Autowired
    GeometryFactory geometryFactory;


    public abstract Field toEntity(FieldDtoIn dto);

    public abstract FieldDtoOut toDto(Field entity);

    @AfterMapping
    protected void mapTitlesAndDescriptions(FieldDtoIn dto, @MappingTarget Field entity) {

        Coordinate[] coordinates = dto.getFieldPolygonCoordinates().stream()
                .map(coordinateDto -> new Coordinate(coordinateDto.getLag(), coordinateDto.getLog()))
                .collect(toUnmodifiableList())
                .toArray(new Coordinate[0]);

        entity.setFarm(farmService.getFarm(dto.getFarmUuid()).get());
        entity.setPolygon(geometryFactory.createPolygon(coordinates));
    }
}
