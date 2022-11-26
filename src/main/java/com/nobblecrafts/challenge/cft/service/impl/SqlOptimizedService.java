package com.nobblecrafts.challenge.cft.service.impl;

import com.nobblecrafts.challenge.cft.dto.Employee;
import com.nobblecrafts.challenge.cft.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;

/**
 * Este serviço deveria utilizar views, functions e procedures
 * mas eu não vou implementar ele agora.
 */
@Slf4j
@RequiredArgsConstructor
@Service("sqlOptimizedService")
public class SqlOptimizedService implements TaskService {
    @Override
    public BigDecimal totalPayment(Set<Long> employees, Integer month, Integer year) {
        return null;
    }

    @Override
    public BigDecimal totalSalaries(Set<Long> employees, Integer month, Integer year) {
        return null;
    }

    @Override
    public BigDecimal totalBenefits(Set<Long> employees, Integer month, Integer year) {
        return null;
    }

    @Override
    public Employee higherPayment(Set<Long> employees, Integer month, Integer year) {
        return null;
    }

    @Override
    public Employee higherBenefits(Set<Long> employees, Integer month, Integer year) {
        return null;
    }

    @Override
    public Employee bestSeller(Set<Long> employees, Integer month, Integer year) {
        return null;
    }
}
