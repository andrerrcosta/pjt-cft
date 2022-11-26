package com.nobblecrafts.challenge.cft.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.math.BigDecimal;
import java.util.Date;

public record Selling(Long id,
                      BigDecimal amount,
                      Date date) {
}
