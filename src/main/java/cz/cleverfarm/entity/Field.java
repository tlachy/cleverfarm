package cz.cleverfarm.entity;


import lombok.*;
import lombok.experimental.FieldDefaults;
import org.locationtech.jts.geom.Polygon;

import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = PRIVATE)
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
public class Field {

    Long id;

    UUID uuid;

    String name;

    String note;

    Polygon polygon;

    Farm farm;

    public double getArea(){
        return polygon.getArea();
    }
}
