package com.example.unscape.validation.handle;

import com.example.unscape.validation.FileType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public class FileTypeHandle implements ConstraintValidator<FileType, MultipartFile> {
  private List<String> contentTypes = new ArrayList<>();

  @Override
  public void initialize(FileType constraintAnnotation) {
    // Implements code here
    this.contentTypes = List.of(constraintAnnotation.contentType());
  }

  @Override
  public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
    if (value == null) return true;
    String contentType = value.getContentType();
    return StringUtils.isEmpty(contentType) || contentTypes.contains(contentType);
  }

}
