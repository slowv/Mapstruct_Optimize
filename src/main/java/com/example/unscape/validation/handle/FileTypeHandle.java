package com.example.unscape.validation.handle;

import com.example.unscape.validation.FileType;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.apache.http.entity.ContentType;

import java.util.HashSet;
import java.util.Set;

public class FileTypeHandle implements ConstraintValidator<FileType, MultipartFile> {
  private static final Set<String> VALIDS = new HashSet<>();
  static  {
    VALIDS.add("");
    VALIDS.add(ContentType.IMAGE_JPEG.getMimeType());
    VALIDS.add(ContentType.IMAGE_PNG.getMimeType());
  }

  @Override
  public void initialize(FileType constraintAnnotation) {
    // Implements code here
  }

  @Override
  public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
    if (value == null) return true;
    String contentType = value.getContentType();
    return StringUtils.isEmpty(contentType) || VALIDS.contains(contentType);
  }

}
