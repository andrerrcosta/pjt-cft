package com.nobblecrafts.challenge.cft.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@Builder
@With
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "selling")
@Entity
@ToString
public class SellingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference(value = "employee_sellings")
    @JoinColumn(name = "employee_id")
    private EmployeeEntity seller;

    private BigDecimal amount;

    @Builder.Default
    private LocalDate sellingDate = LocalDate.now();

    public boolean checkMonthAndYear(Integer month, Integer year) {
        return sellingDate.getMonthValue() == month && sellingDate.getYear() == year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SellingEntity that = (SellingEntity) o;
        return id.equals(that.id) && seller.equals(that.seller);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, seller);
    }

}
