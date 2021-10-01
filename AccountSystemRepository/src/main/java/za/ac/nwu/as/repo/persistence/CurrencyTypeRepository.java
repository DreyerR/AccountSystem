package za.ac.nwu.as.repo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.nwu.as.domain.persistence.CurrencyType;

import java.util.List;

@Repository
public interface CurrencyTypeRepository extends JpaRepository<CurrencyType, Integer> {
    public List<CurrencyType> findAllByOrderByCurrencyTypeIdAsc();
    public CurrencyType findCurrencyTypeByCurrencyTypeName(String currencyTypeName);
}
