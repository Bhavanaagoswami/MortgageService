package com.test.mortgage.service;

import static java.util.Optional.ofNullable;

import com.test.mortgage.mapper.InterestRateMapper;
import com.test.mortgage.model.MortgageRate;
import com.test.mortgage.model.InterestRateEntity;
import com.test.mortgage.repository.InterestRateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Service to get all the mortgage rates.
 */
@Service
@Slf4j
public class InterestRateService {
    private final InterestRateRepository interestRateRepository;
    private final CacheManager cacheManager;
    private final InterestRateMapper interestRateMapper;

    public InterestRateService(InterestRateRepository interestRateRepository,
                               CacheManager cacheManager, InterestRateMapper interestRateMapper) {
        this.interestRateRepository = interestRateRepository;
        this.cacheManager = cacheManager;
        this.interestRateMapper = interestRateMapper;
    }

    /**
     * Method to fetch data from database for specific maturityPeriod.
     *
     * @param maturityPeriod maturityPeriod
     * @return MortgageRate
     */
    public MortgageRate findInterestRateByMaturityPeriod(Integer maturityPeriod) {
        MortgageRate mortgageRate = new MortgageRate();
        Optional<InterestRateEntity> interestRateEntityOptional =
                interestRateRepository.findByMaturityPeriod(maturityPeriod);
        if (interestRateEntityOptional != null && interestRateEntityOptional.isPresent()) {
            InterestRateEntity interestRateEntity = interestRateEntityOptional.get();
            mortgageRate = interestRateMapper.toMortgageRate(interestRateEntity);
        }
        return mortgageRate;
    }

    /**
     * Method to fetch data from database for all maturityPeriod.
     *
     * @return List of MortgageRate
     */
    public List<MortgageRate> findAll() {
        List<InterestRateEntity> interestRateEntityList = interestRateRepository.findAll();
        List<MortgageRate> interestRates = interestRateEntityList
                .stream().map(interestRateMapper::toMortgageRate).toList();
        Objects.requireNonNull(cacheManager.getCache("InterestRateCache"))
                .put("InterestRateList", interestRates);
        log.info("InterestRateList size: {}" , interestRates.size());
        return interestRates;
    }

    @Cacheable(cacheNames = "InterestRateCache")
    public Cache isCached() {
        findAll();
        return ofNullable(cacheManager.getCache("InterestRateCache")).orElseThrow();
    }

}
