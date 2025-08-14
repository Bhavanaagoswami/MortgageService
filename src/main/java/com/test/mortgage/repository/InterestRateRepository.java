package com.test.mortgage.repository;


import com.test.mortgage.model.InterestRateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Repository for mortgage rates.
 */
@Repository
public interface InterestRateRepository extends JpaRepository<InterestRateEntity, Long> {
    Optional<InterestRateEntity> findByMaturityPeriod(Integer maturityPeriod);
}