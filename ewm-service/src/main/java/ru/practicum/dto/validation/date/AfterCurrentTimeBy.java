package ru.practicum.dto.validation.date;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Constraint(validatedBy = AfterCurrentTimeByConstraint.class)
public @interface AfterCurrentTimeBy {
    int hours();
    String message() default "must be any of enum {enum}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
