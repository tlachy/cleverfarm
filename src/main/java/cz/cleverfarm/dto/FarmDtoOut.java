package cz.cleverfarm.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(level = PRIVATE)
@Getter
@Setter
public class FarmDtoOut {

    @NotNull
    UUID uuid;

    @NotBlank
    String name;

    String note;
}
