package com.test.mortgage.mapper;

import com.test.mortgage.model.MortgageRate;
import com.test.mortgage.model.InterestRateEntity;
import org.mapstruct.Mapper;

/**
 * Mapper for interestRateMapper.
 */
@Mapper(componentModel = "spring")
public interface InterestRateMapper {
    MortgageRate toInterestRate(InterestRateEntity interestRateEntity);
}
