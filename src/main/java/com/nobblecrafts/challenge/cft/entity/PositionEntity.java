package com.nobblecrafts.challenge.cft.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Builder
@With
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "company_position")
@Entity
@ToString
public class PositionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private BigDecimal salary;

    private BigDecimal bonus;

    private BigDecimal benefits;

    @Builder.Default
    @JsonManagedReference(value = "position_employee")
    @OneToMany(mappedBy = "position", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<EmployeeEntity> employees = new LinkedHashSet<>();

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        PositionEntity that = (PositionEntity) obj;
        return id.equals(that.id);
    }
}
