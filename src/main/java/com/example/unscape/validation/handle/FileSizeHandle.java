package com.example.unscape.validation.handle;

import com.example.unscape.validation.FileSize;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;


public class FileSizeHandle implements ConstraintValidator<FileSize, MultipartFile> {
    //max size 15mb
    private static final int MAX_SIZE_IMAGE_SIZE = 2000000;

    @Override
    public void initialize(FileSize constraintAnnotation) {
        // Implements code here
    }

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
        return value == null || value.getSize() <= MAX_SIZE_IMAGE_SIZE;
    }

}
