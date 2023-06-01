package com.coockcoock.shop.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CommonErrorDto {
    private final String message;
    private final int status;
}
