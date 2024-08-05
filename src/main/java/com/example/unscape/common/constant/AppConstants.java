package com.example.unscape.common.constant;

import com.example.unscape.service.dto.response.MessageCode;

public class AppConstants {
    private AppConstants() {
    }

    public static final MessageCode OK = new MessageCode("200", "Success");
    public static final MessageCode CREATED = new MessageCode("201", "CREATED");
    public static final MessageCode BAD_REQUEST = new MessageCode("400", "Bad Request");
    public static final MessageCode SERVICE_ERROR = new MessageCode("500", "Service Error");
    public static final MessageCode NOT_FOUND = new MessageCode("404", "Not Found");
    public static final MessageCode FORBIDDEN = new MessageCode("403", "Forbidden access is denied");
    public static final MessageCode NOT_READABLE_REQUEST = new MessageCode("400", "Bad Request: Unable to read request, format is not correct");

}
