package com.example.unscape.validation;

import com.example.unscape.validation.handle.FileSizeHandle;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = FileSizeHandle.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FileSize {
    String message() default "File too large";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
