package com.test.mortgage.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mapstruct.factory.Mappers.getMapper;

import com.test.mortgage.model.InterestRateEntity;
import com.test.mortgage.model.MortgageRate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import java.math.BigDecimal;
import java.sql.Timestamp;


class InterestRateMapperTest {
    private InterestRateEntity interestRateEntity;

    @InjectMocks
    private InterestRateMapper commonMapper = getMapper(InterestRateMapper.class);

    @BeforeEach
    void setup() {
        interestRateEntity = new InterestRateEntity();
        interestRateEntity.setInterestRate(BigDecimal.ONE);
        interestRateEntity.setLastUpdated(Timestamp.valueOf("2025-08-08 11:33:33.000"));
        interestRateEntity.setMaturityPeriod(2);
    }

    @Test
    void toMortgageRate() {
        MortgageRate mortgageRate = commonMapper.toMortgageRate(interestRateEntity);
        assertThat(mortgageRate.getInterestRate()).isEqualTo(BigDecimal.ONE);
        assertThat(mortgageRate.getLastUpdated()).isEqualTo(Timestamp.valueOf("2025-08-08 11:33:33.000"));
        assertThat(mortgageRate.getMaturityPeriod()).isEqualTo(2);
    }

    @Test
    void toMortgageRateWithNullEntity() {
        MortgageRate mortgageRate = commonMapper.toMortgageRate(null);
        assertThat(mortgageRate).isNull();
    }
}