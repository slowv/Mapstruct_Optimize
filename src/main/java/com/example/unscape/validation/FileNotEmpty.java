package com.example.unscape.validation;

import com.example.unscape.validation.handle.FileNotEmptyHandle;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = FileNotEmptyHandle.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FileNotEmpty {
    String message() default "File must not be null or empty";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
