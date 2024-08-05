/*
 * Copyright (C) 2020 Viettel Digital Services. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.example.unscape.controller.errors;

import java.io.IOException;

public class CustomException extends IOException {
    public CustomException(String message) {
        super(message);
    }
}