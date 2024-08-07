package com.example.unscape.validation.handle;

import com.example.unscape.validation.FileSize;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;


public class FileSizeHandle implements ConstraintValidator<FileSize, MultipartFile> {
    private int maxSize;

    @Override
    public void initialize(FileSize constraintAnnotation) {
        // Implements code here
        this.maxSize = constraintAnnotation.maxSize();
    }

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
        return value == null || value.getSize() <= this.maxSize;
    }

}
