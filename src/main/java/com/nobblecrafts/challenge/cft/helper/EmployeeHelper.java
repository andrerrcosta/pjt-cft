package com.nobblecrafts.challenge.cft.helper;

import com.nobblecrafts.challenge.cft.entity.EmployeeEntity;
import com.nobblecrafts.challenge.cft.entity.SellingEntity;
import com.nobblecrafts.challenge.cft.exception.CftDomainException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmployeeHelper {

    @Transactional(propagation = Propagation.MANDATORY)
    public BigDecimal getBenefitBySelling(EmployeeEntity employee, Integer month, Integer year) {
        var output = employee.getPosition().getBenefits()
                .multiply(employee.getSellings().stream()
                        .filter(s -> s.checkMonthAndYear(month, year))
                        .findFirst()
                        .get()
                        .getAmount())
                .divide(new BigDecimal(100))
                .setScale(2, RoundingMode.HALF_EVEN);
        return output;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public BigDecimal getBenefitBySalary(EmployeeEntity employee) {
        var output = employee.getPosition().getBenefits()
                .multiply(employee.getPosition().getSalary())
                .divide(new BigDecimal(100))
                .setScale(2, RoundingMode.HALF_EVEN);
        return output;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public BigDecimal getTotalPaymentBySelling(EmployeeEntity employee, Integer month, Integer year) {
        return employee.getPosition()
                .getSalary()
                .add(employee.getPosition().getBenefits()
                        .multiply(employee.getSellings().stream()
                                .filter(s -> s.checkMonthAndYear(month, year))
                                .findFirst()
                                .get()
                                .getAmount())
                        .divide(new BigDecimal(100)))
                .setScale(2, RoundingMode.HALF_EVEN);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public BigDecimal getTotalPaymentBySalary(EmployeeEntity employee) {
        return employee.getPosition()
                .getSalary()
                .add(employee.getPosition().getBenefits()
                        .multiply(employee.getPosition().getSalary())
                        .divide(new BigDecimal(100)))
                .setScale(2, RoundingMode.HALF_EVEN);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public BigDecimal getSellerAmount(EmployeeEntity employee, Integer month, Integer year) {
        try {
            return employee.getSellings().stream()
                    .filter(s -> s.checkMonthAndYear(month, year))
                    .map(SellingEntity::getAmount)
                    .findFirst()
                    .get()
                    .setScale(2, RoundingMode.HALF_EVEN);
        } catch (NullPointerException n) {
            log.error("The employee is not a seller");
            throw new CftDomainException("The employee is not a seller");
        }
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public BigDecimal getEmployeeBenefit(EmployeeEntity employee, Integer month, Integer year) {
        return employee.getPositionName().equalsIgnoreCase("vendedor") ?
                getBenefitBySelling(employee, month, year) :
                getBenefitBySalary(employee)
                        .setScale(2, RoundingMode.HALF_EVEN);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public BigDecimal getEmployeeTotalPayment(EmployeeEntity employee, Integer month, Integer year) {
        return employee.getPositionName().equalsIgnoreCase("vendedor") ?
                getTotalPaymentBySelling(employee, month, year) :
                getTotalPaymentBySalary(employee).setScale(2, RoundingMode.HALF_EVEN);
    }

}
