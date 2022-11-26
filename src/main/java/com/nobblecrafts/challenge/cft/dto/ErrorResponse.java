package com.nobblecrafts.challenge.cft.dto;

import lombok.Builder;
import lombok.Data;

@Builder
public record ErrorResponse(String code, String message) {
}