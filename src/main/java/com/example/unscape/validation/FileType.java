package com.example.unscape.validation;

import com.example.unscape.validation.handle.FileTypeHandle;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = FileTypeHandle.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FileType {
    String message() default "Invalid file type";

    String[] contentType();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
