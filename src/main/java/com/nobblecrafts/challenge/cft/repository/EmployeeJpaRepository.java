package com.nobblecrafts.challenge.cft.repository;

import com.nobblecrafts.challenge.cft.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Set;

@Repository
public interface EmployeeJpaRepository extends JpaRepository<EmployeeEntity, Long> {

    @Query(nativeQuery = true, value = "select * from employee e " +
            "inner join selling s on s.employee_id = e.id " +
            "where e.position_id = cast(2 as bigint) and e.id in (:sellers) " +
            "and month(s.selling_date) = :month and year(s.selling_date) = :year and ( " +
            "   (month(e.hiring_date) <= :month and year(e.hiring_date) = :year) " +
            "   or (year(e.hiring_date) < :year)" +
            ") " +
            "order by amount desc limit 1")
    EmployeeEntity bestSeller(@Param("sellers") Set<Long> sellers,
                              @Param("month") Integer month,
                              @Param("year") Integer year);

    @Query(nativeQuery = true, value = "select distinct e.*, " +
            "case e.position_id " +
            "when 2 then (s.amount * cp.benefits / 100) " +
            "else (cp.salary * cp.benefits / 100) end as benefits from employee as e " +
            "left join selling as s on e.id = s.employee_id and " +
            "month(s.selling_date) = :month and year(s.selling_date) = :year " +
            "join company_position as cp on cp.id = e.position_id " +
            "where e.id in (:employees) and ( " +
            "   (month(e.hiring_date) <= :month and year(e.hiring_date) = :year) " +
            "   or (year(e.hiring_date) < :year)" +
            ") " +
            "order by benefits desc limit 1")
    EmployeeEntity higherBenefits(@Param("employees") Set<Long> employees,
                                  @Param("month") Integer month,
                                  @Param("year") Integer year);

    @Query(nativeQuery = true, value = "select distinct e.*, " +
            "case e.position_id " +
            "when 2 then ((s.amount * cp.benefits / 100) + cp.salary) " +
            "else ((cp.salary * cp.benefits / 100) + cp.salary) end as totalSalary from employee as e " +
            "left join selling s on s.employee_id = e.id and " +
            "month(s.selling_date) = :month and year(s.selling_date) = :year " +
            "join company_position as cp on cp.id = e.position_id " +
            "where e.id in (:employees) and ( " +
            "   (month(e.hiring_date) <= :month and year(e.hiring_date) = :year) " +
            "   or (year(e.hiring_date) < :year)" +
            ") " +
            "order by totalSalary desc limit 1")
    EmployeeEntity higherPayment(@Param("employees") Set<Long> employees,
                                 @Param("month") Integer month,
                                 @Param("year") Integer year);

    @Query(nativeQuery = true, value = "select cast(sum(benefits) as decimal(18,2)) " +
            "from ( select distinct e.*, " +
            "case e.position_id " +
            "when 2 then (s.amount * cp.benefits / 100) " +
            "else (cp.salary * cp.benefits / 100) end as benefits from employee as e " +
            "left join selling as s on e.id = s.employee_id and " +
            "month(s.selling_date) = :month and year(s.selling_date) = :year " +
            "join company_position as cp on cp.id = e.position_id and cp.benefits > 0 " +
            "where e.id in (:employees) and ( " +
            "   (month(e.hiring_date) <= :month and year(e.hiring_date) = :year) " +
            "   or (year(e.hiring_date) < :year)" +
            "))")
    BigDecimal totalBenefits(@Param("employees") Set<Long> employees,
                             @Param("month") Integer month,
                             @Param("year") Integer year);

    @Query(nativeQuery = true, value = "select cast(sum(cp.salary) as decimal(19,2)) " +
            "from employee e " +
            "inner join company_position cp on cp.id = e.position_id " +
            "where e.id in (:employees) and ( " +
            "   (month(e.hiring_date) <= :month and year(e.hiring_date) = :year) " +
            "   or (year(e.hiring_date) < :year)" +
            ")")
    BigDecimal totalSalaries(@Param("employees") Set<Long> employees,
                             @Param("month") Integer month,
                             @Param("year") Integer year);

    @Query(nativeQuery = true, value = "select sum(payments) " +
            "from (select distinct e.*, " +
            "case e.position_id " +
            "when 2 then (s.amount * cp.benefits / 100) + cp.salary " +
            "else (cp.salary * cp.benefits / 100) + cp.salary " +
            "end as payments from employee as e " +
            "left join selling as s on e.id = s.employee_id and " +
            "month(s.selling_date) = :month and year(s.selling_date) = :year " +
            "join company_position as cp on cp.id = e.position_id " +
            "where e.id in (:employees) and ( " +
            "   (month(e.hiring_date) <= :month and year(e.hiring_date) = :year) " +
            "   or (year(e.hiring_date) < :year)" +
            "))")
    BigDecimal totalPayment(@Param("employees") Set<Long> employees,
                            @Param("month") Integer month,
                            @Param("year") Integer year);


}
