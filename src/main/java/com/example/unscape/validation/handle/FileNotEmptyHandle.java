package com.example.unscape.validation.handle;

import com.example.unscape.validation.FileNotEmpty;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

public class FileNotEmptyHandle implements ConstraintValidator<FileNotEmpty, MultipartFile> {
    @Override
    public void initialize(FileNotEmpty constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext constraintValidatorContext) {
        return multipartFile != null && multipartFile.getSize() != 0;
    }
}
