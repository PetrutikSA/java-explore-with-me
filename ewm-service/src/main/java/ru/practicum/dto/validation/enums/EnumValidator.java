package ru.practicum.dto.validation.enums;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotNull;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = EnumValidatorConstraint.class)
@NotNull
public @interface EnumValidator {
    Class<? extends Enum<?>> enumClazz();
    String message() default "must be any of enum {enum}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
