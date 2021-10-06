package za.ac.nwu.as.repo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import za.ac.nwu.as.domain.persistence.CurrencyType;

import java.util.List;

@Repository
public interface CurrencyTypeRepository extends JpaRepository<CurrencyType, Integer> {
    List<CurrencyType> findAllByOrderByCurrencyTypeIdAsc();
//    CurrencyType findCurrencyTypeByCurrencyTypeName(String currencyTypeName);
    @Query(value = "select * from currencytype where ct_name = :ctName", nativeQuery = true)
    CurrencyType findCurrencyTypeByName(@Param("ctName") String ctName);
}
