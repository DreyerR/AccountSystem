package za.ac.nwu.as.translator.impl;

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
            return currencyTypeDtos;
        }
        catch (Exception e) {
            throw new RuntimeException("CurrencyTypeTranslator: Unable to fetch currency types", e);
        }
    }

    @Override
    public CurrencyType saveCurrencyType(CurrencyTypeDto currencyTypeDto) {
        try {
            CurrencyType currencyType = new CurrencyType(currencyTypeDto.getCurrencyTypeName());
            return currencyTypeRepository.save(currencyType);
        }
        catch (Exception e) {
            throw new RuntimeException("CurrencyTypeTranslator: Unable to save currency type", e);
        }
    }

    @Override
    public CurrencyType fetchCurrencyTypeByName(String currencyTypeName) {
        return currencyTypeRepository.findCurrencyTypeByCurrencyTypeName(currencyTypeName);
    }

    @Override
    public void deleteCurrencyTypeById(Integer ctId) {
        currencyTypeRepository.deleteById(ctId);
    }
}
