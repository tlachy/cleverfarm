package cz.cleverfarm.validator;

import cz.cleverfarm.dto.FieldDtoIn;
import cz.cleverfarm.entity.Farm;
import cz.cleverfarm.service.FarmService;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Polygon;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Optional;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static lombok.AccessLevel.PRIVATE;

@Constraint(validatedBy = FieldDtoValidator.class)
@Documented
@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
public @interface FieldDtoInConstraint {

    String message() default "Field details are not valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
class FieldDtoValidator implements ConstraintValidator<FieldDtoInConstraint, FieldDtoIn> {

    FarmService farmService;
    GeometryFactory geometryFactory;

    @Override
    public boolean isValid(FieldDtoIn fieldDtoIn, ConstraintValidatorContext context) {

        Optional<Farm> farm = farmService.getFarm(fieldDtoIn.getFarmUuid());
        Polygon polygon = geometryFactory.createPolygon(fieldDtoIn.getCoordinateArray());

        if (farm.isEmpty()) {
            return false;
        }
        if (farmService.isInCzechRepublic(polygon)) {
            return false;
        }
        if (farmService.overlapsOtherField(polygon)) {
            return false;
        }
        return true;
    }
}