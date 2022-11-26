package com.nobblecrafts.challenge.cft.repository;

import com.nobblecrafts.challenge.cft.entity.PositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PositionJpaRepository extends JpaRepository<PositionEntity, Long> {
}
