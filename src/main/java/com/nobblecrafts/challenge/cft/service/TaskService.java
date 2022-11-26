package com.nobblecrafts.challenge.cft.service;

import com.nobblecrafts.challenge.cft.dto.Employee;

import java.math.BigDecimal;
import java.util.Set;

public interface TaskService {

    /**
     * Um método que receba uma lista de funcionários, mês e ano e retorne o valor total
     * pago (salário e benefício) a esses funcionários no mês.
     * @param employees
     * @param date
     * @return
     */
    BigDecimal totalPayment(Set<Long> employees, Integer month, Integer year);

    /**
     * Um método que receba uma lista de funcionários, mês e ano e retorne o total pago
     * somente em salários no mês.
     * @param employees
     * @param date
     * @return
     */
    BigDecimal totalSalaries(Set<Long> employees, Integer month, Integer year);

    /**
     * Um método que receba uma lista somente com os funcionários que recebem
     * benefícios, mês e ano e retorne o total pago em benefícios no mês.
     * @param employees
     * @param date
     * @return
     */
    BigDecimal totalBenefits(Set<Long> employees, Integer month, Integer year);


    /**
     * Um método que receba uma lista de funcionários, mês e ano e retorne o que
     * recebeu o valor mais alto no mês.
     * @param employees
     * @param date
     * @return
     */
    Employee higherPayment(Set<Long> employees, Integer month, Integer year);

    /**
     * Um método que receba uma lista somente com os funcionários que recebem
     * benefícios, mês e ano e retorne o nome do funcionário que recebeu o valor mais
     * alto em benefícios no mês.
     * @param employees
     * @param date
     * @return
     */
    Employee higherBenefits(Set<Long> employees, Integer month, Integer year);

    /**
     * Um método que receba uma lista de vendedores, mês e ano e retorne o que mais
     * vendeu no mês.
     * @param employees
     * @param date
     * @return
     */
    Employee bestSeller(Set<Long> employees, Integer month, Integer year);

}
