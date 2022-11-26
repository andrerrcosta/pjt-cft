package com.nobblecrafts.challenge.cft.dto;

import lombok.Builder;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Builder
public record SellerMonthRequest(@NotNull Long id,
                                 @NotNull Date date) {
}
