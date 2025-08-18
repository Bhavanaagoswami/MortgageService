package com.test.mortgage.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.test.mortgage.model.InterestRateEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
class InterestRateRepositoryTest {

    @Autowired
    InterestRateRepository interestRateRepository;

    @BeforeEach
    void setUp() {
        InterestRateEntity interestRateEntity = new InterestRateEntity();
        interestRateEntity.setInterestRate(BigDecimal.ONE);
        interestRateEntity.setMaturityPeriod(4);
        interestRateEntity.setLastUpdated(Timestamp.valueOf(LocalDateTime.now()));
        interestRateRepository.save(interestRateEntity);
    }

    @Test
    void findByMaturityPeriod() {
        Optional<InterestRateEntity> interestRateEntity =
                interestRateRepository.findByMaturityPeriod(3);
        assertThat(interestRateEntity.isPresent()).isTrue();
    }
    @Test
    void findAll() {
        List<InterestRateEntity> interestRateEntities =
                interestRateRepository.findAll();
        assertThat(interestRateEntities.size()).isEqualTo(9);
    }
}