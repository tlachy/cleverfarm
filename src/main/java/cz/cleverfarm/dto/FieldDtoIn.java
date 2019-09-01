package cz.cleverfarm.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cz.cleverfarm.validator.FieldDtoInConstraint;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.locationtech.jts.geom.Coordinate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toUnmodifiableList;
import static lombok.AccessLevel.PRIVATE;

@FieldDtoInConstraint
@FieldDefaults(level = PRIVATE)
@Getter
@Setter
public class FieldDtoIn {

    @NotBlank
    String name;

    String note;

    @Size(min = 4, max = 10000)
    List<CoordinateDto> fieldPolygonCoordinates;

    @NotNull
    UUID farmUuid;

    @JsonIgnore
    public final Coordinate[] getCoordinateArray() {

        return fieldPolygonCoordinates.stream()
                .map(coordinateDto -> new Coordinate(coordinateDto.getLag(), coordinateDto.getLog()))
                .collect(toUnmodifiableList())
                .toArray(new Coordinate[0]);

    }
}
