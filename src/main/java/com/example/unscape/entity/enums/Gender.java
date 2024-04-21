package com.example.unscape.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Gender {
    Other("Khác"),
    Male("Nam"),
    Female("Nữ");

    private final String value;
}
