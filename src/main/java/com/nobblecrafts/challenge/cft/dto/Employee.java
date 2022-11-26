package com.nobblecrafts.challenge.cft.dto;

import java.util.Date;

public record Employee(Long id,
                       String name,
                       Date hiringDate) {
}
