package com.test.mortgage.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Entity for table mortgage_rate table.
 */
@Entity
@Data
@Table(name ="MORTGAGERATE")
public class InterestRateEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "interestrate")
    @Min(value = 0, message = "Minimum value is zero for Interest Rate.")
    @NotNull(message = "Interest Rate can not be null.")
    private BigDecimal interestRate;
    @Min(value = 0, message = "Minimum value is zero for Maturity Period.")
    @Column(name = "maturityperiod")
    private Integer maturityPeriod;
    @Column(name = "lastupdated")
    private Timestamp lastUpdated;
}
