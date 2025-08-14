package com.test.mortgage.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
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
    @Min(0)
    private BigDecimal interestRate;
    @Min(0)
    @Column(name = "maturityperiod")
    private Integer maturityPeriod;
    @Column(name = "lastupdated")
    private Timestamp lastUpdated;
}
