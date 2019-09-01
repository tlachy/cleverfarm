package cz.cleverfarm.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.util.UUID.randomUUID;
import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = PRIVATE)
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
public class Farm {

    Long id;

    UUID uuid;

    String name;

    String note;

    List<Field> fields = new ArrayList<>();
}
