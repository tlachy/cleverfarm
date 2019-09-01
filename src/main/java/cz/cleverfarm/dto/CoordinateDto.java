package cz.cleverfarm.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;

import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(level = PRIVATE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CoordinateDto {

    @NotNull
    Double lag;

    @NotNull
    Double log;
}
