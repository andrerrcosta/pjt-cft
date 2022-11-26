package com.nobblecrafts.challenge.cft.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Builder
@With
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee")
@Entity
@ToString
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, max = 40)
    @NotNull
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference(value = "position_employee")
    @JoinColumn(name = "position_id")
    private PositionEntity position;

    @Builder.Default
    @JsonManagedReference("employee_sellings")
    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<SellingEntity> sellings = new LinkedHashSet<>();

    @Builder.Default
    private LocalDate hiringDate = LocalDate.now();

    @Transient
    private BigDecimal totalSalary;

    @Transient
    private BigDecimal benefits;

    @Transient
    private BigDecimal sellingsAmount;

    public BigDecimal calculateTotalSalaryAndBenefitsBy(BigDecimal amount) {
        return position.getSalary()
                .add(amount.multiply(position.getBenefits()).divide(new BigDecimal(100)));
    }

    public Boolean hasBenefits() {
        return position.getBenefits() != null && position.getBenefits().compareTo(BigDecimal.ZERO) > 0;
    }

    public String getPositionName() {
        return position.getName();
    }

    public BigDecimal getSalary() {
        return position.getSalary();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeEntity that = (EmployeeEntity) o;
        return id.equals(that.id) && position.equals(that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, position);
    }
}
