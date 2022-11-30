package com.nobblecrafts.challenge.cft.service.impl;

import com.nobblecrafts.challenge.cft.dto.Employee;
import com.nobblecrafts.challenge.cft.mapper.DataMapper;
import com.nobblecrafts.challenge.cft.repository.EmployeeJpaRepository;
import com.nobblecrafts.challenge.cft.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Set;


@Slf4j
@Service("sqlService")
@RequiredArgsConstructor
public class SqlService implements TaskService {

    private final EmployeeJpaRepository repository;
    private final DataMapper mapper = DataMapper.INSTANCE;

    @Override
    @Transactional(readOnly = true)
    public BigDecimal totalPayment(Set<Long> employees, Integer month, Integer year) {
        return repository.totalPayment(employees, month, year);
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal totalSalaries(Set<Long> employees, Integer month, Integer year) {
        log.info("\n\nSQL Service: {}\n\n", employees);
        return repository.totalSalaries(employees, month, year);
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal totalBenefits(Set<Long> employees, Integer month, Integer year) {
        return repository.totalBenefits(employees, month, year);
    }

    @Override
    @Transactional(readOnly = true)
    public Employee higherPayment(Set<Long> employees, Integer month, Integer year) {
        var e = repository.higherPayment(employees, month, year);
        log.info("\n\nEmployeeEntity: {}\n\n", e);
        return mapper.toRecord(e);
    }

    @Override
    @Transactional(readOnly = true)
    public Employee higherBenefits(Set<Long> employees, Integer month, Integer year) {
        return mapper.toRecord(repository.higherBenefits(employees, month, year));
    }

    @Override
    @Transactional(readOnly = true)
    public Employee bestSeller(Set<Long> employees, Integer month, Integer year) {
        return mapper.toRecord(repository.bestSeller(employees, month, year));

    }
}
