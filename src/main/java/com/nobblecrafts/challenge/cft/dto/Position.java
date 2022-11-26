package com.nobblecrafts.challenge.cft.dto;

import java.math.BigDecimal;
import java.util.Set;

public record Position(Long id,
                       String name,
                       BigDecimal salary,
                       BigDecimal bonus,
                       BigDecimal benefits) {
}
