package cz.cleverfarm.entity;


import lombok.*;
import lombok.experimental.FieldDefaults;
import org.opengis.geometry.coordinate.Polygon;

import java.util.UUID;

import static java.util.UUID.randomUUID;
import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = PRIVATE)
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
public class Field {

    UUID uuid = randomUUID();

    String name;

    String note;

    Polygon polygon;

    Farm job;
}
