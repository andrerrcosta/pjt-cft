package com.nobblecrafts.challenge.cft.repository;

import com.nobblecrafts.challenge.cft.entity.SellingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface SellingJpaRepository extends JpaRepository<SellingEntity, Long> {

    @Query(value = "select * from selling s where s.id in (:employees) and month(date) = :month and year(date) = :year", nativeQuery = true)
    Set<SellingEntity> getEmployeeSellingsByDate(@Param("employees") Set<Long> employees,
                                                 @Param("month") Integer month,
                                                 @Param("year") Integer year);

}
