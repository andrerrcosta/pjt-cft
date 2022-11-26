package com.nobblecrafts.challenge.cft.service.impl;

import com.nobblecrafts.challenge.cft.dto.Employee;
import com.nobblecrafts.challenge.cft.entity.EmployeeEntity;
import com.nobblecrafts.challenge.cft.exception.CftDomainException;
import com.nobblecrafts.challenge.cft.helper.EmployeeHelper;
import com.nobblecrafts.challenge.cft.mapper.DataMapper;
import com.nobblecrafts.challenge.cft.repository.EmployeeJpaRepository;
import com.nobblecrafts.challenge.cft.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Comparator;
import java.util.Set;

/**
 * Este serviço só usa orientação a objetos e streams, ou seja,
 * não utiliza nenhuma lógica através de queries de sql.
 * É ruim porque o custo de processamento é desnecessário.
 */
@Slf4j
@RequiredArgsConstructor
@Service("onlyOopService")
public class OnlyOOPService implements TaskService {

    private final EmployeeJpaRepository employeeJpaRepository;
    private final EmployeeHelper helper;
    private final DataMapper mapper = DataMapper.INSTANCE;

    @Override
    @Transactional(readOnly = true)
    public BigDecimal totalPayment(Set<Long> employees, Integer month, Integer year) {
        return employeeJpaRepository.findAllById(employees)
                .stream()
                .map(e -> helper.getEmployeeTotalPayment(e, month, year))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal totalSalaries(Set<Long> employees, Integer month, Integer year) {
        var output = employeeJpaRepository.findAllById(employees).stream()
                .filter(e -> e.getHiringDate().isBefore(LocalDate.of(year, Month.of(month), 1))
                        || (e.getHiringDate().getMonthValue() <= month && e.getHiringDate().getYear() == year))

                .map(EmployeeEntity::getSalary)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return output;
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal totalBenefits(Set<Long> employees, Integer month, Integer year) {
        return employeeJpaRepository.findAllById(employees).stream()
                .filter(EmployeeEntity::hasBenefits)
                .map(e -> helper.getEmployeeBenefit(e, month, year))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    @Transactional(readOnly = true)
    public Employee higherPayment(Set<Long> employees, Integer month, Integer year) {
        var output = employeeJpaRepository.findAllById(employees).stream()
                .map(e -> e.withTotalSalary(helper.getEmployeeTotalPayment(e, month, year)))
                .max(Comparator.comparing(EmployeeEntity::getTotalSalary))
                .orElseThrow(() -> new CftDomainException("Data of subject is missing"));
        return mapper.toRecord(output);

    }

    @Override
    @Transactional(readOnly = true)
    public Employee higherBenefits(Set<Long> employees, Integer month, Integer year) {
        var output = employeeJpaRepository.findAllById(employees).stream()
                .filter(EmployeeEntity::hasBenefits)
                .map(e -> e.withBenefits(helper.getEmployeeBenefit(e, month, year)))
                .max(Comparator.comparing(EmployeeEntity::getBenefits))
                .orElseThrow(() -> new CftDomainException("Data of subject is missing"));
        return mapper.toRecord(output);
    }

    @Override
    @Transactional(readOnly = true)
    public Employee bestSeller(Set<Long> employees, Integer month, Integer year) {
        var output = employeeJpaRepository.findAllById(employees).stream()
                .filter(e -> e.getPositionName().equalsIgnoreCase("vendedor"))
                .map(e -> e.withSellingsAmount(helper.getSellerAmount(e, month, year)))
                .max(Comparator.comparing(EmployeeEntity::getSellingsAmount))
                .orElseThrow(() -> new CftDomainException("Data of subject is missing"));
        log.info("\n\nSeller: {}\n\n", output);
        return mapper.toRecord(output);
    }
}
