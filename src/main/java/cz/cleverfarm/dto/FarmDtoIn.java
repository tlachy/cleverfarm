package cz.cleverfarm.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;

import static lombok.AccessLevel.PRIVATE;


@FieldDefaults(level = PRIVATE)
@Getter
@Setter
public class FarmDtoIn {

    @NotBlank
    String name;

    String note;
}
