package za.ac.nwu.as.translator.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import za.ac.nwu.as.domain.dto.CurrencyTypeDto;
import za.ac.nwu.as.domain.persistence.CurrencyType;
import za.ac.nwu.as.repo.persistence.CurrencyTypeRepository;
import za.ac.nwu.as.translator.CurrencyTypeTranslator;

import java.util.ArrayList;
import java.util.List;

@Component
public class CurrencyTypeTranslatorImpl implements CurrencyTypeTranslator {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyTypeTranslatorImpl.class);
    private final CurrencyTypeRepository currencyTypeRepository;

    @Autowired
    public CurrencyTypeTranslatorImpl(CurrencyTypeRepository currencyTypeRepository) {
        this.currencyTypeRepository = currencyTypeRepository;
    }

    @Override
    public List<CurrencyTypeDto> fetchAllCurrencyTypes() {
        try {
            List<CurrencyType> currencyTypes = currencyTypeRepository.findAllByOrderByCurrencyTypeIdAsc();
            List<CurrencyTypeDto> currencyTypeDtos = new ArrayList<>();
            for (CurrencyType currencyType : currencyTypes) {
                currencyTypeDtos.add(new CurrencyTypeDto(currencyType));
            }
            LOGGER.info("Successfully fetched all currency types");
            return currencyTypeDtos;
        }
        catch (Exception e) {
            LOGGER.error("CurrencyTypeTranslator: Unable to fetch currency types {}", e.getMessage());
            throw new RuntimeException("CurrencyTypeTranslator: Unable to fetch currency types");
        }
    }

    @Override
    public CurrencyType saveCurrencyType(CurrencyTypeDto currencyTypeDto) {
        try {
            CurrencyType currencyType = new CurrencyType(currencyTypeDto.getCurrencyTypeName());
            return currencyTypeRepository.save(currencyType);
        }
        catch (Exception e) {
            LOGGER.error("CurrencyTypeTranslator: Unable to save currency type {}", e.getMessage());
            throw new RuntimeException("CurrencyTypeTranslator: Unable to save currency type");
        }
    }

    @Override
    public CurrencyType fetchCurrencyTypeByName(String currencyTypeName) {
        return currencyTypeRepository.findCurrencyTypeByName(currencyTypeName);
    }

    @Override
    public void deleteCurrencyTypeById(Integer ctId) {
        currencyTypeRepository.deleteById(ctId);
        LOGGER.warn("Deleted currency type with ID {}", ctId);
    }
}
