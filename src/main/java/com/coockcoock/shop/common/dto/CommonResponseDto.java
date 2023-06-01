package com.coockcoock.shop.common.dto;

import com.coockcoock.shop.common.dto.CommonErrorDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CommonResponseDto<T> {
    private final boolean success;
    private final T data;
    private final CommonErrorDto errorDto;
}
