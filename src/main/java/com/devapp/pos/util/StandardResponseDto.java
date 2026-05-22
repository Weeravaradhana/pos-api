package com.devapp.pos.util;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class StandardResponseDto {
    private int code;
    private String message;
    private Object data;
}
