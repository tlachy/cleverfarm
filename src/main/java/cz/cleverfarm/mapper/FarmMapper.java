package cz.cleverfarm.mapper;

import cz.cleverfarm.dto.FarmDtoIn;
import cz.cleverfarm.dto.FarmDtoOut;
import cz.cleverfarm.entity.Farm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class FarmMapper {

    public abstract Farm toEntity(FarmDtoIn dto);

    public abstract FarmDtoOut toDto(Farm entity);
}
